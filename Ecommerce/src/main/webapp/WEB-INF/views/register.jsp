<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> <!-- form:form && modelAttribute="product" -->
<!DOCTYPE html>
<html>
<head>
<%@include file="CSS_Common.jsp"%>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<%@include file="navbar.jsp"%>
		</div>
		<div class="row-fluid" style="background-color: coral; padding: 50px">
			<div class="container-fluid">
			
				<c:if test="${userMessage!=''}">
					<div class="text-center pb-4" ><p><b>${userMessage}</b></p></div>
				</c:if>
				
				<form:form action="/Ecommerce/addUser" modelAttribute="user" method="post">
					<div class="form-row">
						<div class="form-group col-md-4">
							<label for="inputCity">First Name</label> <input type="text" name="fname"
								class="form-control" id="inputCity" placeholder="First Name" required>
						</div>
						<div class="form-group col-md-4">
							<label for="inputState">Middle Name</label> <input type="text" name="mname"
								id="inputState" class="form-control" placeholder="Middle Name">
						</div>
						<div class="form-group col-md-4">
							<label for="inputZip">Last Name</label> <input type="text" name="lname"
								class="form-control" id="inputZip" placeholder="Last Name" required>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail4">Email</label> <input type="email" name="userEmail"
								class="form-control" id="inputEmail4" placeholder="Email" required>
						</div>
						<div class="form-group col-md-6">
							<label for="inputPassword4">Password</label> <input name="userPassword"
								type="password" class="form-control" id="inputPassword4"
								placeholder="Password" required>
						</div>
					</div>
					<div class="form-group">
						<label for="inputAddress">Address</label> <input type="text" name="userAddress"
							class="form-control" id="inputAddress" placeholder="Eg. - 1234 Main Street, Kolkata, Pin-700005" required>
					</div>

					<div class="form-group">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="gridCheck" required>
							<label class="form-check-label" for="gridCheck"> Check me
								out </label>
						</div>
					</div>
					<div class="text-center">
					<button type="submit" class="btn btn-primary">Sign in</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>