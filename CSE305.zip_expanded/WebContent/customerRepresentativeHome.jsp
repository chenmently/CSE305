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
		<title>Customer Representative Home</title>
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
		}
		span{
		    font-size:15px;
		}
		a{
		  text-decoration:none; 
		  color: #0062cc;
		  border-bottom:2px solid #0062cc;
		}
		
		.box-part:hover{
		     box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
		}
		
		.box-part{
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
		<h1>Customer Representative Options:</h1>
		<
		<div class="box">
		
    <div class="container">
     	<div class="row">
			 
			    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			    	<div class="box-part text-center">
                       <i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i>
                        <div class="title">
							<h4>Record A Sale</h4>
						</div>
                        <div class="container">
							<form action="getOpenAuctions">
								<input type="submit" value="Record Sale" class="btn btn-primary"/>
							</form>
						</div>
                     </div>
				</div>	 
				 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			    	<div class="box-part text-center">
                       <i class="fa fa-wrench fa-2x" aria-hidden="true"></i>
                        <div class="title">
							<h4>Manage Customer</h4>
						</div>
                        <div class="container">
							<form action="addCustomer.jsp">
								<input type="submit" value="Add Customer" class="btn btn-primary"/>
							</form>
							<form action="getCustomers" class="pt-1">
								<input type="submit" value="View / Edit / Delete Customer" class="btn btn-primary"/>
							</form>
							<form action="searchCustomer.jsp" class="pt-1">
								<input type="submit" value="Search Customer" class="btn btn-primary"/>
							</form>
							
						</div>
                     </div>
				</div>	 
				 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			    	<div class="box-part text-center">
                       <i class="fa fa-lightbulb-o fa-2x" aria-hidden="true"></i>
                        <div class="title">
							<h4>View Suggestions For Customers</h4>
						</div>
                        <div class="container">
							<form action="getCustomers">
								<input type="submit" value="View Suggestions" class="btn btn-primary"/>
							</form>
						</div>
                     </div>
				</div>	 
				 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			    	<div class="box-part text-center">
                       <i class="fa fa-envelope fa-2x" aria-hidden="true"></i>
                        <div class="title">
							<h4>View Customer Mailing List</h4>
						</div>
                        <div class="container">
							<form action="getCustomerMailingList">
								<input type="submit" value="Customer Mailing List" class="btn btn-primary"/>
							</form>
						</div>
                     </div>
				</div>	 
	
		</div>		
    </div>
</div>
		
		<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.1.3/bootstrap.min.js"></script>
	</body>
</html>