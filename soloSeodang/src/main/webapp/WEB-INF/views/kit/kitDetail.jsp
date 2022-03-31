<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>     
<!-- 중앙 컨텐츠 시작 -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board.reply.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/videoAdapter.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/kit_2.css">
<div class="page-main">
	<div class="kitdetail_p">
	<!--<c:if test="${!empty kit.filename}">
		<li>첨부파일 : <a href="file.do?board_num=${kit.kit_num}">${kit.filename}</a></li>
		</c:if>-->
	<c:if test="${fn:endsWith(kit.filename,'.jpg') ||
	              fn:endsWith(kit.filename,'.JPG') ||
	              fn:endsWith(kit.filename,'.gif') ||
	              fn:endsWith(kit.filename,'.GIF') ||
	              fn:endsWith(kit.filename,'.png') ||
	              fn:endsWith(kit.filename,'.PNG')}">
	           
	
		<img src="imageView.do?kit_num=${kit.kit_num}"  style="width:300px; height:300px;" >
	
	</c:if>
	</div>
	<div class="kitdetail">
	<h2>${kit.kit_name}</h2>
	<ul>
		<li>키트번호 : ${kit.kit_num}</li>
		<li>키트명 : ${kit.kit_name}</li>
		<li>조회수 : ${kit.hit}</li>
		<c:if test="${!empty kit.modify_date}">
		<li>최근 수정일 : ${kit.modify_date}</li>
		</c:if>
		<li>작성일 : ${kit.reg_date}</li>
	</ul>
	
	<input type="button" value="장바구니"id="cart" onclick="location.href='kitOder.do'">
		<input type="button" value="관련클래스 구경하기" id="button2" onclick="location.href='class.do'">
		<input type="button" value="목록" onclick="location.href='kitList.do'">
	</div>
	<h2>상세내용</h2>
	<div id="kit_content">
	<p>
		${kit.kit_content}
	</p>
	</div>
	<div class="align-right">
		<c:if test="${!empty session_user_num && session_user_num == kit.user_num}">
		<input type="button" value="수정" onclick="location.href='kitUpdate.do?kit_num=${kit.kit_num}'">	
		<input type="button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('kitDelete.do?kit_num=${kit.kit_num}');
				}
			};
		</script>
		</c:if>
		
	</div>
	</div>
<!-- 중앙 컨텐츠 끝 -->







