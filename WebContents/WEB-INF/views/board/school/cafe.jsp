<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<p class="bg-primary text-white center">급식</p>
	<div class="my-3">
		<form class="form-inline searchForm">					 
			 <div class="form-group mr-2">
			 	<a href="${contextPath}/board/school/school/cafe/${today-1}" type="button" class="btn btn-primary">이전</a>
			 </div>
				<select class="form-control mr-2 category" name="month">
					 <c:forEach items="${board}" var="b">
						 <option value="${b.title}" ${today ==b.title ? 'selected':''} >${b.title}월 </option>
					</c:forEach>								 
				 </select>
			 <div class="form-group mr-2">
			 	<a href="${contextPath}/board/school/school/cafe/${today+1}" type="button" class="btn btn-primary">다음</a>
			 </div>
		</form>
	</div>				
	<div>
		<c:forEach items="${attachList }" var="attach">
			<c:if test="${attach.fileType eq 'IMAGE'}">
				<div class="media border p-3 my-2">
					<div class="media-body d-flex justify-content-betwwen">
						<div class="media-body">
							<img alt="" src="${contextPath}/imgDisplay?filePath=${attach.filePath}&fileName=${attach.fileName}" style="width:800px">
						</div>
					</div>
				</div>
			</c:if>	
			<c:if test="${attach.fileType eq 'OTHER'}">
				<div class="media border p-3 my-2">
					<div class="media-body d-flex justify-content-betwwen">
						<div class="media-body">
							<p>이미지 파일을 등록해주세요</p>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${empty attach.fileType}">
				<div class="media border p-3 my-2">
					<div class="media-body d-flex justify-content-betwwen">
						<div class="media-body">
							<p>이미지 파일을 등록해주세요</p>
						</div>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</div>
		<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_NUTRI')">
			<form class="form-inline searchForm" action="${contextPath }/board/cafeUpdate" method="post" enctype="multipart/form-data">
				<div class="fileForm">
					<select class="form-control mr-2 category" name="title">
						<c:forEach items="${board}" var="b">
							 <option value="${b.title}" ${today ==b.title ? 'selected':''} >${b.title}월 </option>
						</c:forEach>
					 </select>
					<input type="file" name="attachFile" class="form-control attachFile">		
					<input type="hidden" name="kind" value="school">
					<input type="hidden" name="cate_id" value="cafe">
					<input type="hidden" name="writer" value="영양사">
					<input type="hidden" name="content" value="밥">
				</div>
					<button class="btn btn-info">급식 입력</button>	 
			</form>
		</sec:authorize>
</body>
</html>