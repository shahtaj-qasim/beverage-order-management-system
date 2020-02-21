<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="de.uniba.dsg.dsam.model.Incentive"%>
<!DOCTYPE html>
<html>
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
		<h1>Add Incentive</h1>

		<p>&nbsp;</p>

		<form role="form" action="/frontend/beverages/add_beverage/incentives"
			method="post">
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">Incentive Name</span> <input
						name="name" type="text" class="form-control">
				</div>

				<br>
				<div class="input-group">
					<span class="input-group-addon"> Type </span> <select name="type"
						class="form-control">
						<option value="PromotionalGift">Promotional Gift</option>
						<option value="TrialPackage">Trial Package</option>
					</select>
				</div>
				<br>

			</div>
			<a href="/frontend/beverages/add_beverage" class="btn btn-default">Cancel</a>
			<button type="submit" class="btn btn-success">Save</button>

		</form>




	</div>
</body>
</html>