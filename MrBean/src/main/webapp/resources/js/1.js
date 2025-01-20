/**
 * 
 */

$(document).ready(function(){
//	document.getElementById('').value= new Date()
	generateNumber();
	
	
	
	
	
});

// 함수~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
function generateNumber(){
	$.ajax({
		url:'/mrbean/productionplan/generatePlanNumber',
		type:'GET',
		dataType:'text',
		success: function(ProductPlanNo){
			if(ProductPlanNo){
				$('#ProductPlanNo').val(ProductPlanNo.trim())
			} else{
				alert('번호 생성 실패');
			}
		}
	});
	
	
	
}