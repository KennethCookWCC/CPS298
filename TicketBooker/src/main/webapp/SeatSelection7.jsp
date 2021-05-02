<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="movieBean" class="beans.MovieBean" scope="request" />
<jsp:useBean id="showingBean" class="beans.ShowingBean" scope="request" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="WEB-INF/tags.tld"%>
<%@ page import="java.util.List"%>

<%-- <% 
	String pattern = "HH:mm";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String  = simpleDateFormat.format(new Date());
	System.out.println(date);
%> --%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Seat Selection</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<link rel="stylesheet" href="/TicketBooker/css/style.css">

<!-- I think we need this before body for the onload reference -->
<script type="text/javascript" src="/TicketBooker/js/seatScriptOnload2.js"></script>

</head>
<body id="body" onload="onLoadBuildSeats()">

	<%@include file="TB_CustNavBar.jsp" %>
	
	

	<div class="container-fluid text-center movieInfo d-block ">
		<h2 id="showing" data-showingId="${showing.showingId }">${showing.getMovie().getTitle()}</h2>
		<h3><%@ taglib prefix="fmt"
				uri="http://java.sun.com/jsp/jstl/fmt"%>
			<fmt:formatDate type="date" value="${showing.date}" />
			&nbsp;
			<fmt:formatDate value="${showing.time}" pattern="hh:mm a" />
		</h3>
	</div>
	<div class="container-fluid ">
		<div class="row contentContainer">
			<div class="col d-none d-lg-block ">
				<div class="row posterInfo">
					<img class="img-responsive mx-auto" alt=""
						src="img/${showing.getMovie().getImageLink()}">
					<div class="posterDetails">
						<h5>${showing.getMovie().getTitle()}</h5>
						<p>
							<fmt:formatDate value="${showing.time}" pattern="hh:mm a" />
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-8 col-lg-6">
				<div class="seat-container ">
					<t:showingSeats seatList="${seatList}" showing="${showing}" />
				</div>
				<!--close seat container  -->
			</div>
			<!-- close col-8  -->
			<div class="col-md-3 col-lg-3 col-xl-2 cartModule card ">
				<p>Selected Seats:</p>
				<ul id="cartList">
					<li>&nbsp;</li>
				</ul>
				<input id="matinee" type="hidden" name="matinee" value="${matinee }"> <!-- KC add matinee flag -->
					
				<form action="ShowCartServlet" id="cartForm" method="post">
					<input type="hidden" name="testAttr" value="setting testAttr">
					<!-- 						<input type="submit" value="submitBtn"> -->
				</form>
			</div>
		</div>
		<!-- close row -->
	</div>
	<!--Footer-->
	<%@include file="TB_Footer.jsp" %>
	

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$("body").tooltip({
				selector : '[data-bs-toggle=tooltip]'
			});
		});
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
		crossorigin="anonymous"></script>
	<!--  need this before the body -->
	<!--  script type="text/javascript" src="/TicketBooker/js/seatScript.js" /script -->
	<!-- from seatScriptOnLoad.js -->
	<script> seatScriptSetListener() </script>
</body>

</html>