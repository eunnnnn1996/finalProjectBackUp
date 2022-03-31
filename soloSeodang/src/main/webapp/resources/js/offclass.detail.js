$(function(){
	
	let off_num=$('#off_num').val();
	
	function getContextPath() {
		var hostIndex = location.href.indexOf( location.host ) + location.host.length;
		return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
	}
	let contextPath = getContextPath();

	//찜하기
	selectLikeCount();
	$('#like_btn').click(function(){
		$.ajax({
			url:'like.do',
			type:'post',
			data:{off_num:off_num},
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result=='logout'){
					alert('로그인이 필요합니다.');
					//location.href='${pageContext.request.contextPath}//user/login.do';
				}else if(param.result=='success'){
					$('#like_btn').find('img').attr('src',contextPath +'/resources/images/heart_fill.png');
					selectLikeCount();
				}else if(param.result=='cancelLike'){
					$('#like_btn').find('img').attr('src',contextPath +'/resources/images/heart_nofill.png');
					selectLikeCount();
				}else{
					alert('찜 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	})
	function selectLikeCount(){
		$.ajax({
			url:'countLike.do',
			type:'post',
			data:{off_num:off_num},
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result=='success'){
					$('#like_count').text(param.count);
				}else{
					alert('찜 오류 발생!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}
	
	//타임 테이블 보여주기
	
	
})