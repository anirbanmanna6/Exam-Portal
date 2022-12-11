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
<title>Products</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<%@include file="navbar.jsp"%>
		</div>
		<div class="row-fluid" style="background-color: coral; padding: 10px">

			<h2 class="text-center">Add Product</h2>
			<div class="row-fluid add_product">
				<form:form action="/Ecommerce/addProduct" modelAttribute="product" method="post" enctype="multipart/form-data">
					<div class="form-row">
						<div class="form-group col-md-6 offset-md-3">
							<label for="productName">Product Name</label> <input type="text"
								name="productName" class="form-control" id="productName"
								placeholder="Enter here">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6 offset-md-3">
							<label for="productDesc">Product Description</label> <input
								type="text" name="productDesc" class="form-control"
								id="productDesc" placeholder="Enter here">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-3 offset-md-3">
							<label for="productPrice">Product Price</label> <input
								type="number" name="productPrice" class="form-control" step="0.01" 
								id="productPrice" placeholder="Enter here">
						</div>
						<div class="form-group col-md-3">
							<label for="productQuantity">Stock Quantity</label> <input
								type="number" name="productQuantity" class="form-control"
								id="productQuantity" placeholder="Enter here">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-3 offset-md-3">
						<label class="sr-only" for="inlineFormCustomSelect">Preference</label>
						<select class="custom-select form-group col-md-4 offset-md-4"  name="catName">
							<option selected>Category...</option>
							<c:forEach items="${categoryList}" var="category"
							varStatus="status">
							
							<option>${category.categoryName}</option>
							</c:forEach>
						</select>
						</div>
						<div class="form-group col-md-3">
						<div class="custom-file"><label class="custom-file-label" for="validatedCustomFile">Product
									Image</label> <input type="file" class="form-control-file"
									placeholder="Work" id="validatedCustomFile" name="imgFile"></div>
						</div>
					</div>
					
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Add</button>
					</div>
				</form:form>
			</div>
			<div class="row-fluid update_delete_product">
				<h2 class="text-center">Product Data</h2>
				<div class="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">Image</th>
							<th scope="col">Product Name</th>
							<th scope="col">Description</th>
							<th scope="col">Price</th>
							<th scope="col">Stock</th>
							<th scope="col">Category Id</th>
							<th scope="col">Supplier Id</th>
							<th scope="col">Action</th>

						</tr>
					</thead>
					<tbody>
						<!--  -->
						<c:forEach items="${productList}" var="product"
							varStatus="status">
							<tr>
								<th scope="row">								
								<span>
								<a href="<c:url value="/productDisplayIndividualAdmin/${product.productId}" />" class="thumbnail">
								 <img class="img-fluid"
								 style="height: 80px; width: 70px;" alt="No Image"
									src="<c:url value="/resources/product_img/${product.imgUrl}" />">
								</a>
								</span>
								
								</th>
								<td>${product.productName}</td>
								<td>${product.productDesc}</td>
								<td>${product.productPrice}</td>
								<td>${product.productQuantity}</td>
								<td>${product.category.categoryId}</td>
								<td>${product.supplier.supplierId}</td>
								<td>
									<div class="btn-group" role="group">
										<div class="text-center mr-2">
											<a
												href="/Ecommerce/updateProductInitiate/${product.productId}">
												<button type="submit" class="btn btn-warning">Update</button>
											</a>
										</div>
										<div class="text-center">
											<!-- using POST method for security(unauthorized url change) -->
											<a
												href="<c:url value="/deleteProduct/${product.productId}" />">
												<button type="submit" class="btn btn-danger">Delete</button>
											</a>
										</div>
									</div>
								</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>