<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/offclass.css">
<!-- 중앙 컨텐츠 시작 -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/ckeditor_style.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/uploadAdapter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/offclass.time.js"></script>


<div class="container-right">
	<h4>오프라인 CLASS 등록하기</h4>
	<form:form action="offclassOpen.do" modelAttribute="offclassVO" id="open_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<ul class="items">
			<li>
				<form:label path="category_num"><b>01</b></form:label>
				<form:label path="category_num">CLASS 분류</form:label><br>
				<form:select path="category_num" class="off-form-input" id="category_num">
					<form:option value="1">드로잉</form:option>
					<form:option value="2">플라워</form:option>
					<form:option value="3">공예</form:option>
					<form:option value="4">요리</form:option>
					<form:option value="5">베이킹</form:option>
				</form:select>
				<form:errors path="category_num" cssClas="error-color"/>
				<hr size="1" noshade>
			</li>
			<li>
				<form:label path="off_name"><b>02</b></form:label>
				<form:label path="off_name" maxlength='5'>수업명</form:label><br>
				<form:input path="off_name" placeholder="수업명을 입력해주세요." class="off-form-input" id="off_name"/>
				<form:errors path="off_name" cssClass="error-color"/>
				<hr size="1" noshade>
			</li>
			<li>
				<form:label path="off_limit"><b>03</b></form:label>
				<form:label path="off_limit">수업 인원</form:label><br>
				<form:input type="number" path="off_limit" min="1" class="off-form-input" id="off_limit"/>
				<form:errors path="off_limit"  cssClass="error-color"/>
				<hr size="1" noshade>
			</li>
			<li>
				<form:label path="off_price"><b>04</b></form:label>
				<form:label path="off_price">가격</form:label><br>
				<form:input type="number" path="off_price" min="0" class="off-form-input" id="off_price"/>
				<form:errors path="off_price"  cssClass="error-color"/>
				<hr size="1" noshade>
			</li>
			<li>
				<label for="time_date"><b>05</b></label>
				<label for="time_date">수업 일정</label><br>
				<input type="date" id="time_date" class="off-form-input"> <input type="time" id="time_start" class="off-form-input-date">~<input type="time" id="time_end" class="off-form-input-date">
				<input type="button" id="time_register" value="등록">
				<span id="message_id"></span>
			</li>
			<li id="time-item"><li>
			<li><hr size="1" noshade></li>
			<li><label for="off_content"><b>06</b></label></li>
			<li><label for="off_content">수업 상세</label><br></li>
			<li id="content">
				<form:errors path="off_content" cssClass="error-color"/>
				<form:textarea path="off_content" id="off_content"/>
				<!-- <script>
				
			    </script> -->      
			</li>
			<li>
				<form:label path="off_upload">대표사진 선택</form:label>
				<input type="file" name="off_upload" id="off_upload">
			</li>
		</ul>
		<div class="align-center">
			<form:button class="btn btn-outline-secondary" id="submit_btn">전송</form:button>
			<input type="button" value="목록" onclick="location.href='offclassList.do'" class="btn btn-outline-secondary">
		</div>
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->