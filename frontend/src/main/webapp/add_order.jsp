<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="de.uniba.dsg.dsam.model.Beverage" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add Order</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</head>


        
<body>

   <script>

    var selectedBevs = [];
    var id = 0;
    function selectBev(name){
    	var number = document.getElementById("quantNumber"+id).value.toString();
    	name = name.concat(number);
        selectedBevs.push(name);	
        console.log(name);
        id = id+1;
    }
    window.addEventListener('load', function () {
    	document.querySelector('form').addEventListener('submit',function(evt){
        	document.getElementById("length").value = selectedBevs.length;
    		for (i = 0; i < selectedBevs.length; i++) {
            	document.getElementById(i.toString()).value = selectedBevs[i];
            }
    		alert("Order Saved!");
    		location.reload();

        })
    })
    
</script>

	<div class="container">
		<h1>Add Order</h1>
		<p>&nbsp;</p>
		
        
		 <hr>
		<label>Select Beverages</label>
					<table id="beverageTable" style="width:50%;">
				        <tr>
				            <th><b>Beverage Name</b></th>
				            <th><b>Manufacturer</b></th>
				            <th><b>Quantity</b></th>
				            <th><b>Price</b></th>
				             <th># of Products</th>  
				        </tr>
				  
				        <% List<Beverage> beverages = (List<Beverage>)request.getAttribute("beverageList");
				             int id = 0;
				             for (Beverage beverage: beverages) { %>
				            <tr>
				                <td class="bevName"> <%=beverage.getName()%> </td>
				                <td> <%=beverage.getManufacturer()%> </td>
				                <td> <%=beverage.getQuantity()%> </td>
				                <td> <%=beverage.getPrice()%> </td>
				                <td> <input type="number" class="quantity" name="numofbev" step="1" value="0" min="0"
				                 max="<%=beverage.getQuantity()%>" id="quantNumber<%=id%>" ></td>
				                <td><button onClick="selectBev('<%=beverage.getName()%>')" >Buy!</button></td>
				                
				            </tr>
				        <% id++;
				        
				             } %>
              </table>
        <hr>
        
        		<form role="form" action="/frontend/neworder" method="post">
		    
		           <input name="length"  id="length" type="hidden">
		         
		    <%
		       for(int i=0; i<beverages.size(); i++){ %>
		    	   
		    	   <input name="<%=i%>" id="<%=i%>" type="hidden">
		    	   
		    <%   }
		    
		    %>
		    
		    
			<a href="/frontend" class="btn btn-default">Cancel</a>
            
			<button type="submit" id="submit" class="btn btn-success">Save</button>
		</form>		
		


	</div>
</body>
</html>