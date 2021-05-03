<%-- 
    Document   : MyTicketQR - with EL & JSTL & SQL
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



<title>My Ticket</title>
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

	<script src="/TicketBooker/js/qrious.min.js"></script>
	
	<!-- Bootstrap form -->
	<div class="container">

		<div class="formCol col-8 mx-auto">
			<h1 class="text-center myHeader display-4">My Ticket</h1>
			<%
			/*
			 * Show iterate through list of customers tickets with a link or submit action for a specific ticket to show the QR code
			 */
			%>

			<c:if test="false">
			<table>
				<tr>
					<th>Date</th>
					<th>Time</th>
					<th>Movie</th>
					<th>Seat</th>
					<th>Price</th>
					<th>Your Ticket</th>
				</tr>

				<tr>
					<td>${ticket.date }</td>
					<td><fmt:formatDate value="${ticket.time}" pattern="h:mm a" /></td>
					<td>${ticket.title}</td>
					<td>${ticket.seat}</td>
					<td>$${ticket.stringPrice}</td>
				</tr>



			</table>
			</c:if>
			


			<div class="col-md-8 mx-auto">
				<div class="card ticketCard bg-light">
					<div class="text-center">
						<canvas id="qr1"></canvas>
						<script>
							(function() {
								var qr1 = new QRious({
									element : document.getElementById('qr1'),
									value : '${QRvalue}'
								});
							})();
						</script>
					</div>
					<div class="text-center ticketInfo">
						<p>Seat ${ticket.seat}</p>
						<p>${ticket.title}</p>
						<p><fmt:formatDate value="${ticket.getDate()}" pattern="E MM/dd/yyyy" /></p>
						<p><fmt:formatDate value="${ticket.time}" pattern="hh:mm a" /></p>
					</div>
<%-- 					This QR Code is your ticket to the
					<fmt:formatDate value="${ticket.time}" pattern="hh:mm a" />
					showing of <I>${ticket.title }</I> on
					<fmt:formatDate value="${ticket.getDate()}" pattern="E MM/dd/yyyy" />
					<br> Seat ${ticket.seat } --%>

					

					<c:if test="false">
						<form name="custLogin" action="CustLoginServlet" method="post">
							<div class="mb-3">Tickets should go here</div>
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
				</div>
			</div>
		</div>
	</div> <!-- close container  -->
	
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
