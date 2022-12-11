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
<title>My Orders</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<%@include file="navbar.jsp"%>
		</div>
		<div class="row-fluid" style="background-color: coral; padding: 10px">

			<div class="row-fluid update_delete_cart">




				<h2 class="text-center">All previous orders in single page</h2>
				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Order Id</th>
								<th scope="col">Total Amount</th>
								<th scope="col">Order Date</th>
								<th scope="col">Delivery Address</th>
								<th scope="col">Status</th>

							</tr>
						</thead>
						<tbody>
							<!--  -->
							<c:forEach items="${ordderList}" var="ordder" varStatus="status">
	
								<div>
									<tr class="info">
										<th scope="row">*</th>
										<td><a href="<c:url value="/orderIndividual/${ordder.orderId}" />">${ordder.orderId}</a></td>
										<td>${ordder.orderAmount}</td>
										<td>${ordder.orderDateTime}</td>
										<td>${ordder.deliveryAddress}</td>
										<td>${ordder.orderStatus}</td>
									</tr>
								</div>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
</body>
</html>