<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>창고 등록</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-pzjw8f+ua7Kw1TIq0r9+ttL6Ex1v8YcDggp61Gb7mXr8gf6aT3b2n0Gz57ydwK4h" crossorigin="anonymous">
</head>
<script language="javascript" >
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다.
// (＂팝업 API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";
function goPopup(){
//경로는 시스템에 맞게 수정하여 사용
//호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를
//호출하게 됩니다.
var pop = window.open("http://localhost:8080/popup/jusoPopup", "pop","width=570,height=420, scrollbars=yes, resizable=yes");
//** 2017년 5월 모바일용 팝업 API 기능 추가제공 **/
// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서
// 실제 주소검색 URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
// var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes");
}
function jusoCallBack(roadFullAddr, roadAddrPart1, addrDetail, roadAddrPart2, engAddr, jibunAddr, zipNo, admCd,
    rnMgtSn, bdMgtSn, detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm,
    buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){

    // 2017년 2월 제공항목이 추가되었습니다. 원하시는 항목을 추가하여 사용하시면 됩니다.
    document.form.roadFullAddr.value = roadFullAddr;
    document.form.roadAddrPart1.value = roadAddrPart1;
    document.form.roadAddrPart2.value = roadAddrPart2;
    document.form.addrDetail.value = addrDetail;
    document.form.zipNo.value = zipNo;

    // 팝업을 닫기 위한 코드 추가
    if (window.opener) {
        window.opener.document.form.roadFullAddr.value = roadFullAddr;
        window.opener.document.form.roadAddrPart1.value = roadAddrPart1;
        window.opener.document.form.roadAddrPart2.value = roadAddrPart2;
        window.opener.document.form.addrDetail.value = addrDetail;
        window.opener.document.form.zipNo.value = zipNo;
        window.close(); // 팝업을 닫는다
    } else {
        console.error("opener가 존재하지 않습니다. 팝업을 닫을 수 없습니다.");
    }
}
</script>
<body>
<div class="container mt-5">
    <button class="btn btn-primary" onclick="location.href='/dashboard';">대시보드로 돌아가기</button>
    <h1 class="my-4">창고 등록</h1>
<!-- 두 번째 form 태그를 제거하고 첫 번째 form 안에 추가 필드를 넣습니다. -->
<form action="registerWarehouse" method="post" name="form" id="form">
    <!-- 창고 코드, 이름 등 다른 필드들은 이 곳에 포함 -->
    <div class="form-group">
        <label for="warehouseCode">창고 코드</label>
        <input type="text" id="warehouseCode" name="warehouseCode" class="form-control" required autofocus>
    </div>

    <div class="form-group">
        <label for="warehouseName">창고 이름</label>
        <input type="text" id="warehouseName" name="warehouseName" class="form-control" required autofocus>
    </div>

    <!-- 주소 관련 필드들 -->
    <input type="button" onClick="goPopup();" value="주소 검색 팝업" />
    <div class="form-group">
        <label for="roadFullAddr">도로명주소 전체</label>
        <input type="text" id="roadFullAddr" name="roadFullAddr" class="form-control" /><br>
    </div>
    <div class="form-group">
        <label for="roadAddrPart1">도로명주소</label>
        <input type="text" id="roadAddrPart1" name="roadAddrPart1" class="form-control" /><br>
    </div>
    <div class="form-group">
        <label for="addrDetail">상세주소</label>
        <input type="text" id="addrDetail" name="addrDetail" class="form-control" /><br>
    </div>
    <div class="form-group">
        <label for="roadAddrPart2">참고주소</label>
        <input type="text" id="roadAddrPart2" name="roadAddrPart2" class="form-control" /><br>
    </div>
    <div class="form-group">
        <label for="zipNo">우편번호</label>
        <input type="text" id="zipNo" name="zipNo" class="form-control" /><br>
    </div>

    <!-- 창고 설명 필드 -->
    <div class="form-group">
        <label for="warehouseDescription">창고 설명</label>
        <input type="text" id="warehouseDescription" name="warehouseDescription" class="form-control" required>
    </div>

    <!-- Submit button -->
    <button type="submit" class="btn btn-success">등록</button>
</form>
</div>

<!-- Bootstrap JS and dependencies (Optional, if you want Bootstrap JavaScript features) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zyQ5Fy6Y30t4xPFlpX2fYw2P6ybEckK+HVW7X1Pt" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-B4gt1jrGC7Jh4Ag4h5T2lXduZ/xPqZjxdUoFVWrhWfakHhWVf1jr5ldD2MrjAsLx" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0r9+ttL6Ex1v8YcDggp61Gb7mXr8gf6aT3b2n0Gz57ydwK4h" crossorigin="anonymous"></script>
</body>
</html>