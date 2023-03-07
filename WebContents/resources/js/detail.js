$(function(){
	
	$('.modify').click(function(e){
		e.preventDefault();
		$('.moForm').attr('action',`${contextPath}/board/modify`)
					.attr('method', 'get')
		.submit();
	}),
	
	
	$(function(){
		let	sno = $('.replySno').val();
		
		let snovo ={sno:sno};
		
		$.ajax({
			type : 'get',
			url : `${contextPath}/reply/list`,
			contentType : 'apllication/json;charset=utf-8',
			data : snovo, 
			success : function(result) {
				console.log(result);
				result.forEach(function(e){
					let add =`<div class="comment card mb-3 reply${e.rno}">`
						add +='<div class="card-body replyadd">'
						add +=`<p class="card-text">${e.writer}</p>`
						add +='<div class="form-group">'
						add +=`<textarea class="form-control content" readonly="readonly">${e.reply }</textarea>`
						add +='</div>'
						add +='<div class="form-group contentAdd">'
						add +='<input type="hidden" class="form-control commentC">'
						add +=`<input type="hidden" class="form-control commentParent_id" value="${e.rno}">`
						add +=`<input type="hidden" class="form-control commentW" value="">`
						add +='</div>'
						add +='<button type="button" class="btn btn-primary answerBtn">답글</button>';
						
						if(vo.includes('ROLE_ADMIN' || 'ROLE_ADMINIST' )) add +='<button type="button" class="btn btn-primary answerDel">삭제</button>'
						
						add +='</div>'
						add +='</div>'
					
					if(e.level==1){
						$('.comments').append(add);
					}
					
					if(e.level>1){
						$(`.reply${e.parent_id}`).append(add);	
					}
				});
				  $('.answerBtn').click(function(e){
					e.preventDefault();
					if($(this).parent().find('.commentC').attr('type')=='text'){
						let sno1 = $('.replySno').val()
						
						let comment = {
							reply : $(this).parent().find('.commentC').val(),
							writer : $('.replyWriter').val(),
							sno : $('.replySno').val(),
							parent_id : $(this).parent().find('.commentParent_id').val()
						};
						
						$.ajax({
							type : 'post',
							url : `${contextPath}/reply/commentWrite?${_csrfName}=${_csrftoken}`,
							contentType : 'application/json;charset=utf-8',
							data : JSON.stringify(comment) ,
							success : function(result){
								window.location.href = `${contextPath}/board/detail/`+sno1;
							}, 
							error : function(){
								alert('에러');
							}	
						});
					}
					console.log($(this).parent().find('.commentC'));
					$(this).parent().find('.commentC').attr('type','text');                
				}),
				
				$('.answerDel').click(function(e){
					e.preventDefault();
						let sno1 = $('.replySno').val()
						
						let comment = {
							sno : $('.replySno').val(),
							rno : $(this).parent().find('.commentParent_id').val()
						};
						
						$.ajax({
							type : 'post',
							url : `${contextPath}/reply/answerdel?${_csrfName}=${_csrftoken}`,
							contentType : 'application/json;charset=utf-8',
							data : JSON.stringify(comment) ,
							success : function(result){
								window.location.href = `${contextPath}/board/detail/`+sno1;
							}, 
							error : function(){
								alert('에러');
							}	
						});
				})
			},
			error : function() {
				console.log('에러!!!');
			}
		})
	})
	
	

	
});