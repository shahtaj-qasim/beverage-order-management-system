package de.uniba.dsg.dsam.backend.beans;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.CustomerOrder;

import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;

import java.awt.List;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;

import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import  de.uniba.dsg.dsam.backend.entities.CustomerOrderEntity;

@MessageDriven(
		   activationConfig = {
		      @ActivationConfigProperty( propertyName = "destinationType", 
		                                 propertyValue = "javax.jms.Queue")
		   }
		)
public class OrderMessageDrivenBean implements MessageListener {

	private static final Logger logger = Logger.getLogger(OrderMessageDrivenBean.class.getName());

	@Resource
	private MessageDrivenContext mdctx; 
	
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	EntityManager em;

	 
    public OrderMessageDrivenBean() {}

    public void onMessage(Message message) {
    	
    	  logger.info("inside onmsg");
    	      try {
    	  
    	    	  Object o = ((ObjectMessage) message).getObject();
  			      CustomerOrder order = (CustomerOrder) o;
    	          CustomerOrderEntity orderEntity = new CustomerOrderEntity();

    	          logger.info("entity created");
    	          orderEntity.setIssue_date(order.getIssueDate());
    	          
    	          for(int i=0; i<order.getOrderItems().size(); i++) {
    	        	  
    	        	  String bevName = order.getOrderItems().get(i).getName();
    	        	  logger.info(bevName);
    	       
	                  try {
    
	                	  String queryString = String.format("SELECT b FROM BeverageEntity b where b.name = '"+bevName+"'");

                          logger.info("query= " + queryString);
	                	  TypedQuery<BeverageEntity> query = (TypedQuery<BeverageEntity>) em.createQuery(queryString);  	            
	    	              BeverageEntity bevEntity = query.getSingleResult();
	    	              bevEntity.setQuantity(bevEntity.getQuantity() - order.getOrderItems().get(i).getQuantity()); 
	    	              orderEntity.add(bevEntity);
    	              }
	                  
	                  catch(NoResultException e){
		        	      logger.info("Entity Not found  "+ bevName);
		        	      logger.info(e.getMessage());
	                  }
    	          }
    	          
    	          em.persist(orderEntity);
    	          }  
    	     catch (JMSException ex) {
    	         mdctx.setRollbackOnly();
    	         logger.info("catch onmsg");
    	      }       
    	   } 

 }