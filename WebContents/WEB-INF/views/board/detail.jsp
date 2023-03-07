<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/detail.js"></script>
<script src="${contextPath}/resources/js/reply.js"></script>
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
				<c:forEach items="${cateList}" var="c" >
					<li class="list-group-item ${d.cate_id==c.cate_id ? 'list-group-item-primary':'' }">
						<a href="${contextPath}/board/${d.kind}/${d.kind}/${c.cate_id}">${c.cate_name}</a>
						<input type="hidden" class="replySno" value="${d.sno}">
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="col-9">
			<div>
				제목:<input type="text" class="form-control" name="title" value="${d.title }" readonly>
				내용:<textarea rows="10" cols="" name="content" class="form-control" readonly>${d.content }</textarea>
				작성자:<input type="text" class="form-control" name="writer" value="${d.writer }" readonly>
				<input type="hidden" class="form-control" name="kind" value="${d.kind}">
				<input type="hidden" class="form-control" name="cate_id" value="${d.cate_id}">
			</div>
			<c:forEach items="${attachList }" var="attach">
					<c:if test="${attach.fileType eq 'IMAGE'}">
						<div class="media border p-3 my-2">
							<div class="media-body d-flex justify-content-betwwen">
								<div class="media-body">
									<img alt="" src="${contextPath}/imgDisplay?filePath=${attach.filePath}&fileName=${attach.fileName}" style="width:150px">
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
							</div>
						</div>
					</c:if>
					<c:if test="${empty attach.fileType}">
						<div class="media border p-3 my-2">
							<div class="media-body d-flex justify-content-betwwen">
								<div class="media-body">
									<p>파일이 없습니다</p>
								</div>
							</div>
						</div>
					</c:if>
				</c:forEach>
				<c:if test="${user != d.writer}">
					<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ADMINIST','ROLE_PRINCIPAL','ROLE_VICEP')">
						<form action="${contextPath}/board/delete" class="moForm" method="post" >
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
							<button class="btn btn-info modify">수정</button>
							<button class="btn btn-info delete">삭제</button>
							<input type="hidden" value="${d.kind}" name="kind">
							<input type="hidden" value="${d.cate_id}" name="pageName">
							<input type="hidden" value="${d.sno}" name="sno">
						</form>
					</sec:authorize>
				</c:if>
				
				<c:if test="${user eq d.writer}">
					<form action="${contextPath}/board/delete" class="moForm" method="post" >
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						<button class="btn btn-info modify">수정</button>
						<button class="btn btn-info delete">삭제</button>
						<input type="hidden" value="${d.kind}" name="kind">
						<input type="hidden" value="${d.cate_id}" name="pageName">
						<input type="hidden" value="${d.sno}" name="sno">
					</form>
				</c:if>
					<a href="${contextPath}/board/${d.kind}/${d.kind}/${d.cate_id}" type="button" class="btn btn-danger">취소</a>
			<sec:authorize access="isAuthenticated()">		
				<div class="card reply">
				  <div class="card-body row">
					<div class="col-2">
						작성자:<input type="text" name="writer" class="form-control replyWriter" readonly="readonly" value="${user}">
					</div>
				  </div>
				  <div>
				  	<div class="">
						내용:<textarea rows="3" class="form-control replyVal"></textarea>
					</div>
						
					<div class="col-2">
						<button class="form-control btn btn-primary replybtn">입력</button>
					</div>
				 </div>
				</div>
			</sec:authorize>
			<div class="comments">
		    </div>	
		</div>
	</div>
</div>


<%@ include file="../layout/footer.jsp" %>

