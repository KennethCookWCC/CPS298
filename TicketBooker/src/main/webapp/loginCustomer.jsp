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
<link rel="stylesheet" href="/TicketBooker/css/style.css">

</head>
<body>

<!--NAVBAR-->
<%@include file="TB_CustNavBar.jsp" %>

<!--  onsubmit="return validatecustlogin()"	 -->

<%-- 	<div style="text-align: center">
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
			<input type="submit" value="Login" />
<!-- 			<button type="submit">Login</button> -->
		</form>
	</div> --%>
	
<!-- Bootstrap form -->
<div id="form_container row">
	<div class="formCol col-8 mx-auto">
		<h1 class="display-4 text-center">Customer Login</h1>
		<form name="custLogin" action="CustLoginServlet"
				method="post">
		  <div class="mb-3">
		    <label for="login" class="form-label">Login:</label>
		    <input type="text" class="form-control" name="login">
		  </div>
		  <div class="mb-3">
		    <label for="password" class="form-label">Password:</label>
		    <input type="password" class="form-control" name="password">
		    <div id="passwordMsg" class="form-text text-center">${message}</div>
		  </div>
		  <div class="text-center pt-1">
		  	<button type="submit" class="btn btn-primary">Login</button>
		  </div>
		</form>
	</div>
</div>
<div class="row text-center" id="adminLink">
	<p>Looking for the Admin login page instead? <a href="/TicketBooker/LoginAdminJSP">Click Here</a></p>
</div>
<!-- bootstrap form  -->

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
		crossorigin="anonymous" type="text/javascript"></script>
	
</body>
<!--Footer-->
<%@include file="TB_Footer.jsp" %>

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
