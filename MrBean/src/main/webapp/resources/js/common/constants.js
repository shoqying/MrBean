/**
 * constants.js
 * API 엔드포인트와 DOM 선택자를 정의
 */

// API 엔드포인트 정의
export const API = {
    PLAN: {
        LIST: '/productionplan/api/plan',
        CREATE: '/productionplan/api/plan',
        DELETE: '/productionplan/api/plan/',
        NUMBER: '/productionplan/api/generatePlanNumber',
        STATUS: '/productionplan/api/plan'
    },
    WORK: {
        LIST: '/workorders/api/work',
        CREATE: '/workorders/api/work',
        DELETE: '/workorders/api/work/',  
        NUMBER: '/workorders/api/generateWorkNumber', 
        PLANS: '/workorders/api/plans',
        STATUS: '/workorders/api/work'
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
        WORK_PLAN_DATE: '#workPlanDate',
        WORK_REMARK: '#workRemark',
        
        // Common Selectors
        INSERT_BTN: '#insertBtn',
        RESET_BTN: '#resetBtn',
        
        // Additional Selectors
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


export const WORK_STATUS = {
    WAITING: 'WAITING',
    IN_PROGRESS: 'IN_PROGRESS',
    COMPLETED: 'COMPLETED',
    STOPPED: 'STOPPED'
};

export const PLAN_STATUS = {
    PLANNED: 'PLANNED',
    IN_PROGRESS: 'IN_PROGRESS',
    COMPLETED: 'COMPLETED',
    STOPPED: 'STOPPED'
};