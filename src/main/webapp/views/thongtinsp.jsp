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
		<div class="col-4 mt-3" >
			<div class="row p-2">
			<img alt="" src="/img/${sp.image}" >
</div>
</div>
<div class="col-6 ml-4">
		<div class="row mt-3 ">
		 <form class="form" action="Assignment/Call" method="post">
                            <h3 class="text-center text-info">Product information</h3>
                            <div class="form-group">
		<label for="userid">PRODUCT ID:</label>
		<input type="text" name="id" id="id" class="form-control" disabled="disabled" value="${sp.id}">
		</div>
		<div class="form-group">
		<label for="tensp">Product's name:</label>
		<input type="text" name="name" id="name" class="form-control" disabled="disabled" value="${sp.name}">
		</div>
		<div class="form-group">
		<label for="gia">Price:</label>
		<input type="text" name="price" id="price" class="form-control" disabled="disabled" value="${sp.price}">
		</div>
		<div class="form-group">
		<label for="available">Available:</label>
		<input type="text" name="available" id="available" class="form-control" disabled="disabled" value="${sp.available? 'Còn hàng' : 'hết hàng'}">
		</div>
		<div class="form-group">
		<label >Amount:</label><br>
         <input type="number" name="amount" min="1" max="100" value=1>
         </div>
		<div class="form-group">
		<label for="sl">Note:</label>
		<textarea name="mieuta" id="mieuta" class="form-control" rows="4" cols="50" disabled="disabled" ></textarea>
		</div>
		<div class="form-group">
		<button class="btn btn-primary" formaction="/cart/add?id=${sp.id}">Thêm vào giỏ</button>
        </div>
        </form>
		</div>
		</div>
</section>