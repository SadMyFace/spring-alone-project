<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:include page="../layout/header.jsp" />

<div class="mb-3">
	<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Title</th>
      <th scope="col">Writer</th>
      <th scope="col">Content</th>
      <th scope="col">regAt</th>
      <th scope="col">modAt</th>
      <th scope="col">readCount</th>
      <th scope="col">cmtQty</th>
      <th scope="col">hasFile</th>
    </tr>
  </thead>
  <tbody>
	  <c:forEach items="${list }" var="bvo">
	  	<tr>
	      <th scope="row">${bvo.bno }</th>
	      <td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
	      <td>${bvo.writer }</td>
	      <td>${bvo.content }</td>
	      <td>${bvo.regAt }</td>
	      <td>${bvo.modAt }</td>
	      <td>${bvo.readCount }</td>
	      <td>${bvo.cmtQty }</td>
	      <td>${bvo.hasFile }</td>
	    </tr>
	  </c:forEach>
  </tbody>
</table>
</div>

<jsp:include page="../layout/footer.jsp" />