/**
 * modal.js
 * 생산계획 검색 모달 관련 컴포넌트
 */
import { API, SELECTORS } from '../common/constants.js';
import { utils } from '../common/utils.js';

export const Modal = {
    /**
     * 모달 초기화
     */
    init: function() {
        console.log('Modal initializing...');
        this.setupPlanSearchModal();
    },

    /**
     * 계획 검색 모달 설정
     */
    setupPlanSearchModal: function() {
        const modal = $(SELECTORS.MODAL.CONTAINER);
        const self = this;
        
        // 모달 이벤트 리스너 초기화
        modal.off('show.bs.modal hidden.bs.modal');
        
        // 모달 열릴 때 이벤트
        modal.on('show.bs.modal', function() {
            console.log('Modal shown');
            self.loadPlanList();  // 모달 열릴 때 목록 로드
            self.setupSearchEvents();  // 검색 이벤트 설정
        });

        // 모달 닫힐 때 이벤트
        modal.on('hidden.bs.modal', function() {
            // 검색 조건 초기화
            $(SELECTORS.MODAL.SEARCH_TYPE).val('');
            $(SELECTORS.MODAL.SEARCH_PRODUCT).val('');
            $(SELECTORS.MODAL.PLAN_LIST).empty();

            // backdrop과 관련된 클래스와 스타일 제거
            $('.modal-backdrop').remove();
            $('body').removeClass('modal-open').css('padding-right', '');
            
            // body에서 modal 관련 클래스와 스타일 제거
            $('body').removeAttr('style');
        });
    },

    /**
     * 검색 이벤트 설정
     */
    setupSearchEvents: function() {
        const self = this;
        
        // 검색 버튼 이벤트
        $(SELECTORS.MODAL.SEARCH_BTN)
            .off('click')
            .on('click', function(e) {
                e.preventDefault();
                const searchParams = {
                    planType: $(SELECTORS.MODAL.SEARCH_TYPE).val().trim(),
                    productCode: $(SELECTORS.MODAL.SEARCH_PRODUCT).val().trim()
                };
                self.loadPlanList(searchParams);
            });

        // 엔터키 이벤트
        $(SELECTORS.MODAL.SEARCH_TYPE + ',' + SELECTORS.MODAL.SEARCH_PRODUCT)
            .off('keypress')
            .on('keypress', function(e) {
                if (e.which === 13) {
                    e.preventDefault();
                    $(SELECTORS.MODAL.SEARCH_BTN).click();
                }
            });
    },

    /**
     * 계획 목록 로드
     * @param {Object} params - 검색 파라미터
     */
    loadPlanList: function(params = {}) {
        console.log('Loading plan list with params:', params);
        const self = this;
        params.plStatus = 'PLANNED';
        
        // 로딩 중 표시
        $(SELECTORS.MODAL.PLAN_LIST).html(
            '<tr><td colspan="7" class="text-center">로딩 중...</td></tr>'
        );
        
        $.ajax({
            url: API.WORK.PLANS,
            type: 'GET',
            data: params,
            success: function(response) {
                console.log('Plan list loaded:', response);
                self.renderPlanList(response);
            },
            error: function(xhr, status, error) {
                console.error('Failed to load plan list:', error);
                $(SELECTORS.MODAL.PLAN_LIST).html(
                    '<tr><td colspan="7" class="text-center">생산계획 목록 조회에 실패했습니다.</td></tr>'
                );
            }
        });
    },

    /**
     * 계획 목록 렌더링
     * @param {Array} plans - 계획 목록 데이터
     */
    renderPlanList: function(plans) {
        console.log('Rendering plan list:', plans);
        const tbody = $(SELECTORS.MODAL.PLAN_LIST);
        tbody.empty();
        
        if (!plans || plans.length === 0) {
            tbody.html('<tr><td colspan="7" class="text-center">검색 결과가 없습니다.</td></tr>');
            return;
        }
        
        plans.forEach(plan => {
            if (plan.plStatus === 'PLANNED' && plan.planId && plan.planNumber) {
                const tr = $('<tr>')
                    .addClass('cursor-pointer')
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
                    )
                    .on('click', function() {
                        if (window.workModule && typeof window.workModule.selectPlan === 'function') {
                            try {
                                window.workModule.selectPlan(plan);
                                // modal hide 및 backdrop 제거
                                const modal = $(SELECTORS.MODAL.CONTAINER);
                                modal.modal('hide');
                                $('.modal-backdrop').remove();
                                $('body').removeClass('modal-open').css('padding-right', '');
                                $('body').removeAttr('style');
                            } catch (error) {
                                console.error('Error selecting plan:', error);
                                alert('계획 선택 중 오류가 발생했습니다.');
                            }
                        }
                    });
                tbody.append(tr);
            }
        });
    },

    /**
     * 상태별 배지 클래스 반환
     * @param {string} status - 상태 코드
     * @returns {string} 배지 클래스명
     */
    getStatusBadgeClass: function(status) {
        const statusClasses = {
            'PLANNED': 'bg-primary',
            'IN_PROGRESS': 'bg-warning',
            'COMPLETED': 'bg-success',
            'CANCELLED': 'bg-secondary'
        };
        return statusClasses[status] || 'bg-light';
    }
};