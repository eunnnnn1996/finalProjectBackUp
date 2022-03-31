<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 중앙 컨텐츠 시작 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css.bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/kit.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/uploadAdapter.js"></script>

<div class="page-main">
	<h2>글수정</h2>
	<form:form modelAttribute="kitVO" action="kitUpdate.do" id="upate_form"
	               enctype="multipart/form-data">
		<form:hidden path="kit_num"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<form:label path="kit_name">키트이름</form:label>
				<form:input path="kit_name"/>
				<form:errors path="kit_name" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="kit_price">가격</form:label>
				<form:input type="number" path="kit_price" min="0"/>
				<form:errors path="kit_price" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="kit_quantity">판매수량</form:label>
				<form:input type="number" path="kit_quantity" min="1"/>
				<form:errors path="kit_quantity" cssClass="error-color"/>
			</li>
			<li><b>내용</b></li>
			<li>
				<form:textarea path="kit_content"/>
				<form:errors path="kit_content" cssClass="error-color"/>
				<script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#kit_content' ),{
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
				<form:label path="upload">파일업로드</form:label>
				<input type="file" name="upload" id="upload">
				<c:if test="${!empty kitVO.filename}">
				<br>
				<span id="file_detail">(${kitVO.filename})파일이 등록되어 있습이다.
				다시 업로드하면 기존 파일은 삭제됩니다.
				<input type="button" value="파일삭제" id="file_del">
				</span>
<script type="text/javascript">
	$(function(){
		$('#file_del').click(function(){
			let choice = confirm('삭제하시겠습니까?');
			if(choice){
				$.ajax({
					url:'deleteFile.do',
					data:{kit_num:${kitVO.kit_num}},
					type:'post',
					dataType:'json',
					cache:false,
					timeout:30000,
					success:function(param){
						if(param.result == 'logout'){
							alert('로그인 후 사용하세요');
						}else if(param.result == 'success'){
							$('#file_detail').hide();	
						}else{
							alert('파일 삭제 오류 발생');
						}
					},
					error:function(){
						alert('네트워크 오류 발생');
					}
				});
			}
		});
	});
</script>				
				</c:if>
			</li>
		</ul>
		<div class="align-center">
			<form:button>전송</form:button>
			<input type="button" value="등록" 
			                         onclick="location.href='kitList.do'">
		</div>
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->