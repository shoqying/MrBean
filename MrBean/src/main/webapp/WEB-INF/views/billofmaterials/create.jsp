<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="자재 명세서 등록"/>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title mb-4">
            <i class = "bi bi-pencil-square me-1"></i>BOM 등록</h5>

            <!-- BOM 등록 폼 -->
            <form id="bomForm" class="row g-3" onsubmit="submitForm(event)">

                <!-- BOM ID -->
                <div class="col-sm-5">
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            id="bomId"
                            name="bomId"
                            placeholder="예: BOM123"
                            value="${nextBOMId}"
                            required
                            autocomplete="off"
                            readonly
                            oninput="validateInput('bomId')"
                            title="자동 생성된 BOM ID입니다."
                        />
                        <label for="bomId">BOM ID</label>
                    </div>
                    <small
                        id="bomIdError"
                        class="form-text text-danger"
                        style="display: none;"
                    >
                        BOM ID는 'BOM1' 형식처럼 입력해주세요. (예: BOM1 ~ BOM999)
                    </small>
                    <small id="bomIdWarning" class="text-danger" style="display:none;">BOM ID는 변경할 수 없습니다.</small>
                </div>

                <!-- BOM 이름 -->
                <div class="col-md-7">
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            id="bomName"
                            name="bomName"
                            placeholder="예: 제품 제작용 BOM"
                            required
                            autocomplete="off"
                            oninput="validateInput('bomName')"
                            title="BOM의 이름 또는 용도를 입력하세요."
                        />
                        <label for="bomName">BOM 이름</label>
                    </div>
                    <small id="bomIdWarning" class="text-danger" style="display:none;">BOM ID는 변경할 수 없습니다.</small>
                    <small
                        id="bomNameError"
                        class="form-text text-danger"
                        style="display: none;"
                    >
                        BOM 이름을 입력해주세요. (최대 100자)
                    </small>
                </div>

                <!-- 원자재 코드 + 모달 버튼 -->
                <div class="col-sm-5">
                    <div class="form-floating mb-3">
                        <input
                            type="text"
                            class="form-control"
                            id="rmCode"
                            name="rmCode"
                            placeholder="예: ABC"
                            required
                            autocomplete="off"
                            readonly
                            oninput="validateInput('rmCode')"
                            title="원자재 코드가 자동 입력됩니다."
                        />
                        <label for="rmCode">원자재 코드</label>
                    </div>
                    <div class="mb-2">
                        <button
                            type="button"
                            class="btn btn-outline-secondary"
                            data-toggle="modal"
                            data-target="#rawMaterialModal"
                            title="모달에서 원자재 목록을 확인하고 선택하세요."
                        >
                            원자재 선택
                        </button>
                    </div>
                    <small
                        id="rmCodeError"
                        class="form-text text-danger"
                        style="display: none;"
                    >
                        원자재 코드는 대문자 3글자로 입력해주세요. (예: ABC)
                    </small>
                </div>

                <!-- 원자재 비율 (% 기호 고정) -->
                <div class="col-sm-7">
                    <!-- input-group을 활용해 오른쪽에 % 기호 표시 -->
                    <div class="input-group mb-3">
                        <div class="form-floating flex-grow-1">
                            <input
                                type="number"
                                class="form-control"
                                id="bomRatio"
                                name="bomRatio"
                                placeholder="예: 50"
                                required
                                min="0"
                                max="100"
                                oninput="validateInput('bomRatio')"
                                title="0~100 사이 숫자를 입력하세요."
                            />
                            <label for="bomRatio">원자재 비율</label>
                        </div>
                        <!-- % 기호 표시 -->
                        <span class="input-group-text">%</span>
                    </div>
                    <small
                        id="bomRatioError"
                        class="form-text text-danger"
                        style="display: none;"
                    >
                        BOM 비율은 0 이상 100 이하로 입력해주세요.
                    </small>
                </div>

                <!-- BOM 설명 -->
                <div class="col-12">
                    <div class="form-floating mb-3">
                        <textarea
                            class="form-control"
                            id="bomDescription"
                            name="bomDescription"
                            placeholder="BOM에 대한 상세 설명(최대 500자)"
                            rows="4"
                            autocomplete="off"
                            style="height: 120px; resize: none; overflow-y: auto;"
                            oninput="updateCharacterCount()"
                            title="BOM에 대해 자세한 정보를 적어주세요."
                        ></textarea>
                        <label for="bomDescription">BOM 설명</label>
                    </div>
                    <!-- 글자수 오른쪽 정렬 -->
                    <small id="charCount" class="text-muted" style="float: right;">
                        0/500
                    </small>
                </div>

                <!-- 제출 및 초기화 버튼 (중앙 정렬) -->
                <div class="col-12 text-center">
                    <button
                        id="submitBtn"
                        type="submit"
                        class="btn btn-success me-2"
                        disabled
                        title="필수 입력란을 모두 채운 뒤 등록을 진행하세요."
                    >
                    <b><i class="bi bi-check-circle"></i> 등록 </b>
                    </button>
                    <button
                        type="reset"
                        class="btn btn-secondary"
                        title="입력란을 모두 초기화합니다."
                    >
                    <b><i class="bi bi-arrow-counterclockwise me-1"></i>초기화</b>
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>

<!-- 원자재 선택 모달 -->
<div
    class="modal fade"
    id="rawMaterialModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="rawMaterialModalLabel"
    aria-hidden="true"
>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="rawMaterialModalLabel">원자재 선택</h5>
            </div>
            <div class="modal-body">
                <!-- 원자재 목록 테이블 -->
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>원자재 코드</th>
                            <th>원자재 이름</th>
                            <th>원산지</th>
                            <th>보관방법</th>
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
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    닫기
                </button>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/components/toast.js'/>"></script>
<script src="<c:url value='/resources/js/components/resetToast.js'/>"></script>
<script src="<c:url value='/resources/js/bom/createModal.js'/>"></script>
<script src="<c:url value='/resources/js/bom/validation.js'/>"></script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>