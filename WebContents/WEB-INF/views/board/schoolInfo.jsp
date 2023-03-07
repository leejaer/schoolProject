<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<script src="${contextPath}/resources/js/main.js"></script>
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
					<li class="list-group-item ${pageName==c.cate_id ? 'list-group-item-primary':'' }">
						<a href="${contextPath}/board/${kind}/${kind}/${c.cate_id}">${c.cate_name}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
			<div class="col-9"> 
				<c:if test="${pageName eq 'event'}">
					<%@include file ="school/event.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'notice'}">
					<%@include file ="school/notice.jsp" %>
				</c:if>

				<c:if test="${pageName eq 'free'}">
					<%@include file ="school/free.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'cafe'}">
					<%@include file ="school/cafe.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'intro'}">
					<%@include file ="school/intro.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'join'}">
					<%@include file ="viceP/join.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'p_join'}">
					<%@include file ="principal/p_join.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'p_event_sign'}">
					<%@include file ="principal/event.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'event_in'}">
					<%@include file ="administ/event.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'event_sign'}">
					<%@include file ="viceP/event.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'expend'}">
					<%@include file ="administ/expend.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'construction'}">
					<%@include file ="administ/construction.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'sign'}">
					<%@include file ="viceP/approval.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'p_sign'}">
					<%@include file ="principal/approval.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'record'}">
					<%@include file ="teacher/record.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'score'}">
					<%@include file ="student/score.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'sBoard'}">
					<%@include file ="student/sBoard.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'tBoard'}">
					<%@include file ="teacher/tBoard.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'attend'}">
					<%@include file ="teacher/attend.jsp" %>
				</c:if>
				<c:if test="${pageName eq 'sAttedent'}">
					<%@include file ="student/sAttedent.jsp" %>
				</c:if>
			</div>
	</div>
</div>

<%@ include file="../layout/footer.jsp" %>


