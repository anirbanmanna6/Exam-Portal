<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- form:form && modelAttribute="product" -->
<!DOCTYPE html>
<html>
<head>
<%@include file="CSS_Common.jsp"%>
<meta charset="UTF-8">
<title>Individual Product</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<%@include file="navbar.jsp"%>
		</div>
		<div class="row-fluid" style="background-color: coral; padding: 10px">

			<!-- <form action="<c:url value="/addToCart/${product.productId}"/>"> -->
			
				<div class="col-md-6 offset-md-3">
					<p>
						<a
							href="<c:url value="/productDisplayIndividualAdmin/${product.productId}" />"
							class="thumbnail"> <img class="img-fluid"
							src="<c:url value="/resources/product_img/${product.imgUrl}" />" />
						</a>
					</p>
					<p>
						<b>${product.productName}</b>
					</p>
					<p>${product.productDesc}</p>
					<p>Price : ${product.productPrice}</p>
					<p>In stock : ${product.productQuantity}</p>

					<!-- 'Add to Cart' & 'Buy' button for 'ROLE_USER'-->
					<c:if test="${sessionScope.role=='ROLE_USER'}">
						<div class="text-center">
							<div class="btn-group">
								<div class="btn_1 pr-4">
									<!-- using POST method for security(unauthorized url change) -->
									<a
										href="<c:url value="/addToCart/${product.productId}" />">
										<button type="submit" class="btn btn-danger">Add to
											Cart</button>
									</a>
								</div>
								<div class="btn_2">
									<!-- using POST method for security(unauthorized url change) -->
									<a href="<c:url value="/addToCart/${product.productId}" />">
										<button type="submit" class="btn btn-danger">Buy</button>
									</a>
								</div>
							</div>
						</div>
					</c:if>

					<!-- 'Update' & 'Delete' button for 'ROLE_ADMIN'-->
					<c:if test="${sessionScope.role=='ROLE_ADMIN'}">
						<div class="text-center">
							<div class="btn-group" role="group">
								<div class="text-center mr-2">
									<a href="/Ecommerce/updateProductInitiate/${product.productId}">
										<button type="submit" class="btn btn-warning">Update</button>
									</a>
								</div>
								<div class="text-center">
									<!-- using POST method for security(unauthorized url change) -->
									<a href="<c:url value="/deleteProductIndividual/${product.productId}" />">
										<button type="submit" class="btn btn-danger">Delete</button>
									</a>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			<!-- </form>  -->
		</div>
	</div>
</body>
</html>