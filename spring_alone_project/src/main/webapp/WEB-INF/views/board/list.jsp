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

	<!-- paging -->
	<nav aria-label="Page navigation example">
		  <ul class="pagination">
		  <c:if test="${ph.prev }">
		  	<li class="page-item ${(ph.prev eq false) ? 'disabled' : '' }">
		  		<a class="page-link" href="/board/list?pageNo=${ph.startPage - 1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
		  		<span aria-hidden="true">&laquo;</span>
		  		</a>
		  	</li>
		  </c:if>
		  
		  <c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
		  	<li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
		  </c:forEach>
		  
		  <c:if test="${ph.next }">
		  	<li class="page-item ${(ph.next eq false) ? 'disabled' : '' }">
		  		<a class="page-link" href="/board/list?pageNo=${ph.endPage + 1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
		  		<span aria-hidden="true">&raquo;</span>
		  		</a>
		  	</li>
		  </c:if>
		  </ul>
	</nav>
	
	<!-- search -->
	<div class="col-sm-12 col-md-6">
		<form action="#" method="get">
			<div class="input-group mb-3">
				<c:set value="${ph.pgvo.type }" var="typed"></c:set>
				<select class="form-select" name="type" id="input">
					<option ${typed == null ? 'selected' : '' }>Choose...</option>
					<option value="t" ${typed == 't' ? 'selected' : '' }>Title</option>
					<option value="w" ${typed == 'w' ? 'selected' : '' }>Writer</option>
					<option value="c" ${typed == 'c' ? 'selected' : '' }>Content</option>
					<option value="tw" ${typed == 'tw' ? 'selected' : '' }>Title & Writer</option>
					<option value="tc" ${typed == 'tc' ? 'selected' : '' }>Title & Content</option>
					<option value="wc" ${typed == 'wc' ? 'selected' : '' }>Writer & Content</option>
					<option value="twc" ${typed == 'twc' ? 'selected' : '' }>All</option>
				</select>
				<input type="hidden" name="pageNo" value="1">
				<input type="hidden" name="qty" value="${ph.pgvo.qty }">
				<input class="form-control me-2" name="keyword" type="search" placeholder="Search" aria-label="Search" value="${ph.pgvo.keyword }">
				<button class="btn btn-outline-success" type="submit">Search
					<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
				    	${ph.totalCount }
				    	<span class="visually-hidden">unread messages</span>
  					</span>
				</button>
			</div>
		</form>
	</div>

<jsp:include page="../layout/footer.jsp" />

<script type="text/javascript">
	const isDel = `<c:out value="${isDel}" />`;
	
	if(isDel == 1){
		alert("게시글 삭제 완료");
	}
</script>