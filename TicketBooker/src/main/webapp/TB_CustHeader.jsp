<%--
TB_CustNavBar.jsp - include for the ticketbooker customer navigation bar
 --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<link rel="stylesheet" href="/TicketBooker/css/style.css">





	<nav
		class="navbar navbar-custom navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			&nbsp; <a class="navbar-brand" href="#">TicketBooker</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link" href="/TicketBooker/MovieServlet">Movies</a> <a
						class="nav-link" href="#">Prices</a>
				</div>
				<div class="ms-auto">
				${user.getName()}
					<!--  ${user.isLoginOK()} returns true or false -->
					<c:choose>
						<c:when test="${user.loginOK}">
							<a href="Logout">Logout</a>
						</c:when>
				    	<c:otherwise>
				    	 	<a href="LoginCustomerJSP">Login</a>
				    	 	
				    	 	<!--  http://localhost:8080/TicketBooker/LoginCustomerJSP -->
				    	</c:otherwise>
				    </c:choose>
				    &nbsp;
					<a href="/TicketBooker/ShowCartServlet">${cart.count()} 
					<img src="/TicketBooker/img/cartIcon1.png" height="25px" width="25px" />
					</a> &nbsp;
				</div>
			</div>
		</div>
	</nav>
	