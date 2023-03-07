<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

<style>
.input_error{
	font-size: 13px;
	color: red;
}
.error_on{
	border-color: red;
}


</style>
<script>
$(function(){
	let error_form = $('.input_error')

	if(error_form.length>0){
		error_form.prev().addClass('error_on')
	}
})
</script>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    
	<p class="bg-primary text-white center">성적</p>
	<table class="table">
			<tr>
				<td>번호</td>
				<td>종류</td>
				<td>이름</td>
				<td>생일</td>
				<td>국어</td>
				<td>수학</td>
				<td>영어</td>
				<td>역사</td>
			</tr>
		<form action="${contextPath}/test/write?${_csrf.parameterName}=${_csrf.token}" method="post">
			<c:forEach items="${StudentList}" var="s" varStatus="var" >
					<tr>
						<td>
							${s.pno }
							<input type="hidden" name="testDTO[${var.index}].pno" value="${s.pno}">
						</td>
						<td>
							<select class="mr-2 category" name="testDTO[${var.index}].testKind">
						 		<option value="중간" selected="selected">중간</option>
						 		<option value="기말">기말</option>
				 			</select>
						</td>
						<td><input type="hidden" name="testDTO[${var.index}].sname" value="${s.pname}">${s.pname }</td>
						<td>${s.year}</td>
						<td>
							<input type="text" name="testDTO[${var.index}].korea" style="width:40px">
							<form:errors path="testDTO.testDTO[${var.index}].korea" element="div" class="input_error"/>
						</td>
						<td>
							<input type="text" name="testDTO[${var.index}].math" style="width:40px">
							<form:errors path="testDTO.testDTO[${var.index}].math" element="div" class="input_error"/>
						</td>
						<td>
							<input type="text" name="testDTO[${var.index}].english" style="width:40px">
							<form:errors path="testDTO.testDTO[${var.index}].english" element="div" class="input_error"/>
						</td>
						<td>
							<input type="text" name="testDTO[${var.index}].history" style="width:40px">
							<form:errors  path="testDTO.testDTO[${var.index}].history" element="div" class="input_error"/>
						</td>
					</tr>
			</c:forEach>
			<input type="hidden" name="kind" value="${kind}">
			<input type="hidden" name="pageName" value="${pageName}">
			<button>입력</button>
		</form>	
	</table>
	<c:if test="${not empty test }">
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
			<c:forEach items="${test }" var="t">
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
	</c:if>