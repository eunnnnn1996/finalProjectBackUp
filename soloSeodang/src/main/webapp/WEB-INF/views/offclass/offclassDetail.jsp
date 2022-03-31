<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/offclass.css">
<!-- 중앙 컨텐츠 시작 -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/videoAdapter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/offclass.detail.js"></script>

<div class="container-right">
	<div class="container-head">
		<h3>오프라인 클래스 상세</h3>
		<div>
			<c:if test="${offclass.user_num == session_user_num }">
				<input class="btn btn-outline-secondary"  type="button" value="수정" onclick="location.href='offclassUpdate.do?off_num=${offclass.off_num}'">
			</c:if>
			<c:if test="${offclass.user_num == session_user_num || session_user_auth==4 }">
				<input class="btn btn-outline-secondary"  type="button" value="삭제" onclick="location.href='offclassDelete.do?off_num=${offclass.off_num}'">
			</c:if>
		</div>
	</div>
	<div class="detail_content">
		<div class="ck_content">
			${offclass.off_content }
		</div>
		<div class="sidebar">
			<div class="sidbar_content">
			<input type="hidden" value="${offclass.off_num }" id="off_num">
				<div>
				${offclass.category_num }
				</div>
				<h3>${offclass.off_name }</h3>
				<h4>${offclass.off_price }원</h4>
				<hr size="1">
				<h4>클래스 일정</h4>
				<div>
				<c:forEach var="offTimetableVO" items="${list }">
					<div aria-label="${offTimetableVO.time_date }">
						<button class="btn" >${offTimetableVO.time_date }월</button>
					</div>
				</c:forEach>
				</div>
				<hr size="1" noshade>
				<div id="timetable">
				
				</div>
				<input class="btn btn-outline-secondary" type="button" value="장바구니 담기"><br>
				<input class="btn btn-outline-secondary" type="button" value="바로 구매">
				<c:choose>
				    <c:when test="${likecheck eq '0' or empty likecheck}"> 
				    	<button class="btn" id="like_btn" >
				        	<img src="${pageContext.request.contextPath }/resources/images/heart_nofill.png">
				        	<span id="like_count"></span>
					    </button>
				    </c:when>
				    <c:otherwise> 
				        <button class="btn" id="like_btn" >
				        	<img src="${pageContext.request.contextPath }/resources/images/heart_fill.png">
				        	<span id="like_count"></span>
					    </button>
				    </c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->