<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> <!-- form:form && modelAttribute="product" -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<title>Product Update</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<%@include file="navbar.jsp"%>
		</div>
		<div class="row-fluid" style="background-color: coral; padding: 10px">

			<h2 class="text-center">Update Product</h2>

			<div class="col-md-6 offset-md-3">
				<form:form action="/Ecommerce/updateProductProcess" modelAttribute="product" method="post" enctype="multipart/form-data">
					<!-- Other way -->
					<!-- form action="<c:url value="/updateProductProcess" />" > -->

					<div class="form-row">
						<label for="productId">Product Id</label> <input type="text"
							class="form-control" id="productId" name="productId"
							value="${product.productId}" readonly>
					</div>
					<div class="form-row">
						<label for="productName">Product Name</label> <input type="text"
							class="form-control" id="productName" name="productName"
							value="${product.productName}">
					</div>
					<div class="form-row">
						<label for="productDesc">Product Description</label> <input
							type="text" class="form-control" id="productDesc"
							name="productDesc" value="${product.productDesc}">
					</div>
					
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="productPrice">Product Price</label> <input
								type="number" name="productPrice" class="form-control" step="0.01" 
								id="productPrice" placeholder="Enter here" value="${product.productPrice}">
						</div>
						<div class="form-group col-md-6">
							<label for="productQuantity">Stock Quantity</label> <input
								type="number" name="productQuantity" class="form-control"
								id="productQuantity" placeholder="Enter here" value="${product.productQuantity}">
						</div>
					</div>
					
					<div class="form-row">
						<div class="form-group" style="width:100%;">
						<label class="sr-only" for="inlineFormCustomSelect">Preference</label>
						<select class="custom-select form-group"  name="catName">
							<option selected>${product.category.categoryName}</option>
							<c:forEach items="${categoryList}" var="category"
							varStatus="status">
							
							<option>${category.categoryName}</option>
							</c:forEach>
						</select>
						</div>
					</div>
					<div class="form-row">
					<!-- to make the image in center - mx-auto d-block -->
						<span> <img class ="mx-auto d-block img-fluid" style="justify-content: center; display: flex; height: 180px; width: 150px; margin-top:10px; margin-bottom:20px;"
							alt="No Image" 
							src="<c:url value="/resources/product_img/${product.imgUrl}" />">

						</span>
					</div>
					<div class="form-row">
						<div class="custom-file">
							<label class="custom-file-label" for="validatedCustomFile">Change
								Image</label> <input type="file" class="form-control-file"
								placeholder="Work" id="validatedCustomFile" name="imgFile">
						</div>
					</div>
					<div class="text-center" style="margin-top:10px;">
						<button type="submit" class="btn btn-warning">Update</button>
					</div>

				</form:form>
			</div>
		</div>
	</div>
</body>
</html>