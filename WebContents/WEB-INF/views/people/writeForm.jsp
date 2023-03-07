<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
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
			<form action="${contextPath }/people/write?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
				<div>
					직업:
		 			<select class="form-control mr-2 category" name="kind">
						 	<option value="학생" >학생</option>
							<option value="교사" >교사</option>
							<option value="영양사">영양사</option>	
							<option value="경비" >경비</option>	
							<option value="교감">교감</option>	
							<option value="교장" >교장</option>	
							<option value="행정" >행정</option>	
				 	</select>
					이름:<input type="text" class="form-control" name="pname">
					출생년도:<input type="text" class="form-control" name="year">
					전화번호:<input type="text" class="form-control" name="tel">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<input type="hidden" class="form-control" name="cate_id" value="${pageName}">
					<input type="hidden" class="form-control" name="${kind}" value="${kind}">
					<div>
						사진:
						<div class="fileForm">
							<input type="file" name="attachFile" class="form-control attachFile">
							${message}		
						</div>
					</div>		
				</div>
				<button class="btn btn-info">글쓰기</button>
				<a href="${contextPath}/board/${kind}/${kind}/${pageName}" type="button" class="btn btn-danger">취소</a>
			</form>
		</div>
	</div>
</div>


<%@ include file="../layout/footer.jsp" %>


