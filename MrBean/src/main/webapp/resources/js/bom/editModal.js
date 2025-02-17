const BASE_URL = 'api/billofmaterials';
const modal = document.getElementById("editModal");

function openEditModal(bom) {
    $('#editBomId').val(bom.bomId);
    $('#editBomName').val(bom.bomName);
    $('#editRmCode').val(bom.rmCode);
    $('#editBomRatio').val(bom.bomRatio);
    $('#editBomDescription').val(bom.bomDescription);

    // Fetch raw materials and populate the dropdown
    $.ajax({
        url: 'api/rawmaterials',
        method: 'GET',
        success: function(rawMaterials) {
            const $editRmName = $('#editRmName');
            $editRmName.empty();
            $editRmName.append('<option selected disabled>원자재를 선택하세요</option>');
            rawMaterials.forEach(function(rawMaterial) {
                $editRmName.append(`<option value="${rawMaterial.rmCode}">${rawMaterial.rmName}</option>`);
            });
            $editRmName.val(bom.rmCode); // Set the selected value
        },
        error: function() {
            showToast('원자재 정보를 불러오는 중 오류가 발생했습니다.', 'error');
        }
    });

    // Update rmCode when rmName is selected
    $('#editRmName').on('change', function() {
        const selectedRmCode = $(this).val();
        $('#editRmCode').val(selectedRmCode);
    });

    modal.removeAttribute("inert");
    $('#editModal').modal('show');
}

function submitEditForm() {
    let bomData = {
        bomId: $('#editBomId').val(),
        bomName: $('#editBomName').val(),
        rmCode: $('#editRmCode').val(),
        bomRatio: $('#editBomRatio').val(),
        bomDescription: $('#editBomDescription').val()
    };

    // 데이터 유효성 검사
    if (!bomData.bomName || !bomData.rmCode || !bomData.bomRatio) {
        showToast('모든 필드를 올바르게 입력해주세요.', 'error');
        return;
    }

    // BOM ID에서 숫자만 추출
    const numericId = bomData.bomId.replace('BOM', '');

    // 모달 닫기
    $('#editModal').modal('hide');

    $.ajax({
        url: `${BASE_URL}/${numericId}`,
        method: 'PUT',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(bomData),
        success: function (response) {
            showToast('수정이 완료되었습니다.', 'success');
            // 테이블 동적 업데이트
            updateBomTable();
        },
        error: function (xhr) {
            let errorMessage = '수정 중 오류가 발생했습니다.';
            if (xhr.responseJSON && xhr.responseJSON.message) {
                errorMessage = xhr.responseJSON.message;
            }
            showToast(errorMessage, 'error');
        }
    });
}

document.addEventListener('DOMContentLoaded', function() {
    const editButton = document.getElementById('editButton');
    const formFields = document.querySelectorAll('#editModal input, #editModal textarea, #editModal select');

    formFields.forEach(field => {
        field.addEventListener('input', function() {
            let isFormChanged = false;
            formFields.forEach(field => {
                if (field.value !== field.defaultValue) {
                    isFormChanged = true;
                }
            });
            editButton.disabled = !isFormChanged;
        });
    });
});

function updateBomTable() {
    $.ajax({
        url: `${BASE_URL}`,
        method: 'GET',
        success: function(response) {
            if (Array.isArray(response)) {
                const $tbody = $('table.datatable tbody');
                $tbody.empty();
                const currentTime = new Date().getTime(); // Define currentTime here
                response.forEach(function(bom) {
                    const bomUpdatedAt = new Date(bom.bomUpdatedAt).getTime();
                    const isNew = (currentTime - bomUpdatedAt) < (5 * 1000); // 15 seconds in milliseconds
                    const newLabel = isNew ? '<span class="new-label">●</span>' : '';
                    const row = `
                        <tr>
                            <td>${newLabel} ${bom.bomId}</td>
                            <td>${bom.bomName}</td>
                            <td>${bom.rmCode}</td>
                            <td>${bom.rmName}</td>
                            <td>${bom.bomRatio}</td>
                            <td>${bom.bomDescription}</td>
                            <td>
                                <button class="btn btn-sm btn-primary" onclick="editBOM('${bom.bomId}')">수정</button>
                                <button type="button" class="btn btn-danger btn-sm" onclick="{ deleteBom('${bom.bomId}'); }">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </td>
                        </tr>
                    `;
                    $tbody.append(row);
                });
            } else {
                showToast('BOM 목록을 불러오는 중 오류가 발생했습니다.', 'error');
            }
        },
        error: function() {
            console.error('AJAX request failed'); // AJAX 실패 로그
            showToast('BOM 목록을 불러오는 중 오류가 발생했습니다.', 'error');
        }
    });
}

$(document).ready(function() {
    $('#editBomId').on('click', function() {
        $('#bomIdWarning').show();
    });

    $('#editBomName, #editRmName, #editBomRatio, #editBomDescription').on('click', function() {
        $('#bomIdWarning').hide();
    });
});

function editBOM(bomId) {
    const numericId = bomId.replace('BOM', '');

    $.ajax({
        url: `${BASE_URL}/${numericId}`,
        type: 'GET',
        success: function(response) {
            openEditModal(response);
        },
        error: function() {
            showToast('BOM 정보를 불러오는 중 오류가 발생했습니다.', 'error');
        }
    });
}

function deleteBom(bomId) {
    if (!confirm('정말 삭제하시겠습니까?')) {
        return;
    }

    // Extract numeric ID from BOM ID
    const numericId = bomId.replace('BOM', '');

    $.ajax({
        url: `${BASE_URL}/${numericId}`,
        method: 'DELETE',
        success: function(response) {
            showToast('삭제가 완료되었습니다.', 'success');
            updateBomTable();
        },
        error: function(error) {
            showToast('삭제 중 오류가 발생했습니다.', 'error');
        }
    });
}