<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/writeForm.js"></script>
<input type="hidden" value="${pageName}" class="pageName" name="pageName">

<div class="container"> 
	<div>
		<nav class="navbar navbar-expand-sm bg-light navbar-light">
		  <ul class="navbar-nav">
		    <li class="nav-item active">
		      학교
		    </li>
		  </ul>
		</nav>
	</div>
	<div class="row">
		<div class="col-3">
			<ul class="list-group">
					<li class="list-group-item ${pageName==c.cate_id ? 'list-group-item-primary':'' }">
						<a href="${contextPath}/board/list/school">배율 고등학교</a>
					</li>
				<c:forEach items="${cateList}" var="c" >
					<li class="list-group-item ${pageName==c.cate_id ? 'list-group-item-primary':'' }">
						<a href="${contextPath}/board/list/school/${c.cate_id}">${c.cate_name}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="col-9">
			<form action="${contextPath }/board/modify?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
				<div>
					제목:<input type="text" class="form-control" name="title" value="${d.title}">
					내용:<textarea rows="10" cols="" name="content" class="form-control">${d.content }</textarea>
					작성자:<input type="text" class="form-control" name="writer" value="${d.writer }" readonly="readonly">
					
					<div>
						<div class="fileForm">
							<button type="button" class="btn btn-primary add">폼추가</button>
							<button type="button" class="btn btn-danger del">폼삭제</button>
							<input type="file" name="attachFile" class="form-control attachFile">		
						</div>
					</div>		
					<input type="hidden" class="form-control" name="kind" value="${kind}">
					<input type="hidden" class="form-control" name="cate_id" value="${pageName}">
					<input type="hidden" class="form-control" name="sno" value="${d.sno}">
				</div>

		
				<c:forEach items="${attachList }" var="attach">
						<c:if test="${attach.fileType eq 'IMAGE'}">
							<div class="media border p-3 my-2">
								<div class="media-body d-flex justify-content-betwwen">
									<div class="media-body">
										<img alt="" src="${contextPath}/imgDisplay?filePath=${attach.filePath}&fileName=${attach.fileName}" style="width:150px">
									</div>
									<div>
										<label for="delCheck">삭제:</label>
										<input type="checkbox" class="form-control delCheck" name="delCheck" value="${attach.sno}" id="delCheck">
									</div>								
								</div>
							</div>
						</c:if>	
						<c:if test="${attach.fileType eq 'OTHER'}">
							<div class="media border p-3 my-2">
								<div class="media-body d-flex justify-content-betwwen">
									<div class="media-body">
										<img alt=""  src="${contextPath}/resources/images/fileicon.png" width="10"><a class="bnt btn-info" href="${contextPath}/download?filePath=${attach.filePath}&fileName=${attach.fileName}">다운로드</a>
									</div>
									<div>
										<label for="delCheck">삭제:</label>
										<input type="checkbox" class="form-control delCheck" name="delCheck" value="${attach.sno}" id="delCheck">
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${empty attach.fileType}">
						<div class="media-body d-flex justify-content-betwwen">
								<div class="media-body d-flex justify-content-betwwen">
									<div class="media-body">
										<img alt=""  src="${contextPath}/resources/images/fileicon.png" width="10"><a class="bnt btn-info" href="${contextPath}/download?filePath=${attach.filePath}&fileName=${attach.fileName}">다운로드</a>
									</div>
									<div>
										<label for="delCheck">삭제:</label>
										<input type="checkbox" class="form-control delCheck " name="delCheck" value="${attach.sno}" id="delCheck">
									</div>
								</div>
							</div>
						</c:if>
				</c:forEach>
				<button class="btn btn-info">수정</button>
				<a href="${contextPath}/board/list/${kind}/${pageName}" type="button" class="btn btn-danger">취소</a>
			</form>
		</div>
	</div>
</div>


<%@ include file="../layout/footer.jsp" %>


