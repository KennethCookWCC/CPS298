<%--
TB_AdminNavBar.jsp - include for the ticketbooker administrative navigation bar
 --%>
<!--  % @  page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" % -->

<!DOCTYPE html>

<nav class="navbar navbar-custom navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Ticket Booker</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNavAltMarkup"
			aria-controls="navbarNavAltMarkup" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<!-- <a class="nav-link" href="/TicketBooker/MovieServlet">Movies</a>  -->
				<c:if test="${user.loginOK}">
					<a class="nav-link"	href="/TicketBooker/AdminLoginServlet#showingslist">Showings</a>
				</c:if>
			</div>
			<div class="ms-auto">
				<c:if test="${user.loginOK}">
					Welcome ${user.getName()} &nbsp; 
					<a href="Logout" class="tbNav"><strong>Logout</strong></a>
				</c:if>
			</div>
		</div>
	</div>
</nav>
