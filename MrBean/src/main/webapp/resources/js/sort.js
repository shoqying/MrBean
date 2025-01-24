function changeSort(column) {
    // 현재 정렬 상태
    let currentSortKey = '${param.sortKey}';
    let currentSortOrder = '${param.sortOrder}';

    // 클릭한 컬럼이 현재 정렬 중인 컬럼과 같은지 확인
    if (column == currentSortKey) {
        // 정렬 순서를 토글(ASC <-> DESC)
        if (currentSortOrder == 'ASC') {
            currentSortOrder = 'DESC';
        } else {
            currentSortOrder = 'ASC';
        }
    } else {
        // 새로 클릭한 컬럼은 기본으로 ASC로 설정
        currentSortOrder = 'ASC';
    }
    // 새 정렬 파라미터로 페이지 다시 로드
    let url = '?sortKey=' + column + '&sortOrder=' + currentSortOrder;
    window.location.href = url;
}
