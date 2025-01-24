$(document).ready(function() {
    // 모달이 열릴 때마다 원자재 목록을 다시 불러온다(원한다면 한번만 불러올 수도 있음)
    $('#rawMaterialModal').on('show.bs.modal', function (e) {
        loadRawMaterialList();
    });
});

// Ajax로 원자재 목록 불러오기
function loadRawMaterialList() {
    $.ajax({
        url: '/rawmaterials',  // Controller에서 @GetMapping("/rawmaterials/list") 설정
        type: 'GET',
        success: function(data) {
            // data: List<RawMaterialDTO> 형태 (JSON)
            let tbody = $('#rawMaterialTableBody');
            tbody.empty(); // 기존 행 초기화

            data.forEach(function(item) {
                let row = `
                    <tr>
                        <td>${item.rmCode}</td>
                        <td>${item.rmName}</td>
                        <td>${item.rmDescription}</td>
                        <td>
                            <button type="button" class="btn btn-primary"
                                onclick="selectRawMaterial('${item.rmCode}')">
                                선택
                            </button>
                        </td>
                    </tr>
                `;
                tbody.append(row);
            });
        },
        error: function(err) {
            console.error(err);
        }
    });
}

// 모달에서 특정 원자재 선택 시, 폼에 rmCode를 설정하고 모달을 닫음
function selectRawMaterial(code) {
    $('#rmCode').val(code);
    $('#rawMaterialModal').modal('hide');
}

// 예시: BOM 등록 폼 전송 시 처리
function submitForm(event) {
    event.preventDefault();
    // 유효성 검사 후 문제 없으면 폼 전송(예: Ajax or form.submit)
    // ...
}
