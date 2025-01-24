// work.js
import { API, SELECTORS } from '../common/constants.js';
import { utils } from '../common/utils.js';

export const workModule = {
    isSubmitting: false,
    selectedPlan: null,

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
                alert('작업지시 번호 생성 실패');
            }
        });
    },

    validateForm: function() {
        if(!this.selectedPlan) {
            alert('생산계획을 선택해주세요.');
            return false;
        }

        var workDate = $(SELECTORS.FORM.WORK_PLAN_DATE).val();
        if(!workDate) {
            alert('작업예정일을 입력해주세요.');
            return false;
        }

        var quantity = $(SELECTORS.FORM.WORK_QUANTITY).val();
        return utils.validateQuantity(quantity, this.selectedPlan.planQuantity);
    },

    submit: function() {
        if(this.isSubmitting || !this.validateForm()) return;

        this.isSubmitting = true;
        $(SELECTORS.FORM.INSERT_BTN).prop('disabled', true);

        var formData = {
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
    
    delete: function(workId) {
        if(!confirm('이 작업계획을 삭제하시겠습니까?')) return;
        
        var self = this;
        $.ajax({
            url: API.WORK.DELETE + workId,
            type: 'DELETE',
            success: function(response) {
                self.updateList(response);
                alert('작업계획이 삭제되었습니다.');
            },
            error: function() {
                alert('삭제에 실패했습니다.');
            }
        });
    },

    updateList: function(workList) {
        var tbody = $(SELECTORS.TABLE.BODY);
        tbody.empty();

        var self = this;
        workList.forEach(function(work) {
            var row = $('<tr>').append(
                $('<td>').text(work.workOrderNo),
                $('<td>').text(work.workPlanNo),
                $('<td>').text(utils.formatDate(work.workPlanDate)),
                $('<td>').text(work.workQuantity),
                $('<td>').append(
                    $('<span>')
                        .addClass('badge ' + self.getStatusBadgeClass(work.workStatus))
                        .text(work.workStatus)
                ),
                $('<td>').text(work.workRemark),
                $('<td>').text(work.workCreatedBy),
                $('<td>').append(
                    $('<button>')
                        .addClass('btn btn-danger btn-sm')
                        .append($('<i>').addClass('bi bi-trash'))
                        .on('click', function() {
                            self.delete(work.workId);
                        })
                )
            );
            tbody.append(row);
        });
    },

    getStatusBadgeClass: function(status) {
        var statusClasses = {
            'WAITING': 'bg-secondary',
            'IN_PROGRESS': 'bg-warning',
            'COMPLETED': 'bg-success',
            'STOPPED': 'bg-danger'
        };
        return statusClasses[status] || 'bg-light';
    },

    resetForm: function() {
        this.selectedPlan = null;
        $(SELECTORS.FORM.WORK_NUMBER).val('');
        $(SELECTORS.FORM.WORK_PLAN_NO).val('');
        $(SELECTORS.FORM.WORK_PLAN_DATE).val('');
        $(SELECTORS.FORM.WORK_QUANTITY).val('');
        $(SELECTORS.FORM.WORK_REMARK).val('');
        $(SELECTORS.FORM.WORK_STATUS).val('WAITING');
        
        $('#planType').val('');
        $('#productCode').val('');
        $('#planQuantityDisplay').text('0');
        
        this.generateNumber();
    },

    setupEventListeners: function() {
        var self = this;
        
        $('form').on('submit', function(e) {
            e.preventDefault();
            return false;
        });

        $(SELECTORS.FORM.INSERT_BTN).on('click', function() {
            self.submit();
        });

        $(SELECTORS.FORM.RESET_BTN).on('click', function() {
            if(confirm('모든 입력을 초기화하시겠습니까?')) {
                self.resetForm();
            }
        });
    },

    init: function() {
        this.generateNumber();
        this.setupEventListeners();
    }
};