<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="container">
	<input type="hidden" name="loginId" class="form-control" value="${loginId}">
	<div class="jumbotron">
		<h2>회원 로그인</h2>
	</div>
	<form action="${contextPath}/member/login" method="post">
		<div class="form-group">
			<label>아이디: </label>
			<input type="text" name="id" class="form-control">
		</div>
		<div class="form-group">
			<label>비밀번호 : </label>
			<input type="password" name="pwd" class="form-control">
		</div>
		로그인 상태 유지 : <input type="checkbox" name="remember-me">
		<button class="btn btn-primary">로그인</button>
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger">
				<strong>${errorMessage}</strong>
			</div>
		</c:if>
		<!-- csrf 토큰 -->
		 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	</form>
</div>
<%@ include file="../layout/footer.jsp" %>


