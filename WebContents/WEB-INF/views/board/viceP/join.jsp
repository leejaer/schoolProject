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
	<p class="bg-primary text-white center">인원</p>
	<div class="my-3">
		<div class="form-group">
		 	<a href="${contextPath}/board/vicep/vicep/join" type="button" class="btn btn-primary">새로고침</a>
		</div>
	</div>
	<table class="table">
		<tr>
			<td>번호</td>
			<td>직업</td>
			<td>이름</td>
			<td>전화번호</td>
			<td>등록일</td>
			<td>상태</td>
			<td>결제</td>
		</tr>
		<c:forEach items="${list}" var="l">
			<form action="${contextPath}/approval/write?${_csrf.parameterName}=${_csrf.token}" method="post">
				<tr>
					<td>
					<input type="hidden" value="${l.pno}" name="no">
					<input type="hidden" value="${kind}" name="k">
					<input type="hidden" value="${pageName}" name="p">
					<input type="hidden" value="vicep" name="progress">
					<input type="hidden" value="교감승인" name="state">
					${l.pno}
					</td>
					
					<td><input type="hidden" name="title" value="${l.kind}${l.pname}">${l.kind}
					</td>
					<td><input type="hidden" value="${pageName}" name="kind">${l.pname}</td>
					<td>${l.tel}</td>
					<td>${l.joinDate}</td>
					<td>
						<c:if test="${l.kind == '교사'}">
				 			<select class="form-control mr-2 category" name="content">
							 	<option value="전학">전근</option>
								<option value="휴직">휴직</option>
								<option value="해고">해고</option>	
					 		</select>
				 		</c:if>
						<c:if test="${l.kind eq '학생'}">
				 			<select class="form-control mr-2 category" name="content">
							 	<option value="전학">전학</option>
								<option value="정학">정학</option>
								<option value="퇴학">퇴학</option>	
					 		</select>
				 		</c:if>
				 	</td>
					<td><button class="form-control">결정</button></td>
				</tr>
			</form>
		</c:forEach>
	</table>
	<a href="${contextPath}/people/write/vicep/${pageName}">입력</a>
</body>
</html>