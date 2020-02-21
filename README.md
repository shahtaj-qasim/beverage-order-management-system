# Distributed Systems Architecture And Middleware :  Assignment 1

# Projet BeverageStore

**Table of Contents**  
- [Installation](#installation)
    - [PostgreSQL](#postgresql-database)
    - [Build project](#build-project)
    - [Schema](#schema-)
- [Description](#description)
- [Teamwork](#teamwork)
    - [Development steps](#development-steps)


## Project Overview

- `backend/beans` includes the business functionality of the demo application
- `client` includes the JSP frontend to add beverages, create orders, and manage incentives
- `queuefiller` includes a JSP frontend for sending orders to the JMS queue that will be persisted by the MDB of the `ejb` project
- `ear` creates an EAR from all the subprojects for deployment inside Glassfish
- `shared` includes all classes/interfaces that are used by the other projects


## Installation

Minimal setup:

- Execute the global build via `gradlew build`
- Deploy the EAR from `ear/build/libs` inside Glassfish, e.g., copy to `%GLASSFISH_HOME%\glassfish\domains\domain1\autodeploy`
- Open `http://localhost:8080/frontend` to see the initial UI of the frontend project

#### PostgreSQL Database
   1. Setup Postgres Database driver:
   Get the correct JDBC driver JAR for your PostgreSQL version from https://jdbc.postgresql.org/
   Copy `resources/postgresql-*.*.****.jar` inside `[GlassFish directory]/glassfish/domains/[domain dir]/lib/`

   2. Setup of Postgres Database:
   [PostGres directory]/pgAdmin4/bin/pgAdmin4.exe (default password: admin)
   Add new login role (you have to use a password as GlassFish JDBC Resources will require one) [Name: tester, Password: tester, Privileges: can_login: true]
   Add new database [Name: BeverageStore, Owner: tester]

#### Build project

1. Check settings in `gradle.properties`, especially the path to Glassfish.  
Default path : **c:/glassfish5/glassfish**

2. In the project's root folder:
Run target `gradlew startGlassfish`
Run target `gradlew initServer`
Run target `gradlew build`
Run target `gradlew deploy`

### Setup JMS queue in GlassFish
        - Setup connection factory
              - Resources -> JMS Resources -> Connection Factories
              - Pool Name: BeverageStoreCF
              - Resource Type: javax.jms.QueueConnectionFactory
              - Configure XATransaction Support for this factory
        - Setup destination  
              - Resources -> JMS Resources -> Destination Resources
              - JNDI Name: BeverageStoreQueue
              - Physical Destination Name: PhysicalQueue
              - Resource Type: javax.jms.Queue

### Schema :

![schema](resources/schema.jpeg)

### Play around
- Go to http://localhost:8080/frontend for the main page. You will see 'Users' and 'Salesman' option. Click on Users if you want to place an order, Click on Salesman if you want to create beverages, create incentives, manage incentives.
- Go to http://localhost:8080/frontend/neworder for sending new orders to the JMS queue and persisting them into the DB
- Start the application client inside your Browser http://localhost:8080/frontend/beverages to create new beverages
- Go to http://localhost:8080/frontend/beverages/add_beverage and from there you can create new incentives and manage incentives


## Description
On the first screen of our application we can choose between user and salesman :
![choice](resources/home.PNG)

if we choose **user** , we go on the order page :
![order](resources/add_order.PNG)
and we can increment the number of each beverage that we want and then save the order:
![order2](resources/add_order2.PNG)

If we choose **salesman**, we go on the beverages management pages :
We see the existing beverages.

![beverages](resources/beverages.PNG)

If we click on create new beverage:  
We can create a new beverage and in incentive we can see the existing incentive that we can choose.
![addbeverage](resources/add_beverage.png)
We can create new incentive:
![incentive](resources/add_incentive.PNG)
On cancel, we go back to the **add beverage** page.

We can manage the incentives :
![manage](resources/manage_incentive.PNG)

We can edit an incentive :
![edit](resources/edit_incentive.PNG)

Or delete one :
![delete](resources/delete_incentive.PNG)


Important Notes:
1. It is required to build and deploy the project for it to work
2. Go to create beverage to see all the options (Create new incentive, manage incentives)
3. It is not allowed to edit the incentive type, it remains what it has been set the first time, you can only edit the name
4. Click on user to add an order and click on Salesman to create beverages, create incentives, manage incentives and check the revenue
5. In orders, click on 'Buy' which confirms that you buy that beverage. After selecting your beverages, you click save to confirm the entire order.
6. Please make sure, you don't have two beverages of the same name, we didn't do any validation for that
7. Please add data for checking the functionalities
8. Assign incentive is not working and so, we could not generate revenue for Trial Package and Promotional Gift

Reasons behind using Stateless and/or Stateful?
We used Stateless for BeverageManagementBean, IncentiveManagementBean and OrderMessageBean because we did not have to preserve any state on the 
client side. We don't have any specific client so we don't have instance state for a specific client. Every operation is a one-time operation, we 
don't save the state of any operation and use it later for something.



## Teamwork

#### Development steps

* create a branch for a task with the name of the task   
  example: **task1**
* code the task on this branch
* build the project
* deploy the project
* if there is no problem :   
on branch **task1**:
```
git status
git add .
git commit -m "message" // exemple : "improved readme"
git push
```
* Create a merge request in GitLab.
* Verify the change comparing to master.
* If all is correct and there is no conflict : **Merge**

* Then on branch master :
```
git checkout master
git pull --rebase
```
If there is conflict in the merge request :
 * resolve carefully the conflict in GitLab, or :
 * On branch **task1**:
```
git checkout task1
git rebase master
```
resolve the conflicts with Atom or GitHub or something like that.
