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
	<h3>Thống kê năm  ${year}</h3>
	<table border="2" class="table center table-info">
	<tr>
	<th rowspan = "4"> Tháng </th>
	<th></th>
	<th colspan = "${so}"> Danh sách sản phẩm </th>
	<th rowspan = "4">Tổng tiền </th>
	<th rowspan = "4"> Thao tác </th>
	</tr>
	
	<tr>
	<th>ID SP</th>
	<c:forEach var="item" items="${sp}">
	<th>${item.id}</th>
	</c:forEach>
	</tr>
	
	<tr>
	<th >Tên SP</th>
	<c:forEach var="item" items="${sp}">
	<th rowspan = "2">${item.name}</th>
	</c:forEach>
	</tr>
	<tr>
	<th rowspan = "13">SL Đã bán</th>
	</tr>
	<c:forEach var="item" items="${cactruong}">
	<tr>
	
	<c:forEach var="ite" items="${item}">
	<c:if test="${Double.valueOf(ite)==0}">
				<td>${ite}</td>
	</c:if>
	<c:if test="${Double.valueOf(ite)>0}">
				<th>${ite}</th>
	</c:if>
	
	</c:forEach>
	<th><a href="/thongke/detail?month=${item.get(0)}&year=${year}&tong=${item.get(26)}">Chi tiết</a></th>
	</tr>
	</c:forEach>
	
	<tr>
	<th colspan = "2" class="table-danger">Tổng </th>
	<c:forEach var="item" items="${tongsl}">
	<c:if test="${Double.valueOf(item)==0}">
				<td>${item}</td>
	</c:if>
	<c:if test="${Double.valueOf(item)>0}">
				<th class="table-danger">${item}</th>
	</c:if>
	</c:forEach>
	<th class="table-dark">${tongtien}</th>
	</tr>
	</table>

	</div>
	<div class="row">
				<div class="col">
					<nav aria-label="Page navigation example">
						<ul class="pagination">
						<li class="page-item"><a class="page-link" href="/thongke?year=<c:choose>
						<c:when test="${year==nammax.get(0)}">${nammax.get(0)}</c:when>
						<c:otherwise>${year+1}</c:otherwise>
						</c:choose>">Previous</a></li>
							
							<c:forEach var="item" items="${cacnam}">
							<li class="page-item"><a class="page-link" href="/thongke?year=${item}">${item}</a></li>
							</c:forEach>
							<li class="page-item"><a class="page-link" href="/thongke?year=<c:choose>
						<c:when test="${year==nammin.get(0)}">${nammin.get(0)}</c:when>
						<c:otherwise>${year-1}</c:otherwise>
						</c:choose>">Next</a></li>
						</ul>
					</nav>
				</div>
			</div>
	</div>
	