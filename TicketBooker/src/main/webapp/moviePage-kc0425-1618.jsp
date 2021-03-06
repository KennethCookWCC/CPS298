<%! String imgPrefix = "/src/main/webapp/WEB-INF/img/"; %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.List,java.sql.Date"%>
<%-- <jsp:useBean id = "movieList" class = "beans.MovieListBean" scope = "request" />
<jsp:useBean id = "movieBean" class = "beans.MovieBean" scope = "request" />
 --%>
<%
	String hw= "hello world";
/* 	List ml = (List) movieList.getMovies(); */
	List sl = (List) session.getAttribute("sl");

/* 	for(int i=0; i< showings.size(); i++){
		System.out.println(showings.get(i).toString());
	} */
/* 	String msg = (String) session.getAttribute("msg"); */
	
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Now Playing</title>
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
<link href="bootstrap4-glyphicons/css/bootstrap-glyphicons.css" rel="stylesheet">
 -->
<link rel="stylesheet" href="/TicketBooker/css/style.css">
<style>

/*             .navbar {
                margin-bottom: 0;
                border-radius: 0;
                background-color: #FFC300;
                border: none;
            }
 */
.navbar-inverse .navbar-brand {
	color: #000000;
}

.navbar-inverse .navbar-nav>li>a {
	color: #000000;
}

.col-half-offset {
	margin-left: 4.166666667%;
}

.no-gutters {
	margin-right: 0;
	margin-left: 0;
}
</style>
</head>
<body>

	<!--NAVBAR-->
	<nav
		class="navbar navbar-custom navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			&nbsp;
			<a class="navbar-brand" href="#">Ticket Booker</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>


			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link" href="/TicketBooker/MovieServlet">Movies</a> <a
						class="nav-link" href="#prices">Prices</a>
				</div>
				<div class="ms-auto">
					<a href="/TicketBooker/ShowCartServlet">${cart.count()}</a> 
					<img src="/TicketBooker/img/cartIcon1.png" height="25px" width="25px" />
					&nbsp;
				</div>
			</div>
		</div>
	</nav>

	<%-- 			<div>
                <c:forEach items="${showings}" var="showing">
                	<p>found</p>
                    <p>${showing.getShowingId()}</p>
                    <p>${showing.showingId}</p>      
                </c:forEach>
            </div> --%>
	<!--NOW PLAYING, IMAGES, SHOWTIMES-->

	<div class="container text-center">
		<div class="row row-no-gutters" id="movieRow">
			<div id="nowPlaying">
				<br> <br>
				<h1>
					<strong>Now Playing</strong>
				</h1>
				<p>
					<strong>Click on a show time to buy tickets:</strong>
				</p>
				<br>
			</div>
			<div id="dateSelectDiv mx-auto">
				<form action="/TicketBooker/MovieServlet" method="POST">
					<select name="date">
						<c:forEach items="${showingDates}" var="date">
							<c:choose>
								<c:when test="${dateParam == date.toString()}">
									<option value="${date}" selected>${date.toString()}</option>
								</c:when>
								<c:otherwise>
									<option value="${date}">${date.toString()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> <input type="submit" name="submit" value="Go" />
				</form>
			</div>
			<!--MOVIE 1-->

			<c:forEach items="${ml}" var="movie">
				<div class="col-md-3 col-lg-4 col-xl-3">
					<img src="img/${movie.imageLink}" class="img-responsive"
						style="width: 100%" alt="Image">
					<h4>${movie.getTitle()}</h4>
					<div class="card card-body bg-light">
						<c:forEach items="${showings}" var="showing">
							<c:if test="${movie.id == showing.movieId}">
								<a class="card-link"
									href="/TicketBooker/ShowingServlet?showingId=${showing.showingId}"
									role="button"><fmt:formatDate value="${showing.time}"
										pattern="hh:mm a" /></a>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>


	<!--PRICES-->

	<div class="container text-center" id="prices">
		<h4>Prices:</h4>
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card card-body text-center">
					<p style="padding-top: 8px">
						<strong>Matinee (11am-3pm) </strong> $5.00 | <strong>Standard
						</strong> $10.00
					</p>
				</div>
			</div>
			<div class="col-sm-3"></div>

		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="/TicketBooker/js/moviePageScript.js"></script>
</body>
</html>
