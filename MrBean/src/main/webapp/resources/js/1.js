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
        SUBMIT_BTN: '#submitBtn',       // 계획등록 버튼
        RESET_BTN: '#resetBtn'          // 초기화 버튼
    }
};

$(document).ready(function(){
	console.log('jQuery loaded:', typeof $ !== 'undefined')
    generateNumber();
    setupEventListeners();
});

/**
 * 함수 정의
 */
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
                //resetForm(); 추후 추가
            } else {
                alert(response.message || '계획 등록에 실패');
            }
        }
    });
}

/**
 * 이벤트 리스너 설정
 */
function setupEventListeners(){
    $(SELECTORS.FORM.SUBMIT_BTN).on('submit', function(e){
        e.preventDefault();
        console.log("제출 클릭")
//        if(validateForm()){
//            submitPP();
//        }
        submitPP();
    });
    // 폼 자체의 submit 이벤트를 막아 새로고침 방지
    $(SELECTORS.FORM.CONTAINER).on('submit', function (e) {
        e.preventDefault(); // 폼의 기본 submit 동작 막기
        console.log("폼 제출 이벤트 발생");

        // validateForm() 함수 추가 가능
        submitPP(); // 데이터 전송 함수 호출
    });
}