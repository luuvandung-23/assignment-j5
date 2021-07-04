<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
<!-- Required meta tags -->
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<title>Hello, world!</title>
</head>
<main class="container">
	<div class="row">
		<div class="col">
			<c:if test="${not empty message_qlsp}">
				<div class="alert alert-success">${message_qlsp}</div>
			</c:if>
			<c:if test="${not empty error_qltk}">
				<div class="alert alert-danger">${error_qlsp}</div>
			</c:if>
		</div>
	</div>
	<div class="row">
	<div class="col-4 mt-3" >
			<div class="row p-2">
			<img alt="" src="/img/${product.image}" >
</div>
</div>
		<div class="col-6 ml-4">
			<form:form id="login-form" class="form" action="/managerproduct/insert?page=${page}&typesort=${typesort_page}&properties=${properties}"
				modelAttribute="product" enctype="multipart/form-data">
				<div class="form-group">
					<label for="userid">PRODUCT ID:</label>
					<form:input path="id" class="form-control"  />
				</div>
				<div class="form-group">
					<label for="tensp">Product's name:</label>
					<form:input path="name" class="form-control" />
					<form:errors path="name" element="li" delimiter="; "
						cssClass="error" />
				</div>
				<div class="form-group">
				<label for="tensp">Category :</label>
				
					<select id="idDmSelect" name="idDmSelect">
					
					<c:forEach var="item" items="${categorys}">
					<option value="${item.id}" <c:if test="${item.id==categoryID}">selected</c:if> >${item.name}</option>
					</c:forEach>
		</select>
		
				</div>
				<div class="form-group">
					<label for="gia">Price:</label>
					<form:input path="price" class="form-control" />
					<form:errors path="price" element="li" delimiter="; "
						cssClass="error" />
				</div>
				<div class="form-group">
					<label for="anh">Image:</label> <input type="file" name="image" 
						class="form-control">
				</div>
				<div class="form-group">
					<label for="kg">Create date :</label>
					 <form:input path="createDate" class="form-control" />
				</div>
				<div class="form-group">
					<label for="sl">Available:</label> <form:radiobutton path="available"
						value="true" label="Còn hàng" /><form:radiobutton path="available"
						value="false" label="Hết hàng" />
				</div>
				<div class="form-group">
					<button class="btn btn-primary">Create</button>
					<button class="btn btn-warning">Update</button>
					<button class="btn btn-info" formaction="/managerproduct/clear">Reset</button>
				</div>
			</form:form>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<table class="table table-stripe">
				<tr>
					<th><a href="/managerproduct?typesort=${typesort}&properties=id">Product ID</a></th>
					<th>Image</th>
					<th>Category</th>
					<th><a href="/managerproduct?typesort=${typesort}&properties=name">Product's name</a></th>
					<th><a href="/managerproduct?typesort=${typesort}&properties=price">Price</a></th>
					<th><a href="/managerproduct?typesort=${typesort}&properties=createDate">Create date</a></th>
					<th>Available</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach var="item" items="${listproduct.content}">
					<tr>
						<td>${item.id}</td>
						<td><img alt="" width="100px" height="50px"
							src="/img/${item.image}"></td>
						<td>${item.category.name}</td>
						<td>${item.name}</td>
						<td>${item.price}</td>
						<td>${item.createDate}</td>
						<td>${item.available ? 'Còn hàng':'Hết hàng'}</td>
						<td><a
							href="/managerproduct?id=${item.id}&typesort=${typesort_page}&properties=${properties}&page=${page}">Edit</a>
							<c:if test="${item.available==true}">
								<a
									href="/managerproduct?available=false&id=${item.id}&typesort=${typesort_page}&properties=${properties}&page=${page}">Disable</a>
							</c:if> <c:if test="${item.available==false}">
								<a
									href="/managerproduct?available=true&id=${item.id}&typesort=${typesort_page}&properties=${properties}&page=${page}">Active</a>
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<li class="page-item"><a class="page-link"
						href="/managerproduct?page=<c:choose>
						<c:when test="${page==0}">0</c:when>
						<c:otherwise>${page-1}</c:otherwise>
						</c:choose>&typesort=${typesort_page}&properties=${properties}">Previous</a></li>
					<c:forEach var="item" items="${listtrang}">
						<li class="page-item"><a class="page-link"
							href="/managerproduct?page=${item-1}&typesort=${typesort_page}&properties=${properties}">${item}</a></li>
					</c:forEach>
					<li class="page-item"><a class="page-link"
						href="/managerproduct?page=<c:choose>
						<c:when test="${page==listproduct.totalPages-1}">${listproduct.totalPages-1}</c:when>
						<c:otherwise>${page+1}</c:otherwise>
						</c:choose>&typesort=${typesort_page}&properties=${properties}">Next</a></li>
				</ul>
			</nav>
		</div>
	</div>
</main>