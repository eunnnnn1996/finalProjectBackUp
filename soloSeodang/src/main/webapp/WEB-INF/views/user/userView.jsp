<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<style>
	.page-main a {
		font-size:22px;
	}
</style>
<!-- 중앙 컨텐츠 시작 -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		//프로필 사진 업로드
		$('#photo_btn').click(function(){
			$('#photo_choice').show();
			$(this).hide();
		});
		
		//처음 화면에 보여지는 이미지 읽기
		let photo_path = $('.my-photo').attr('src');
		let my_photo;
		$('#upload').change(function(){
			my_photo = this.files[0];
			if(!my_photo){
				$('.my-photo').attr('src',photo_path);
				return;
			}
			
			if(my_photo.size > 1024 * 1024){
				alert(Math.round(my_photo.size/1024) 
						        + 'kbytes(1024kbytes까지만 업로드 가능)');
				$('.my-photo').attr('src',photo_path);
				$(this).val('');
				return;
			}
			
			//이미지 미리보기
			var reader = new FileReader();
			reader.readAsDataURL(my_photo);
			
			reader.onload=function(){
				$('.my-photo').attr('src',reader.result);
			};
		});//end of change
		
		//이미지를 서버에 전송
		$('#photo_submit').click(function(){
			if($('#upload').val()==''){
				alert('파일을 선택하세요!');
				$('#upload').focus();
				return;
			}
			
			//파일 전송
			var form_data = new FormData();
			form_data.append('upload',my_photo);
			
			$.ajax({
				url:'updateMyPhoto.do',
				type:'post',
				data:form_data,
				dataType:'json',
				contentType:false,
				enctype:'multipart/form-data',
				processData:false,
				success:function(param){
					if(param.result == 'logout'){
						alert('로그인 후 사용하세요!');
					}else if(param.result == 'success'){
						alert('프로필 사진이 수정되었습니다.');
						photo_path = $('.my-photo').attr('src');
						$('#upload').val('');
						$('#photo_choice').hide();
						$('#photo_btn').show();
					}else{
						alert('파일 전송 오류 발생');
					}
				},
				errror:function(){
					alert('네트워크 오류 발생');
				}
			});
		});//end of click
		
		$('#photo_reset').click(function(){
			$('.my-photo').attr('src',photo_path);
			$('#upload').val('');
			$('#photo_choice').hide();
			$('#photo_btn').show();
		});
		
	});
</script>
<div class="page-main">
	<h2>프로필 사진</h2>
	<ul>
		<li>
			<c:if test="${empty user.photo_name}">
			<img src="${pageContext.request.contextPath}/resources/images/face.png"
			                     width="200" height="200" class="my-photo">
			
			</c:if>
			<c:if test="${!empty user.photo_name}">
			<img src="${pageContext.request.contextPath}/user/photoView.do"
			                     width="200" height="200" class="my-photo">
			</c:if>
		</li>
		<li>
			<div class="align-center">
				<input type="button" value="수정" id="photo_btn">
			</div>
			<div id="photo_choice" style="display:none;">
				<input type="file" id="upload" accept="image/gif,image/png,image/jpeg">
				<input type="button" value="전송" id="photo_submit">
				<input type="button" value="취소" id="photo_reset">
			</div>
		</li>
	</ul>
	<h2>회원 상세 정보</h2>
	<ul>
		<li>이름 : ${user.name}</li>
		<li>전화번호 : ${user.phone}</li>
		<li>우편번호 : ${user.zipcode}</li>
		<li>주소 : ${user.address1}</li>
		<li>상세주소 : ${user.address2}</li>
		<li>이메일 : ${user.email}</li>
		<li>나이 : ${user.age}</li>
		<li>가입날짜 : ${user.reg_date}</li>
		<c:if test="${!empty user.modify_date}">
		<li>정보 수정일 : ${user.modify_date}</li>
		</c:if>
	</ul>
	<hr size="1" width="100%">
	<p class="align-right">
		<input type="button" value="회원정보수정" 
		                   onclick="location.href='update.do'">
		<input type="button" value="비밀번호변경" 
		                   onclick="location.href='changePassword.do'"> 
		<input type="button" value="회원탈퇴" 
		                   onclick="location.href='delete.do'"> 
		<input type="button" value="회원메인메뉴" 
		                   onclick="location.href='myMenu.do'">                                          
	</p>
</div>
<!-- 중앙 컨텐츠 끝 -->