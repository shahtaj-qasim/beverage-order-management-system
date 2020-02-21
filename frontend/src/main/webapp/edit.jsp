<%@ page import="de.uniba.dsg.dsam.model.*"%>

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
		<h1>Edit Incentive</h1>

		<p>&nbsp;</p>

		<%
			Incentive inc = (Incentive) request.getAttribute("inc");
		%>
		<form role="form"
			action="/frontend/beverages/add_beverage/incentives/edit"
			method="post">
			<input type="hidden" name="inc_id" value="<%=inc.getInc_id()%>">

			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">Incentive Name</span> <input
						name="inc_name" type="text" class="form-control"
						value="<%=inc.getInc_name()%>">
				</div>
			</div>
			<div class="input-group">
				<span class="input-group-addon"> Type </span>
				<%
					if (inc instanceof PromotionalGift) {
				%>
				<input name="type" disabled type="text" class="form-control"
					value="Promotional Gift">
				<%
					} else if (inc instanceof TrialPackage) {
				%>
				<input name="type" disabled type="text" class="form-control"
					value="Trial Package">
				<%
					}
				%>


			</div>
			<br> <a href="/frontend/beverages/add_beverage/incentives"
				class="btn btn-default">Cancel</a>
			<button type="submit" class="btn btn-success">Save</button>
		</form>

	</div>
</body>
</html>