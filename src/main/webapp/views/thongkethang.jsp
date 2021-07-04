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
	<div class="col">
	<h3>Thống kê tháng ${month} năm ${year}</h3>
	<table border="2" class="table center table-info">
	<tr>
	<th > ID Product </th>
	<th> Product Name</th>
	<th > SL Đã bán</th>
	<th >Tổng tiền </th>
	</tr>
	<c:forEach var="item" items="${thongke}">
	<tr>
	<td>${item.product.id}</td>
	<td>${item.product.name}</td>
	<td>${item.quantity}</td>
	<td>${item.amount}</td>
	</tr>
	</c:forEach>
	<tr class="table-danger">
	<th>Tông thu :</th>
	<th colspan ="2"></th>
	<th>${tong}</th>
	</tr>
	</table>

	</div>
	</div>