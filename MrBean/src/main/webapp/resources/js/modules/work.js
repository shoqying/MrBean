/**
 * work.js
 * 작업지시 관련 모듈
 */
import { API, SELECTORS } from '../common/constants.js';
import { utils } from '../common/utils.js';

export const workModule = {
    // 상태 관리
    state: {
        isSubmitting: false,
        selectedPlan: null,
        lastUpdate: null
    },

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
            start: ['WAITING', 'STOPPED'].indexOf(status) !== -1,
            complete: status === 'IN_PROGRESS',
            stop: status === 'IN_PROGRESS'
        };
    },

    /**
     * 작업지시 번호 자동 생성
     */
    generateNumber: function() {
        const self = this;
        $.ajax({
            url: API.WORK.NUMBER,
            type: 'GET',
            success: function(workNumber) {
                if(workNumber) {
                    $(SELECTORS.FORM.WORK_NUMBER).val(workNumber.trim());
                }
            },
            error: function(xhr, status, error) {
                console.error('작업지시 번호 생성 실패:', error);
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
                .on('click', function(e) {
                    e.stopPropagation();
                    self.updateWorkStatus(work.workId, 'IN_PROGRESS');
                }),
            
            $('<button>')
                .addClass('btn btn-success btn-sm')
                .toggleClass('active', work.workStatus === 'COMPLETED')
                .prop('disabled', !buttonStates.complete)
                .html('<i class="bi bi-check-lg"></i> 완료')
                .on('click', function(e) {
                    e.stopPropagation();
                    self.updateWorkStatus(work.workId, 'COMPLETED');
                }),
                
            $('<button>')
                .addClass('btn btn-danger btn-sm')
                .toggleClass('active', work.workStatus === 'STOPPED')
                .prop('disabled', !buttonStates.stop)
                .html('<i class="bi bi-stop-fill"></i> 중지')
                .on('click', function(e) {
                    e.stopPropagation();
                    self.updateWorkStatus(work.workId, 'STOPPED');
                })
        );
    },

    /**
     * 생산계획 선택 처리
     */
    selectPlan: function(plan) {
        if (!plan) {
            return;
        }
        
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
            alert('생산계획을 선택해주세요.');
            return false;
        }

        const workDate = $(SELECTORS.FORM.WORK_PLAN_DATE).val();
        if(!workDate) {
            alert('작업예정일을 입력해주세요.');
            return false;
        }

        const quantity = parseInt($(SELECTORS.FORM.WORK_QUANTITY).val());
        return utils.validateQuantity(quantity, this.state.selectedPlan.planQuantity);
    },

    /**
     * 작업지시 등록
     */
    submit: function() {
        if(this.state.isSubmitting || !this.validateForm()) {
            return;
        }

        this.state.isSubmitting = true;
        $(SELECTORS.FORM.INSERT_BTN).prop('disabled', true);

        const formData = {
            workOrderNo: $(SELECTORS.FORM.WORK_NUMBER).val(),
            planId: this.state.selectedPlan.planId,
            workPlanNo: this.state.selectedPlan.planNumber,
            workPlanDate: $(SELECTORS.FORM.WORK_PLAN_DATE).val(),
            workQuantity: parseInt($(SELECTORS.FORM.WORK_QUANTITY).val()),
            workStatus: $(SELECTORS.FORM.WORK_STATUS).val(),
            workRemark: $(SELECTORS.FORM.WORK_REMARK).val(),
            workCreatedBy: "SYSTEM"
        };

        const self = this;
        $.ajax({
            url: API.WORK.CREATE,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                self.updateList(response);
                alert("작업지시가 등록되었습니다.");
                self.resetForm();
            },
            error: function(xhr, status, error) {
                console.error('작업지시 등록 실패:', error);
                alert('작업지시 등록에 실패했습니다.');
            },
            complete: function() {
                self.state.isSubmitting = false;
                $(SELECTORS.FORM.INSERT_BTN).prop('disabled', false);
            }
        });
    },

    /**
     * 작업상태 업데이트 및 생산계획 상태 연동
     */
 
	 updateWorkStatus: function(workId, status) {
	     if (!workId || !status) {
	         return;
	     }
	     
	     const self = this;
	     $.ajax({
	         url: API.WORK.STATUS + '/' + workId + '/status',
	         type: 'PATCH',
	         contentType: 'application/json',
	         data: JSON.stringify({ workStatus: status }),
	         success: function(response) {
	             const currentWork = response.find(function(work) {
	                 return work.workId === workId;
	             });
	
	             if(currentWork && currentWork.planId) {
	                 const sameWorks = response.filter(function(work) {
	                     return work.planId === currentWork.planId;
	                 });
	                 
	                 // 작업상태에 따른 생산계획 상태 매핑
	                 let planStatus;
	                 switch(status) {
	                     case 'WAITING':
	                         planStatus = 'PLANNED';
	                         break;
	                     case 'IN_PROGRESS':
	                         planStatus = 'IN_PROGRESS';
	                         break;
	                     case 'COMPLETED':
	                         // 모든 작업이 완료된 경우에만 생산계획을 완료 상태로 변경
	                         const allCompleted = sameWorks.every(function(work) {
	                             return work.workStatus === 'COMPLETED';
	                         });
	                         planStatus = allCompleted ? 'COMPLETED' : 'IN_PROGRESS';
	                         break;
	                     case 'STOPPED':
	                         planStatus = 'STOPPED';
	                         break;
	                     default:
	                         planStatus = 'PLANNED';
	                 }
	
	                 if (window.planModule && typeof window.planModule.updatePlanStatus === 'function') {
	                     window.planModule.updatePlanStatus(currentWork.planId, planStatus);
	                 }
	             }
	
	             self.updateList(response);
	         },
	         error: function(xhr, status, error) {
	             console.error('작업 상태 변경 실패:', error);
	             alert('상태 변경에 실패했습니다.');
	         }
	     });
	 },

    /**
     * 작업지시 삭제
     */
    delete: function(workId) {
        if(!workId || !confirm('이 작업계획을 삭제하시겠습니까?')) {
            return;
        }
        
        const self = this;
        $.ajax({
            url: API.WORK.DELETE + workId,
            type: 'DELETE',
            success: function(response) {
                self.updateList(response);
                alert('작업계획이 삭제되었습니다.');
            },
            error: function(xhr, status, error) {
                console.error('작업 삭제 실패:', error);
                alert('삭제에 실패했습니다.');
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
                        .on('click', function(e) {
                            e.stopPropagation();
                            self.delete(work.workId);
                        })
                )
            );
            tbody.append(row);
        });

        this.state.lastUpdate = new Date();
    },

    /**
     * 폼 초기화
     */
    resetForm: function() {
        this.state.selectedPlan = null;
        
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
        
        // 이벤트 리스너 중복 방지를 위한 네임스페이스 사용
        $(SELECTORS.FORM.INSERT_BTN).off('click.work').on('click.work', function() {
            self.submit();
        });

        $(SELECTORS.FORM.RESET_BTN).off('click.work').on('click.work', function() {
            if(confirm('모든 입력을 초기화하시겠습니까?')) {
                self.resetForm();
            }
        });

        $(SELECTORS.FORM.WORK_ORDER_FORM).off('submit.work').on('submit.work', function(e) {
            e.preventDefault();
            return false;
        });

        // 수량 입력 validation
        $(SELECTORS.FORM.WORK_QUANTITY).off('input.work').on('input.work', function() {
            const quantity = parseInt($(this).val());
            const maxQuantity = self.state.selectedPlan ? self.state.selectedPlan.planQuantity : null;

            if (!quantity || quantity <= 0) {
                $(this).addClass('is-invalid');
                $(this).next('.invalid-feedback').remove();
                $(this).after('<div class="invalid-feedback">수량은 0보다 커야 합니다.</div>');
            } else if (maxQuantity && quantity > maxQuantity) {
                $(this).addClass('is-invalid');
                $(this).next('.invalid-feedback').remove();
                $(this).after('<div class="invalid-feedback">수량은 ' + maxQuantity + '를 초과할 수 없습니다.</div>');
            } else {
                $(this).removeClass('is-invalid');
                $(this).next('.invalid-feedback').remove();
            }
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
            $(this).off('click').on('click', function(e) {
                e.stopPropagation();
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
        console.log('Work module initialized');
    }
};