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
	<p class="bg-primary text-white center">자유 게시판</p>
	<div class="my-3">
		<form action="${contextPath}/board/teacherBoard/teacherBoard/tBoard?${_csrf.parameterName}=${_csrf.token}" method="get" class="form-inline searchForm">
			<select class="form-control mr-2 category">
		 	<option value="" >공지</option>
		 		</select>
					<input type="hidden" name="category" value="${pageName}">		
					<select class="form-control mr-2 category" name="type">
					 	<option value="T" ${cri.type == 'T' ? 'selected':''}>제목</option>
						<option value="C" ${cri.type == 'C' ? 'selected':''}>내용</option>
						<option value="W" ${cri.type == 'W' ? 'selected':''}>작성자</option>	
				 </select>
		 	<div class="form-group mr-2">
				<input type="search" class="form-control" name="keyword" value="${cri.keyword}">
			</div>
			<div class="form-group mr-2">
			 	<input type="hidden" value="${kind}" name="kind">
			 	<input type="hidden" value="${pageName}" name="pageName">
			 	<button class="btn btn-primary btnnn">검색</button>
			</div>
			<div class="form-group">
			 	<a href="${contextPath}/board/teacherBoard/teacherBoard/tBoard" type="button" class="btn btn-primary">새로고침</a>
			</div>
		</form>
	</div>
	<table class="table">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
		</tr>
		<c:forEach items="${board}" var="b">
			<tr>
				<td><input type="hidden" value="${b.sno}" name="sno">${b.sno}</td>
				<td><a href="${contextPath}/board/detail/${b.sno}">${b.title}</a>
				<c:if test="${b.attachFileCnt>0}"> <img alt=""  src="${contextPath}/resources/images/fileicon.png" width="10"></c:if>
				</td>
				<td>${b.writer}</td>
				<td>${b.writeDate}</td>
			</tr>
		</c:forEach>
	</table>
	<ul class="pagination">
		<c:if test="${p.prev}">
			<li class="page-item">
				<a class="page-link" href="${p.startPage-1}">이전</a>
			</li>
		</c:if>
		<c:forEach begin="${p.startPage }" end="${p.endPage}" var="pageNum">
			<li class="page-item ${cri.page == pageNum ? 'active':''}">
				<a class="page-link" href="${pageNum}">${pageNum}</a>
			</li>		
		</c:forEach>
		<c:if test="${p.next}">
			<li class="page-item">
				<a class="page-link" href="${p.endPage+1 }">다음</a>
			</li>		
		</c:if>
	</ul>
	<a href="${contextPath}/board/write/${kind}/${pageName}">글 쓰기</a>
</body>
</html>