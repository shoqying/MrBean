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

    // Log the data to check if fields are correctly populated
    console.log('BOM Data:', bomData);

    // Validate data before sending
    if (!bomData.bomName || !bomData.rmCode || !bomData.bomRatio || !bomData.bomDescription) {
        showToast('모든 필드를 올바르게 입력해주세요.', 'error');
        return;
    }

    // Extract numeric ID from BOM ID
    const numericId = bomData.bomId.replace('BOM', '');

    // Close the modal before making the AJAX request
    $('#editModal').modal('hide');

    $.ajax({
        url: `${BASE_URL}/${numericId}`,
        method: 'PUT',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(bomData),
        success: function (response) {
            showToast('수정이 완료되었습니다.', 'success');
            // Update the table dynamically
            updateBomTable();
        },
        error: function (error) {
            showToast('수정 중 오류가 발생했습니다.', 'error');
        }
    });
}

function updateBomTable() {
    $.ajax({
        url: `${BASE_URL}`,
        method: 'GET',
        success: function(response) {
            if (Array.isArray(response)) {
                const $tbody = $('table.datatable tbody');
                $tbody.empty();
                response.forEach(function(bom) {
                    const row = `
                        <tr>
                            <td>${bom.bomId}</td>
                            <td>${bom.bomName}</td>
                            <td>${bom.rmCode}</td>
                            <td>${bom.rmName}</td>
                            <td>${bom.bomRatio}</td>
                            <td>${bom.bomDescription}</td>
                            <td>
                                <button class="btn btn-sm btn-primary" onclick="editBOM('${bom.bomId}')">수정</button>
                                <button class="btn btn-sm btn-danger" onclick="deleteBom('${bom.bomId}')">삭제</button>
                            </td>
                        </tr>
                    `;
                    $tbody.append(row);
                });
                sortTableByBomId();
            } else {
                showToast('BOM 목록을 불러오는 중 오류가 발생했습니다.', 'error');
            }
        },
        error: function() {
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

    // Sort the table by bomId on initial load
    sortTableByBomId();
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