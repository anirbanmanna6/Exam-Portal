<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> <!-- form:form && modelAttribute="product" -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="CSS_Common.jsp"%>

<title>Product Catalog</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<%@include file="navbar.jsp"%>
		</div>
		<div class="row-fluid" style="background-color: coral; padding: 10px;">
			<div class="row product_display">
			<c:forEach items="${productList}" var="product">
				<div class = "col-md-3 col-sm-6 col-xs-12 text-center">
				<!-- <div class = "col-sm-4 col-md-3 text-center">  -->
					<a href="<c:url value="/productDisplayIndividualAdmin/${product.productId}" />" class="thumbnail">
					<img class="img-fluid" src="<c:url value="/resources/product_img/${product.imgUrl}" />"/>
					</a>
					
					<p>${product.productName}</p>
					<p>Price : ${product.productPrice}</p>
					<p>In Stock : ${product.productQuantity}</p>
					
				</div>
			
			</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>