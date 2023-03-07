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
		 	<a href="${contextPath}/board/principal/principal/p_sign" type="button" class="btn btn-primary">새로고침</a>
		</div>
	</div>
	<table class="table">
			<tr>
				<td>번호</td>
				<td>행사</td>
				<td>내용</td>
				<td>상태</td>
				<td>승인</td>
				<td>반려/삭제</td>
			</tr>
			<c:forEach items="${sign}" var="e">
					<c:if test="${e.state == '교감승인'}">
						<tr>
							<td>${e.eno}</td>
							<td>${e.name}</td>
							<td>${e.expend}</td>
							<td>${e.state}</td>
							<td>
								<form action="${contextPath}/approval/updateSign?${_csrf.parameterName}=${_csrf.token}" method="post">
										<input type="hidden" value="${kind}" name="kind">
								 		<input type="hidden" value="${pageName}" name="pageName">
								 		<input type="hidden" value="${e.eno}" name="eno">
								 		<input type="hidden" value="교장승인" name="state">
							 			<button>승인</button>
								</form>
							</td>
							<td>
						 		<c:if test="${e.state != '교장승인'}">
									<form action="${contextPath}/approval/expendDel?${_csrf.parameterName}=${_csrf.token}" method="post">
										<input type="hidden" value="${kind}" name="kind">
								 		<input type="hidden" value="${pageName}" name="pageName">
								 		<input type="hidden" value="${e.eno}" name="eno">
							 			<button>반려/삭제</button>
									</form>
								</c:if>
							</td>
						</tr>
					</c:if>						
					<c:if test="${e.state == '교장승인'}">
						<tr>
							<td>${e.eno}</td>
							<td>${e.name}</td>
							<td>${e.expend}</td>
							<td>${e.state}</td>
							<td>
								처리완료
							</td>
							<td>
								<form action="${contextPath}/approval/expendDel?${_csrf.parameterName}=${_csrf.token}" method="post">
									<input type="hidden" value="${kind}" name="kind">
							 		<input type="hidden" value="${pageName}" name="pageName">
							 		<input type="hidden" value="${e.eno}" name="eno">
						 			<button>삭제</button>
								</form>
							</td>
						</tr>
					</c:if>						
			</c:forEach>
	</table>
</body>
</html>