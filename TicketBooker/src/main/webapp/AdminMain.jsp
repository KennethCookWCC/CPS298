<%-- 
    Document   : AdminMain main page - with EL & JSTL & SQL
    Created on : 4/23/2021
    Author     : kcook
--%>
<%!String imgPrefix = "/src/main/webapp/WEB-INF/img/";%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List,java.sql.Date"%>
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
<!-- These don't seem to work
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script type="text/javascript"
    src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
 -->


<title>Administrator Login</title>
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
	<%@include file="TB_AdminNavBar.jsp" %>

<!--  onsubmit="return validatecustlogin()"	 -->

	<div class="container text-center">
		<h1 class="myHeader display-4">TicketBooker Admin Main Menu</h1>
		
		<c:if test="${!user.loginOK}">
		<form name="adminLogin" action="AdminLoginServlet"
			method="post">
			<label for="login">Login:</label> 
			<input name="login" size="30" /> 
			<br> <br> 
			<label for="password">Password:</label> 
			<input type="password" name="password" size="30" /> 
			<br>${message} 
			<br> <br>
			<button type="submit">Login</button>
		</form>
		</c:if>
		<c:if test="${user.loginOK}">
		<div class="row"><h1>Sold Tickets</h1></div>
		<div id="tableRow" class="row"> 
			<table class="table">
				<tr>
					<th>Date</th>
					<th>Time</th>
					<th>Movie</th>
					<th>Screen</th>
					<th>Seat</th>
					<th>Customer</th>
					<th>Ticket ID</th>
					<th>Price</th>
				</tr>
				<c:forEach items = "${soldTickets}" var = "ticket">
					<tr>
						<td>
							<c:choose>
							<c:when test="${ticket.screen == '0'}">&nbsp;</c:when>
							<c:otherwise>${ticket.date }</c:otherwise>
							</c:choose>
						</td>
						<td>${ticket.time}</td>
						<td>${ticket.movieTitle}</td>
						<td>
							<c:choose>
							<c:when test="${ticket.screen == '0'}">&nbsp;</c:when>
							<c:otherwise>${ticket.screen }</c:otherwise>
							</c:choose>
						</td>
						<td>${ticket.seat}</td>
						<td>${ticket.customer}</td>
						<td>${ticket.ticketId}</td>
						<td>$${ticket.stringPrice}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
			<div id="showingslist" class="row"><h1>Scheduled Showings</h1></div>
			<div class="row">
			<div class="col-8 mx-auto">
			<table class="table">
				<tr>
					<th>Date</th>
					<th>Time</th>
					<th>Screen</th>
					<th>Movie</th>
				</tr>
				<c:forEach items = "${showings}" var = "showing">
					<tr>
						<td>${showing.date}</td>
						<td><fmt:formatDate value="${showing.time}" pattern="hh:mm a" /></td>
						<td>${showing.screenId}</td>
						<td>${showing.movie.title}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
			</div>
		</c:if>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
		crossorigin="anonymous" type="text/javascript"></script>
	
</body>
</html>
