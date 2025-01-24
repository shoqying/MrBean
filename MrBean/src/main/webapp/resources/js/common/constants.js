export const API = {
    PLAN: {
        LIST: '/productionplan/api/plan',
        CREATE: '/productionplan/api/plan',
        DELETE: '/productionplan/api/plan/',
        NUMBER: '/productionplan/api/generatePlanNumber'
    },
    WORK: {
        LIST: '/workorders/api/work',
        CREATE: '/workorders/api/work',
        DELETE: '/workorders/api/work/',
        NUMBER: '/workorders/api/generateWorkNumber', 
        PLANS: '/workorders/api/plans'
    }
};

export const SELECTORS = {
    FORM: {
        START_DATE: '#planStartDate',
        END_DATE: '#planEndDate',
        QUANTITY: '#planQuantity',
        REMARK: '#remark',
        INSERT_BTN: '#insertBtn',
        RESET_BTN: '#resetBtn',
        
        PLAN_NUMBER: '#planNumber',
        PRIORITY: '#priority',
        PLAN_TYPE: '#planType',
        PRODUCT_CODE: '#productCode',
        PLSTATUS: '.plStatus',
        
        WORK_NUMBER: '#workNumber',
        WORK_PLAN_NO: '#workPlanNo',
        WORK_STATUS: '.workStatus',
        WORK_QUANTITY: '#workQuantity',
        WORK_PLAN_DATE: '#workPlanDate',
        WORK_REMARK: '#workRemark',
        WORK_SBTN: 'workStartBtn',
        WORK_CBTN: 'workCompletedBtn',
        WORK_SBTN: 'workStoppedBtn'
    },
    TABLE: {
        BODY: '.datatable tbody'
    },
    MODAL: {
        PLAN_SEARCH: '#planSearchModal',
        PLAN_LIST: '#planSearchResults',
        SEARCH_TYPE: '#searchPlanType', 
        SEARCH_PRODUCT: '#searchProductCode',
        SEARCH_BTN: '#searchPlanBtn'
    }
};