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
<title>Payment</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<%@include file="navbar.jsp"%>
		</div>
		<div class="row-fluid" style="background-color: coral; padding: 10px; height:100%">

			<div class="row-fluid update_delete_cart">


				<h2 class="text-center pb-4">Payment page</h2>
				<div class="col-md-4 offset-md-4">
					<form action="<c:url value="/paymentSuccess" />" method="post">
						<div>
							<input type="hidden" name="grandTotal" value="${grandTotal}">
						</div>
						<div class="payment">
							<div class="payment c">
								<label for="inputCity"><b>UPI Id</b></label> 
								<input
									type="text" name="upi" class="form-control" id="inputCity"
									placeholder="Enter UPI id here" required>
								<small id="emailHelp" class="form-text text-muted">Only UPI payment system is available currently.</small>
							</div>

						</div>
						<div class="text-center pt-4">
							<button type="submit" class="btn btn-primary">Pay ${grandTotal}</button>
						</div>
					</form>
				</div>

				<div class="table-responsive pt-5">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Product Name</th>
								<th scope="col">Product Price</th>
								<th scope="col">Quantity</th>
								<th scope="col">Sub Total</th>
							</tr>
						</thead>
						<tbody>
							<!--  -->
							<c:forEach items="${cartList}" var="cart" varStatus="status">
								<tr class="info">
									<th scope="row">*</th>
									<td>${cart.product.productName}</td>
									<td>${cart.product.productPrice}</td>
									<td>${cart.buyQuantity}</td>
									<td>${cart.product.productPrice*cart.buyQuantity}</td>
								</tr>
							</c:forEach>

							<tr class="warning">
								<td colspan="4" class="text-center">Total Purchase Amount :</td>
								<td colspan="2">${grandTotal}</td>
							</tr>

							</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
</body>
</html>