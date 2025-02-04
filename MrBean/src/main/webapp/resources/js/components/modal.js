/**
 * modal.js
 * 생산계획 검색 모달 컴포넌트 (ES5)
 */
import { API, SELECTORS } from '../common/constants.js';
import { utils } from '../common/utils.js';

var Modal = {
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
        
        this.setupPlanSearchModal();
        this.setupSearchEvents();
        this.setupKeyboardEvents();
        
        // Bootstrap 5 모달 설정
        this.$modal.data('bs.modal', new bootstrap.Modal(this.$modal[0], {
            backdrop: 'static',
            keyboard: false
        }));
        
        console.log('Modal initialized');
    },

    /**
     * 계획 검색 모달 설정
     */
    setupPlanSearchModal: function() {
        var self = this;
        
        // 기존 모달 이벤트 제거
        this.$modal.off('show.bs.modal hidden.bs.modal');
        
        // 모달 열릴 때 이벤트
        this.$modal.on('show.bs.modal', function() {
            self.loadPlanList();  // 초기 목록 로드
            console.log('Modal shown');
        });

        // 모달 닫힐 때 이벤트
        this.$modal.on('hidden.bs.modal', function() {
            self.resetModal();
            console.log('Modal hidden');
        });
    },

    /**
     * 검색 이벤트 설정
     */
    setupSearchEvents: function() {
        var self = this;
        
        // 검색 버튼 이벤트
        this.$searchBtn.off('click').on('click', function(e) {
            e.preventDefault();
            if (self.state.isLoading) return;
            self.loadPlanList();
        });

        // 계획종류 선택 이벤트
        this.$searchType.off('change').on('change', function() {
            if (!self.state.isLoading) {
                self.loadPlanList();
            }
        });

        // 제품코드 입력 이벤트 - 엔터키 처리
        this.$searchProduct.off('keyup').on('keyup', function(e) {
            if (e.keyCode === 13 && !self.state.isLoading) {
                self.loadPlanList();
            }
        });

        // 계획 선택 이벤트 - 이벤트 위임 사용
        this.$planList.off('click', 'tr.cursor-pointer').on('click', 'tr.cursor-pointer', function() {
            var planData = $(this).data('plan');
            if (planData) {
                self.handlePlanSelection(planData);
            }
        });
    },

    /**
     * 키보드 이벤트 설정
     */
    setupKeyboardEvents: function() {
        var self = this;
        
        // Enter 키 이벤트 처리
        this.$searchType.add(this.$searchProduct).on('keypress', function(e) {
            if (e.which === 13) {
                e.preventDefault();
                self.$searchBtn.click();
            }
        });
    },

    /**
     * 계획 목록 로드
     */
    loadPlanList: function() {
        var self = this;
        if (this.state.isLoading) return;
        
        this.state.isLoading = true;
        
        // 검색 파라미터 구성
        var searchParams = {};
        var planType = this.$searchType.val() || '';
        var productCode = this.$searchProduct.val() || '';
        
        // 각 검색 조건이 있을 때만 파라미터에 추가
        if (planType.trim()) {
            searchParams.planType = planType.trim();
        }
        if (productCode.trim()) {
            searchParams.productCode = productCode.trim();
        }
        
        // 기본 상태 필터 추가
        searchParams.plStatus = 'PLANNED';
        
        // 로딩 표시
        this.$planList.html(
            '<tr><td colspan="7" class="text-center">' +
            '<div class="spinner-border text-primary" role="status">' +
            '<span class="visually-hidden">로딩 중...</span></div></td></tr>'
        );
        
        $.ajax({
            url: API.WORK.PLANS,
            type: 'GET',
            data: searchParams,
            success: function(response) {
                self.renderPlanList(response);
                console.log('Search parameters:', searchParams);
            },
            error: function(xhr, status, error) {
                console.error('Failed to load plan list:', error);
                self.handleError('생산계획 목록 조회에 실패했습니다.');
            },
            complete: function() {
                self.state.isLoading = false;
            }
        });
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
        
        if (!plans || plans.length === 0) {
            this.$planList.html(
                '<tr><td colspan="7" class="text-center">검색 결과가 없습니다.</td></tr>'
            );
            return;
        }
        
        var validPlans = plans.filter(function(plan) {
            return plan.plStatus === 'PLANNED' && plan.planId && plan.planNumber;
        });

        var self = this;
        validPlans.forEach(function(plan) {
            var tr = self.createPlanRow(plan);
            self.$planList.append(tr);
        });
    },

    /**
     * 계획 행 생성
     */
    createPlanRow: function(plan) {
        var tr = $('<tr>')
            .addClass('cursor-pointer')
            .data('plan', plan)
            .append(
                $('<td>').text(plan.planNumber || ''),
                $('<td>').text(plan.planType || ''),
                $('<td>').text(plan.productCode || ''),
                $('<td>').text(plan.planQuantity || '0'),
                $('<td>').text(utils.formatDate(plan.planStartDate) || ''),
                $('<td>').text(utils.formatDate(plan.planEndDate) || ''),
                $('<td>').append(
                    $('<span>')
                        .addClass('badge ' + this.getStatusBadgeClass(plan.plStatus))
                        .text(plan.plStatus || '')
                )
            );

        return tr;
    },

    /**
     * 계획 선택 처리
     */
    handlePlanSelection: function(plan) {
        if (window.workModule && typeof window.workModule.selectPlan === 'function') {
            try {
                window.workModule.selectPlan(plan);
                var modalInstance = bootstrap.Modal.getInstance(this.$modal[0]);
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
    },

    /**
     * 상태별 배지 클래스 반환
     */
    getStatusBadgeClass: function(status) {
        var statusClasses = {
            'PLANNED': 'bg-primary',
            'IN_PROGRESS': 'bg-warning',
            'COMPLETED': 'bg-success',
            'CANCELLED': 'bg-secondary',
            'STOPPED': 'bg-danger'
        };
        return statusClasses[status] || 'bg-light';
    }
};

// 모듈 내보내기
export { Modal };