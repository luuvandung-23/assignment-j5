<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
	<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
</head>
<section class="row">
		<div class="col-9">
			<div class="row p-2">
				<c:forEach var="item" items="${listsp.content}">
					<div class="col-3 mt-2">
						<div class="card text-center">
							<div class="card-body">
								<img src="/img/${item.image}" width="200px" height="200px" alt="" class="img-fluid">
								<div class="row border-top mt-2">
									<b>${item.name}</b>
									<p>${item.price}</p>
								</div>
							</div>
							<div class="card-footer">
								<a href="/inforPro?msp=${item.id}" class="btn btn-success">Mua</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<div class="col">
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<li class="page-item"><a class="page-link" href="/home?trang=<c:choose>
						<c:when test="${page==0}">0</c:when>
						<c:otherwise>${page-1}</c:otherwise>
						</c:choose>">Previous</a></li>
							<c:forEach var="item" items="${listtrang}">
							<li class="page-item"><a class="page-link" href="/home?trang=${item-1}">${item}</a></li>
							</c:forEach>
							<li class="page-item"><a class="page-link" href="/home?trang=<c:choose>
						<c:when test="${page==listsp.totalPages-1}">${listsp.totalPages-1}</c:when>
						<c:otherwise>${page+1}</c:otherwise>
						</c:choose>">Next</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
		<div class="col-3">
			<div class="row mt-3 mb-3">
				<div class="col">
					<div class="card">
					<div class="card-header">
					<i class="far fa-thumbs-up">Danh mục</i>
					</div>
					<ul class="list-group list-group-flush">
					<li class="list-group-item"><a href="home/all">Tất cả</a></li>
						<c:forEach var="item" items="${listdm}">
						<li class="list-group-item"><a href="/home?idDm=${item.id}">${item.name}</a></li>
						</c:forEach>
					</ul>
					</div>
				</div>
			</div>
		</div>
	</section>