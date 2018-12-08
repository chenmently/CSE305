<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
	This is the Home page for Customer Representative
	This page contains various buttons to navigate the online auction house
	This page contains customer representative specific accessible buttons
-->

<html>
	<head>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
		<title>Customer Home</title>
		<style>
		
		@import url('https://fonts.googleapis.com/css?family=Lobster');
		@import url('https://fonts.googleapis.com/css?family=Montserrat');
		
		h1{
			margin-top: 40px;
			text-align: center;	
		}
		body{
			font-family: 'Montserrat', sans-serif;
		    background: #eee;
		    background-image: url("./background.svg");
		    background-repeat: no-repeat;
		    text-align: center;
		}
		span{
		    font-size:15px;
		}
		a{
		  text-decoration:none; 
		  color: #0062cc;
		  border-bottom:2px solid #0062cc;
		}
		
		.card:hover{
		     box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
		}
		
		.card{
			padding:60px 0px;
		    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
    		transition: 0.3s;
		    background:#FFF;
		    border-radius:15px;
		    padding:60px 10px;
		    margin:10px 0px;
		    height: 90%;
		}
		.text{
		    margin:20px 0px;
		}
		
		.fa{
			
		     color:#4183D7;
		}
		.btn
		{
			border:none;
			margin:5px 
		}
			
		</style>
	</head>
	<body>
		<jsp:include page = "header.html" />
		<h1>Welcome to Quickbid!</h1>
		<div class="container">
			<h2>Customer Options:</h2>
			<%
			String email = (String)session.getAttribute("email");
			String role = (String)session.getAttribute("role");
			
			//redirect to appropriate home page if already logged in
			if(email != null) {
				if(role.equals("manager")) {
					response.sendRedirect("managerHome.jsp");
				}
				else if(role.equals("customerRepresentative")) {
					response.sendRedirect("customerRepresentativeHome.jsp");
				}
			}
			else {
				// redirect to log in if not alreaddy logged in
				response.sendRedirect("index.jsp");
			}
			%>
			
			<div class="row">
				<div class="col">
					<div class="card">
					  <div class="card-body">
					  	<i class="fa  fa-2x fa-eye" aria-hidden="true"></i>
					    <h5 class="card-title">View Bid History</h5>
    					<div class="container">
							<form action="getAuctions">
								<input type="submit" value="Bid History" class="btn btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					  	<i class="fa  fa-2x fa-handshake-o" aria-hidden="true"></i>
					    <h5 class="card-title">History of all auctions participated in</h5>
    					<div class="container">
							<form action="getAuctionHistory">
								<input type="submit" value="All History" class="btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					  	<i class="fa  fa-2x fa-usd" aria-hidden="true"></i>
					    <h5 class="card-title">Items sold by Seller</h5>
    					<div class="container">
							<form action="getSellers">
								<input type="submit" value="Seller Info" class="btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					  	<i class="fa  fa-2x fa-gavel" aria-hidden="true"></i>
					    <h5 class="card-title">View Items in Auctions</h5>
    					<div class="container">
							<form action="searchItems">
								<input type="submit" value="View Auctions" class="btn btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					  	<i class="fa  fa-2x fa-fire" aria-hidden="true"></i>
					    <h5 class="card-title">View Best Seller Items</h5>
    					<div class="container">
							<form action="getBestsellersForCustomer">
								<input type="submit" value="View Best Sellers" class="btn btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				<div class="col">
					<div class="card">
					  <div class="card-body">
					  	<i class="fa fa-2x fa-list-alt" aria-hidden="true"></i>
					    <h5 class="card-title">Personalized Item Suggestion List</h5>
    					<div class="container">
							<form action="personalizedSuggestions">
								<input type="submit" value="View Personalized Suggestions" class="btn btn btn-primary"/>
							</form>
						</div>
					  </div>
					</div>
				</div>
				
		</div>
		
		
		<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.1.3/bootstrap.min.js"></script>
	</body>
</html>