<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style type="text/css">
	body {
  margin: 0;
  padding: 0;
  height: 100vh;
}
#login .container #login-row #login-column #login-box {
  margin-top: 40px;
  max-width: 650px;
  height: 610px;
  border: 1px solid #9C9C9C;
  background-color: #EAEAEA;
}
#login .container #login-row #login-column #login-box #login-form {
  padding: 20px;
}
#login .container #login-row #login-column #login-box #login-form #register-link {
  margin-top: -85px;
}
</style>
</head>
    <div id="login">
        <div class="container">
		<c:if test="${not empty error}">
	<div class="alert alert-danger">${error}</div>
	</c:if>
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                    <form:form id="login-form" class="form" action="/register" modelAttribute="item" enctype="multipart/form-data">
                            <h3 class="text-center text-info">Registration</h3>
                            <div class="form-group">
                                <label  class="text-info">Username:</label><br>
                                <form:input path="username"  class="form-control" /> <form:errors path="username" element="li" delimiter="; " cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label  class="text-info">Password:</label><br>
                                <form:input path="password" type="password" class="form-control" /> <form:errors path="password" element="li" delimiter="; " cssClass="error"/>
                            </div> 
                            <div class="form-group">
                                <label class="text-info">Fullname:</label><br>
                                <form:input path="fullname"  class="form-control" /> <form:errors path="fullname" element="li" delimiter="; " cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label  class="text-info">Email:</label><br>
                                  <form:input path="email" class="form-control" /> <form:errors path="email" element="li" delimiter="; " cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label for="username" class="text-info">Image:</label><br>
                                 <input type="file" name="image" class="form-control">
                            </div>
                            <div class="form-group">
                                <button class="btn btn-info btn-md" >Create</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>