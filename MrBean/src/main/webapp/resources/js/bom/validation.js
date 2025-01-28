// billOfMaterialsValidation.js

// 입력 필드 유효성 검사
function validateInput(fieldId) {
    const field = document.getElementById(fieldId);
    const errorField = document.getElementById(fieldId + 'Error');
    const submitButton = document.getElementById('submitBtn');

    // 오류 메시지 표시 영역이 없는 경우 그냥 종료
    if (!errorField) return;

    // BOM ID 유효성 검사: BOM + 1~999 (예: BOM1 ~ BOM999)
    if (fieldId === 'bomId') {
        const regex = /^BOM[1-9][0-9]{0,2}$/;
        if (!regex.test(field.value.trim())) {
            errorField.style.display = 'block';
            submitButton.disabled = true;
            return;
        } else {
            errorField.style.display = 'none';
        }
    }

    // BOM 이름은 입력 필수(최대 100자 → 필요시 확장)
    if (fieldId === 'bomName') {
        if (field.value.trim() === '') {
            errorField.style.display = 'block';
            submitButton.disabled = true;
            return;
        } else {
            errorField.style.display = 'none';
        }
    }

    // BOM 비율(0~100 범위)
    if (fieldId === 'bomRatio') {
        const ratio = parseInt(field.value, 10);
        if (isNaN(ratio) || ratio < 0 || ratio > 100) {
            errorField.style.display = 'block';
            submitButton.disabled = true;
            return;
        } else {
            errorField.style.display = 'none';
        }
    }

    // 원자재 코드 유효성 검사: 대문자 3글자
    if (fieldId === 'rmCode') {
        const rmRegex = /^[A-Z]{3}$/;
        if (!rmRegex.test(field.value.trim())) {
            errorField.style.display = 'block';
            submitButton.disabled = true;
            return;
        } else {
            errorField.style.display = 'none';
        }
    }

    // 공백 검증 (공통)
    if (field.value.trim() === '') {
        errorField.style.display = 'block';
        submitButton.disabled = true;
    } else {
        errorField.style.display = 'none';
        submitButton.disabled = false;
    }

    // 전체 폼 유효성 검사
    checkFormValidity();
}

// 글자 수 표시 업데이트 함수 (BOM 설명)
function updateCharacterCount() {
    const textarea = document.getElementById('bomDescription');
    const charCountDisplay = document.getElementById('charCount');
    const maxLength = 500; // 최대 글자 수 지정

    let currentLength = textarea.value.length;

    // 최대 글자 수 초과 처리: 초과 부분 자동 삭제
    if (currentLength > maxLength) {
        textarea.value = textarea.value.substring(0, maxLength);
        currentLength = maxLength;
    }

    // 글자 수를 동적으로 업데이트
    charCountDisplay.textContent = currentLength + "/" + maxLength;

    // 글자 수가 최대치(500자)에 도달하면 색상 변경
    if (currentLength === maxLength) {
        charCountDisplay.style.setProperty('color', '#dc3545', 'important');
    } else {
        charCountDisplay.style.removeProperty('color');
    }
    checkFormValidity();
}

// 전체 폼 유효성 검사
function checkFormValidity() {
    const submitButton = document.getElementById('submitBtn');

    const bomId = document.getElementById('bomId');
    const bomName = document.getElementById('bomName');
    const bomRatio = document.getElementById('bomRatio');
    const rmCode = document.getElementById('rmCode');
    const bomDescription = document.getElementById('bomDescription');

    // BOM ID 정규식 일치 여부
    const isBomIdValid = /^BOM[1-9][0-9]{0,2}$/.test(bomId.value.trim());
    // BOM 이름은 비어 있지 않아야 함
    const isBomNameValid = bomName.value.trim() !== '';
    // BOM 비율은 0 이상 100 이하의 숫자
    const ratioValue = parseInt(bomRatio.value, 10);
    const isBomRatioValid = !isNaN(ratioValue) && ratioValue >= 0 && ratioValue <= 100;
    // 원자재 코드는 대문자 3글자
    const isRmCodeValid = /^[A-Z]{3}$/.test(rmCode.value.trim());
    // BOM 설명은 500자 이하
    const isBomDescriptionValid = bomDescription.value.length <= 500;

    // 모든 필드가 유효하면 제출 버튼 활성화
    if (
        isBomIdValid &&
        isBomNameValid &&
        isBomRatioValid &&
        isRmCodeValid &&
        isBomDescriptionValid
    ) {
        submitButton.disabled = false;
    } else {
        submitButton.disabled = true;
    }
}

// 문서 로드 완료 후 이벤트 리스너 등록
document.addEventListener("DOMContentLoaded", function() {
    const bomIdField = document.getElementById('bomId');
    const bomNameField = document.getElementById('bomName');
    const bomRatioField = document.getElementById('bomRatio');
    const rmCodeField = document.getElementById('rmCode');
    const bomDescriptionField = document.getElementById('bomDescription');

    // 각 필드에 대한 'input' 이벤트에 유효성 검사 함수 연결
    bomIdField.addEventListener('input', () => validateInput('bomId'));
    bomNameField.addEventListener('input', () => validateInput('bomName'));
    bomRatioField.addEventListener('input', () => validateInput('bomRatio'));
    rmCodeField.addEventListener('input', () => validateInput('rmCode'));
    bomDescriptionField.addEventListener('input', () => updateCharacterCount());

    // BOM ID 필드 클릭 시 경고 메시지 표시
    bomIdField.addEventListener('click', function() {
        const bomIdWarning = document.getElementById('bomIdWarning');
        bomIdWarning.style.display = 'block';
    });

    // 다른 필드 클릭 시 경고 메시지 숨기기
    const otherFields = ['bomName', 'rmCode', 'bomRatio', 'bomDescription'];
    otherFields.forEach(function(fieldId) {
        const field = document.getElementById(fieldId);
        field.addEventListener('click', function() {
            const bomIdWarning = document.getElementById('bomIdWarning');
            bomIdWarning.style.display = 'none';
        });
    });
});

// 폼 제출 처리 함수
function submitForm(event) {
    event.preventDefault(); // 폼의 기본 제출 동작을 막음

    const form = document.getElementById('bomForm');
    const formData = new FormData(form);
    const bomData = {};

    // 폼 데이터를 JSON 형태로 변환
    formData.forEach((value, key) => {
        bomData[key] = value;
    });

    // 서버에 데이터 전송
    fetch('/api/billofmaterials', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bomData)
    })
    .then(response => {
        if (!response.ok) {
            // 오류 응답이 온 경우, 에러 메시지를 꺼내서 예외로 던집니다.
            return response.json().then(data => {
                throw new Error(data.message || '서버 응답이 정상적이지 않습니다.');
            });
        }
        // 정상 응답(2xx)이면 JSON 반환
        return response.json();
    })
    .then(data => {
        // 2xx 상태이므로 BOM 등록 성공
        showToast('BOM 등록 성공', 'success');
        document.getElementById("bomForm").reset(); // 폼 초기화
        checkFormValidity(); // 버튼 비활성화 갱신
    })
    .catch(error => {
        // 에러 응답이거나 통신 자체 문제 발생 시 처리
        showToast(error.message || '서버에 문제가 발생했습니다.', 'error');
    });
}