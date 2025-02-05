/**
 * utils.js
 * 공통으로 사용되는 유틸리티 함수들을 정의
 */
export const utils = {
    /**
     * 날짜를 YYYY-MM-DD 형식의 문자열로 변환
     * @param {string} dateString - 변환할 날짜 문자열
     * @returns {string} 포맷된 날짜 문자열
     */
    formatDate: function(dateString) {
        if(!dateString) return '';
        const date = new Date(dateString);
        return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;
    },

    /**
     * 시작일과 종료일 유효성 검사
     * @param {string} startDate - 시작일
     * @param {string} endDate - 종료일
     * @returns {boolean} 검증 통과 여부
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
    },

    /**
     * 수량 유효성 검사
     * @param {number} quantity - 검사할 수량
     * @param {number} maxQuantity - 최대 허용 수량 (선택적)
     * @returns {boolean} 검증 통과 여부
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
    }
};