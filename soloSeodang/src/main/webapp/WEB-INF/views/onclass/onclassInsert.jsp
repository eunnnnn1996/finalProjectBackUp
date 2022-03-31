<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 중앙 컨텐츠 시작 -->
<!-- 부트스트랩 라이브러리 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/onclass.css">

<script type="text/javascript">
	$(function(){
		$('#open_form').submit(function(){
			if($('#upload').val().trim()==''){
				alert('대표 사진을 선택하세요!');
				$('#upload').val('').focus();
				return false;
			}
		});
	});
</script>

<style>
.ck-editor__editable_inline{
	
	min-height:250px;
}
.container{width:1200px; max-width:none!important;}
</style>
<!-- ckedior 라이브러리 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/uploadAdapter.js"></script>
<div class="page-main">
	<h2>글쓰기</h2>
	<form:form modelAttribute="onclassVO" action="onclassInsert.do" 
	                 enctype="multipart/form-data" id="open_form">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<form:label path="category_num"><b>01</b></form:label>
				<form:label path="category_num">CLASS 분류</form:label><br>
				<form:select class="off-form-input" path="category_num">
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
				<form:label path="on_name"><b>02</b></form:label>
				<form:label path="on_name">수업명</form:label><br>
				<form:input class="off-form-input" path="on_name"/>
				<form:errors path="on_name" cssClass="error-color"/>
				<hr size="1" noshade>
			</li>
			<li>
				<form:label path="on_price"><b>03</b></form:label>
				<form:label path="on_price">가격</form:label><br>
				<form:input class="off-form-input" type="number" path="on_price"/>
				<form:errors path="on_price" cssClass="error-color"/>
				<hr size="1" noshade>
			</li>
			<li><label for="on_content"><b>04</b></label></li>
			<li><label for="on_content">수업 상세</label><br></li>
			<li>
				<form:textarea path="on_content"/>
				<form:errors path="on_content" cssClass="error-color"/>
				<hr size="1" noshade>
				<script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					} 
				 ClassicEditor
		            .create( document.querySelector( '#on_content' ),{
		            	extraPlugins: [MyCustomUploadAdapterPlugin]
		            })
		            .then( editor => {
						window.editor = editor;
					} )
		            .catch( error => {
		                console.error( error );
		            } );
			    </script>      
			</li>
			<li>
				<form:label path="upload"><b>대표사진 설정</b></form:label>
				<input type="file" name="upload" id="upload">
				<form:errors path="upload" cssClass="error-color"/>
			</li>
		</ul>	 
		<div class="align-center">
			<form:button>전송</form:button>
		</div>                                          
	</form:form>
</div>