<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!--
	This is the login page
	This page contains a text field for username and another for password
	This page redirects to the Home page
-->

<html>
	
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
		<title>Login | Online Auction House</title>
		<style>
		@import url('https://fonts.googleapis.com/css?family=Lobster');
		body{
			color:white;
			background: #fe8c00;  /* fallback for old browsers */
			background: -webkit-linear-gradient(to right, #f83600, #fe8c00);  /* Chrome 10-25, Safari 5.1-6 */
			background: linear-gradient(to right, #f83600, #fe8c00); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
			background-size: cover;
		}
		h1{
			font-family: 'Lobster', cursive;
			text-align: center;
			margin-bottom: 50px;
		}
		h2{
			font-family: 'Lobster', cursive;
			margin-top : 30px;
			text-align: center;
			margin-bottom : 30px;
		}
		.jumbotron
		 {
		 	background: rgba(204, 204, 204, 0.2);
			margin-top: 50px;
			margin-left: 100px;
			margin-right: 100px;
		}
		.btn 
		{
			display:block; 
			margin : 0 auto;
		}
		.login
		{
			margin-left:180px;
			margin-right:180px;
		}
		</style>
	</head>
	<body>
	
		
		<div class="jumbotron">
			<div class="container">
			<h1>Welcome to QuickBid</h1>
				
				<%
					String email = (String)session.getAttribute("email");
					String role = (String)session.getAttribute("role");
					
					//redirect to home page if already logged in
					if(email != null) {
						if(role.equals("manager")) {
							response.sendRedirect("managerHome.jsp");
						}
						else if(role.equals("customerRepresentative")) {
							response.sendRedirect("customerRepresentativeHome.jsp");
						}
						else {
							response.sendRedirect("home.jsp");	
						}
					}
					
					String status = request.getParameter("status");
					if(status != null) {
						if(status.equals("false")) {
							out.print("Incorrect Login credentials!");
						}
						else {
							out.print("Some error occurred! Please try again.");
						}
					}
				%>
				<form class = "login" action="login">
					<div class="form-group">
						<input type="email" class="form-control" name="username" placeholder="Username" required>
					</div>
					<div class="form-group">
		            	<input type="password" class="form-control" name="password" placeholder="Password" required>
		        	</div>
					<input type="submit" value="Login" class="btn" style = "background-color:white;"/>
				</form>
			</div>
		</div>
		
		<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.1.3/bootstrap.min.js"></script>
	</body>
</html>