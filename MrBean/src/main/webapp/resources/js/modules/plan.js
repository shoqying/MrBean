/**
 * plan.js
 * 생산계획 관련 모듈
 */
import { API, SELECTORS, STATUS, DEFAULTS, MESSAGES } from '../common/constants.js';
import { utils } from '../common/utils.js';

export const planModule = {
    // 상태 관리
    state: {
        isSubmitting: false,
        lastUpdate: null
    },

    /**
     * 생산계획 번호 자동 생성
     */
    generateNumber: function() {
        utils.numberUtils.generateNumber('plan', (number) => {
            $(SELECTORS.FORM.PLAN_NUMBER).val(number);
        });
    },
    
    /**
     * 폼 입력값 검증
     */
    validateForm: function() {
        const startDate = $(SELECTORS.FORM.START_DATE).val();
        const endDate = $(SELECTORS.FORM.END_DATE).val();
        if(!utils.dateUtils.validateDates(startDate, endDate)) return false;

        const quantity = $(SELECTORS.FORM.QUANTITY).val();
        if(!utils.numberUtils.validateQuantity(quantity)) return false;

        return true;
    },

    /**
     * 생산계획 등록
     */
    submit: function() {
        if(this.state.isSubmitting || !this.validateForm()) return;

        this.state.isSubmitting = true;
        $(SELECTORS.FORM.INSERT_BTN).prop('disabled', true);

        const formData = {
            planNumber: $(SELECTORS.FORM.PLAN_NUMBER).val(),
            planType: $(SELECTORS.FORM.PLAN_TYPE).val(),
            planStartDate: $(SELECTORS.FORM.START_DATE).val(),
            planEndDate: $(SELECTORS.FORM.END_DATE).val(),
            productCode: $(SELECTORS.FORM.PRODUCT_CODE).val(),
            planQuantity: parseInt($(SELECTORS.FORM.QUANTITY).val()),
            remark: $(SELECTORS.FORM.REMARK).val(),
            plStatus: $(SELECTORS.FORM.PLSTATUS).val(),
            createdBy: DEFAULTS.WORK.CREATED_BY
        };

        $.ajax({
            url: API.PLAN.CREATE,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: (response) => {
                this.updateList(response);
                alert(MESSAGES.SUCCESS.PLAN_CREATE);
                this.resetForm();
            },
            error: () => {
                alert(MESSAGES.ERROR.PLAN_CREATE);
            },
            complete: () => {
                this.state.isSubmitting = false;
                $(SELECTORS.FORM.INSERT_BTN).prop('disabled', false);
            }
        });
    },
    
    /**
     * 생산계획 상태 업데이트
     */
    updatePlanStatus: function(planId, plStatus) {
        $.ajax({
            url: `${API.PLAN.STATUS}/${planId}/status`,
            type: 'PATCH',
            contentType: 'application/json',
            data: JSON.stringify({ plStatus: plStatus }),
            success: (response) => {
                this.updateList(response);
                console.log('Plan status updated:', plStatus);
            },
            error: () => {
                console.error('Failed to update plan status');
                alert(MESSAGES.ERROR.STATUS_UPDATE);
            }
        });
    },

    /**
     * 생산계획 삭제
     */
    delete: function(planId) {
        if(!confirm(MESSAGES.CONFIRM.DELETE_PLAN)) return;
        
        $.ajax({
            url: `${API.PLAN.DELETE}${planId}`,
            type: 'DELETE',
            success: (response) => {
                this.updateList(response);
                alert(MESSAGES.SUCCESS.PLAN_DELETE);
            },
            error: () => {
                alert(MESSAGES.ERROR.PLAN_DELETE);
            }
        });
    },

    /**
     * 생산계획 목록 업데이트
     */
    updateList: function(planList) {
        const tbody = $(SELECTORS.TABLE.BODY);
        tbody.empty();

        planList.forEach(plan => {
            const row = this.createPlanRow(plan);
            tbody.append(row);
        });

        this.state.lastUpdate = new Date();
    },

    /**
     * 계획 행 생성
     */
    createPlanRow: function(plan) {
        return $('<tr>').append(
            $('<td>').text(plan.planNumber || ''),
            $('<td>').text(plan.planType || ''),
            $('<td>').text(utils.dateUtils.formatDate(plan.planStartDate) || ''),
            $('<td>').text(utils.dateUtils.formatDate(plan.planEndDate) || ''),
            $('<td>').append(
                $('<span>')
                    .addClass('badge ' + utils.statusUtils.getBadgeClass(plan.plStatus))
                    .text(utils.statusUtils.getDisplayName(plan.plStatus, 'plan'))
            ),
            $('<td>').text(plan.productCode || ''),
            $('<td>').text(plan.planQuantity || ''),
            $('<td>').text(plan.remark || ''),
            $('<td>').text(plan.createdBy || ''),
            $('<td>').append(
                $('<button>')
                    .addClass('btn btn-danger btn-sm')
                    .html('<i class="bi bi-trash"></i>')
                    .on('click', () => this.delete(plan.planId))
            )
        );
    },
    
    /**
     * 폼 초기화
     */
    resetForm: function() {
        // 기본값 정의
        const defaultValues = {
            planType: DEFAULTS.PLAN.PLAN_TYPE,
            plStatus: DEFAULTS.PLAN.STATUS
        };
        
        // 모든 입력 필드 초기화
        $(SELECTORS.FORM.START_DATE).val('');
        $(SELECTORS.FORM.END_DATE).val('');
        $(SELECTORS.FORM.QUANTITY).val('');
        $(SELECTORS.FORM.REMARK).val('');
        $(SELECTORS.FORM.PRODUCT_CODE).val('');
        
        // 기본값 설정
        $(SELECTORS.FORM.PLAN_TYPE).val(defaultValues.planType);
        $(SELECTORS.FORM.PLSTATUS).val(defaultValues.plStatus);
        
        // 계획번호 재생성
        this.generateNumber();
        
        // 유효성 검사 피드백 제거
        $('.is-invalid').removeClass('is-invalid');
        $('.invalid-feedback').remove();
    },

    /**
     * 이벤트 리스너 설정
     */
    setupEventListeners: function() {
        // 등록 버튼 이벤트
        utils.eventUtils.bindSafe(SELECTORS.FORM.INSERT_BTN, 'click', 'plan', () => {
            this.submit();
        });

        // 초기화 버튼 이벤트
        utils.eventUtils.bindSafe(SELECTORS.FORM.RESET_BTN, 'click', 'plan', () => {
            if(confirm(MESSAGES.CONFIRM.RESET_FORM)) {
                this.resetForm();
            }
        });

        // 날짜 입력 이벤트
        utils.eventUtils.bindSafe(SELECTORS.FORM.START_DATE, 'change', 'plan', () => {
            const endDate = $(SELECTORS.FORM.END_DATE).val();
            if(endDate) utils.dateUtils.validateDates($(SELECTORS.FORM.START_DATE).val(), endDate);
        });

        utils.eventUtils.bindSafe(SELECTORS.FORM.END_DATE, 'change', 'plan', () => {
            const startDate = $(SELECTORS.FORM.START_DATE).val();
            if(startDate) utils.dateUtils.validateDates(startDate, $(SELECTORS.FORM.END_DATE).val());
        });

        // 수량 입력 이벤트
        utils.eventUtils.bindSafe(SELECTORS.FORM.QUANTITY, 'input', 'plan', (e) => {
            const quantity = parseInt($(e.target).val());
            const isValid = !isNaN(quantity) && quantity > 0;
            utils.formUtils.showValidationFeedback(
                e.target,
                isValid,
                '수량은 0보다 커야 합니다.'
            );
        });
    },

    /**
     * 모듈 초기화
     */
    init: function() {
        this.generateNumber();
        this.setupEventListeners();
        console.log('Plan module initialized');
    }
};