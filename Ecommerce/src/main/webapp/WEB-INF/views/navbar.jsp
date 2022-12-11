<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="CSS_Common.jsp"%>
<title>Navbar</title>
</head>
<body>
	<nav
		class="navbar navbar-expand-lg justify-content-between navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Fashion Destination</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav"
			style="list-style-type: none;">

			<c:if test="${!sessionScope.loggedIn}">
				<ul class="navbar-nav">
					<li class="nav-item active"><a class="nav-link"
						href="/Ecommerce/login">Home <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/login" />">Login</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/Ecommerce/register">Register</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="#" />">Contact Us</a></li>
					<li class="nav-item"><a class="nav-link" href="#">About</a></li>
				</ul>
			</c:if>

			<c:if test="${sessionScope.loggedIn}">
				<c:if test="${sessionScope.role=='ROLE_ADMIN'}">
					<ul>
						<li class="nav-item" style="display:inline;"><a class="nav-link"
							href="<c:url value="/getAllProduct" />">Manage Product</a></li>
						<li class="nav-item" style="display:inline;"><a class="nav-link"
							href="/Ecommerce/category">Manage Category</a></li>
						<li class="nav-item" style="display:inline;"><a class="nav-link"
							href="<c:url value="/productDisplayAdmin" />">Product Catalog</a></li>
					</ul>
				</c:if>
				<c:if test="${sessionScope.role=='ROLE_USER'}">
					<li class="nav-item active"><a class="nav-link"
						href="/Ecommerce/productDisplay">Home <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Product Category </a>

						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="<c:url value="/productDisplay" />">Men's</a>
							<a class="dropdown-item" href="<c:url value="/productDisplay" />">Women's</a>
							<a class="dropdown-item" href="<c:url value="/productDisplay" />">Kid's</a>
						</div></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="#" />">Contact Us</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/cart" />">CART</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/order" />">My Orders</a></li>
				</c:if>
			</c:if>


			<c:if test="${sessionScope.loggedIn}">
				<!-- common section for ADMIN and USER -->
				<div class="mr-sm-2 ml-auto">
					<font color="white" face="calibri" size="2">Welcome
						${sessionScope.userEmail}</font> <a href="<c:url value="/logout" />"
						class="btn btn-danger">Log Out</a>
				</div>
			</c:if>
		</div>
	</nav>
</body>
</html>