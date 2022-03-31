<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/List.css">
      

<c:if test="${!empty session_user_num && session_user_auth == 3}">
<div><button type="button" class="btn btn-outline-secondary" onclick="location.href='onclassInsert.do'">수업등록</button></div>
</c:if>


<c:if test="${count == 0}">
	<div class="result-display">표시할 게시물이 없습니다.</div>
</c:if>

<div class="main-category">
	<div class="category-s"><i class="bi bi-calendar-check-fill"><a href="${pageContext.request.contextPath}/onclass/onclassList.do">최신순</a></i></div>
	<div class="category-s"><i class="bi bi-lightning-charge-fill">찜순</i></div>
	<div class="category-s"><i class="bi bi-list-ol"><a href="${pageContext.request.contextPath}/onclass/onclassList.do?category=1">조회순</a></i></div>
</div>

<form action="onclassList.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>강의 이름</option>
				</select>
			</li>
			<li>
			<input type="search" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="검색">
			</li>
		</ul>
	</form>
<c:if test="${count > 0}">
<c:forEach var="onclass" items="${list}">
	<div class="home">
		<ul>
			<li>
				<div  onclick="location.href='onclassDetail.do?on_num=${onclass.on_num}'">
					<div class="list_img">
						<img src="imageView.do?on_num=${onclass.on_num}" 
	                                     					 style="max-width:200px;max-height:200px;margin-left:15px; margin-top:10px; border-radius: 10%;">	                                     					 
					</div>                                     					 
		<%-- <div class="btn_one">                                     					 
				<c:if test="${sessionScope.session_user_num == onclass.user_num}">
			    		<!-- 본인게시물만 삭제 수정 가능 %관리자도 삭제 가능하게 -->
				      <button type="button" class="btn btn-dark" onclick="location.href='onclassModify.do?on_num=${onclass.on_num}'">수정</button>
				      <button type="button" class="btn btn-dark" onclick="location.href='onclassDelete.do?on_num=${onclass.on_num}'">삭제</button>		      
			    </c:if>
			</div> --%>														         							                                     					 	
				</div>
			</li>	
		</ul>	
	</div>
</c:forEach>

<div class="align-center">${pagingHtml}</div>

</c:if>
	


    