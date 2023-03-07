$(function(){
	let formCount =1;
	var formTag =$('.expned .expendForm').clone();
	$('.addForm .add').click(function(){
		
		if(formCount>=10){
			alert('최대 10개까지입니다')
			return;
		}

		let add = "";
		add += '<tr class="expendForm">'
		add += '<td><input type="text" name="expendDTO['
		add += formCount
		add += '].name" class="form-control">'
		add += '<form:errors path="expendDTO.expendDTO['
		add += formCount
		add += '].name" element="div" class="input_error"/>'
		add += '<input type="hidden" name="expendDTO['
		add += formCount
		add += '].state" class="form-control" value="행정">'
		add += '<input type="hidden" name="expendDTO['
		add += formCount
		add += '].kind" class="form-control" value="expend">'
		add += '</td>'
		
		add += '<td><input type="text" name="expendDTO['
		add += formCount
		add += '].expend" class="form-control"></td>'
		add += '<form:errors path="expendDTO.expendDTO['
		add += formCount
		add += '].expend" element="div" class="input_error"/>'		
		add += '<td><input type="text" name="expendDTO['
		add += formCount
		add += '].count" class="form-control"></td>'
		add += '<form:errors path="expendDTO.expendDTO['
		add += formCount
		add += '].count" element="div" class="input_error"/>'		
		add += '</tr>'
			
		
		$('.expend').append(add);
		formCount++;
	})
	
	$('.addForm .del').click(function(){
		if(formCount<=1){
			alert('1개이하로는 삭제 불가')
			return;
		} 
		
		$('.expend .expendForm').eq(formCount-1).remove();
		formCount--;
		
	})
	
})