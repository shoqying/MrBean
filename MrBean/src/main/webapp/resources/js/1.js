
$(document).ready(function(){
//	document.getElementById('').value= new Date()
	generateNumber();
	
	
	
	
	
});

/**
 *  함수 정의
 * 
 */
function generateNumber(){
	console.log("generateNumber 호출");
	$.ajax({
		url:'/productionplan/generatePlanNumber',
		type:'GET',
		dataType:'text',
		success: function(ProductPlanNo){
			console.log("ProductPlanNo:", ProductPlanNo);
			if(ProductPlanNo){
				$('#ProductPlanNo').val(ProductPlanNo.trim())
			} else{
				alert('번호 생성 실패');
			}
		}
	});
}

function submitPP(){
	const formData = {
			planNumber:$('#planNumber').val(),
			
	};//forData
	$.ajax({
		
		
	});//ajax
	
}//submitPP


/**
 * 이벤트 리스너 설정
 */

function setupEventListeners(){
	// 폼 제출
	$('').on('submit', function(){
		e.preventDefault();
		if(validateForm()){
			submitproductionPlan();
			submitPP();
		}
		
	});
	
}
