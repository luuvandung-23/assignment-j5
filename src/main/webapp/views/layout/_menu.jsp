<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<style type="text/css">
	.nav-link:hover{
	text-decoration: underline;
	}
	.img-border{
	border-radius:50%
	}
	</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/home/all">Home <span class="sr-only">(current)</span></a>
      </li>
      <c:if test="${account.role==true}">
      <li class="nav-item active">
        <a class="nav-link" href="/billManagement">Bill Management<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="/managerproduct">Product Management <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#">Quản lý danh mục<span class="sr-only">(current)</span></a>
      </li>
       <li class="nav-item active">
        <a class="nav-link" href="/thongke">Thống kề<span class="sr-only">(current)</span></a>
      </li>
      </c:if>
      <c:if test="${account.role==false}">
      <li class="nav-item active">
        <a class="nav-link" href="/cart/view">Giỏ hàng<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="/hoadon">Hóa đơn<span class="sr-only">(current)</span></a>
      </li>
      </c:if>
      <c:if test="${account == null}">
      <li class="nav-item active">
        <a class="nav-link" href="/cart/view">Giỏ hàng<span class="sr-only">(current)</span></a>
      </li>
      </c:if>
      <form class="form-inline my-2 my-lg-0" action="/home" method="post">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" value="${tim}"  aria-label="Search" name="tim" id="tim" >
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
    </ul>
    <ul class="navbar-nav inline my-2 my-lg-0">
    <c:if test="${account == null}">
			<li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
			<li class="nav-item"><a class="nav-link" href="/register">Register</a></li>
	 </c:if>
	 <c:if test="${account != null}">
	<li class="nav-item"> <img class="img-border" width="40px" height="40px" src="/img/${account.photo}"></li>
			<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Call/login">${account.fullname}</a></li>
			<li class="nav-item"><a class="nav-link" href="/logout">Đăng xuất</a></li>
	 </c:if>
	</ul>
  </div>
</nav>
</body>