<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp" %>
<script src="${contextPath}/resources/js/writeForm.js"></script>
<input type="hidden" value="${pageName}" class="pageName" name="pageName">

<div class="container"> 
	<div>
		<nav class="navbar navbar-expand-sm bg-light navbar-light">
		  <ul class="navbar-nav">
		    <li class="nav-item active">
		      학교
		    </li>
		  </ul>
		</nav>
	</div>
	<div class="row">
		<div class="col-3">
			<ul class="list-group">
				<c:forEach items="${cateList}" var="c" >
					<li class="list-group-item ${pageName==c.cate_id ? 'list-group-item-primary':'' }">
						<a href="${contextPath}/board/adminst/adminst/${c.cate_id}">${c.cate_name}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="col-9">
			<form action="${contextPath}/approval/eWrite?${_csrf.parameterName}=${_csrf.token}" method="post" >
				<div>
					제목:<input type="text" class="form-control" name="title">
					내용:<textarea rows="10" cols="" name="content" class="form-control"></textarea>
					<input type="hidden" class="form-control" name="progress" value="${kind}">
					<input type="hidden" class="form-control" name="state" value="행정">
					<input type="hidden" class="form-control" name="kind" value="${pageName}">
					<input type="hidden" class="form-control" name="k" value="${kind}">
					<input type="hidden" class="form-control" name="p" value="${pageName}">
				</div>
				<button class="btn btn-info">글쓰기</button>
				<a href="${contextPath}/board/${kind}/${kind}/${pageName}" type="button" class="btn btn-danger">취소</a>
			</form>
		</div>
	</div>
</div>


<%@ include file="../../layout/footer.jsp" %>


