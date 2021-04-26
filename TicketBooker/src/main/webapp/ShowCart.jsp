<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String test = (String) session.getAttribute("testAttr");
%>
<jsp:useBean id="movieList" class="beans.MovieListBean" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Cart</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<link rel="stylesheet" href="/TicketBooker/css/style.css">



</head>
<body>

<%@include file="TB_CustNavBar.jsp" %>

	<h1>Customer Cart</h1>
	<h1>${testAttr}</h1>
	<!-- 
	<h2>Test: ${cart.test}</h2>
	Cart count: ${cart.count}
	-->
	<c:choose>

		<c:when test="${cart.count != 0}">
			<c:forEach items="${cart.cart}" var="ticket">
				<p>showingId:${ticket.showing_id} &nbsp; seatId:${ticket.getId()}</p>
				
			</c:forEach>
		</c:when>
		<c:otherwise>
			<h2>Your cart is empty</h2>
		</c:otherwise>
	</c:choose>

</body>
</html>