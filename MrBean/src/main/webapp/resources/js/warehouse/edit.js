const BASE_URL = '/warehouses';

// 창고 삭제 함수 정의
async function deleteWarehouse(wCode) {
    if (!confirm('정말 삭제하시겠습니까?')) {
        return;
    }

    try {
        const response = await fetch(`${BASE_URL}/${wCode}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            const data = await response.json();
            throw new Error(data.message || '삭제 중 오류가 발생했습니다.');
        }

        showToast('삭제가 완료되었습니다.', 'success');
        await updateWarehouseTable(); // 테이블 업데이트를 기다림
    } catch (error) {
        showToast(error.message || '삭제 중 오류가 발생했습니다.', 'error');
    }
}

// 창고 테이블 업데이트 함수 정의
async function updateWarehouseTable() {
    try {
        const response = await fetch(`${BASE_URL}/refresh`, {
            method: 'GET'
        });

        if (!response.ok) {
            throw new Error('창고 목록을 불러오는 중 오류가 발생했습니다.');
        }

        const contentType = response.headers.get('Content-Type');
        let data;
        if (contentType && contentType.includes('application/json')) {
            data = await response.json();
        } else {
            data = await response.text();
        }

        console.log('Response data:', data); // 서버에서 받은 데이터 로그 출력

        if (Array.isArray(data)) {
            const $tbody = $('table.datatable tbody');
            $tbody.empty();
            data.forEach(function(warehouse) {
                const row = `
                    <tr>
                        <td>${warehouse.wCode}</td>
                        <td>${warehouse.wName}</td>
                        <td>${warehouse.wRoadFullAddr}</td>
                        <td>${warehouse.wAddrDetail}</td>
                        <td>${warehouse.wZipNo}</td>
                        <td>${warehouse.wDescription}</td>
                        <td>
                            <button class="btn btn-sm btn-primary" onclick="openEditModal('${warehouse.WCode}', '${warehouse.WName}', '${warehouse.WRoadFullAddr}', '${warehouse.WAddrDetail}', '${warehouse.WZipNo}', '${warehouse.WDescription}')">수정</button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="deleteWarehouse('${warehouse.WCode}')">
                                <i class="bi bi-trash"></i>
                            </button>
                        </td>
                    </tr>
                `;
                $tbody.append(row);
            });
        } else {
            showToast('창고 목록을 불러오는 중 오류가 발생했습니다.', 'error');
        }
    } catch (error) {
        showToast('창고 목록을 불러오는 중 오류가 발생했습니다.', 'error');
    }
}

// 창고 수정 모달 열기 함수 정의
function openEditModal(wCode, wName, wRoadFullAddr, wAddrDetail, wZipNo, wDescription) {
    document.getElementById("editWCode").value = wCode;
    document.getElementById("editWName").value = wName;
    document.getElementById("editWRoadFullAddr").value = wRoadFullAddr;
    document.getElementById("editWAddrDetail").value = wAddrDetail;
    document.getElementById("editWZipNo").value = wZipNo;
    document.getElementById("editWDescription").value = wDescription;

    $('#editModal').modal('show');
}

// 창고 수정 폼 제출 함수 정의
async function submitEditForm() {
    let warehouseData = {
        wCode: $('#editWCode').val(),
        wName: $('#editWName').val(),
        wRoadFullAddr: $('#editWRoadFullAddr').val(),
        wAddrDetail: $('#editWAddrDetail').val(),
        wZipNo: $('#editWZipNo').val(),
        wDescription: $('#editWDescription').val()
    };

    // 데이터 유효성 검사
    if (!warehouseData.wName || !warehouseData.wRoadFullAddr || !warehouseData.wAddrDetail || !warehouseData.wZipNo) {
        showToast('모든 필드를 올바르게 입���해주세요.', 'error');
        return;
    }

    // 모달 닫기
    $('#editModal').modal('hide');

    try {
        const response = await fetch(`${BASE_URL}/${warehouseData.wCode}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(warehouseData)
        });

        if (!response.ok) {
            const data = await response.json();
            throw new Error(data.message || '수정 중 오류가 발생했습니다.');
        }

        showToast('수정이 완료되었습니다.', 'success');
        await updateWarehouseTable(); // 테이블 업데이트를 기다림
    } catch (error) {
        showToast(error.message || '수정 중 오류가 발생했습니다.', 'error');
    } finally {
        // 모달이 완전히 닫히도록 강제
        $('body').removeClass('modal-open');
        $('.modal-backdrop').remove();
    }
}

// 모달 닫기 버튼 이벤트 리스너 추가
document.addEventListener('DOMContentLoaded', function() {
    const closeButton = document.querySelector('#editModal .btn-secondary');
    closeButton.addEventListener('click', function() {
        $('#editModal').modal('hide');
        $('body').removeClass('modal-open');
        $('.modal-backdrop').remove();
    });

    const editButton = document.getElementById('editButton');
    const formFields = document.querySelectorAll('#editModal input, #editModal textarea');

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