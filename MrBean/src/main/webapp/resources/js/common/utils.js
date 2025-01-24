export const utils = {
    formatDate(dateString) {
        if(!dateString) return '';
        const date = new Date(dateString);
        return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;
    },

    validateDates(startDate, endDate) {
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

    validateQuantity(quantity, maxQuantity = null) {
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