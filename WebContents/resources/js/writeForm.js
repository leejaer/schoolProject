$(function(){
	let formCount =1;
	
	$('.fileForm .add').click(function(){
		if(formCount>=5){
			alert('최대 5개까지입니다')
			return;
		}
		
		let fileFormTag =$('.fileForm .attachFile').eq(0).clone().val('');
		$('.fileForm').append(fileFormTag);
		formCount++;
	})
	
	$('.fileForm .del').click(function(){
		if(formCount<=1){
			alert('1개이하로는 삭제 불가')
			return;
		}    
		
		$('.fileForm .attachFile').eq(formCount-1).remove();
		formCount--;
	})
	
})