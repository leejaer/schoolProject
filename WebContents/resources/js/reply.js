$(function(){
	
	$('.replybtn').click(function(e){
		let sno1 = $('.replySno').val()
		let reply = {
			sno : $('.replySno').val(), 
			reply : $('.replyVal').val(),
			writer : $('.replyWriter').val()
		};
		
		console.log(JSON.stringify(reply));
		$.ajax({
			type : 'post',
			url : `${contextPath}/reply/write?${_csrfName}=${_csrftoken}`,
			contentType : 'application/json;charset=utf-8',
			data : JSON.stringify(reply) ,
			success : function(result){
				window.location.href = `${contextPath}/board/detail/`+sno1;
			}, 
			error : function(){
				alert('에러');
			}	
		});
	})
});