/**
 * modal.js
 * 생산계획 검색 모달 컴포넌트
 */
import { API, SELECTORS, STATUS, MESSAGES } from '../common/constants.js';
import { utils } from '../common/utils.js';

export const Modal = {
    // 상태 관리
    state: {
        isLoading: false,
        searchParams: null
    },

    /**
     * 모달 초기화
     */
    init: function() {
        // DOM 요소 캐싱
        this.$modal = $(SELECTORS.MODAL.CONTAINER);
        this.$searchBtn = $(SELECTORS.MODAL.SEARCH_BTN);
        this.$searchType = $(SELECTORS.MODAL.SEARCH_TYPE);
        this.$searchProduct = $(SELECTORS.MODAL.SEARCH_PRODUCT);
        this.$planList = $(SELECTORS.MODAL.PLAN_LIST);
        
        this.setupModal();
        this.setupEvents();
        
        // Bootstrap 모달 초기화
        this.$modal.data('bs.modal', new bootstrap.Modal(this.$modal[0], {
            backdrop: 'static',
            keyboard: false
        }));
        
        console.log('Modal initialized');
    },

    /**
     * 모달 설정
     */
    setupModal: function() {
        // 기존 이벤트 제거
        this.$modal.off('show.bs.modal hidden.bs.modal');
        
        // 모달 이벤트 설정
        utils.eventUtils.bindSafe(this.$modal, 'show.bs.modal', 'modal', () => {
            this.loadPlanList();
            console.log('Modal shown');
        });

        utils.eventUtils.bindSafe(this.$modal, 'hidden.bs.modal', 'modal', () => {
            this.resetModal();
            console.log('Modal hidden');
        });
    },

    /**
     * 이벤트 설정
     */
    setupEvents: function() {
        // 검색 버튼 클릭
        utils.eventUtils.bindSafe(this.$searchBtn, 'click', 'modal', (e) => {
            e.preventDefault();
            if (!this.state.isLoading) this.loadPlanList();
        });

        // 계획종류 변경
        utils.eventUtils.bindSafe(this.$searchType, 'change', 'modal', () => {
            if (!this.state.isLoading) this.loadPlanList();
        });

        // 제품코드 엔터키
        utils.eventUtils.bindSafe(this.$searchProduct, 'keyup', 'modal', (e) => {
            if (e.keyCode === 13 && !this.state.isLoading) this.loadPlanList();
        });

        // 계획 선택 - 이벤트 위임 방식으로 수정
        utils.eventUtils.bindSafe(this.$planList, 'click', 'modal', (e) => {
            const $target = $(e.target).closest('tr.cursor-pointer');
            if ($target.length) {
                const planData = $target.data('plan');
                if (planData) this.handlePlanSelection(planData);
            }
        });
    },

    /**
     * 계획 목록 로드
     */
    loadPlanList: function() {
        if (this.state.isLoading) return;
        this.state.isLoading = true;
        
        // 검색 파라미터 구성
        const searchParams = this.buildSearchParams();
        
        // 로딩 표시
        this.showLoading();
        
        $.ajax({
            url: API.WORK.MODAL_PLANS,
            type: 'GET',
            data: searchParams,
            success: (response) => {
                this.renderPlanList(response);
                console.log('Search parameters:', searchParams);
            },
            error: (xhr, status, error) => {
                console.error('Failed to load plan list:', error);
                this.handleError('생산계획 목록 조회에 실패했습니다.');
            },
            complete: () => {
                this.state.isLoading = false;
            }
        });
    },

    /**
     * 검색 파라미터 구성
     */
    buildSearchParams: function() {
        const params = {
            plStatus: STATUS.PLAN.PLANNED
        };
        
        const planType = this.$searchType.val();
        const pName = this.$searchProduct.val();
        
        if (planType && planType.trim()) {
            params.planType = planType.trim();
        }
        if (pName && pName.trim()) {
            params.pName = pName.trim();
        }
        
        return params;
    },

    /**
     * 로딩 표시
     */
    showLoading: function() {
        this.$planList.html(
            '<tr><td colspan="7" class="text-center">' +
            '<div class="spinner-border text-primary" role="status">' +
            '<span class="visually-hidden">로딩 중...</span></div></td></tr>'
        );
    },

    /**
     * 에러 처리
     */
    handleError: function(message) {
        this.$planList.html(
            '<tr><td colspan="7" class="text-center text-danger">' +
            '<i class="bi bi-exclamation-circle"></i> ' + message +
            '</td></tr>'
        );
    },

    /**
     * 계획 목록 렌더링
     */
    renderPlanList: function(plans) {
        this.$planList.empty();
        
        if (!plans || !plans.length) {
            this.$planList.html(
                '<tr><td colspan="7" class="text-center">검색 결과가 없습니다.</td></tr>'
            );
            return;
        }
        
        const validPlans = plans.filter(plan => 
            plan.plStatus === STATUS.PLAN.PLANNED && plan.planId && plan.planNumber
        );

        validPlans.forEach(plan => {
            const tr = this.createPlanRow(plan);
            this.$planList.append(tr);
        });
    },

    /**
     * 계획 행 생성
     */
    createPlanRow: function(plan) {
        return $('<tr>')
            .addClass('cursor-pointer')
            .data('plan', plan)
            .append(
                $('<td>').text(plan.planNumber || ''),
                $('<td>').text(plan.planType || ''),
                $('<td>').text(plan.pName || ''),
                $('<td>').text(plan.planQuantity || '0'),
                $('<td>').text(utils.dateUtils.formatDate(plan.planStartDate) || ''),
                $('<td>').text(utils.dateUtils.formatDate(plan.planEndDate) || ''),
                $('<td>').append(
                    $('<span>')
                        .addClass('badge ' + utils.statusUtils.getBadgeClass(plan.plStatus))
                        .text(utils.statusUtils.getDisplayName(plan.plStatus, 'plan'))
                )
            );
    },

    /**
     * 계획 선택 처리
     */
    handlePlanSelection: function(plan) {
        if (window.workModule && window.workModule.selectPlan) {
            try {
                window.workModule.selectPlan(plan);
                const modalInstance = bootstrap.Modal.getInstance(this.$modal[0]);
                if (modalInstance) {
                    modalInstance.hide();
                }
            } catch (error) {
                console.error('Error selecting plan:', error);
                alert('계획 선택 중 오류가 발생했습니다.');
            }
        }
    },

    /**
     * 모달 초기화
     */
    resetModal: function() {
        this.$searchType.val('');
        this.$searchProduct.val('');
        this.$planList.empty();

        this.state.searchParams = null;
        this.state.isLoading = false;

        $('.modal-backdrop').remove();
        $('body').removeClass('modal-open').css('padding-right', '');
    }
};