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
	<p class="bg-primary text-white center">공사</p>
	<div class="my-3">
		<div class="form-group">
		 	<a href="${contextPath}/board/vicep/vicep/sign" type="button" class="btn btn-primary">새로고침</a>
		</div>
	</div>
	<table class="table">
			<tr>
				<td>번호</td>
				<td>행사</td>
				<td>내용</td>
				<td>상태</td>
				<td>승인</td>
				<td>반려</td>
			</tr>
			<c:forEach items="${sign}" var="e">
					<tr>
						<td>${e.eno}</td>
						<td>${e.name}</td>
						<td>${e.expend}</td>
						<td>${e.state}</td>
						<td>
							<c:if test="${e.state != '교장승인'}">
								<form action="${contextPath}/approval/updateSign?${_csrf.parameterName}=${_csrf.token}" method="post">
									<input type="hidden" value="${kind}" name="kind">
							 		<input type="hidden" value="${pageName}" name="pageName">
							 		<input type="hidden" value="${e.eno}" name="eno">
							 		<input type="hidden" value="교감승인" name="state">
						 			<button>승인</button>
								</form>
							</c:if>
							<c:if test="${e.state == '교장승인'}">
									처리완료			
							</c:if>
						</td>
						<td>
					 		<c:if test="${e.state != '교장승인'}">
								<form action="${contextPath}/approval/expendDel?${_csrf.parameterName}=${_csrf.token}" method="post">
									<input type="hidden" value="${kind}" name="kind">
							 		<input type="hidden" value="${pageName}" name="pageName">
							 		<input type="hidden" value="${e.eno}" name="eno">
						 			<button>반려</button>
								</form>
							</c:if>
							<c:if test="${e.state == '교장승인'}">
								처리완료
							</c:if>
						</td>
					</tr>
			</c:forEach>
	</table>
</body>
</html>