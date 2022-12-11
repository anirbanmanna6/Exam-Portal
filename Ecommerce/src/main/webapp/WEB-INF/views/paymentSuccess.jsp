<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@page import="com.entity.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="CSS_Common.jsp"%>
<meta charset="UTF-8">
<title>Order Status</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<%@include file="navbar.jsp"%>
		</div>
		<div class="row-fluid" style="background-color: coral; padding: 10px">

			<div class="row-fluid update_delete_cart">
				

				<h2 class="text-center pb-4">Check Out Page</h2>
				<div class="col-md-4 offset-md-4">
					<form action="<c:url value="/productDisplay" />">
					<div class="address">
						<span> You order is confirmed </span>
						<p>Order Id is ${ordder.orderId}</p>
					</div>
					<div class="text-center pt-4">
							<button type="submit" class="btn btn-primary">Explore More Products</button>
					</div>
					</form>
				</div>

			</div>
		</div>
	</div>
</body>
</html>