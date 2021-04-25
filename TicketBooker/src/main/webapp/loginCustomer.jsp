<%-- 
    Document   : loginCustomer - with EL & JSTL & SQL
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
<!-- These don't seem to work
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script type="text/javascript"
    src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
 -->


<script type="text/javascript">
/* the validation functions don't work. */
/* is bootstrap messing this up? */
/*	function validatecustlogin() {
	
		var cklog = document.forms["custLogin"]["login"].value;
		var ckpwd = document.forms["custLogin"]["password"].value;
		var m = "";
		if (cklog == "") {
			m += "Please enter a login name. ";
		}
		if (ck[ckpwd == "") {
			m += "Please enter a password.";
		}
		
		if( m == "" ) {
			return true;
		} else {
			alert(m);
			return false;
		} 
		
	} */
	</script>



<title>Customer Login</title>
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
<style type="text/css">

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
						class="nav-link" href="#">Prices</a>
				</div>
				<div class="ms-auto">
					<a href="/TicketBooker/ShowCartServlet">${cart.count()}</a> 
					<img src="/TicketBooker/img/cartIcon1.png" 
						height="25px" width="25px" alt="" />
				</div>
			</div>
		</div>
	</nav>
<!--  onsubmit="return validatecustlogin()"	 -->

	<div style="text-align: center">
		<h1>Customer Login</h1>
		<form name="custLogin" action="CustLoginServlet"
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
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
		crossorigin="anonymous" type="text/javascript"></script>
	<script type="text/javascript"
		src="/TicketBooker/js/moviePageScript.js"></script>
</body>

<script type="text/javascript">
/* this does not work. Bootstrap to blame? */
/*
    $(document).ready(function() {
        $("#custLogin").validate({
            rules: {
                login: {
                    required: true,
                    login: true
                },
         
                password: "required",
            },
             
            messages: {
                login: {
                    required: "Please enter login name",
                    login: "Please enter a valid login name address"
                },
                 
                password: "Please enter password"
            }
        });
 
    });
  */  
</script>

</html>
