// 팝업을 열기 함수
function openAddressPopup() {
    window.open("/popup/jusoPopup", "pop", "width=570,height=420, scrollbars=yes, resizable=yes");
}

// 주소 콜백 함수
function jusoCallBack(roadFullAddr, addrDetail, zipNo) {
    document.getElementById('wRoadFullAddr').value = roadFullAddr;
    document.getElementById('wAddrDetail').value = addrDetail;
    document.getElementById('wZipNo').value = zipNo;
    validateInput('wRoadFullAddr'); // 도로명 주소 입력 후 유효성 검사
    validateInput('wAddrDetail');   // 상세주소 입력 후 유효성 검사
    validateInput('wZipNo');        // 우편번호 입력 후 유효성 검사
}

// 입력 필드 유효성 검사
function validateInput(fieldId) {
    const field = document.getElementById(fieldId);
    const errorField = document.getElementById(fieldId + 'Error');
    const submitButton = document.getElementById('submitBtn');

    // 오류 메시지 표시 영역이 없는 경우 종료
    if (!errorField) return;

    // 창고 코드 유효성 검사: A1 ~ Z99
    if (fieldId === 'wCode') {
        const regex = /^[A-Z]{1}[1-9]{1}[0-9]{0,1}$/;
        if (!regex.test(field.value.trim())) {
            errorField.style.display = 'block';
            submitButton.disabled = true;
            return;
        } else {
            errorField.style.display = 'none';
        }
    }
    // 필드 값이 비어 있으면 오류 메시지 표시 (공백 검증)
    if (field.value.trim() === '') {
        errorField.style.display = 'block';
        submitButton.disabled = true; // 제출 버튼 비활성화
    } else {
        errorField.style.display = 'none';
        submitButton.disabled = false; // 제출 버튼 활성화
    }
    // 전체 폼 유효성 검사
    checkFormValidity();
}

// 글자 수 표시 업데이트 함수 (창고 설명)
function updateCharacterCount() {
    const textarea = document.getElementById('wDescription');
    const charCountDisplay = document.getElementById('charCount');
    const maxLength = 500; // 최대 글자 수 지정

    // 현재 입력된 텍스트 길이 가져오기
    const currentLength = textarea.value.length;

    // 최대 글자 수 초과 처리: 초과 부분 자동 삭제
    if (currentLength > maxLength) {
        textarea.value = textarea.value.substring(0, maxLength);
        currentLength = maxLength; // 잘라낸 후 현재 길이를 갱신
    }

    // 글자 수를 동적으로 업데이트
    charCountDisplay.textContent = currentLength + "/" + maxLength;

    // 글자 수가 최대치(500자)에 도달하면 빨간색으로 표시
    if (currentLength === maxLength) {
        charCountDisplay.style.setProperty('color', '#dc3545', 'important');
    } else {
        charCountDisplay.style.removeProperty('color');
    }
}

// 전체 폼 유효성 검사
function checkFormValidity() {
    const submitButton = document.getElementById('submitBtn');
    const wCode = document.getElementById('wCode');
    const wName = document.getElementById('wName');
    const wDescription = document.getElementById('wDescription');

    // 창고 코드 검증: A1 ~ Z99 (최대 3자)
    const isWCodeValid = /^[A-Z]{1}[1-9]{1}[0-9]{0,1}$/.test(wCode.value.trim());
    // 창고 이름은 입력 필수
    const isWNameValid = wName.value.trim() !== '';
    // 창고 설명은 500자 이하
    const isWDescriptionValid = wDescription.value.length <= 500;

    // 모든 필드가 유효할 때만 제출 버튼을 활성화
    if (isWCodeValid && isWNameValid && isWDescriptionValid) {
        submitButton.disabled = false;
    } else {
        submitButton.disabled = true;
    }
}

// 문서 로드 완료 시 이벤트 리스너 등록
document.addEventListener("DOMContentLoaded", function() {
    // 창고 코드 필드의 입력에 대해 유효성 검사 수행
    const wCodeField = document.getElementById('wCode');
    const wNameField = document.getElementById('wName');
    const wDescriptionField = document.getElementById('wDescription');

    // 창고 코드, 창고 이름, 창고 설명의 'input' 이벤트에 유효성 검사 함수 연결
    wCodeField.addEventListener('input', function() {
        validateInput('wCode');
    });

    wNameField.addEventListener('input', function() {
        validateInput('wName');
    });

    wDescriptionField.addEventListener('input', function() {
        updateCharacterCount();
    });
});

// 폼 제출을 처리하는 함수
function submitForm(event) {
    event.preventDefault(); // 폼의 기본 제출 동작을 막습니다.

    const form = document.getElementById('warehouseForm');
    const formData = new FormData(form);
    const warehouseData = {};

    // 폼 데이터를 JSON으로 변환
    formData.forEach((value, key) => {
        warehouseData[key] = value;
    });

   // Fetch API를 이용하여 서버에 데이터 전송
   fetch('/warehouses', {
       method: 'POST',
       headers: {
           'Content-Type': 'application/json'
       },
       body: JSON.stringify(warehouseData) // JSON 데이터 전송
   })
   .then(async response => {
       // 서버 응답을 JSON으로 변환
       const data = await response.json();

       // 서버 오류 응답 처리
       if (!response.ok) {
           throw new Error(data.message || "서버 요청에 실패하였습니다."); // 서버 메시지 포함
       }

       // 성공적인 응답 처리
       showToast(data.message, "success");
       form.reset(); // 폼 초기화

   })
   .catch(error => {
       // 서버에서 전달된 오류 메시지 또는 일반 오류 메시지 표시
       showToast(error.message || "이미 등록된 창고 코드입니다.", "error");
   });
   function deleteWarehouse(wCode) {
       if (!confirm('정말 삭제하시겠습니까?')) {
           return;
       }

       $.ajax({
           url: `${BASE_URL}/warehouses/${wCode}`,
           method: 'DELETE',
           success: function(response) {
               showToast('삭제가 완료되었습니다.', 'success');
               updateWarehouseTable();
           },
           error: function(error) {
               showToast('삭제 중 오류가 발생했습니다.', 'error');
           }
       });
   }

   function updateWarehouseTable() {
       $.ajax({
           url: `${BASE_URL}/warehouses`,
           method: 'GET',
           success: function(response) {
               if (Array.isArray(response)) {
                   const $tbody = $('table.datatable tbody');
                   $tbody.empty();
                   response.forEach(function(warehouse) {
                       const row = `
                           <tr>
                               <td>${warehouse.WCode}</td>
                               <td>${warehouse.WName}</td>
                               <td>${warehouse.WRoadFullAddr}</td>
                               <td>${warehouse.WAddrDetail}</td>
                               <td>${warehouse.WZipNo}</td>
                               <td>${warehouse.WDescription}</td>
                               <td>
                                   <button class="btn btn-sm btn-primary" onclick="openEditModal('${warehouse.WCode}', '${warehouse.WName}', '${warehouse.WRoadFullAddr}', '${warehouse.WAddrDetail}', '${warehouse.WZipNo}', '${warehouse.WDescription}')">수정</button>
                                   <button type="button" class="btn btn-danger btn-sm" onclick="if(confirm('정말 삭제하시겠습니까?')) { deleteWarehouse('${warehouse.WCode}'); }">
                                       <i class="bi bi-trash"></i> 삭제
                                   </button>
                               </td>
                           </tr>
                       `;
                       $tbody.append(row);
                   });
               } else {
                   showToast('창고 목록을 불러오는 중 오류가 발생했습니다.', 'error');
               }
           },
           error: function() {
               showToast('창고 목록을 불러오는 중 오류가 발생했습니다.', 'error');
           }
       });
   }
}