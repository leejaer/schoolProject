<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="container">
	<div class="jumbotron">
		<h1>상세</h1>
	</div>
	<form action="${contextPath}/member/update?${_csrf.parameterName}=${_csrf.token}" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div class="form-group">
			<input type="text" name="id" class="form-control" placeholder="아이디" value="${info.id}" readonly="readonly">
			<form:errors path="memberDTO.id" element="div" class="input_error"/>
		</div>
		<div class="form-group">
			<input type="password" name="pwd" class="form-control" placeholder="비밀번호">
			<form:errors path="memberDTO.pwd" element="div" class="input_error"/>
		</div>
		<div class="form-group">
			<input type="password" name="pwdConfirm" class="form-control" placeholder="비밀번호확인">
			<form:errors path="memberDTO.pwdConfirm" element="div" class="input_error"/>
		</div>
		<div class="form-group">
			<input type="text" name="email" class="form-control" placeholder="이메일" value="${info.email}">
			<form:errors path="memberDTO.email" element="div" class="input_error"/>
		</div>
		<div class="form-group">
			<input type="text" name="pname" class="form-control" placeholder="이름" value="${info.pname}" readonly="readonly">
			<form:errors path="memberDTO.pname" element="div" class="input_error"/>
		</div>
		<div class="form-group">
			<input type="text" name="tel" class="form-control" placeholder="휴대폰번호" value="${info.tel}" readonly="readonly">
			<form:errors path="memberDTO.tel" element="div" class="input_error"/>
		</div>
		<ul ></ul>
		<button class="btn btn-primary">수정하기</button>
		<a href="${contextPath}/member/info" class="btn btn-primary">취소</a>
	</form>
</div>
<%@ include file="../layout/footer.jsp" %>


<script>
	let message = "${message}"
	if(message!='') alert(message);
	
</script>
