<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 중앙 컨텐츠 시작 -->
<!-- 부트스트랩 라이브러리 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/user.css">
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main">
	<h2 style="text-align:center">my메뉴</h2>
	<ul class="profile-photo">
		<li>
			<img id="cen_img" src="${pageContext.request.contextPath}/resources/images/face.png"
			                     width="160" height="160" class="my-photo" onclick="location.href='myPage.do'">내정보
		</li>
		<li>
			<img id="cen_img" src="${pageContext.request.contextPath}/resources/images/myclass.png"
			                     width="160" height="160" class="my-photo" onclick="location.href='${pageContext.request.contextPath}/myclass/myclassMain.do'"> my클래스     
		</li>
		<li>
			<img id="cen_img" src="${pageContext.request.contextPath}/resources/images/qna.png"
			                     width="160" height="160" class="my-photo" onclick="location.href='myqnaList.do'">1:1문의 내역
		</li>
		<li>
			<img id="cen_img" src="${pageContext.request.contextPath}/resources/images/myreserve.png"
			                     width="160" height="160" class="my-photo" onclick="location.href='${pageContext.request.contextPath}/onqna/onqnaList.do'">찜목록
		</li>
		<li>
			<img id="cen_img" src="${pageContext.request.contextPath}/resources/images/shoppingbasket.png"
			                     width="160" height="160" class="my-photo" onclick="location.href='myMenu.do'">장바구니
		</li>
		<li>
			<img id="cen_img" src="${pageContext.request.contextPath}/resources/images/buylist.png"
			                     width="160" height="160" class="my-photo" onclick="location.href='test.do'">구매내역
		</li>
	</ul>
</div>
<!-- 중앙 컨텐츠 끝 -->


