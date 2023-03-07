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
	<p class="bg-primary text-white center">성적</p>
	<table class="table">
			<tr>
				<td>이름</td>
				<td>출석</td>
				<td>상태</td>
			</tr>
			<tr>
				<td>${attend.aname}</td>
				<td>${attend.date}</td>
				<td>${attend.condition}</td>
			</tr>
	</table>
</body>
</html>