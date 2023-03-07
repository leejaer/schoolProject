<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../layout/header.jsp" %>
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
			<form action="${contextPath }/board/constructionWrite?${_csrf.parameterName}=${_csrf.token}" method="post">
				<table class="table expend">
					<tr>
						<th>공사 명</th>
						<th>비용</th>
						<th>일수</th>
					</tr>
					<tr class="expendForm">
						<td>
							<input type="text" name="name" class="form-control">
							<form:errors path="expendVO.name" element="div" class="input_error"/>
							<input type="hidden" name="state" class="form-control" value="행정">
							<input type="hidden" name="kind" class="form-control" value="construction">
						</td>
						<td>
							<input type="text" name="expend" class="form-control">
							<form:errors path="expendVO.expend" element="div" class="input_error"/>
						</td>
						
						<td>
							<input type="text" name="count" class="form-control">
							<form:errors path="expendVO.count" element="div" class="input_error"/>
						</td>
					</tr>
				</table>
				<input type="hidden" value="${kind}" name="k">
				<input type="hidden" value="${pageName}" name="pageName">
				<button class="btn btn-info">글쓰기</button>
				<a href="${contextPath}/board/${kind}/${kind}/${pageName}" type="button" class="btn btn-danger">취소</a>
			</form>
		</div>
	</div>
</div>


<%@ include file="../../layout/footer.jsp" %>


