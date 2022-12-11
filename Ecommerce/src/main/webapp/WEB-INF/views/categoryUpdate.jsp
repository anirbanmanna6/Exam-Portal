<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<%@include file="navbar.jsp"%>
		</div>
		<div class="row-fluid" style="background-color: coral; padding: 10px">

			<h2 class="text-center">Update Category</h2>
			
			<div class="col-md-6 offset-md-3">
			<form action="/Ecommerce/updateCategoryProcess" method="post">
			<!-- Other way -->
			<!-- form action="<c:url value="/updateCategoryProcess" />" > -->
			
			<div class="form-group">
				<label for="categoryId">Category Id</label> <input type="text"
					class="form-control" id="categoryId" name="categoryId" value="${category.categoryId}" readonly>
			</div>
			<div class="form-group">
				<label for="categoryName">Category Name</label> <input type="text"
					class="form-control" id="categoryName" name="categoryName" value="${category.categoryName}">
			</div>
			<div class="form-group">
				<label for="categoryDesc">Category Description</label> <input type="text"
					class="form-control" id="categoryDesc" name="categoryDesc" value="${category.categoryDesc}">
			</div>
			<div class="text-center">
			<button type="submit" class="btn btn-warning">Update</button>
			</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>