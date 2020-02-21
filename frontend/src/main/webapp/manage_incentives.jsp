<%@ page import="java.util.*"%>
<%@ page import="de.uniba.dsg.dsam.model.*"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Beverage Store Demo</title>

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
		<h1>Beverage Store</h1>

		<div class="row">
			<div class="col-md-4">
				<h3>Incentive Name</h3>
			</div>
			<div class="col-md-4">
				<h3>Type</h3>
			</div>
			<div class="col-md-4"></div>
		</div>

		<%
			try {
				List<Incentive> incentives = (List<Incentive>) request.getAttribute("incentiveList");

				for (Incentive inc : incentives) {
		%>
		<div class="row">
			<div class="col-md-3">
				<h4><%=inc.getInc_name()%></h4>
			</div>
			<div class="col-md-4">
				<%
					if (inc instanceof PromotionalGift) {
				%>
				<h4>Promotional Gift</h4>
				<%
					} else if (inc instanceof TrialPackage) {
				%>
				<h4>Trial Package</h4>

				<%
					}
				%>

			</div>
			<div class="col-md-4">
				<div class="btn-group" role="group">
					<a
						href="/frontend/beverages/add_beverage/incentives/edit?inc_id=<%=inc.getInc_id()%>"
						class="btn btn-primary">Edit Incentive</a> <a
						id="<%=inc.getInc_id()%>" href="" class="delete btn btn-danger">Delete
						Incentive</a>
				</div>
			</div>
		</div>
		<%
			}
			} catch (Error e) {
			}
		%>

		
		<a href="/frontend/beverages/add_beverage" class="btn btn-default">Back
			to Beverages</a>

	</div>

	<script>
		$(document)
				.ready(
						function() {
							$(".delete")
									.click(
											function() {
												event.preventDefault();

												$
														.ajax({
															url : '/frontend/beverages/add_beverage/incentives?inc_id='
																	+ event.target.id,
															type : 'DELETE',
															success : function(
																	response) {
																location
																		.reload();
															}
														});
											});
						});
	</script>
</body>
</html>

