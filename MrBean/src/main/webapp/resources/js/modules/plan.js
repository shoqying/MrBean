// plan.js 
import { API, SELECTORS } from '../common/constants.js';
import { utils } from '../common/utils.js';

export const planModule = {
   isSubmitting: false,

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
               alert('번호 생성 실패');
           }
       });
   },

   validateForm: function() {
       var startDate = $(SELECTORS.FORM.START_DATE).val();
       var endDate = $(SELECTORS.FORM.END_DATE).val();
       if(!utils.validateDates(startDate, endDate)) return false;

       var quantity = $(SELECTORS.FORM.QUANTITY).val();
       if(!utils.validateQuantity(quantity)) return false;

       return true;
   },

   submit: function() {
       if(this.isSubmitting || !this.validateForm()) return;

       this.isSubmitting = true;
       $(SELECTORS.FORM.INSERT_BTN).prop('disabled', true);

       var self = this;
       var formData = {
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
               self.updateList(response);
               alert("생산계획이 등록되었습니다.");
               self.resetForm();
           },
           error: function() {
               alert('생산계획 등록에 실패했습니다.');
           },
           complete: function() {
               self.isSubmitting = false;
               $(SELECTORS.FORM.INSERT_BTN).prop('disabled', false);
           }
       });
   },

   delete: function(planId) {
       if(!confirm('이 생산계획을 삭제하시겠습니까?')) return;
       
       var self = this;
       $.ajax({
           url: API.PLAN.DELETE + planId,
           type: 'DELETE',
           success: function(response) {
               self.updateList(response);
               alert('생산계획이 삭제되었습니다.');
           },
           error: function() {
               alert('삭제에 실패했습니다.');
           }
       });
   },

   updateList: function(planList) {
       var tbody = $(SELECTORS.TABLE.BODY);
       tbody.empty();

       var self = this;
       planList.forEach(function(plan) {
           var row = $('<tr>').append(
               $('<td>').text(plan.planNumber),
               $('<td>').text(plan.priority),
               $('<td>').text(plan.planType),
               $('<td>').text(utils.formatDate(plan.planStartDate)),
               $('<td>').text(utils.formatDate(plan.planEndDate)),
               $('<td>').append(
                   $('<span>')
                       .addClass('badge ' + self.getStatusBadgeClass(plan.plStatus))
                       .text(plan.plStatus)
               ),
               $('<td>').text(plan.productCode),
               $('<td>').text(plan.planQuantity),
               $('<td>').text(plan.remark),
               $('<td>').text(plan.createdBy),
               $('<td>').append(
                   $('<button>')
                       .addClass('btn btn-danger btn-sm delete-btn')
                       .append($('<i>').addClass('bi bi-trash'))
                       .data('plan-id', plan.planId)
               )
           );
           tbody.append(row);
       });
   },

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

   getStatusBadgeClass: function(status) {
       var statusClasses = {
           'PLANNED': 'bg-primary',
           'IN_PROGRESS': 'bg-warning', 
           'COMPLETED': 'bg-success',
           'CANCELLED': 'bg-secondary'
       };
       return statusClasses[status] || 'bg-light';
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

       $(document).on('click', '.delete-btn', function() {
           var planId = $(this).data('plan-id');
           self.delete(planId);
       });

       this.setupFormValidation();
   },

   setupFormValidation: function() {
       $(SELECTORS.FORM.START_DATE).on('change', function() {
           var endDate = $(SELECTORS.FORM.END_DATE).val();
           if(endDate) utils.validateDates($(this).val(), endDate);
       });

       $(SELECTORS.FORM.END_DATE).on('change', function() {
           var startDate = $(SELECTORS.FORM.START_DATE).val();
           if(startDate) utils.validateDates(startDate, $(this).val());
       });

       $(SELECTORS.FORM.QUANTITY).on('input', function() {
           var quantity = parseInt($(this).val());
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

   init: function() {
       this.generateNumber();
       this.setupEventListeners();
   }
};