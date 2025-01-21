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
    },
    TABLE:{
    	BODY: '.datatable tbody'
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
// 생산계획 목록 비동기 업데이트
function updatePlanList(planList) {
    console.log('Updating plan list:', planList);
    
    const tbody = $(SELECTORS.TABLE.BODY);
    tbody.empty(); // 기존 테이블 내용을 비움

    // 새로운 데이터로 테이블 rows 생성
    planList.forEach(plan => {
        const row = $('<tr>').append(
            $('<td>').text(plan.planNumber || '값이 없습니다'),
            $('<td>').text(plan.priority || '값이 없습니다'),
            $('<td>').text(plan.planType || '값이 없습니다'),
            $('<td>').text(plan.planStartDate || '값이 없습니다'),
            $('<td>').text(plan.planEndDate || '값이 없습니다'),
            $('<td>').text(plan.productCode || '값이 없습니다'),
            $('<td>').text(plan.planQuantity || '값이 없습니다'),
            $('<td>').text(plan.remark || '값이 없습니다'),
            $('<td>').text(plan.createdBy || '값이 없습니다')
        );
        tbody.append(row);
    });
}
// 폼 유효성 체크
function validateForm(){
	let isValid = true;
	const today = new Date();
	today.setHours(0,0,0,0); // Date 날짜의 비교를 위해 시간부분 0으로 초기화
	
	//시작일자 체크 이전으로 설정 가능하게 할려면 필요없음
    const startDate = new Date($(SELECTORS.FORM.START_DATE).val());
    if (startDate < today) {
        alert('시작일자는 현재 날짜보다 이전일 수 없습니다.');
        isValid = false;
        return isValid;
    }
    
	//종요일자 체크(시작일자보다 이전 설정 불가능)
    const endDate = new Date($(SELECTORS.FORM.END_DATE).val());
    if (endDate < startDate) {
        alert('종료일자는 시작일자보다 이전일 수 없습니다.');
        isValid = false;
        return isValid;
    }	
    
    // 계획수량 체크
    const quantity = parseInt($(SELECTORS.FORM.QUANTITY).val());
    if (isNaN(quantity) || quantity <= 0) {
        alert('계획수량은 0보다 커야 합니다.');
        isValid = false;
        return isValid;
    }
    
    return isValid;
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
        success: function(response) {
            console.log('FormData:', formData);
            console.log('Server response:', response);
            updatePlanList(response); // response가 직접 planList 배열
            alert("계획 등록 완료");
            resetForm();
        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
            alert('계획 등록에 실패했습니다.');
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
        if(validateForm()){
            submitPP();
        }
    });
    
    // 초기화
    $('#resetBtn').on('click', function() {
        if (confirm('모든 입력을 초기화하시겠습니까?')) {
            resetForm();
        }
    });
    // 실시간 날짜 유효성 검사
    // 시작일자
    $(SELECTORS.FORM.START_DATE).on('change', function() {
        const today = new Date();
        today.setHours(0, 0, 0, 0);
        const startDate = new Date($(this).val());
        
        if (startDate < today) {
            alert('시작일자는 현재 날짜보다 이전일 수 없습니다.');
            $(this).val(''); // 입력값 초기화
        } else {
            // 종료일자가 이미 입력되어 있다면 검사
            const endDate = new Date($(SELECTORS.FORM.END_DATE).val());
            if (endDate && endDate < startDate) {
                alert('종료일자는 시작일자보다 이전일 수 없습니다.');
                $(SELECTORS.FORM.END_DATE).val(''); // 종료일자 초기화
            }
        }
    });
    // 종료일자
    
    $(SELECTORS.FORM.END_DATE).on('change', function() {
        const startDate = new Date($(SELECTORS.FORM.START_DATE).val());
        const endDate = new Date($(this).val());
        
        if (startDate && endDate < startDate) {
            alert('종료일자는 시작일자보다 이전일 수 없습니다.');
            $(this).val(''); // 입력값 초기화
        }
    });

    // 실시간 수량 유효성 검사
    $(SELECTORS.FORM.QUANTITY).on('input', function() {
        const quantity = parseInt($(this).val());
        if (isNaN(quantity) || quantity <= 0) {
            $(this).addClass('is-invalid'); // Bootstrap의 invalid 스타일 적용
            // 에러 메시지 표시 (옵션)
            if (!$(this).next('.invalid-feedback').length) {
                $(this).after('<div class="invalid-feedback">계획수량은 0보다 커야 합니다.</div>');
            }
        } else {
            $(this).removeClass('is-invalid');
            $(this).next('.invalid-feedback').remove();
        }
    });
}