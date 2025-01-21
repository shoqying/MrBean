// DOM 셀렉터 상수 정의
const SELECTORS = {
    FORM: {
        CONTAINER: '#productPlanForm',  // 전체 생산계획 폼
        PLAN_NUMBER: '#planNumber',     // 생산계획 번호 입력 필드
        PRIORITY: '#priority',          // 우선순위 선택
        PLAN_TYPE: '#planType',         // 계획종류 선택
        START_DATE: '#planStartDate',   // 시작일자 입력
        END_DATE: '#planEndDate',       // 종료일자 입력
        PRODUCT_CODE: '#productCode',   // 제품구분 선택
        QUANTITY: '#planQuantity',      // 계획수량 입력
        REMARK: '#remark',             // 비고 입력
        INSERT_BTN: '#intsertBtn',       // 계획등록 버튼
        RESET_BTN: '#resetBtn',          // 초기화 버튼
        a: '#a'
    }
};

/**
 * 페이지 시작시 실행
 * 
 */


$(document).ready(function(){
	console.log('jQuery loaded:', typeof $ !== 'undefined')
    // 주문번호 생성 요청
	generateNumber();
	// 이벤트 리스너 설정
    setupEventListeners();
});

/**
 * 함수 정의
 */

//주문 번호 생성
function generateNumber(){
    console.log("generateNumber 호출");
    $.ajax({
        url: '/productionplan/generatePlanNumber',
        type: 'GET',
        dataType: 'text',
        success: function(planNumber){
            console.log("planNumber:", planNumber);
            if(planNumber){
                $(SELECTORS.FORM.PLAN_NUMBER).val(planNumber.trim());
            } else {
                alert('번호 생성 실패');
            }
        }
    });
}
// 폼제출
function submitPP(){
    const formData = {
        planNumber: $(SELECTORS.FORM.PLAN_NUMBER).val(),
        planType: $(SELECTORS.FORM.PLAN_TYPE).val(),
        planStartDate: $(SELECTORS.FORM.START_DATE).val(),
        planEndDate: $(SELECTORS.FORM.END_DATE).val(),
        productCode: $(SELECTORS.FORM.PRODUCT_CODE).val(),
        planQuantity: $(SELECTORS.FORM.QUANTITY).val(),
        remark: $(SELECTORS.FORM.REMARK).val(),
        priority: $(SELECTORS.FORM.PRIORITY).val()
    };
    console.log('FormData:', JSON.stringify(formData));
    
    $.ajax({
        url: '/productionplan/plan',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(formData),
        success: function(response){
        	console.log('FormData:', formData);
            if (response.status === 'success'){
                alert("계획 등록 완료");
                resetForm(); // 폼 초기화
                //updatePlanList(response); // 서버에서 반환된 최신 목록으로 갱신 추가로 만들어야함
            } else {
                alert(response.message || '계획 등록에 실패');
            }
        }
    });
}


// 폼 초기화 입력필드 초기 복원
function resetForm(){
	// 폼 입력 필드들을 초기화
    $(SELECTORS.FORM.PLAN_NUMBER).val('');
    $(SELECTORS.FORM.PRIORITY).val('MEDIUM');
    $(SELECTORS.FORM.PLAN_TYPE).val('일일');
    $(SELECTORS.FORM.START_DATE).val('');
    $(SELECTORS.FORM.END_DATE).val('');
    $(SELECTORS.FORM.PRODUCT_CODE).val('케냐');
    $(SELECTORS.FORM.QUANTITY).val('');
    $(SELECTORS.FORM.REMARK).val('');
	
    // 주문번호 생성 요청
	generateNumber();
	
}

/**
 * 이벤트 리스너 설정
 */
function setupEventListeners(){
	
	// 폼 제출
    $(SELECTORS.FORM.INSERT_BTN).on('click', function(e){
        e.preventDefault();
        console.log("제출 클릭")
//        if(validateForm()){
//            submitPP();
//        }
        submitPP();
    });
    
    // 초기화
    $('#resetBtn').on('click', function() {
        if (confirm('모든 입력을 초기화하시겠습니까?')) {
            resetForm();
        }
    });

}