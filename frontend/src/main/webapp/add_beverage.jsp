<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="de.uniba.dsg.dsam.model.Incentive"%>




<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Beverage Store</title>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Add Beverage</h1>

		<p>&nbsp;</p>

		<form role="form" action="/frontend/beverages" method="post">
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">Beverage Name</span> <input
						name="name" type="text" class="form-control">
				</div>
				<br>
				<div class="input-group">
					<span class="input-group-addon">Manufacturer</span> <input
						name="manufacturer" type="text" class="form-control">
				</div>
				<br>
				<div class="input-group">
					<span class="input-group-addon">Quantity</span> <input
						name="quantity" type="number" class="form-control">
				</div>
				<br>
				<div class="input-group">
					<span class="input-group-addon">Price</span> <input name="price"
						type="number" class="form-control">
				</div>
				<br>
				<div class="input-group">
					<span class="input-group-addon"> Incentive </span> <select
						name="inc_id" class="form-control">
						<option value="null">
					     none
						</option>						<%
							try {
								List<Incentive> incentives = (List<Incentive>) request.getAttribute("incentiveList");
								for (Incentive incentive : incentives) {
						%>
						<option value="<%=incentive.getInc_id()%>">
							<%=incentive.getInc_name()
							%>
						</option>
						<%
							}
							} catch (Exception e) {
							}
						%>
					</select>
				</div>
				<br>

			</div>
			<a href="/frontend/beverages" class="btn btn-default">Cancel</a>
			<button type="submit" class="btn btn-success">Save</button>


		</form>
		<br>


		<p>
			<a href="/frontend/beverages/add_beverage/incentives/add_incentive"
				class="btn btn-primary">Create new incentive</a>
		</p>
		<p>
			<a href="/frontend/beverages/add_beverage/incentives"
				class="btn btn-primary">Manage incentives</a>
		</p>


	</div>

</body>
</html>
