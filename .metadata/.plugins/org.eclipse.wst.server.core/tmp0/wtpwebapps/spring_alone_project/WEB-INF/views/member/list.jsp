<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
    
<jsp:include page="../layout/header.jsp"></jsp:include>
<div class="container-md">
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">email</th>
	      <th scope="col">pwd</th>
	      <th scope="col">nickName</th>
	      <th scope="col">regAt</th>
	      <th scope="col">lastLogin</th>
	      <th scope="col">Roll</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach items="${list }" var="mvo">
	    <tr>
	      <th scope="row">${mvo.email }</th>
	      <td>${mvo.pwd }</a></td>
	      <td>${mvo.nickName }</td>
	      <td>${mvo.regAt }</td>
	      <td>${mvo.lastLogin }</td>
	      <td><c:forEach items="${mvo.authList }" var="auths">
			${auths.auth }
		  </c:forEach></td>
	    </tr>
	    </c:forEach>
	  </tbody>
	</table>
</div>
	
<jsp:include page="../layout/footer.jsp"></jsp:include> 