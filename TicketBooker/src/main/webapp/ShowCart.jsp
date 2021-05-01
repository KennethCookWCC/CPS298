<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

	<div class="container py-3">
		<div class="pricing-header p-3 pb-md-4 mx-auto text-center">
			<h1 class="display-4 fw-normal">Your Cart</h1>
			<p class="fs-5 text-muted">
				You're almost done! </br> Click checkout to buy your tickets.
			</p>
		</div>

		<!-- CART DIV -->
		<main>
			<div class="row row-cols-1 row-cols-md-2 mb-2 text-center">
				<div class="col">
					<div class="card mb-4 rounded-3 shadow-sm">
						<div class="card-header py-3">
							<h4 class="my-0 fw-normal">Cart</h4>
						</div>
						
						<!--oddly necessary div-->
						<div class="card-body">
							<h2 class="display-6 text-center mb-4"></h2>

							<div class="table-responsive">
								<table class="table text-center">
									<thead>
										<tr>
											<th style="width: 45%;">Movie</th>
											<th style="width: 15%;">Date</th>
											<th style="width: 20%;">Time</th>
											<th style="width: 15%;">Price</th>
											<th style="width: 5%;"></th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${cart.count != 0}">
												
													<c:forEach items="${cart.cart}" var="ticket">
														<tr>
														<th scope="row" class="text-start"><div>
																<h6 class="my-0">${ticket.getTitle() }</h6>
																<small class="text-muted">Row
																	${ticket.getRow()}, Seat ${ticket.getNumber() }</small>
															</div></th>
														<!--DATE-->
														<td><fmt:formatDate value="${ticket.getDate()}"
																pattern="MM/dd" /></td>
														<!--TIME-->
														<td>${ticket.getTime()}</td>
														<!--PRICE-->
														<td>${ticket.getPrice()}</td>
														<!--REMOVE-->
														<td><button type="button"
																class="w-100 btn btn-sm btn-light">
																<svg xmlns="http://www.w3.org/2000/svg" width="16"
																	height="16" fill="currentColor"
																	class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                            						<path
																		d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293 5.354 4.646z" />
                                            					</svg>
																</button>
														</td>
														</tr>
													</c:forEach>
											</c:when>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- Close cart column -->

				<!-- SUBTOTAL DIV -->
				<div class="col">
					<div class="card mb-4 rounded-3 shadow-sm">

						<div class="card-body">

							<div class="table-responsive">
								<table class="table text-center">
									<thead>
										<tr>
											<th scope="row" class="text-start">SUBTOTAL</th>
											<td>$45.00</td>
										</tr>

									</thead>

								</table>
								<div class="text-muted" style="font-size: 12px">TAXES &
									DISCOUNTS WILL BE CALCULATED AT CHECKOUT</div>
								<br>

								<a href="/TicketBooker/CheckoutServlet"><button type="button" class="w-100 btn btn-lg btn-primary">Checkout</button></a>
								<a href="/TicketBooker/MovieServlet"><button type="button" class="w-100 btn btn-lg btn-secondary">Continue Shopping</button></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>

		<footer class="my-5 pt-5 text-muted text-center text-small">
			<p class="mb-1">&copy; 2021 Ticket Booker</p>
			<ul class="list-inline">
				<li class="list-inline-item"><a href="#">Privacy</a></li>
				<li class="list-inline-item"><a href="#">Terms</a></li>
				<li class="list-inline-item"><a href="#">Support</a></li>
			</ul>
		</footer>
	</div>

</body>
</html>