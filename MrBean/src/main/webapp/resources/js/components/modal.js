// modal.js
import { API, SELECTORS } from '../common/constants.js';
import { utils } from '../common/utils.js';
import { workModule } from '../modules/work.js';

export const Modal = {
   init: function() {
       this.setupPlanSearchModal();
   },

   setupPlanSearchModal: function() {
      var modal = $(SELECTORS.MODAL.PLAN_SEARCH);
      var self = this;
      
      modal.removeAttr('aria-hidden').removeAttr('tabindex');
      
      modal.on('show.bs.modal', function() {
          modal.find('button, input, select').attr('tabindex', '0');
          // 모달 열릴 때 바로 목록 로드
          self.loadPlanList();
      });

      modal.on('hidden.bs.modal', function() {
          modal.find('button, input, select').removeAttr('tabindex');
          // 모달 닫을 때 백드롭 제거 
          $('.modal-backdrop').remove();
          $('body').removeClass('modal-open').css('padding-right', '');
      });

      this.setupSearchEvents();
   },

   setupSearchEvents: function() {
       $(SELECTORS.MODAL.SEARCH_BTN).on('click', function() {
           var searchParams = {
               planType: $(SELECTORS.MODAL.SEARCH_TYPE).val(),
               productCode: $(SELECTORS.MODAL.SEARCH_PRODUCT).val()
           };
           this.loadPlanList(searchParams);
       }.bind(this));

       $(SELECTORS.MODAL.SEARCH_TYPE + ',' + SELECTORS.MODAL.SEARCH_PRODUCT).on('keydown', function(e) {
           if (e.which === 13) {
               $(SELECTORS.MODAL.SEARCH_BTN).click();
           }
       });
   },

   loadPlanList: function(params) {
       if(!params) params = {};
       
       var searchParams = Object.assign({}, params, {
           plStatus: 'PLANNED'
       });
       
       $.ajax({
           url: API.WORK.PLANS,
           type: 'GET',
           data: searchParams,
           success: function(response) {
               this.renderPlanList(response);
           }.bind(this),
           error: function() {
               $(SELECTORS.MODAL.PLAN_LIST).empty()
                   .append('<tr><td colspan="7" class="text-center">생산계획 목록 조회에 실패했습니다.</td></tr>');
           }
       });
   },

   renderPlanList: function(plans) {
       var planList = $(SELECTORS.MODAL.PLAN_LIST);
       planList.empty();
       
       if (!plans || plans.length === 0) {
           planList.append('<tr><td colspan="7" class="text-center">검색 결과가 없습니다.</td></tr>');
           return;
       }
       
       var self = this;
       plans.forEach(function(plan) {
           var tr = $('<tr>')
               .addClass('cursor-pointer plan-row')
               .append(
                   $('<td>').text(plan.planNumber),
                   $('<td>').text(plan.planType),
                   $('<td>').text(plan.productCode),
                   $('<td>').text(plan.planQuantity),
                   $('<td>').text(utils.formatDate(plan.planStartDate)),
                   $('<td>').text(utils.formatDate(plan.planEndDate)),
                   $('<td>').append(
                       $('<span>')
                           .addClass('badge ' + self.getStatusBadgeClass(plan.plStatus))
                           .text(plan.plStatus)
                   )
               )
               .on('click', function() {
                   self.selectPlan(plan);
                   $(SELECTORS.MODAL.PLAN_SEARCH).modal('hide');
               });
           planList.append(tr);
       });
   },

   selectPlan: function(plan) {
       workModule.selectedPlan = plan;
       $(SELECTORS.FORM.WORK_PLAN_NO).val(plan.planNumber);
       $('#planType').val(plan.planType);
       $('#productCode').val(plan.productCode);
       $(SELECTORS.FORM.WORK_PLAN_DATE).val(utils.formatDate(plan.planStartDate));
       $(SELECTORS.FORM.WORK_QUANTITY).val(plan.planQuantity);
       $('#planQuantityDisplay').text(plan.planQuantity);
   },

   getStatusBadgeClass: function(status) {
       var statusClasses = {
           'PLANNED': 'bg-primary',
           'IN_PROGRESS': 'bg-warning',
           'COMPLETED': 'bg-success',
           'CANCELLED': 'bg-secondary'
       };
       return statusClasses[status] || 'bg-light';
   }
};