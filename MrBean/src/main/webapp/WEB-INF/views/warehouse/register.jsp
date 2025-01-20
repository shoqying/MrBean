<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>창고 등록</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"></head>
    <link rel="stylesheet" href="<c:url value='/resources/css/toastStyle.css'/>">
    <script src="<c:url value='/resources/scripts/toast.js'/>"></script>
<body>
<div class="container mt-5">
    <button class="btn btn-primary" onclick="location.href='/dashboard';">대시보드로 돌아가기</button>
    <h1 class="my-4">창고 등록</h1>

    <!-- 창고 등록 폼 -->
    <form id="warehouseForm" onsubmit="submitForm(event)">
        <div class="form-group">
            <label for="wCode">창고 코드</label>
            <input type="text" id="wCode" name="wCode" class="form-control" required autofocus autocomplete="off">
        </div>

        <div class="form-group">
            <label for="wName">창고 이름</label>
            <input type="text" id="wName" name="wName" class="form-control" required autofocus autocomplete="off">
        </div>

        <!-- 주소 관련 필드들 -->
        <input type="button" onClick="openAddressPopup();" value="주소 검색 팝업" class="btn btn-secondary mb-3" />

        <div class="form-group">
            <label for="wRoadFullAddr">전체 도로명주소</label>
            <input type="text" id="wRoadFullAddr" name="wRoadFullAddr" class="form-control" required readonly autocomplete="off" />
        </div>

        <div class="form-group">
            <label for="wAddrDetail">상세주소</label>
            <input type="text" id="wAddrDetail" name="wAddrDetail" class="form-control" required autocomplete="off"/>
        </div>

        <div class="form-group">
            <label for="wZipNo">우편번호</label>
            <input type="text" id="wZipNo" name="wZipNo" class="form-control" required readonly autocomplete="off"/>
        </div>

        <!-- 창고 설명 필드 -->
        <div class="form-group">
            <label for="wDescription">창고 설명</label>
            <input type="text" id="wDescription" name="wDescription" class="form-control" autocomplete="off">
        </div>

        <!-- 제출 버튼 -->
        <button type="submit" class="btn btn-success">등록</button>
    </form>
</div>

<!-- Bootstrap JS 및 의존성 (선택사항) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- JavaScript -->
<script type="text/javascript">
// 팝업을 열기 위한 함수
function openAddressPopup() {
    window.open("/popup/jusoPopup", "pop", "width=570,height=420, scrollbars=yes, resizable=yes");
}

// 주소 콜백 함수
function jusoCallBack(roadFullAddr, addrDetail, zipNo) {
    // 주소 값들을 해당 폼 필드에 입력
    document.getElementById('wRoadFullAddr').value = roadFullAddr;
    document.getElementById('wAddrDetail').value = addrDetail;
    document.getElementById('wZipNo').value = zipNo;
}

// 폼 제출을 처리하는 함수
function submitForm(event) {
    event.preventDefault();  // 폼의 기본 제출 동작을 막음
    const form = document.getElementById('warehouseForm');
    const formData = new FormData(form);
    const warehouseData = {};

    // 폼 데이터를 JSON으로 변환
    formData.forEach((value, key) => {
        warehouseData[key] = value;
    });

    // 전송되는 JSON 데이터 확인을 위한 로그
    console.log("Sending data:", JSON.stringify(warehouseData)); // 데이터가 잘 생성되었는지 확인

    // Fetch API를 이용하여 서버에 데이터 전송
    fetch('/api/warehouses', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(warehouseData)
    })
    .then((response) => {
        // 서버 응답 상태 확인 (HTTP 200 이상)
        if (!response.ok) {
            // 서버가 2xx 상태 코드가 아닌 응답을 반환하면 오류 메시지 출력
            throw new Error(`서버 오류: ${response.status} ${response.statusText || 'Unknown error'}`);
        }
        return response.json(); // 정상적인 응답이면 JSON 파싱
    })
    .then((data) => {
        if (data.success) {
            showToast(data.message, "success");
            form.reset(); // 폼 초기화
        } else {
            showToast(data.message, "error");
        }
    })
    .catch((error) => {
        // Fetch 오류 또는 JSON 파싱 오류 처리
        showToast(error.message || "서버 요청 중 오류가 발생했습니다.", "error");
        console.error("Error:", error);
    });
}
</script>

</body>
</html>