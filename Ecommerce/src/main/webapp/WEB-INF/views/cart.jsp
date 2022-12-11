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
<title>Cart</title>
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
								<th scope="col">#</th>
								<th scope="col">Product Name</th>
								<th scope="col">Product Price</th>
								<th scope="col">Quantity</th>
								<th scope="col">Sub Total</th>
								<th scope="col">Action</th>

							</tr>
						</thead>
						<tbody>
							<!--  -->
							<c:forEach items="${cartList}" var="cart" varStatus="status">
	
								<div>
								<form action="<c:url value="/updateCartQuantity/${cart.cartId}" />" method="post">
									<tr class="info">
										<th scope="row">*</th>
										<td>${cart.product.productName}</td>
										<td>${cart.product.productPrice}</td>
										<td><input type="number" value="${cart.buyQuantity}"
											name="newQuantity"></td>
										<td>${cart.product.productPrice*cart.buyQuantity}</td>
										<td>
											<div class="btn-group" role="group">
												<div class="text-center mr-2">
													
													<div>
													<button name="update" type="submit" class="btn btn-warning">Update</button>
													</div>
													
												</div>
												</form>
												<div class="text-center">
													<!-- using POST method for security(unauthorized url change) -->
													<form action="<c:url value="/deleteCartItem/${cart.cartId}" />" method="post">
														<div>
														<button name ="delete" type="submit" class="btn btn-danger">Delete</button>
														</div>
													</form>
												</div>
											</div>
										</td>
									</tr>
								</div>
							</c:forEach>

							<tr class="warning text-center">
								<td colspan="4">Total Purchase Amount :</td>
								<td colspan="2">${grandTotal}</td>
							</tr>

							<tr class="info text-center">
								<td colspan="3"><a class=""
									href="<c:url value="/productDisplay" />">
										<button class="btn-btn-success">Continue Shopping</button>
								</a></td>
								<td colspan="3">
								<a class=""
									href="<c:url value="/checkOut" />">
										<button class="btn-btn-success">CheckOut</button>
								</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
</body>
</html>