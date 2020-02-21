<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@ page import="de.uniba.dsg.dsam.model.Beverage" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p> Overall Revenue : </p>

<% 


List<Beverage> beverages = (List<Beverage>)request.getAttribute("beverageList");
double revenue =0;
for (Beverage beverage: beverages) {
	revenue = revenue + beverage.getPrice()*(beverage.getInitQuantity()-beverage.getQuantity());
}
 %>
 <p><%=revenue %></p>

<br><br>

<p> No incentive revenue: </p>

<%
revenue=0;
for (Beverage beverage: beverages) {
	if(beverage.getIncentive()==null)
		revenue = revenue + beverage.getPrice()*(beverage.getInitQuantity()-beverage.getQuantity());
}

%>

 <p><%=revenue %></p>
</body>
</html>