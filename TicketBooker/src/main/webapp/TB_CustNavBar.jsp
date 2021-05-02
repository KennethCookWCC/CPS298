<%--
TB_CustNavBar.jsp - include for the ticketbooker customer navigation bar
 --%>
<!--  % @  page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" % -->

<!DOCTYPE html>

	<nav
		class="navbar navbar-custom navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">TicketBooker</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link" href="/TicketBooker/MovieServlet">Movies</a> 
					<a class="nav-link" href="/TicketBooker/MovieServlet#prices">Prices</a>

					<c:if test="${user.loginOK}">
						<a class="nav-link" href="MyTicketsServlet">My Tickets</a>
					</c:if>
				</div>
				<div class="ms-auto">
					<span class="me-3">${user.getName()}</span>
					<!--  ${user.isLoginOK()} returns true or false -->
					<c:choose>
						<c:when test="${user.loginOK}">
							<a href="Logout" class="pe-3 tbNav"><strong>Logout</strong></a>
						</c:when>
				    	<c:otherwise>
				    	 	<a href="LoginCustomerJSP" class="pe-3 tbNav"><strong>Login</strong></a>
				    	 	
				    	 	<!--  http://localhost:8080/TicketBooker/LoginCustomerJSP -->
				    	</c:otherwise>
				    </c:choose>
					<a href="/TicketBooker/ShowCartServlet" class="tbNav">${cart.count()} 
					<img src="/TicketBooker/img/cartIcon1.png" height="25px" width="25px" />
					</a>
				</div>
			</div>
		</div>
	</nav>
<!-- 	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
		crossorigin="anonymous" type="text/javascript"></script> -->
	