<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>창고 등록</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/toastStyle.css'/>">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bs5-toast@1.0.0"></script>
</head>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <h1 class="my-4">창고 등록</h1>

    <!-- 창고 등록 폼 -->
    <form id="warehouseForm" onsubmit="submitForm(event)">
        <div class="form-group">
            <label for="wCode">창고 코드</label>
            <input type="text" id="wCode" name="wCode" class="form-control" required autofocus autocomplete="off" oninput="validateInput('wCode')">
            <small id="wCodeError" class="form-text text-danger" style="display: none;">창고 코드는 A1~Z99 형식으로 입력해주세요. (예: A1, B99)</small>
        </div>

        <div class="form-group">
            <label for="wName">창고 이름</label>
            <input type="text" id="wName" name="wName" class="form-control" required autofocus autocomplete="off" oninput="validateInput('wName')">
            <small id="wNameError" class="form-text text-danger" style="display: none;">창고 이름을 입력해주세요.</small>
        </div>

        <!-- 주소 관련 필드들 -->
        <input type="button" onClick="openAddressPopup();" value="주소 검색" class="btn btn-secondary mb-3" />

        <div class="form-group">
            <label for="wRoadFullAddr">전체 도로명주소</label>
            <input type="text" id="wRoadFullAddr" name="wRoadFullAddr" class="form-control" required readonly autocomplete="off" oninput="validateInput('wRoadFullAddr')"/>
            <small id="wRoadFullAddrError" class="form-text text-danger" style="display: none;">도로명 주소를 입력해주세요.</small>
        </div>

        <div class="form-group">
            <label for="wAddrDetail">상세주소</label>
            <input type="text" id="wAddrDetail" name="wAddrDetail" class="form-control" required autocomplete="off" oninput="validateInput('wAddrDetail')"/>
            <small id="wAddrDetailError" class="form-text text-danger" style="display: none;">상세주소를 입력해주세요.</small>
        </div>

        <div class="form-group">
            <label for="wZipNo">우편번호</label>
            <input type="text" id="wZipNo" name="wZipNo" class="form-control" required readonly autocomplete="off" oninput="validateInput('wZipNo')"/>
            <small id="wZipNoError" class="form-text text-danger" style="display: none;">우편번호를 입력해주세요.</small>
        </div>

        <!-- 창고 설명 입력 필드 -->
        <div class="form-group">
            <label for="wDescription">창고 설명</label>
            <textarea id="wDescription" name="wDescription" class="form-control" rows="4" autocomplete="off" style="resize: none; overflow-y: auto;"></textarea>
            <small id="charCount" class="form-text text-muted" style="float: right;">0/500</small>
        </div>

        <!-- 제출 버튼 -->
        <button id="submitBtn" type="submit" class="btn btn-success" disabled>등록</button>
    </form>
</div>

<script src="<c:url value='/resources/js/toast.js'/>"></script>
<script src="<c:url value='/resources/js/warehouseFormValidation.js'/>"></script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</html>