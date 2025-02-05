/**
 * constants.js
 * 애플리케이션에서 사용되는 모든 상수 정의
 */

// API 엔드포인트 정의
export const API = {
    PLAN: {
        LIST: '/productionplan/api/plan', // 생산계획 기본 목록 조회 (selectPP)
        CREATE: '/productionplan/api/plan', // 생산계획 등록
        DELETE: '/productionplan/api/plan/', // 생산계획 삭제
        NUMBER: '/productionplan/api/generatePlanNumber', // 생산계획 번호 생성
        STATUS: '/productionplan/api/plan' // 생산계획 상태 업데이트
    },
    WORK: {
        LIST: '/workorders/api/work', // 작업지시 목록 조회
        CREATE: '/workorders/api/work', // 작업지시 등록
        DELETE: '/workorders/api/work/', // 작업지시 삭제
        NUMBER: '/workorders/api/generateWorkNumber', // 작업지시 번호 생성
        MODAL_PLANS: '/workorders/api/plans', // 작업지시용 생산계획 목록 조회 (selectPPM)
        STATUS: '/workorders/api/work' // 작업지시 상태 업데이트
    }
};

// DOM 선택자 정의
export const SELECTORS = {
    FORM: {
        // Plan Form Selectors
        START_DATE: '#planStartDate',
        END_DATE: '#planEndDate',
        QUANTITY: '#planQuantity',
        REMARK: '#remark',
        PLAN_NUMBER: '#planNumber',
        PRIORITY: '#priority',
        PLAN_TYPE: '#planType',
        PRODUCT_CODE: '#productCode',
        PLSTATUS: '.plStatus',
        
        // Work Form Selectors
        WORK_NUMBER: '#workNumber',
        WORK_PLAN_NO: '#workPlanNo',
        WORK_STATUS: '.workStatus',
        WORK_QUANTITY: '#workQuantity',
        WORK_COMQUANTITY:'#completedQuantity',
        WORK_START_DATE: '#workStartDate',
        WORK_REMARK: '#workRemark',
        
        // Common Form Elements
        INSERT_BTN: '#insertBtn',
        RESET_BTN: '#resetBtn',
        
        // Additional Form Elements
        PLAN_QUANTITY_DISPLAY: '#planQuantityDisplay',
        WORK_ORDER_FORM: '#workOrderForm'
    },
    
    TABLE: {
        BODY: '.datatable tbody',
        DELETE_BTN: '.delete-btn',
        BUTTON_GROUP: '.btn-group[data-work-id]'
    },
    
    MODAL: {
        CONTAINER: '#planSearchModal',
        PLAN_LIST: '#planSearchResults',
        SEARCH_TYPE: '#searchPlanType',
        SEARCH_PRODUCT: '#searchProductCode',
        SEARCH_BTN: '#searchPlanBtn',
        CLOSE_BTN: '.btn-close'
    }
};

// 상태 코드 정의
export const STATUS = {
    PLAN: {
        PLANNED: 'PLANNED',
        WAITING: 'WAITING',
        IN_PROGRESS: 'IN_PROGRESS',
        COMPLETED: 'COMPLETED',
        STOPPED: 'STOPPED'
    },
    WORK: {
        WAITING: 'WAITING',
        IN_PROGRESS: 'IN_PROGRESS',
        COMPLETED: 'COMPLETED',
        STOPPED: 'STOPPED'
    }
};

// 기본값 정의
export const DEFAULTS = {
    PLAN: {
        PRIORITY: 'MEDIUM',
        PLAN_TYPE: '일일',
        STATUS: 'PLANNED'
    },
    WORK: {
        STATUS: 'WAITING',
        CREATED_BY: 'SYSTEM'
    }
};

// 메시지 정의
export const MESSAGES = {
    CONFIRM: {
        DELETE_PLAN: '이 생산계획을 삭제하시겠습니까?',
        DELETE_WORK: '이 작업계획을 삭제하시겠습니까?',
        RESET_FORM: '모든 입력을 초기화하시겠습니까?'
    },
    SUCCESS: {
        PLAN_CREATE: '생산계획이 등록되었습니다.',
        PLAN_DELETE: '생산계획이 삭제되었습니다.',
        WORK_CREATE: '작업지시가 등록되었습니다.',
        WORK_DELETE: '작업계획이 삭제되었습니다.'
    },
    ERROR: {
        NUMBER_GENERATE: '번호 생성에 실패했습니다.',
        PLAN_CREATE: '생산계획 등록에 실패했습니다.',
        PLAN_DELETE: '삭제에 실패했습니다.',
        WORK_CREATE: '작업지시 등록에 실패했습니다.',
        WORK_DELETE: '삭제에 실패했습니다.',
        STATUS_UPDATE: '상태 변경에 실패했습니다.',
        PLAN_REQUIRED: '생산계획을 선택해주세요.',
        DATE_REQUIRED: '작업예정일을 입력해주세요.'
    }
};