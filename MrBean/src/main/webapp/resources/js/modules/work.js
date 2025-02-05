/**
 * work.js
 * 작업지시 관련 모듈
 */
import { API, SELECTORS, STATUS, DEFAULTS, MESSAGES } from '../common/constants.js';
import { utils } from '../common/utils.js';

export const workModule = {
    // 상태 관리
    state: {
        isSubmitting: false,
        selectedPlan: null,
        lastUpdate: null
    },

    /**
     * 작업지시 번호 자동 생성
     */
    generateNumber: function() {
        utils.numberUtils.generateNumber('work', (number) => {
            $(SELECTORS.FORM.WORK_NUMBER).val(number);
        });
    },

    /**
     * 버튼 상태 반환
     */
    getButtonStates: function(status) {
        return {
            start: [STATUS.WORK.WAITING, STATUS.WORK.STOPPED].includes(status),
            complete: status === STATUS.WORK.IN_PROGRESS,
            stop: status === STATUS.WORK.IN_PROGRESS
        };
    },

    /**
     * 작업관리 버튼 그룹 생성
     */
    createButtonGroup: function(work) {
        const buttonStates = this.getButtonStates(work.workStatus);
        
        return $('<div>').addClass('btn-group').append(
            // 시작 버튼
            $('<button>')
                .addClass('btn btn-primary btn-sm')
                .toggleClass('active', work.workStatus === STATUS.WORK.IN_PROGRESS)
                .prop('disabled', !buttonStates.start)
                .html('<i class="bi bi-play-fill"></i> 시작')
                .on('click', (e) => {
                    e.stopPropagation();
                    this.updateWorkStatus(work.workId, STATUS.WORK.IN_PROGRESS);
                }),
            
            // 완료 버튼
            $('<button>')
                .addClass('btn btn-success btn-sm')
                .toggleClass('active', work.workStatus === STATUS.WORK.COMPLETED)
                .prop('disabled', !buttonStates.complete)
                .html('<i class="bi bi-check-lg"></i> 완료')
                .on('click', (e) => {
                    e.stopPropagation();
                    this.updateWorkStatus(work.workId, STATUS.WORK.COMPLETED);
                }),
                
            // 중지 버튼
            $('<button>')
                .addClass('btn btn-danger btn-sm')
                .toggleClass('active', work.workStatus === STATUS.WORK.STOPPED)
                .prop('disabled', !buttonStates.stop)
                .html('<i class="bi bi-stop-fill"></i> 중지')
                .on('click', (e) => {
                    e.stopPropagation();
                    this.updateWorkStatus(work.workId, STATUS.WORK.STOPPED);
                })
        );
    },

    /**
     * 생산계획 선택 처리
     */
    selectPlan: function(plan) {
        if (!plan) return;
        
        this.state.selectedPlan = plan;
        $(SELECTORS.FORM.WORK_PLAN_NO).val(plan.planNumber);
        $(SELECTORS.FORM.PLAN_TYPE).val(plan.planType);
        $(SELECTORS.FORM.PRODUCT_CODE).val(plan.productCode);
        $(SELECTORS.FORM.PLAN_QUANTITY_DISPLAY).text(plan.planQuantity);
    },

    /**
     * 폼 입력값 검증
     */
    validateForm: function() {
        if(!this.state.selectedPlan) {
            alert(MESSAGES.ERROR.PLAN_REQUIRED);
            return false;
        }

        const workDate = $(SELECTORS.FORM.WORK_START_DATE).val();
        if(!workDate) {
            alert(MESSAGES.ERROR.DATE_REQUIRED);
            return false;
        }

        const quantity = parseInt($(SELECTORS.FORM.WORK_QUANTITY).val());
        return utils.numberUtils.validateQuantity(quantity, this.state.selectedPlan.planQuantity);
    },

    /**
     * 작업지시 등록
     */
    submit: function() {
        if(this.state.isSubmitting || !this.validateForm()) return;

        this.state.isSubmitting = true;
        $(SELECTORS.FORM.INSERT_BTN).prop('disabled', true);

        const formData = {
            workOrderNo: $(SELECTORS.FORM.WORK_NUMBER).val(),
            planId: this.state.selectedPlan.planId,
            workPlanNo: this.state.selectedPlan.planNumber,
            workStartDate: $(SELECTORS.FORM.WORK_START_DATE).val(),
            workQuantity: parseInt($(SELECTORS.FORM.WORK_QUANTITY).val()),
            workRemark: $(SELECTORS.FORM.WORK_REMARK).val(),
            workCreatedBy: DEFAULTS.WORK.CREATED_BY,
            workStatus: STATUS.WORK.WAITING,  // 명시적으로 WAITING 상태 설정
            shouldUpdatePlan: true  // plan 상태도 함께 업데이트
        };

        $.ajax({
            url: API.WORK.CREATE,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: (response) => {
                this.updateList(response);
                alert(MESSAGES.SUCCESS.WORK_CREATE);
                this.resetForm();
            },
            error: () => {
                alert(MESSAGES.ERROR.WORK_CREATE);
            },
            complete: () => {
                this.state.isSubmitting = false;
                $(SELECTORS.FORM.INSERT_BTN).prop('disabled', false);
            }
        });
    },

    /**
     * 작업상태 업데이트
     */
    updateWorkStatus: function(workId, status) {
        // 해당 작업의 planId를 찾기
        const workRow = $(`[data-work-id="${workId}"]`).closest('tr');
        const planNo = workRow.find('td:eq(1)').text();  // 두 번째 열의 planNo 가져오기
        const planId = workRow.data('plan-id');  // 행에 planId 데이터 추가

        const data = {
            workId: workId,
            workStatus: status,
            workPlanNo: planNo,         // 생산계획 번호 추가
            planId: planId,           // planId 추가
            shouldUpdatePlan: true      // plan 상태 업데이트 플래그
        };

        $.ajax({
            url: `${API.WORK.STATUS}/${workId}/status`,
            type: 'PATCH',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: (response) => {
                this.updateList(response);
                console.log('Work and Plan status updated:', status);
                console.log('data:', data);
            },
            error: () => {
                console.error('Failed to update status');
                alert(MESSAGES.ERROR.STATUS_UPDATE);
            }
        });
    },



    /**
     * 작업지시 삭제
     */
    delete: function(workId) {
        if(!workId || !confirm(MESSAGES.CONFIRM.DELETE_WORK)) return;
        
        $.ajax({
            url: `${API.WORK.DELETE}${workId}`,
            type: 'DELETE',
            success: (response) => {
                this.updateList(response);
                alert(MESSAGES.SUCCESS.WORK_DELETE);
            },
            error: (xhr, status, error) => {
                console.error('Failed to delete work:', error);
                alert(MESSAGES.ERROR.WORK_DELETE);
            }
        });
    },

    /**
     * 작업지시 목록 업데이트
     */
    updateList: function(workList) {
        if (!Array.isArray(workList)) {
            console.error('Invalid work list data:', workList);
            return;
        }

        const tbody = $(SELECTORS.TABLE.BODY);
        tbody.empty();

        workList.forEach(work => {
            const row = this.createWorkRow(work);
            tbody.append(row);
        });

        this.state.lastUpdate = new Date();
    },

    /**
     * 작업지시 행 생성 
     */
    createWorkRow: function(work) {
        return $('<tr>')
        .data('plan-id', work.planId)    
        .append(
            $('<td>').text(work.workOrderNo || ''),
            $('<td>').text(work.workPlanNo || ''),
            $('<td>').text(utils.dateUtils.formatDate(work.workStartDate) || ''),
            $('<td>').text(work.workQuantity || ''),
            $('<td>').text(work.completedQuantity || ''),
            $('<td>').append(
                $('<span>')
                    .addClass('badge ' + utils.statusUtils.getBadgeClass(work.workStatus))
                    .text(utils.statusUtils.getDisplayName(work.workStatus, 'work'))
            ),
            $('<td>').text(work.workRemark || ''),
            $('<td>').text(work.workCreatedBy || ''),
            $('<td>').append(this.createButtonGroup(work)),
            $('<td>').append(
                $('<button>')
                    .addClass('btn btn-danger btn-sm ' + SELECTORS.TABLE.DELETE_BTN.slice(1))
                    .data('work-id', work.workId)
                    .html('<i class="bi bi-trash"></i>')
                    .on('click', (e) => {
                        e.stopPropagation();
                        this.delete(work.workId);
                    })
            )
        );
    },

    /**
     * 폼 초기화
     */
    resetForm: function() {
        this.state.selectedPlan = null;
        
        const defaultValues = {
            workStatus: DEFAULTS.WORK.STATUS
        };
        
        utils.formUtils.resetForm(SELECTORS.FORM.WORK_ORDER_FORM, defaultValues);
        $(SELECTORS.FORM.PLAN_QUANTITY_DISPLAY).text('0');
        
        this.generateNumber();
    },

    /**
     * 이벤트 리스너 설정
     */
    setupEventListeners: function() {
        // 등록 버튼 이벤트
        utils.eventUtils.bindSafe(SELECTORS.FORM.INSERT_BTN, 'click', 'work', () => {
            this.submit();
        });

        // 초기화 버튼 이벤트
        utils.eventUtils.bindSafe(SELECTORS.FORM.RESET_BTN, 'click', 'work', () => {
            if(confirm(MESSAGES.CONFIRM.RESET_FORM)) {
                this.resetForm();
            }
        });

        // 폼 제출 방지
        utils.eventUtils.bindSafe(SELECTORS.FORM.WORK_ORDER_FORM, 'submit', 'work', (e) => {
            e.preventDefault();
            return false;
        });

        // 수량 입력 validation
        utils.eventUtils.bindSafe(SELECTORS.FORM.WORK_QUANTITY, 'input', 'work', (e) => {
            const quantity = parseInt($(e.target).val());
            const maxQuantity = this.state.selectedPlan ? this.state.selectedPlan.planQuantity : null;
            let isValid = true;
            let message = '';

            if (!quantity || quantity <= 0) {
                isValid = false;
                message = '수량은 0보다 커야 합니다.';
            } else if (maxQuantity && quantity > maxQuantity) {
                isValid = false;
                message = `수량은 ${maxQuantity}를 초과할 수 없습니다.`;
            }

            utils.formUtils.showValidationFeedback(e.target, isValid, message);
        });
    },

    /**
     * 작업관리 버튼 초기화
     */
    initializeButtons: function() {
        $(SELECTORS.TABLE.BUTTON_GROUP).each((_, group) => {
            const $group = $(group);
            const workId = $group.data('work-id');
            const workStatus = $group.data('work-status');
            
            $group.empty().append(
                this.createButtonGroup({
                    workId: workId,
                    workStatus: workStatus
                })
            );
        });

        $(SELECTORS.TABLE.DELETE_BTN).each((_, btn) => {
            const $btn = $(btn);
            const workId = $btn.data('work-id');
            utils.eventUtils.bindSafe($btn, 'click', 'work', (e) => {
                e.stopPropagation();
                this.delete(workId);
            });
        });
    },

    /**
     * 모듈 초기화
     */
    init: function() {
        this.generateNumber();
        this.setupEventListeners();
        this.initializeButtons();
        console.log('Work module initialized');
    }
};