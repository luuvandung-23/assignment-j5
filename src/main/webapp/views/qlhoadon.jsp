<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<section class="row">
	<div class="col-4 mt-3 mr-3">
		<div class="row mb-4">
			<div class="col ">
				<h3>Danh sách hóa đơn</h3>
				<table class="table table-stripe con">
					<tr>
						<th><a href="/billManagement?typesort=${typeSort}">Order
								ID</a></th>
						<th>Date Created</th>
						<th>Address</th>
						<th >Status</th>
						<th></th>
					</tr>
					<c:forEach var="item" items="${dshd.content}">
						<tr class="text-center">
							<td>${item.id}</td>
							<td>${item.createDate}</td>
							<td>${item.address}</td>
							<td class="table-danger"><c:choose>
						<c:when test="${item.status==0}">Chờ xác nhận</c:when>
						<c:when test="${item.status==1}">Đã xác nhận</c:when>
						<c:when test="${item.status==2}">Đang vận chuyển</c:when>
						<c:when test="${item.status==3}">Hoàn thành</c:when>
						<c:otherwise>Thất bại</c:otherwise>
						</c:choose></td>
							<td><a class="btn btn-primary"
								href="/billManagement?trang=${page}&typesort=${typesort_page}&id=${item.id}">view</a>
							<c:if test="${item.status==0}">
									<a class="btn btn-danger"
										href="/billManagement?trang=${page}&typesort=${typesort_page}&id=${item.id}&status=1">Xác nhận</a>
								</c:if>
								<c:if test="${item.status==1}">
									<a class="btn btn-danger"
										href="/billManagement?trang=${page}&typesort=${typesort_page}&id=${item.id}&status=2">Đang vận chuyển</a>
								</c:if>
								<c:if test="${item.status==2}">
									<a class="btn btn-success"
										href="/billManagement?trang=${page}&typesort=${typesort_page}&id=${item.id}&status=3">Hoàn thành</a>
										<a class="btn btn-danger"
										href="/billManagement?trang=${page}&typesort=${typesort_page}&id=${item.id}&status=-1">Thất bại</a>
								</c:if>
								</td>
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
							href="/billManagement?trang=<c:choose>
						<c:when test="${page==0}">0</c:when>
						<c:otherwise>${page-1}</c:otherwise>
						</c:choose>&typesort=${typesort_page}">Previous</a></li>
						<c:forEach var="item" items="${listtrang}">
							<li class="page-item"><a class="page-link"
								href="/billManagement?trang=${item-1}&typesort=${typesort_page}">${item}</a></li>
						</c:forEach>
						<li class="page-item"><a class="page-link"
							href="/billManagement?trang=<c:choose>
						<c:when test="${page==dshd.totalPages-1}">${dshd.totalPages-1}</c:when>
						<c:otherwise>${page+1}</c:otherwise>
						</c:choose>&typesort=${typesort_page}">Next</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>

	<div class="col-6 ml-3 mt-3">
		<div class="row mb-4">
			<div class="col">
				<h3>Danh sách sản phẩm</h3>
				<table class="table table-stripe">
					<tr>
						<th>Product ID</th>
						<th>Image</th>
						<th>Product's name</th>
						<th>Price</th>
						<th>Amount</th>
						<th>Total</th>
					</tr>
					<c:forEach var="item" items="${cart}">
						<tr>
							<td>${item.product.id}</td>
							<td><img alt="" width="100px" height="50px"
								src="/img/${item.product.image}"></td>
							<td>${item.product.name}</td>
							<td>${item.price}</td>
							<td>${item.quantity}</td>
							<td>${item.product.price*item.quantity}</td>
						</tr>
					</c:forEach>
					<tr>
						<th>Thành tiền :</th>
						<th>${thanhtien}</th>
					</tr>
				</table>
			</div>
		</div>
	</div>
</section>