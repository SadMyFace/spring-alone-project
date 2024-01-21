<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="../layout/header.jsp"></jsp:include> 

<div class="container-md">
	<h2>Member Join Page</h2> <br>
	
	<form action="/member/register" method="post">
		<div class="mb-3">
	  		<label for="e" class="form-label">Email</label>
	  		<input type="text" name="email" class="form-control" id="e" placeholder="example@000.com">
	  		<p id="emailCheckText"></p>
	  		<div>
		  		<button type="button" class="btn btn-outline-success" id="checkEmail">중복 확인</button>
	  		</div>
		</div>
		<div class="mb-3">
	  		<label for="p" class="form-label">Password</label>
	  		<input type="password" name="pwd" class="form-control" id="p" placeholder="Password">
		</div>
		<div class="mb-3">
	  		<label for="p2" class="form-label">Password</label>
	  		<input type="password" name="pwdCheck" class="form-control" id="p2" placeholder="PasswordCheck">
		</div>
		<p id="pwdCheckText"></p>
		<div class="mb-3">
	  		<label for="n" class="form-label">NickName</label>
	  		<input type="text" name="nickName" class="form-control" id="n" placeholder="NickName">
		</div>
		<button type="submit" class="btn btn-primary" id="signUp">SignUp</button>
	</form>
</div>

<script src="/resources/js/memberRegister.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include> 