<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
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
						<a href="${contextPath}/board/${kind}/${kind}/${c.cate_id}">${c.cate_name}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="col-9">
			<form action="${contextPath }/board/write?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
				<div>
					제목:<input type="text" class="form-control" name="title" value="${vo.title}">
						<form:errors path="schoolVO.title" element="div" class="input_error"/>
					내용:<textarea rows="10" cols="" name="content" class="form-control">${vo.content}</textarea>
						<form:errors path="schoolVO.content" element="div" class="input_error"/>
					작성자:<input type="text" class="form-control" name="writer" readonly="readonly"  value="${user}">
						<form:errors path="schoolVO.writer" element="div" class="input_error"/>
					<div>
						<div class="fileForm">
							<button type="button" class="btn btn-primary add">폼추가</button>
							<button type="button" class="btn btn-danger del">폼삭제</button>
							<input type="file" name="attachFile" class="form-control attachFile">		
						</div>
					</div>		
					<input type="hidden" class="form-control" name="kind" value="${kind}">
					<input type="hidden" class="form-control" name="cate_id" value="${pageName}">
				</div>
				<button class="btn btn-info">글쓰기</button>
				<a href="${contextPath}/board/${kind}/${kind}/${pageName}" type="button" class="btn btn-danger">취소</a>
			</form>
		</div>
	</div>
</div>


<%@ include file="../layout/footer.jsp" %>




