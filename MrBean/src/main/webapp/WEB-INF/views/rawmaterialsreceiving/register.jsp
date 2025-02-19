<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="pageTitle" value="원자재 입고 등록"/>
<c:set var="sidebarTitle" value="창고관리"/>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-beta2/css/bootstrap.min.css">

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h5 class="card-title mb-4">
                <i class="bi bi-pencil-square me-1"></i> 원자재 입고 등록
            </h5>

            <!-- 원자재 입고 등록 폼 -->
            <form:form method="POST" action="/rawmaterialsreceiving/register" modelAttribute="rawMaterial" class="row g-4">

                <!-- 로트 번호 (자동 생성된 값 표시) -->
                <div class="col-md-6">
                    <label for="rmlNo" class="form-label">로트 번호</label>
                    <input type="text" id="rmlNo" name="rmlNo" value="${lotNo}" class="form-control" readonly />
                </div>

                <!-- 원자재 코드 -->
                <div class="col-md-6">
                    <label for="rmCode" class="form-label">원자재 코드</label>
                    <input type="text" id="rmCode" name="rmCode" class="form-control" required readonly />
                    <!-- 원자재 선택 버튼 -->
                    <button type="button" class="btn btn-outline-primary mt-2" data-bs-toggle="modal" data-bs-target="#rawMaterialModal">
                        원자재 선택
                    </button>
                </div>

                <!-- 입고 수량 -->
                <div class="col-md-6">
                    <label for="rrQuantity" class="form-label">입고 수량</label>
                    <input type="number" id="rrQuantity" name="rrQuantity" class="form-control" required />
                </div>

                <!-- 입고 단위 -->
                <div class="col-md-6">
                    <label for="rrUnit" class="form-label">입고 단위</label>
                    <input type="text" id="rrUnit" name="rrUnit" class="form-control" required />
                </div>

                <!-- 제출 버튼 -->
                <div class="col-12 text-center">
                    <button type="submit" class="btn btn-success btn-lg">입고 등록</button>
                </div>

            </form:form>
        </div>
    </div>
</div>

<!-- 원자재 선택 모달 -->
<div class="modal fade" id="rawMaterialModal" tabindex="-1" aria-labelledby="rawMaterialModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="rawMaterialModalLabel">원자재 선택</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-hover">
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
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-beta2/js/bootstrap.bundle.min.js"></script>
<script src="<c:url value='/resources/js/components/toast.js'/>"></script>
<script src="<c:url value='/resources/js/components/resetToast.js'/>"></script>
<script src="<c:url value='/resources/js/bom/createModal.js'/>"></script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
