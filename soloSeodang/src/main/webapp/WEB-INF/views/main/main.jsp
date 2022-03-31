<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
    
    
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">

<!-- 메인 시작 -->
<div class="page-main">
	<ul>
		<li><a id="category" href="${pageContext.request.contextPath}/main/main.do">전체보기</a></li>
		<li><a id="category" href="${pageContext.request.contextPath}/main/main.do?category_num=1">카테고리1</a></li>
		<li><a id="category" href="${pageContext.request.contextPath}/main/main.do?category_num=2">카테고리2</a></li>
		<li><a id="category" href="${pageContext.request.contextPath}/main/main.do?category_num=3">카테고리3</a></li>
		<li><a id="category" href="${pageContext.request.contextPath}/main/main.do?category_num=4">카테고리4</a></li>
		<li><a id="category" href="${pageContext.request.contextPath}/main/main.do?category_num=5">카테고리5</a></li>
	</ul>
</div>
<!-- 시작 -->
	
<div class="align-center" style="padding-right:50px">${pagingHtml}</div>

<!-- 끝 -->