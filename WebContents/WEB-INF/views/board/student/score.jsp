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
	<p class="bg-primary text-white center">성적 입력</p>
		<table class="table">
			<tr>
				<td>번호</td>
				<td>종류</td>
				<td>이름</td>
				<td>국어</td>
				<td>수학</td>
				<td>영어</td>
				<td>역사</td>
			</tr>
			<c:forEach items="${test}" var="t">
				<tr>
					<td>${t.pno}</td>
					<td>${t.testKind}</td>
					<td>${t.sname}</td>
					<td>${t.korea }</td>
					<td>${t.math }</td>
					<td>${t.english }</td>
					<td>${t.history }</td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>