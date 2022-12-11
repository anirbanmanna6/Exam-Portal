<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- form:form && modelAttribute="product" -->
<!DOCTYPE html>
<html>
<head>
<%@include file="CSS_Common.jsp"%>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<body>
	<div class="container">
		<div class="row-fluid">
			<%@include file="navbar.jsp"%>
		</div>
		<div class="row-fluid" style="background-color: coral; padding: 50px">
			<div class="col-md-6 offset-md-3">
				<div class="card p-4">

					<c:if test="${userMessage!=''}">
						<div class="text-center pb-4">
							<p>
								<b>${userMessage}</b>
							</p>
						</div>
					</c:if>
					
					<form action="<c:url value="/loginInitiate"  />" method="post">
						<div class="login_email">

							<label for="inputEmail4">Email</label> <input type="email"
								name="userEmail" class="form-control" id="inputEmail4"
								placeholder="Enter Here" required>

						</div>
						<div class="login_password pt-3">

							<label for="inputPassword4">Password</label> <input
								name="userPassword" type="password" class="form-control"
								id="inputPassword4" placeholder="Enter Here" required>

						</div>
						<div class="text-center pt-3">
							<button type="submit" class="btn btn-primary btn-lg">Login</button>
						</div>


					</form>
					<div class="text-center pt-3">
						<br> Not registered yet? Click Here <a class="nav-link"
							href="/Ecommerce/register">
							<button class="btn btn-success btn-sm" type="submit">Register</button>
						</a>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>