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
	<p class="bg-primary text-white center">행사</p>
	<div class="my-3">
			<div class="form-group">
			 	<a href="${contextPath}/board/adminst/adminst/event_in" type="button" class="btn btn-primary">새로고침</a>
			</div>
	</div>
	<table class="table">
			<tr>
				<td>번호</td>
				<td>행사</td>
				<td>내용</td>
				<td>승인</td>
				<td>삭제</td>
			</tr>
			<c:forEach items="${event}" var="e">
				<tr>
					<td>${e.ano }</td>
					<td>${e.title }</td>
					<td>${e.content }</td>
					<td>${e.state }</td>
					<c:if test="${e.progress eq 'adminst'}">
						<td>
							<form action="${contextPath}/approval/eventDel?${_csrf.parameterName}=${_csrf.token}" method="post">
							 	<input type="hidden" value="${kind}" name="kind">
						 		<input type="hidden" value="${pageName}" name="pageName">	
								<input type="hidden" value="${e.ano}" name="ano">
								<button>삭제</button>
							</form>
						</td>
					</c:if>
					<c:if test="${e.progress != 'adminst'}">
						<td>====</td>
					</c:if>
				</tr>
			</c:forEach>
	</table>
	<a href="${contextPath}/board/eWrite/adminst/${pageName}">입력</a>
</body>
</html>