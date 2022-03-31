<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<style>
	.page-main a {
		font-size:22px;
	}
</style>
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main">
	<h2>QNA 리스트</h2>
	<ul class="profile-photo">
		<li>
			<img id="cen_img" src="${pageContext.request.contextPath}/resources/images/"
			                     width="160" height="160" class="my-photo" onclick="location.href='myoqnaList.do'">전체 qna
		</li>
		<li>
			<img id="cen_img" src="${pageContext.request.contextPath}/resources/images/"
			                     width="160" height="160" class="my-photo">온라인 강의 qna
		</li>
		<li>
			<img id="cen_img" src="${pageContext.request.contextPath}/resources/images/"
			                     width="160" height="160" class="my-photo">오프라인 강의 qna    
		</li>
	</ul>
</div>
<!-- 중앙 컨텐츠 끝 -->

