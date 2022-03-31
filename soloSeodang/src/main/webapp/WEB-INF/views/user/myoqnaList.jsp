<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style_dw.css"
	type="text/css" />
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>

<div class="page-main">
	<h2>TEst - QnA 목록 - Test</h2>

	<div>
		<img
			src="${pageContext.request.contextPath }/resources/image/oqnaImage.png"
			style="width: 953px; hieght: 338px">
	</div>


	<table>
		<tr style="font-family: 'Gowun Dodum', sans-serif;">
			<th style="width: 8%;">글번호</th>
			<th style="width: 50%;">제목</th>
			<th style="width: 10%;">회원등급</th>
			<th style="width: 10%;">작성자</th>
			<th style="width: 12%;">작성자ID</th>
			<th style="width: 10%;">작성일</th>
		</tr>
		<c:forEach var="oqna" items="${list}">
			<tr>
				<td style="width: 8%;">${oqna.qna_num }</td>
				<td style="width: 50%;"><a
					href="oqnaDetail.do?qna_num=${oqna.qna_num }">${oqna.title }</a></td>
				<td style="width: 10%;"><c:if test="${oqna.auth==0 }">탈퇴회원</c:if>
					<c:if test="${oqna.auth==1 }">정지회원</c:if> <c:if
						test="${oqna.auth==2 }">일반회원</c:if> <c:if test="${oqna.auth==3 }">선생님</c:if>
					<c:if test="${oqna.auth==4 }">관리자</c:if></td>
				<td style="width: 10%;">${oqna.name }</td>
				<td style="width: 12%;">${oqna.id }</td>
				<td style="width: 10%;">${oqna.reg_date }</td>
			</tr>
		</c:forEach>
	</table>

</div>

