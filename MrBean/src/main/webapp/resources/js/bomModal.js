// 모달 열기
function openEditModal(bom) {
    // 모달 안에 수정할 데이터를 세팅
    $('#editBomId').val(bom.bomId);
    $('#editBomName').val(bom.bomName);
    $('#editRmCode').val(bom.rmCode);
    $('#editBomRatio').val(bom.bomRatio);
    $('#editBomDescription').val(bom.bomDescription);
    $('#editModal').modal('show');
}

// BOM 수정 요청 (AJAX 예시)
function submitEditForm() {
    let bomData = {
        bomId: $('#editBomId').val(),
        bomName: $('#editBomName').val(),
        rmCode: $('#editRmCode').val(),
        bomRatio: $('#editBomRatio').val(),
        bomDescription: $('#editBomDescription').val()
    };

    $.ajax({
        url: '<c:url value="/billofmaterials"/>' + '/' + bomData.bomId,
        method: 'PUT',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(bomData),
        success: function(response) {
            showToast('수정이 성공적으로 완료되었습니다.'); // 토스트 메세지
            $('#editModal').modal('hide');
            location.reload(); // 수정 후 페이지 새로고침
        },
        error: function(error) {
            showToast('수정 중 오류가 발생했습니다.');
        }
    });
}

// BOM 삭제 요청 (AJAX 예시)
function deleteBom(bomId) {
    if (!confirm('정말 삭제하시겠습니까?')) {
        return;
    }
    $.ajax({
        url: '<c:url value="/billofmaterials"/>' + '/' + bomId,
        method: 'DELETE',
        success: function(response) {
            showToast('삭제가 성공적으로 완료되었습니다.');
            location.reload();
        },
        error: function(error) {
            showToast('삭제 중 오류가 발생했습니다.');
        }
    });
}
