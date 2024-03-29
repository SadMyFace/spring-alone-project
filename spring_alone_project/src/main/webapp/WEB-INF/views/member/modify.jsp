<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
    
<jsp:include page="../layout/header.jsp"></jsp:include> 

<c:set value="${mvo }" var="m" ></c:set>
<div class="container-md">
	<h2>Member Modify Page</h2> <br>
	
	<form action="/member/modify" method="post">
		<div class="mb-3">
	  		<label for="e" class="form-label">Email</label>
	  		<input type="text" name="email" class="form-control" id="e" value="${m.email }" readonly="readonly">
		</div>
		<div class="mb-3">
	  		<label for="p" class="form-label">Password</label>
	  		<input type="password" name="pwd" class="form-control" id="p" placeholder="Password">
		</div>
		<div class="mb-3">
	  		<label for="n" class="form-label">NickName</label>
	  		<input type="text" name="nickName" class="form-control" id="n" value="${m.nickName }">
		</div>
		
		<c:forEach items="${m.authList }" var="auths">
			${auths.auth }
		</c:forEach>
		<button type="submit" class="btn btn-primary">Modify</button>
		<a href="/member/delete?email=${m.email }"><button type="button" class="btn btn-primary">Delete</button></a>
	</form>
</div>


<jsp:include page="../layout/footer.jsp"></jsp:include> 