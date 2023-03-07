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
	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ADMINIST','ROLE_PRINCIPAL','ROLE_VICEP')">
		<form action="${contextPath }/board/pictureUpdate?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
			<div class="fileForm">
				<input type="file" name="attachFile" class="form-control attachFile">		
				<input type="hidden" name="sno" value="1">		
				<input type="hidden" name="kind" value="school">		
			</div>
			<button class="btn btn-info">그림 교체</button>	 
		</form>
	</sec:authorize>
</body>
</html>