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
				<td>번호</td>
				<td>이름</td>
				<td>생일</td>
				<td>출석일</td>
				<td>상태</td>
			</tr>
		<form action="${contextPath}/attend/write?${_csrf.parameterName}=${_csrf.token}" method="post">
			<c:forEach items="${StudentList}" var="s" varStatus="var" >
					<tr>
						<td>
						<input type="hidden" name="attendDTO[${var.index}].pno" value="${s.pno }">
						${s.pno }
						</td>
						<td>
						<input type="hidden" name="attendDTO[${var.index}].aname" value="${s.pname }">
						${s.pname }
						</td>
						<td>${s.year}</td>
						<td><select class="mr-2 category" name="attendDTO[${var.index}].year">
						 		<option value="2023" selected="selected">2023</option>
						 		<option value="2022">2022</option>
						 		<option value="2021">2021</option>
 				 			</select>
 				 			<select class="mr-2 category" name="attendDTO[${var.index}].month">
						 		<option value="01">1</option>
						 		<option value="02">2</option>
						 		<option value="03">3</option>
						 		<option value="04">4</option>
						 		<option value="05">5</option>
						 		<option value="06">6</option>
						 		<option value="07">7</option>
						 		<option value="08">8</option>
						 		<option value="09">9</option>
						 		<option value="10">10</option>
						 		<option value="11">11</option>
						 		<option value="12">12</option>
 				 			</select>
 				 			<select class="mr-2 category" name="attendDTO[${var.index}].date">
						 		<c:forEach begin="1" end="31" varStatus="s" >
						 			<c:if test="${s.count>=10}">
						 				<option value="${s.count}">${s.count }</option>
						 			</c:if>
						 			<c:if test="${s.count<10}">
						 				<option value="0${s.count}">${s.count }</option>
						 			</c:if>
						 		</c:forEach>
 				 			</select>
 				 		</td>
						<td>
							<select class="mr-2 category" name="attendDTO[${var.index}].condition">
						 		<option value="출석" selected="selected">출석</option>
						 		<option value="결석">결석</option>
						 		<option value="지각">지각</option>
						 		<option value="조퇴">조퇴</option>
 				 			</select>
						</td>
					</tr>
			</c:forEach>
			<input type="hidden" name="kind" value="${kind}">
			<input type="hidden" name="pageName" value="${pageName}">
			
			<button>입력</button>
		</form>	
	</table>
	<c:if test="${not empty attendList}">
		<table class="table">
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>출석일</td>
				<td>상태</td>
			</tr>
			<c:forEach items="${attendList}" var="a">
				<tr>
					<td>${a.pno }</td>
					<td>${a.aname }</td>
					<td>${a.date }</td>
					<td>${a.condition }</td>
				</tr>				
			</c:forEach>
		</table>
	</c:if>

</body>
</html>