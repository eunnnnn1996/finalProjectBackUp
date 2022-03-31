<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!-- 나중에 필요 할까봐 일단 삭제 안했습니다. -->  
<!-- 왼쪽 메뉴 시작 -->
<ul>
	<li><a href="${pageContext.request.contextPath}/board/list.do">게시판</a></li>
	<c:if test="${!empty user_num && user_auth == 4}">
	<li><a href="${pageContext.request.contextPath}/user/admin_list.do">회원관리</a></li>	
	</c:if>
</ul>
<!-- 왼쪽 메뉴 끝 -->