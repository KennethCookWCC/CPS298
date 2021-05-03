<%-- 
    Document   : MyTickets - with EL & JSTL & SQL
    Created on : 4/23/2021
    Author     : kcook
--%>
<%!String imgPrefix = "/src/main/webapp/WEB-INF/img/";%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List"%>
<%-- <jsp:useBean id = "movieList" class = "beans.MovieListBean" scope = "request" />
<jsp:useBean id = "movieBean" class = "beans.MovieBean" scope = "request" />
<jsp:useBean id = "showingBean" class = "beans.ShowingBean" scope = "request" />
 --%>
<%
String hw = "hello world";
/* 	for(int i=0; i< showings.size(); i++){
	System.out.println(showings.get(i).toString());
} */
/* 	String msg = (String) session.getAttribute("msg"); */
%>

<!DOCTYPE html>
<html lang="en">
<head>



<title>Checkout Confirmation</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
         -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<!-- 
<link href="bootstrap4-glyphicons/css/bootstrap-glyphicons.css"
	rel="stylesheet">
 -->
<link rel="stylesheet" href="/TicketBooker/css/style.css">

</head>
<body>

	<!--NAVBAR-->
	<%@include file="TB_CustNavBar.jsp"%>


	<!-- Bootstrap form -->
	<div id="container">
		<header class="text-center myHeader">
			<h1>Your tickets were successfully purchased!</h1>
		</header>
		<div class="row mx-auto justify-content-center">
			<div class="col-6">
				<div class="card">
					<div class="card-header">
						<h4>Purchase Information</h4>
						<p>Purchase Date:&nbsp;<fmt:formatDate value="${purchase.date}" pattern="E MM/dd" /> </p>
						<p>Purchase Time:&nbsp;<fmt:formatDate value="${purchase.time}" pattern="hh:mm a" /> </p>
					</div>
					<div class="card-body">
						<table class="table">
							<tr>
								<td>Subtotal</td>
								<td>$${purchase.getStringSubtotal() }</td>
							</tr>
							<tr>
								<td>Tax</td>
								<td>$${purchase.getStringSalesTax() }</td>
							</tr>
							<tr>
								<td>Total</td>
								<td>$${purchase.getStringTotal() }</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div> <!-- Close Row -->
		<div class="row mx-auto justify-content-center text-center">
			<h1>Purchased Tickets</h1>
			<div class="col-6">
				<table class="table">
					<tr>
						<th class="text-center">Date</th>
						<th class="text-center">Title</th>
						<th class="text-center">Movie</th>
						<th class="text-center">Seat</th>
						<th class="text-center">Price</th>
					</tr>
					<c:forEach items = "${purchCart.cart}" var = "ticket">
						<tr>
							<td><fmt:formatDate value="${ticket.date}" pattern="E MM/dd" /></td>
							<td><fmt:formatDate value="${ticket.time}" pattern="hh:mm a" /></td>
							<td>${ticket.title}</td>
							<td>Row ${ticket.getRow()}, Seat ${ticket.getNumber() }</td>
							<td>$${ticket.stringPrice}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<%-- <div class="formCol col-8 mx-auto">
			<c:if test="false">
			<form name="custLogin" action="CustLoginServlet" method="post">
				<div class="mb-3">
					Tickets should go here
				</div>
				<div class="mb-3">
					<label for="login" class="form-label">Login:</label> <input
						type="text" class="form-control" name="login">
				</div>
				<div class="mb-3">
					<label for="password" class="form-label">Password:</label> <input
						type="password" class="form-control" name="password">
					<div id="passwordMsg" class="form-text text-center">${message}</div>
				</div>
				<div class="text-center pt-1">
					<button type="submit" class="btn btn-primary">Login</button>
				</div>
			</form>
			</c:if>
		</div> --%>
	</div>
	
	<!--Footer-->
	<%@include file="TB_Footer.jsp" %>

	

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
		crossorigin="anonymous" type="text/javascript"></script>
</body>


</html>
