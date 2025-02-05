/**
 * work.js
 * 작업지시 관련 모듈
 */
import { API, SELECTORS } from '../common/constants.js';
import { utils } from '../common/utils.js';

export const workModule = {
    // 상태 관리
    isSubmitting: false,
    selectedPlan: null,

    /**
     * 상태 코드를 표시용 텍스트로 변환
     */
    getStatusDisplayName: function(status) {
        const statusNames = {
            'WAITING': 'WAITING',
            'IN_PROGRESS': 'IN_PROGRESS',
            'COMPLETED': 'COMPLETED',
            'STOPPED': 'STOPPED'
        };
        return statusNames[status] || status;
    },

    /**
     * 상태별 배지 클래스 반환
     */
    getStatusBadgeClass: function(status) {
        const statusClasses = {
            'WAITING': 'bg-secondary',
            'IN_PROGRESS': 'bg-warning',
            'COMPLETED': 'bg-success',
            'STOPPED': 'bg-danger'
        };
        return statusClasses[status] || 'bg-light';
    },

    /**
     * 상태별 버튼 활성화 여부 반환
     */
    getButtonStates: function(status) {
        return {
            start: ['WAITING', 'STOPPED'].includes(status),
            complete: status === 'IN_PROGRESS',
            stop: status === 'IN_PROGRESS'
        };
    },

    /**
     * 작업지시 번호 자동 생성
     */
    generateNumber: function() {
        $.ajax({
            url: API.WORK.NUMBER,
            type: 'GET',
            success: function(workNumber) {
                if(workNumber) {
                    $(SELECTORS.FORM.WORK_NUMBER).val(workNumber.trim());
                }
            },
            error: function() {
                alert('작업지시 번호 생성에 실패했습니다.');
            }
        });
    },

    /**
     * 작업관리 버튼 그룹 생성
     */
    createButtonGroup: function(work) {
        const buttonStates = this.getButtonStates(work.workStatus);
        const self = this;
        
        return $('<div>').addClass('btn-group').append(
            $('<button>')
                .addClass('btn btn-primary btn-sm')
                .toggleClass('active', work.workStatus === 'IN_PROGRESS')
                .prop('disabled', !buttonStates.start)
                .html('<i class="bi bi-play-fill"></i> 시작')
                .on('click', function() {
                    self.updateWorkStatus(work.workId, 'IN_PROGRESS');
                }),
            
            $('<button>')
                .addClass('btn btn-success btn-sm')
                .toggleClass('active', work.workStatus === 'COMPLETED')
                .prop('disabled', !buttonStates.complete)
                .html('<i class="bi bi-check-lg"></i> 완료')
                .on('click', function() {
                    self.updateWorkStatus(work.workId, 'COMPLETED');
                }),
                
            $('<button>')
                .addClass('btn btn-danger btn-sm')
                .toggleClass('active', work.workStatus === 'STOPPED')
                .prop('disabled', !buttonStates.stop)
                .html('<i class="bi bi-stop-fill"></i> 중지')
                .on('click', function() {
                    self.updateWorkStatus(work.workId, 'STOPPED');
                })
        );
    },

    /**
     * 생산계획 선택 처리
     */
    selectPlan: function(plan) {
        this.selectedPlan = plan;
        $(SELECTORS.FORM.WORK_PLAN_NO).val(plan.planNumber);
        $(SELECTORS.FORM.PLAN_TYPE).val(plan.planType);
        $(SELECTORS.FORM.PRODUCT_CODE).val(plan.productCode);
        $(SELECTORS.FORM.PLAN_QUANTITY_DISPLAY).text(plan.planQuantity);
    },

    /**
     * 폼 입력값 검증
     */
    validateForm: function() {
        if(!this.selectedPlan) {
            alert('생산계획을 선택해주세요.');
            return false;
        }

        const workDate = $(SELECTORS.FORM.WORK_PLAN_DATE).val();
        if(!workDate) {
            alert('작업예정일을 입력해주세요.');
            return false;
        }

        const quantity = parseInt($(SELECTORS.FORM.WORK_QUANTITY).val());
        return utils.validateQuantity(quantity, this.selectedPlan.planQuantity);
    },

    /**
     * 작업지시 등록
     */
    submit: function() {
        if(this.isSubmitting || !this.validateForm()) return;

        this.isSubmitting = true;
        $(SELECTORS.FORM.INSERT_BTN).prop('disabled', true);

        const formData = {
            workOrderNo: $(SELECTORS.FORM.WORK_NUMBER).val(),
            planId: this.selectedPlan.planId,
            workPlanNo: this.selectedPlan.planNumber,
            workPlanDate: $(SELECTORS.FORM.WORK_PLAN_DATE).val(),
            workQuantity: parseInt($(SELECTORS.FORM.WORK_QUANTITY).val()),
            workStatus: $(SELECTORS.FORM.WORK_STATUS).val(),
            workRemark: $(SELECTORS.FORM.WORK_REMARK).val(),
            workCreatedBy: "SYSTEM"
        };

        $.ajax({
            url: API.WORK.CREATE,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                this.updateList(response);
                alert("작업지시가 등록되었습니다.");
                this.resetForm();
            }.bind(this),
            error: function() {
                alert('작업지시 등록에 실패했습니다.');
            },
            complete: function() {
                this.isSubmitting = false;
                $(SELECTORS.FORM.INSERT_BTN).prop('disabled', false);
            }.bind(this)
        });
    },

    /**
     * 작업상태 업데이트 및 생산계획 상태 연동
     */
    updateWorkStatus: function(workId, status) {
        const self = this;
        $.ajax({
            url: API.WORK.STATUS + '/' + workId + '/status',
            type: 'PATCH',
            contentType: 'application/json',
            data: JSON.stringify({ workStatus: status }),
            success: function(response) {
                // 현재 작업의 planId 찾기
                const currentWork = response.find(work => work.workId === workId);

                // 같은 planId를 가진 모든 작업의 상태 확인
                if(currentWork && currentWork.planId) {
                    const sameWorks = response.filter(work => work.planId === currentWork.planId);
                    const inProgressExists = sameWorks.some(work => work.workStatus === 'IN_PROGRESS');
                    const allCompleted = sameWorks.every(work => work.workStatus === 'COMPLETED');

                    // Plan 상태 결정
                    let planStatus;
                    if(allCompleted) {
                        planStatus = 'COMPLETED';
                    } else if(inProgressExists) {
                        planStatus = 'IN_PROGRESS';
                    } else {
                        planStatus = 'PLANNED';
                    }

                    // Plan 상태 업데이트 호출
                    if(window.planModule) {
                        window.planModule.updatePlanStatus(currentWork.planId, planStatus);
                    }
                }

                self.updateList(response);
            },
            error: function() {
                alert('상태 변경에 실패했습니다.');
            }
        });
    },

    /**
     * 작업지시 삭제
     */
    delete: function(workId) {
        if(!confirm('이 작업계획을 삭제하시겠습니까?')) return;
        
        $.ajax({
            url: API.WORK.DELETE + workId,
            type: 'DELETE',
            success: function(response) {
                this.updateList(response);
                alert('작업계획이 삭제되었습니다.');
            }.bind(this),
            error: function() {
                alert('삭제에 실패했습니다.');
            }
        });
    },

    /**
     * 작업지시 목록 업데이트
     */
    updateList: function(workList) {
        const tbody = $(SELECTORS.TABLE.BODY);
        tbody.empty();
        const self = this;

        workList.forEach(function(work) {
            const row = $('<tr>').append(
                $('<td>').text(work.workOrderNo || ''),
                $('<td>').text(work.workPlanNo || ''),
                $('<td>').text(utils.formatDate(work.workPlanDate) || ''),
                $('<td>').text(work.workQuantity || ''),
                $('<td>').append(
                    $('<span>')
                        .addClass('badge ' + self.getStatusBadgeClass(work.workStatus))
                        .text(self.getStatusDisplayName(work.workStatus))
                ),
                $('<td>').text(work.workRemark || ''),
                $('<td>').text(work.workCreatedBy || ''),
                $('<td>').append(self.createButtonGroup(work)),
                $('<td>').append(
                    $('<button>')
                        .addClass('btn btn-danger btn-sm ' + SELECTORS.TABLE.DELETE_BTN.slice(1))
                        .data('work-id', work.workId)
                        .html('<i class="bi bi-trash"></i>')
                        .on('click', function() {
                            self.delete(work.workId);
                        })
                )
            );
            tbody.append(row);
        });
    },

    /**
     * 폼 초기화
     */
    resetForm: function() {
        this.selectedPlan = null;
        $(SELECTORS.FORM.WORK_NUMBER).val('');
        $(SELECTORS.FORM.WORK_PLAN_NO).val('');
        $(SELECTORS.FORM.WORK_PLAN_DATE).val('');
        $(SELECTORS.FORM.WORK_QUANTITY).val('');
        $(SELECTORS.FORM.WORK_REMARK).val('');
        $(SELECTORS.FORM.WORK_STATUS).val('WAITING');
        
        $(SELECTORS.FORM.PLAN_TYPE).val('');
        $(SELECTORS.FORM.PRODUCT_CODE).val('');
        $(SELECTORS.FORM.PLAN_QUANTITY_DISPLAY).text('0');
        
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

        $(SELECTORS.FORM.WORK_ORDER_FORM).off('submit').on('submit', function(e) {
            e.preventDefault();
            return false;
        });
    },

    /**
     * 작업관리 버튼 초기화
     */
    initializeButtons: function() {
        const self = this;
        $(SELECTORS.TABLE.BUTTON_GROUP).each(function() {
            const workId = $(this).data('work-id');
            const workStatus = $(this).data('work-status');
            
            $(this).empty().append(
                self.createButtonGroup({
                    workId: workId,
                    workStatus: workStatus
                })
            );
        });

        $(SELECTORS.TABLE.DELETE_BTN).each(function() {
            const workId = $(this).data('work-id');
            $(this).off('click').on('click', function() {
                self.delete(workId);
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
    }
};