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
	<p class="bg-primary text-white center">지출</p>
	<div class="my-3">
		<div class="form-group">
		 	<a href="${contextPath}/board/adminst/adminst/expend" type="button" class="btn btn-primary">새로고침</a>
		</div>
	</div>
	<table class="table">
			<tr>
				<td>번호</td>
				<td>지출 내용</td>
				<td>비용</td>
				<td>승인</td>
				<td>삭제</td>
			</tr>
			<c:forEach items="${expend}" var="e">
				<form action="${contextPath}/approval/expendDel?${_csrf.parameterName}=${_csrf.token}" method="post">
					<tr>
						<td>${e.eno}</td>
						<td>${e.name}</td>
						<td>${e.expend}</td>
						<td>${e.state}</td>
						<td>
							<input type="hidden" value="${kind}" name="kind">
					 		<input type="hidden" value="${pageName}" name="pageName">
					 		<input type="hidden" value="${e.eno}" name="eno">
					 		<c:if test="${e.state eq '행정'}">
					 			<button>삭제</button>
					 		</c:if>
					 		<c:if test="${e.state != '행정'}">
					 			<c:if test="${e.state != '교장승인'}">
					 				결제중
					 			</c:if>
					 			<c:if test="${e.state == '교장승인'}">
					 				처리완료
					 			</c:if>
					 		</c:if>
						</td>
					</tr>
				</form>
			</c:forEach>
	</table>
	<a href="${contextPath}/board/expend/adminst/${pageName}">입력</a>
</body>
</html>