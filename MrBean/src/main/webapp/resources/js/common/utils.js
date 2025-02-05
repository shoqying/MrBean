/**
 * utils.js
 * 공통으로 사용되는 유틸리티 함수들을 정의
 */
import { API } from './constants.js';

export const utils = {
    /**
     * 날짜 관련 유틸리티
     */
    dateUtils: {
        /**
         * 날짜를 YYYY-MM-DD 형식의 문자열로 변환
         */
        formatDate: function(dateString) {
            if(!dateString) return '';
            const date = new Date(dateString);
            return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;
        },

        /**
         * 시작일과 종료일 유효성 검사
         */
        validateDates: function(startDate, endDate) {
            const start = new Date(startDate);
            const end = new Date(endDate);
            const today = new Date();
            today.setHours(0, 0, 0, 0);

            if(start < today) {
                alert('시작일자는 현재 날짜보다 이전일 수 없습니다.');
                return false;
            }

            if(end < start) {
                alert('종료일자는 시작일자보다 이전일 수 없습니다.');
                return false;
            }

            return true;
        }
    },

    /**
     * 수량 관련 유틸리티
     */
    numberUtils: {
        /**
         * 수량 유효성 검사
         */
        validateQuantity: function(quantity, maxQuantity = null) {
            const parsedQuantity = parseInt(quantity);
            if(isNaN(parsedQuantity) || parsedQuantity <= 0) {
                alert('수량은 0보다 커야 합니다.');
                return false;
            }
            if(maxQuantity !== null && parsedQuantity > maxQuantity) {
                alert(`수량은 ${maxQuantity}를 초과할 수 없습니다.`);
                return false;
            }
            return true;
        },

        /**
         * 번호 자동 생성
         */
        generateNumber: function(type, callback) {
            const API_MAP = {
                plan: API.PLAN.NUMBER,
                work: API.WORK.NUMBER
            };
            
            $.ajax({
                url: API_MAP[type],
                type: 'GET',
                success: function(number) {
                    if(number && callback) {
                        callback(number.trim());
                    }
                },
                error: function() {
                    alert('번호 생성에 실패했습니다.');
                }
            });
        }
    },

    /**
     * 상태 관련 유틸리티
     */
    statusUtils: {
        /**
         * 상태 코드를 표시용 텍스트로 변환
         */
        getDisplayName: function(status, type) {
            const statusMap = {
                plan: {
                    'PLANNED': 'PLANNED',
                    'WAITING': 'WAITING',
                    'IN_PROGRESS': 'IN_PROGRESS',
                    'COMPLETED': 'COMPLETED',
                    'STOPPED': 'STOPPED'
                },
                work: {
                    'WAITING': 'WAITING',
                    'IN_PROGRESS': 'IN_PROGRESS',
                    'COMPLETED': 'COMPLETED',
                    'STOPPED': 'STOPPED'
                }
            };
            return statusMap[type][status] || status;
        },

        /**
         * 상태별 배지 클래스 반환
         */
        getBadgeClass: function(status) {
            const statusClasses = {
                'PLANNED': 'bg-primary',
                'WAITING': 'bg-secondary',
                'IN_PROGRESS': 'bg-warning',
                'COMPLETED': 'bg-success',
                'STOPPED': 'bg-danger',
                'CANCELLED': 'bg-secondary'
            };
            return statusClasses[status] || 'bg-light';
        }
    },

    /**
     * 이벤트 관련 유틸리티
     */
    eventUtils: {
        /**
         * 안전한 이벤트 바인딩 (중복 방지)
         * @param {string|jQuery} selector - 이벤트를 바인딩할 요소
         * @param {string} eventType - 이벤트 타입
         * @param {string} namespace - 이벤트 네임스페이스
         * @param {function} handler - 이벤트 핸들러
         * @param {string} [delegateSelector] - 위임할 선택자 (선택적)
         */
        bindSafe: function(selector, eventType, namespace, handler, delegateSelector) {
            const $element = $(selector);
            const eventName = eventType + '.' + namespace;
            
            $element.off(eventName);
            
            if (delegateSelector) {
                $element.on(eventName, delegateSelector, handler);
            } else {
                $element.on(eventName, handler);
            }
        }
    },

    /**
     * 폼 관련 유틸리티
     */
    formUtils: {
        /**
         * 폼 초기화
         */
        resetForm: function(formSelector, defaultValues = {}) {
            const form = $(formSelector);
            form.find('input, select, textarea').each(function() {
                const field = $(this);
                const name = field.attr('name');
                field.val(defaultValues[name] || '');
            });
        },

        /**
         * 유효성 검사 피드백 표시
         */
        showValidationFeedback: function(element, isValid, message) {
            const $element = $(element);
            $element.toggleClass('is-invalid', !isValid);
            let feedback = $element.next('.invalid-feedback');
            
            if(!isValid) {
                if(!feedback.length) {
                    $element.after('<div class="invalid-feedback">' + message + '</div>');
                } else {
                    feedback.text(message);
                }
            } else {
                feedback.remove();
            }
        }
    }
};