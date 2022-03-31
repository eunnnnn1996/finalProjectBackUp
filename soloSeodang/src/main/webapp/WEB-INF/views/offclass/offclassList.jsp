<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/offclass.css">
<div class="container-right">
	<h3>오프라인 CLASS</h3>
	<c:if test="${!empty session_user_num && session_user_auth>=3}">
	<div class="align-right">
		<input type="button" value="CLASS 등록" onclick="location.href='offclassOpen.do'" class="btn btn-outline-secondary">
	</div>
	</c:if>
	<c:if test="${count==0 }">
		<div >등록된 클래스가 없습니다.</div>
	</c:if>
	<c:if test="${count>0 }">
	<div class="item-space">
		<c:forEach var="item" items="${list }">
			<div class="horizontal-area">
				<a href="offclassDetail.do?off_num=${item.off_num }">
					<div class="image-container">
						<img src="imageView.do?off_num=${item.off_num}" >
					</div>
					<div class="item-category">${item.category_num }</div>
					<div class="name">${item.name }</div>
					<div>${item.off_name }</div>
					<div><img src="${pageContext.request.contextPath}/resources/images/heart_gray.png"></div>
					<div class="align-right"><b>${item.off_price }원</b></div>
				</a>
			</div>
		</c:forEach>
	</div>
	<div class="align-center">${pagingHtml }</div>
	</c:if>
</div>