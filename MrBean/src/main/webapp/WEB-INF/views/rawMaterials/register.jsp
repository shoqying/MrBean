<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="원자재 등록"/>
<c:set var="sidebarTitle" value="기준정보관리"/>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<div class="container mt-5">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title mb-4">
                <i class="bi bi-pencil-square me-1"></i>원자재 등록
            </h5>

            <!-- 원자재 등록 폼 -->
            <form:form modelAttribute="rawMaterialsVO" method="POST" action="/rawMaterials/register" class="row g-3">

                <!-- 원자재 코드 -->
                <div class="col-sm-5">
                    <div class="form-floating mb-3">
                        <form:input path="rmCode" id="rmCode" type="text" class="form-control" placeholder="예: REB "/>
                        <label for="rmCode">원자재 코드</label>
                    </div>
                    <small id="rmCodeError" class="form-text text-danger" style="display: none;">원자재 코드를 입력해주세요.</small>
                </div>

                <!-- 원자재명 -->
                <div class="col-md-7">
                    <div class="form-floating mb-3">
                        <form:input path="rmName" id="rmName" type="text" class="form-control" placeholder="예: 원자재명" required="true" title="원자재명을 입력하세요."/>
                        <label for="rmName">원자재명</label>
                    </div>
                    <small id="rmNameError" class="form-text text-danger" style="display: none;">원자재명을 입력해주세요.</small>
                </div>

                <!-- 원산지 -->
                <div class="col-sm-5">
                    <div class="form-floating mb-3">
                        <form:input path="rmOrigin" id="rmOrigin" type="text" class="form-control" placeholder="예: 대한민국" required="true" title="원산지를 입력하세요."/>
                        <label for="rmOrigin">원산지</label>
                    </div>
                    <small id="rmOriginError" class="form-text text-danger" style="display: none;">원산지를 입력해주세요.</small>
                </div>

                <!-- 보관 방법 -->
                <div class="col-12">
                    <div class="form-floating mb-3">
                        <textarea class="form-control" id="rmStorageMethod" name="rmStorageMethod" placeholder="보관 방법에 대한 설명(최대 500자)" rows="4" autocomplete="off" style="height: 120px; resize: none; overflow-y: auto;" oninput="updateCharacterCount()" title="보관 방법에 대해 자세한 정보를 적어주세요."></textarea>
                        <label for="rmStorageMethod">보관 방법</label>
                    </div>
                    <small id="charCount" class="text-muted" style="float: right;">0/500</small>
                </div>

                <!-- 제출 및 초기화 버튼 -->
                <div class="col-12 text-center">
                    <button type="submit" class="btn btn-success me-2" title="필수 입력란을 모두 채운 뒤 등록을 진행하세요."><b><i class="bi bi-check-circle"></i> 등록</b></button>
                    <button type="reset" class="btn btn-secondary" title="입력란을 모두 초기화합니다."><b><i class="bi bi-arrow-counterclockwise me-1"></i> 초기화</b></button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/components/toast.js'/>"></script>
<script src="<c:url value='/resources/js/components/resetToast.js'/>"></script>
<script src="<c:url value='/resources/js/warehouse/validation.js'/>"></script>
<script>
    window.onload = function() {
        <c:if test="${not empty message}">
            showToast("${message}");
        </c:if>
    };
</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>