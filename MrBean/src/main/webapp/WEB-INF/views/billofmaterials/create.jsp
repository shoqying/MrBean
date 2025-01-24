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
    <script src="https://cdn.jsdelivr.net/npm/bs5-toast@1.0.0"></script>
</head>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

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

        <!-- 원자재 코드 -->
        <div class="form-group">
            <label for="rmCode">원자재 코드</label>
            <div class="input-group">
                <input
                    type="text"
                    id="rmCode"
                    name="rmCode"
                    class="form-control"
                    required
                    autocomplete="off"
                    readonly
                    oninput="validateInput('rmCode')"
                >
                <div class="input-group-append">
                    <!-- '원자재 선택' 버튼 클릭 시 모달 오픈 -->
                    <button type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#rawMaterialModal">
                        원자재 선택
                    </button>
                </div>
            </div>
            <small id="rmCodeError" class="form-text text-danger" style="display: none;">
                원자재 코드는 대문자 3글자로 입력해주세요. (예: ABC)
            </small>
        </div>

        <!-- 원자재 비율 -->
        <div class="form-group">
            <label for="bomRatio">원자재 비율</label>
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
                oninput="updateCharacterCount()"
            ></textarea>
            <small id="charCount" class="form-text text-muted text-right">0/500</small>
        </div>

        <!-- 제출 버튼 -->
        <button id="submitBtn" type="submit" class="btn btn-success" disabled>등록</button>
    </form>
</div>

<!-- 원자재 선택 모달 -->
<div class="modal fade" id="rawMaterialModal" tabindex="-1" role="dialog" aria-labelledby="rawMaterialModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="rawMaterialModalLabel">원자재 선택</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span>&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <!-- 원자재 목록 테이블 -->
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>원자재 코드</th>
                    <th>원자재 이름</th>
                    <th>설명</th>
                    <th>선택</th>
                </tr>
            </thead>
            <tbody id="rawMaterialTableBody">
                <!-- Ajax 결과로 동적 생성 -->
            </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <!-- 모달 창 닫기 버튼 -->
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<!-- JQuery, Bootstrap JS (Modal에 필요) -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/toast.js'/>"></script>
<!-- 모달 창 스크립트 -->
<script src="<c:url value='/resources/js/billOfMaterialsModal.js'/>"></script>
<!-- 유효성 검사 스크립트 -->
<script src="<c:url value='/resources/js/billOfMaterialsValidation.js'/>"></script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</html>