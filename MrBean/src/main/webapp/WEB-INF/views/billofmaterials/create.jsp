<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>BOM 등록</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/toastStyle.css'/>">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bs5-toast@1.0.0"></script>
</head>
<body>
<div class="container mt-5">
    <!-- 대시보드 돌아가기 버튼 -->
    <button class="btn btn-primary" onclick="location.href='/dashboard';">대시보드로 돌아가기</button>

    <h1 class="my-4">BOM 등록</h1>

    <!-- BOM 등록 폼 -->
    <form id="bomForm" onsubmit="submitForm(event)">

        <!-- BOM ID -->
        <div class="form-group">
            <label for="bomId">BOM ID</label>
            <input
                type="text"
                id="bomId"
                name="bomId"
                class="form-control"
                value="${nextBOMId}"
                readonly
                required
                autofocus
                autocomplete="off"
                oninput="validateInput('bomId')"
            >
            <small id="bomIdError" class="form-text text-danger" style="display: none;">
                BOM ID는 'BOM1' 형식처럼 입력해주세요. (예: BOM1 ~ BOM999)
            </small>
        </div>

        <!-- BOM 이름 -->
        <div class="form-group">
            <label for="bomName">BOM 이름</label>
            <input
                type="text"
                id="bomName"
                name="bomName"
                class="form-control"
                required
                autocomplete="off"
                oninput="validateInput('bomName')"
            >
            <small id="bomNameError" class="form-text text-danger" style="display: none;">
                BOM 이름을 입력해주세요. (최대 100자)
            </small>
        </div>

        <!-- BOM 비율 -->
        <div class="form-group">
            <label for="bomRatio">BOM 비율</label>
            <input
                type="number"
                id="bomRatio"
                name="bomRatio"
                class="form-control"
                required
                min="0"
                max="100"
                oninput="validateInput('bomRatio')"
            >
            <small id="bomRatioError" class="form-text text-danger" style="display: none;">
                BOM 비율은 0 이상 100 이하로 입력해주세요.
            </small>
        </div>

        <!-- 원자재 코드 -->
        <div class="form-group">
            <label for="rmCode">원자재 코드</label>
            <input
                type="text"
                id="rmCode"
                name="rmCode"
                class="form-control"
                required
                autocomplete="off"
                oninput="validateInput('rmCode')"
            >
            <small id="rmCodeError" class="form-text text-danger" style="display: none;">
                원자재 코드는 대문자 3글자로 입력해주세요. (예: ABC)
            </small>
        </div>

        <!-- BOM 설명 -->
        <div class="form-group">
            <label for="bomDescription">BOM 설명</label>
            <textarea
                id="bomDescription"
                name="bomDescription"
                class="form-control"
                rows="4"
                autocomplete="off"
                style="resize: none; overflow-y: auto;"
                oninput="validateCharCount('bomDescription', 500)"
            ></textarea>
            <small id="charCount" class="form-text text-muted text-right">0/500</small>
        </div>

        <!-- 제출 버튼 -->
        <button id="submitBtn" type="submit" class="btn btn-success" disabled>등록</button>
    </form>
</div>

<!-- 유효성 검사 스크립트 (예시) -->
<script src="<c:url value='/resources/js/billOfMaterialsValidation.js'/>"></script>
</body>
</html>