<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배율 고등학교</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="${contextPath}/resources/css/style.css">
<script src="${contextPath}/resources/js/main.js"></script>
<script> const contextPath="${contextPath}"</script>
<script> const _csrfName="${_csrf.parameterName}"</script>
<script> const _csrftoken="${_csrf.token}"</script>
<script> const kind="${kind}"</script>
<script> const pageName="${pageName}"

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.authorities" var="vo"/>
	let vo =[];
	<c:forEach var="c" items="${vo}">
		vo.push('${c}')	
	</c:forEach>
</sec:authorize>

</script>



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
	};
	
	$('.logout').click(function(e){
		e.preventDefault();
		let logoutInput = $('<form/>');
		let csrf = $('<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">');
		logoutInput.attr('method','post')
					.attr('action','${contextPath}/member/logout')
					.append(csrf)
					.appendTo('body')
					.submit()
	}),
	
	$('.memberInfo').click(function(e){
		e.preventDefault();
		let detail = $('<form/>');
		let csrf = $('<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">');
		detail.attr('method','get')
					.attr('action','${contextPath}/member/info')
					.append(csrf)
					.appendTo('body')
					.submit()		
	})
	
})
</script>


</head>
<body>
<nav class="navbar navbar-expand-sm bg-info navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="${contextPath}"><img alt="" src="${contextPath}/resources/images/배율.png" width="50"></a>

  <!-- Links -->
  <ul class="navbar-nav">
    <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        학교
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="${contextPath}/board/school/school/intro">학교 소개</a>
        <a class="dropdown-item" href="${contextPath}/board/school/school/event">행사</a>
        <a class="dropdown-item" href="${contextPath}/board/school/school/notice">알림</a>
        <a class="dropdown-item" href="${contextPath}/board/school/school/free">자유 게시판</a>
        <a class="dropdown-item" href="${contextPath}/board/school/school/cafe">급식</a>
      </div>
    </li>
    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')">
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        학생
	      </a>
	      <div class="dropdown-menu">
	        <a class="dropdown-item" href="${contextPath}/board/studentBoard/studentBoard/sAttedent">출석</a>
	        <a class="dropdown-item" href="${contextPath}/board/studentBoard/studentBoard/score">성적표</a>
	        <a class="dropdown-item" href="${contextPath}/board/studentBoard/studentBoard/sBoard">학생 게시판</a>
	      </div>
	    </li>
	</sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')">
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        교사
	      </a>
	      <div class="dropdown-menu">
	        <a class="dropdown-item" href="${contextPath}/board/teacherBoard/teacherBoard/attend">출석</a>
	        <a class="dropdown-item" href="${contextPath}/board/teacherBoard/teacherBoard/record">성적 입력</a>
	        <a class="dropdown-item" href="${contextPath}/board/teacherBoard/teacherBoard/tBoard">교사 게시판</a>
	      </div>
	    </li>
	</sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ADMINIST')">
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        행정
	      </a>
	      <div class="dropdown-menu">
	        <a class="dropdown-item" href="${contextPath}/board/adminst/adminst/expend">지출</a>
	        <a class="dropdown-item" href="${contextPath}/board/adminst/adminst/event_in">행사 입력</a>
	        <a class="dropdown-item" href="${contextPath}/board/adminst/adminst/construction">공사</a>
	      </div>
	    </li>
	</sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_VICEP')">
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        교감
	      </a>
	      <div class="dropdown-menu">
	        <a class="dropdown-item" href="${contextPath}/board/vicep/vicep/join">인원</a>
	        <a class="dropdown-item" href="${contextPath}/board/vicep/vicep/event_sign">행사 결정</a>
	        <a class="dropdown-item" href="${contextPath }/board/vicep/vicep/sign">결재</a>
	      </div>
	    </li>
	</sec:authorize>
    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_PRINCIPAL')">
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        교장
	      </a>
	      <div class="dropdown-menu">
	        <a class="dropdown-item" href="${contextPath }/board/principal/principal/p_sign">결재</a>
	        <a class="dropdown-item" href="${contextPath}/board/principal/principal/p_event_sign">행사 결정</a>
	        <a class="dropdown-item" href="${contextPath}/board/principal/principal/p_join">인원</a>
	      </div>
	    </li>
    </sec:authorize>
    <li class="nav-item dropdown">
	    <sec:authorize access="isAnonymous()">
			<a class="nav-link dropdown-toggle float-right" href="#" id="navbardrop" data-toggle="dropdown">
				로그인
			</a>
			<div class="dropdown-menu">
			     	<a class="dropdown-item" href="${contextPath}/member/login">
				로그인
			</a>
			<a class="dropdown-item" href="${contextPath}/member/join">회원가입</a>
			<!--<a class="dropdown-item" href="${contextPath}/member/findMember">아이디/비밀번호찾기</a>-->
			</div>
       	</sec:authorize>
      	<sec:authorize access="isAuthenticated()">
			<a class="nav-link dropdown-toggle float-right" href="#" id="navbardrop" data-toggle="dropdown">
				<sec:authentication property="principal.username" var="user"/>${user}님
			</a>
			<div class="dropdown-menu">
	        	<a class="dropdown-item memberInfo" href="#" >회원정보</a>
	        	<a class="dropdown-item logout" href="#" >로그아웃</a>
	        </div>
        </sec:authorize>
    </li>
  </ul>
</nav>
<br>
  


