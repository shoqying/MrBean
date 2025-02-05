/**
 * plan.js
 * 생산계획 관련 모듈
 */
import { API, SELECTORS } from '../common/constants.js';
import { utils } from '../common/utils.js';

export const planModule = {
    // 폼 제출 중복 방지 플래그
    isSubmitting: false,

    /**
     * 상태 코드를 표시용 텍스트로 변환
     * @param {string} status - 상태 코드
     */
    getStatusDisplayName: function(status) {
        const statusNames = {
            'PLANNED': 'PLANNED',
            'IN_PROGRESS': 'IN_PROGRESS',
            'COMPLETED': 'COMPLETED',
            'CANCELLED': 'CANCELLED'
        };
        return statusNames[status] || status;
    },

    /**
     * 생산계획 번호 자동 생성
     */
    generateNumber: function() {
        $.ajax({
            url: API.PLAN.NUMBER,
            type: 'GET',
            success: function(planNumber) {
                if(planNumber) {
                    $(SELECTORS.FORM.PLAN_NUMBER).val(planNumber.trim());
                }
            },
            error: function() {
                alert('번호 생성에 실패했습니다.');
            }
        });
    },
    
    /**
     * 폼 입력값 검증
     */
    validateForm: function() {
        const startDate = $(SELECTORS.FORM.START_DATE).val();
        const endDate = $(SELECTORS.FORM.END_DATE).val();
        if(!utils.validateDates(startDate, endDate)) return false;

        const quantity = $(SELECTORS.FORM.QUANTITY).val();
        if(!utils.validateQuantity(quantity)) return false;

        return true;
    },

    /**
     * 생산계획 등록
     */
    submit: function() {
        if(this.isSubmitting || !this.validateForm()) return;

        this.isSubmitting = true;
        $(SELECTORS.FORM.INSERT_BTN).prop('disabled', true);

        const formData = {
            planNumber: $(SELECTORS.FORM.PLAN_NUMBER).val(),
            planType: $(SELECTORS.FORM.PLAN_TYPE).val(),
            planStartDate: $(SELECTORS.FORM.START_DATE).val(),
            planEndDate: $(SELECTORS.FORM.END_DATE).val(),
            productCode: $(SELECTORS.FORM.PRODUCT_CODE).val(),
            planQuantity: parseInt($(SELECTORS.FORM.QUANTITY).val()),
            remark: $(SELECTORS.FORM.REMARK).val(),
            priority: $(SELECTORS.FORM.PRIORITY).val(),
            plStatus: $(SELECTORS.FORM.PLSTATUS).val(),
            createdBy: "SYSTEM"
        };

        $.ajax({
            url: API.PLAN.CREATE,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                this.updateList(response);
                alert("생산계획이 등록되었습니다.");
                this.resetForm();
            }.bind(this),
            error: function() {
                alert('생산계획 등록에 실패했습니다.');
            },
            complete: function() {
                this.isSubmitting = false;
                $(SELECTORS.FORM.INSERT_BTN).prop('disabled', false);
            }.bind(this)
        });
    },
    
    /**
     * 생산계획 상태 업데이트
     * @param {number} planId - 계획 ID
     * @param {string} plStatus - 변경할 상태
     */
    updatePlanStatus: function(planId, plStatus) {
        $.ajax({
            url: API.PLAN.STATUS + '/' + planId + '/status',
            type: 'PATCH',
            contentType: 'application/json',
            data: JSON.stringify({ plStatus: plStatus }),
            success: function(response) {
                this.updateList(response);
                console.log('계획 상태 업데이트 완료:', plStatus);
            }.bind(this),
            error: function() {
                console.error('계획 상태 변경 실패');
            }
        });
    },

    /**
     * 생산계획 삭제
     * @param {number} planId - 삭제할 계획 ID
     */
    delete: function(planId) {
        if(!confirm('이 생산계획을 삭제하시겠습니까?')) return;
        
        $.ajax({
            url: API.PLAN.DELETE + planId,
            type: 'DELETE',
            success: function(response) {
                this.updateList(response);
                alert('생산계획이 삭제되었습니다.');
            }.bind(this),
            error: function() {
                alert('삭제에 실패했습니다.');
            }
        });
    },

    /**
     * 생산계획 목록 업데이트
     * @param {Array} planList - 계획 목록 데이터
     */
    updateList: function(planList) {
        const tbody = $(SELECTORS.TABLE.BODY);
        tbody.empty();
        const self = this;

        planList.forEach(function(plan) {
            const row = $('<tr>').append(
                $('<td>').text(plan.planNumber || ''),
                $('<td>').text(plan.priority || ''),
                $('<td>').text(plan.planType || ''),
                $('<td>').text(utils.formatDate(plan.planStartDate) || ''),
                $('<td>').text(utils.formatDate(plan.planEndDate) || ''),
                $('<td>').append(
                    $('<span>')
                        .addClass('badge ' + self.getStatusBadgeClass(plan.plStatus))
                        .text(self.getStatusDisplayName(plan.plStatus))
                ),
                $('<td>').text(plan.productCode || ''),
                $('<td>').text(plan.planQuantity || ''),
                $('<td>').text(plan.remark || ''),
                $('<td>').text(plan.createdBy || ''),
                $('<td>').append(
                    $('<button>')
                        .addClass('btn btn-danger btn-sm')
                        .html('<i class="bi bi-trash"></i>')
                        .on('click', function() {
                            self.delete(plan.planId);
                        })
                )
            );
            tbody.append(row);
        });
    },
    
    /**
     * 상태별 배지 클래스 반환
     * @param {string} status - 상태 코드
     */
    getStatusBadgeClass: function(status) {
        const statusClasses = {
            'PLANNED': 'bg-primary',
            'IN_PROGRESS': 'bg-warning',
            'COMPLETED': 'bg-success',
            'CANCELLED': 'bg-secondary'
        };
        return statusClasses[status] || 'bg-light';
    },
    
    /**
     * 폼 초기화
     */
    resetForm: function() {
        $(SELECTORS.FORM.PLAN_NUMBER).val('');
        $(SELECTORS.FORM.PRIORITY).val('MEDIUM');
        $(SELECTORS.FORM.PLAN_TYPE).val('일일');
        $(SELECTORS.FORM.START_DATE).val('');
        $(SELECTORS.FORM.END_DATE).val('');
        $(SELECTORS.FORM.PRODUCT_CODE).val('케냐');
        $(SELECTORS.FORM.QUANTITY).val('');
        $(SELECTORS.FORM.REMARK).val('');
        $(SELECTORS.FORM.PLSTATUS).val('PLANNED');
        
        this.generateNumber();
    },
    
    /**
     * 이벤트 리스너 설정
     */
    setupEventListeners: function() {
        const self = this;
        
        // 이벤트 리스너 중복 방지를 위해 이전 이벤트 제거
        $(SELECTORS.FORM.INSERT_BTN).off('click').on('click', function() {
            self.submit();
        });

        $(SELECTORS.FORM.RESET_BTN).off('click').on('click', function() {
            if(confirm('모든 입력을 초기화하시겠습니까?')) {
                self.resetForm();
            }
        });

        // 날짜 변경 이벤트
        $(SELECTORS.FORM.START_DATE).off('change').on('change', function() {
            const endDate = $(SELECTORS.FORM.END_DATE).val();
            if(endDate) utils.validateDates($(this).val(), endDate);
        });

        $(SELECTORS.FORM.END_DATE).off('change').on('change', function() {
            const startDate = $(SELECTORS.FORM.START_DATE).val();
            if(startDate) utils.validateDates(startDate, $(this).val());
        });

        // 수량 입력 이벤트
        $(SELECTORS.FORM.QUANTITY).off('input').on('input', function() {
            const quantity = parseInt($(this).val());
            if(isNaN(quantity) || quantity <= 0) {
                $(this).addClass('is-invalid');
                if(!$(this).next('.invalid-feedback').length) {
                    $(this).after('<div class="invalid-feedback">수량은 0보다 커야 합니다.</div>');
                }
            } else {
                $(this).removeClass('is-invalid');
                $(this).next('.invalid-feedback').remove();
            }
        });
    },

    /**
     * 모듈 초기화
     */
    init: function() {
        this.generateNumber();
        this.setupEventListeners();
    }
};