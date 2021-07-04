<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
	<div class="row mb-4">
	<c:if test="${not empty error}">
	<div class="alert alert-danger">${error}</div>
	</c:if>
	<div class="col">
	<h3>Giỏ hàng</h3>
	<table class="table table-stripe">
	<tr>
	<th>Product ID</th>
	<th>Image</th>
	<th>Product's name</th>
	<th>Price</th>
	<th>Amount</th>
	<th>Total</th>
	<th>&nbsp;</th>
	</tr>
	<c:forEach var="item" items="${cart}">
	<form action="/cart/update/${item.id}" method="post">
	<tr>
		<td>${item.id}</td>
		<td><img alt="" width="100px" height="50px" src="/img/${item.image}"></td>
		<td>${item.name}</td>
		<td>${item.price}</td>
		<td><input type="number" name="amount" value="${item.amount}" min="1" max="100" value=1
						onblur="this.form.submit()" style="width: 50px;"></td>
		<td>${item.price*item.amount}</td>
		<td>
		<a href="/cart/remove/${item.id}">Delete</a>
		</td>
	</tr>
	</form>
	</c:forEach>
	<tr>
	<th>Thành tiền :</th>
	<th>
	${thanhtien}
	</th>
	</tr>
	</table>
	<form method="post">
	<div class="form-group">
		<label for="tensp">Address:</label>
		<input type="text" name="address" id="name" class="form-control" >
	</div>
	<button class="btn btn-danger" formaction="/cart/order">Đặt hàng</button>
	<button class="btn btn-info" formaction="/cart/clear">Hủy</button>
	</form>
	</div>
	</div>