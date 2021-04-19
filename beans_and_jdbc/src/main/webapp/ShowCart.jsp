<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String test = (String) session.getAttribute("testAttr");
%>
<jsp:useBean id = "movieList" class = "beans.MovieListBean" scope = "request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>${testAttr}</h1>
<h2>Test: ${cart.test}</h2>
	<c:forEach items="${cart.cart}" var="ticket">
		<p>seatId: ${ticket.getId()}</p>
		<p>showingId: ${ticket.showing_id}</p>
	</c:forEach>
</body>
</html>