<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<p class="bg-primary text-white center">입학 / 입사</p>
	<div class="my-3">
		<div class="form-group">
		 	<a href="${contextPath}/board/principal/principal/p_join" type="button" class="btn btn-primary">새로고침</a>
		</div>
	</div>
	<table class="table">
		<tr>
			<td>번호</td>
			<td>정보</td>
			<td>상태</td>
			<td>내용</td>
			<td>결제</td>
			<td>반려</td>
		</tr>
		<c:forEach items="${list}" var="l">
			<tr>
				<td>${l.ano}</td>
				<td>${l.title}</td>
				<td>${l.state}</td>
				<td>${l.content}</td>
				<td>
					<form action="${contextPath}/approval/ok?${_csrf.parameterName}=${_csrf.token}" method="post">
						<input type="hidden" value="${kind}" name="k">
				 		<input type="hidden" value="${pageName}" name="p">
						<input type="hidden" value="${l.ano}" name="ano">
						<input type="hidden" value="${l.no}" name="no">
						<button>승인</button>
					</form>
					</td>
				<td>
					<form action="${contextPath}/approval/deny?${_csrf.parameterName}=${_csrf.token}" method="post">
						<input type="hidden" value="${kind}" name="k">
					 	<input type="hidden" value="${pageName}" name="p">
						<input type="hidden" value="${l.ano}" name="ano">
						<input type="hidden" value="반려" name="state">
						<button>반려</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>