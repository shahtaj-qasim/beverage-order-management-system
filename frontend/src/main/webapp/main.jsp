<%@ page import="java.util.*" %>
<%@ page import="de.uniba.dsg.dsam.model.Beverage" %>
<%@ page import="de.uniba.dsg.dsam.model.Incentive" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Beverage Store Demo</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Beverage Store</h1>
		   <hr><ol>
		   <table id="bev" style="width:50%;">
				        <tr>
				            <th><b>Beverage Name</b></th>
				            <th><b>Manufacturer</b></th>
				            <th><b>Quantity</b></th>
				            <th><b>Price</b></th>
				        </tr>
				        
				        
		    <%
            List<Beverage> beverages = (List<Beverage>)request.getAttribute("beverageList");
                for (Beverage beverage: beverages) { %>

                   <tr>
				                <td class="bevName"> <%=beverage.getName()%> </td>
				                <td> <%=beverage.getManufacturer()%> </td>
				                <td> <%=beverage.getQuantity()%> </td>
				                <td> <%=beverage.getPrice()%> </td>
    
				            </tr>
                <%} %>
        </ol><hr>

		<p><a href="/frontend/beverages/add_beverage" class="btn btn-primary">Create new beverage</a></p>
    <p><a href="/frontend/beverages/view_revenue" class="btn btn-primary">View Revenue</a></p>


	</div>
</body>
</html>
