<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<div class="swiper mySwiper">
  <div class="swiper-wrapper">
    <div class="swiper-slide"><a href=""><img alt="" src="${contextPath}/resources/images/메인.png" width="50"></a></div>
  </div>
</div>

<div class="container">
	<div class="row">
		<div class="col-1"></div>
		<div class="col-4">
			<table class="table table-hover">
				<thead>
			      <tr>
			        <th colspan="4">행사</th>
			      </tr>
			    </thead>
   		    	<tbody>
				      <tr>
				      	<th>제목</th>
				      	<th>작성자</th>
				      </tr>
				    <c:forEach items="${event}" var="e" begin="1" end="5">
					      <tr>
					      	<td>${e.title }</td>
					      	<td>${e.writer }</td>
					      </tr>
				    </c:forEach>
			    </tbody>
			</table>		
		</div>
		<div class="col-1"></div>
		
		<div class="col-5">
			<table class="table table-hover">
				<thead>
			      <tr>
			        <th colspan="4">알림</th>
			      </tr>
			    </thead>
		    	<tbody>
				      <tr>
				      	<th>제목</th>
				      	<th>작성자</th>
				      </tr>
				    <c:forEach items="${notice}" var="n" begin="1" end="5">
					      <tr>
					      	<td>${n.title }</td>
					      	<td>${n.writer }</td>
					      </tr>
				    </c:forEach>
			    </tbody>
			</table>		
		</div>
		<div class="col-1"></div>
	</div>
	
	
</div>


<%@ include file="layout/footer.jsp" %>


<script>
var swiper = new Swiper(".mySwiper", {
    spaceBetween: 30,
    centeredSlides: true,
    autoplay: {
      delay: 2500,
      disableOnInteraction: false,
    },
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
    },
  });
  
	let message = "${message}"
	if(message!='') alert(message);
  
</script>