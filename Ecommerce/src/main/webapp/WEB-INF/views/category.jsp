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
<title>Category</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<%@include file="navbar.jsp"%>
		</div>
		<div class="row-fluid" style="background-color: coral; padding: 10px">

			<h2 class="text-center">Add Category</h2>
			<div class="row-fluid add_category">
				<form action="/Ecommerce/addCategory">
					<div class="form-row">
						<div class="form-group col-md-6 offset-md-3">
							<label for="categoryName">Category Name</label> <input
								type="text" name="categoryName" class="form-control"
								id="categoryName" placeholder="enter here">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6 offset-md-3">
							<label for="categoryDesc">Category Description</label> <input
								type="text" name="categoryDesc" class="form-control"
								id="categoryDesc" placeholder="enter here">
						</div>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Add</button>
					</div>
				</form>
			</div>
			<div class="row-fluid update_delete_category">
				<h2 class="text-center">Category Data</h2>
				<div class="table-responsive">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Category Name</th>
							<th scope="col">Category Description</th>
							<th scope="col">Action</th>

						</tr>
					</thead>
					<tbody>
						<!--  -->
						<c:forEach items="${categoryList}" var="category"
							varStatus="status">
							<tr>
								<th scope="row">*</th>
								<td>${category.categoryName}</td>
								<td>${category.categoryDesc}</td>
								<td>
									<div class="btn-group" role="group">
										<div class="text-center mr-2">
											<a href="/Ecommerce/updateCategoryInitiate/${category.categoryId}">
												<button type="submit" class="btn btn-warning">Update</button>
											</a>
										</div>
										<div class="text-center">
										<!-- using POST method for security(unauthorized url change) -->
											<a href="<c:url value="/deleteCategory/${category.categoryId}" />"   >
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