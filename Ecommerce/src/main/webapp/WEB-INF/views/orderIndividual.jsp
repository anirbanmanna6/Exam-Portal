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
<title>Order Details</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<%@include file="navbar.jsp"%>
		</div>
		<div class="row-fluid" style="background-color: coral; padding: 10px">

			<div class="row-fluid update_delete_cart">




				<h2 class="text-center">Cart Data</h2>
				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th scope="col">Image</th>
								<th scope="col">Product Name</th>
								<th scope="col">Product Price</th>
								<th scope="col">Quantity</th>
								<th scope="col">Sub Total</th>

							</tr>
						</thead>
						<tbody>
							<!--  -->
							<c:forEach items="${cartList}" var="cart" varStatus="status">

								<div>
										<tr class="info">
											<td><span>
											<a href="<c:url value="/productDisplayIndividual/${cart.product.productId}" />" class="thumbnail">
											 <img class="img-fluid"
													style="height: 100px; width: 80px;" alt="No Image"
													src="<c:url value="/resources/product_img/${cart.product.imgUrl}" />">
													</a>
												</span>												
											</td>
											<td>${cart.product.productName}</td>
											<td>${cart.product.productPrice}</td>
											<td>${cart.buyQuantity}</td>
											<td>${cart.product.productPrice*cart.buyQuantity}</td>
										
								
								</tr>
								</div>
							</c:forEach>

							<tr class="warning text-center">
								<td colspan="4">Total Purchase Amount :</td>
								<td colspan="1">${grandTotal}</td>
							</tr>

							<tr class="info text-center">
								<td colspan="3"><a class=""
									href="<c:url value="/productDisplay" />">
										<button class="btn-btn-success">Explore Products ${order.orderStatus}</button>
								</a></td>
								
								<c:if test="${ordder.orderStatus=='Confirmed'}">
								<td colspan="2"><a class="" href="<c:url value="#" />">
										<button class="btn-btn-success">Cancel Order</button>
								</a></td>
								</c:if>
								<c:if test="${ordder.orderStatus=='Delivered'}">
								<td colspan="2"><a class="" href="<c:url value="/downloadInvoice/${ordder.orderId}" />">
										<button class="btn-btn-success">Download Invoice</button>
								</a></td>
								</c:if>
							</tr>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
</body>
</html>