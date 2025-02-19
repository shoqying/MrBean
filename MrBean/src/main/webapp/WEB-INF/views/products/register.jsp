<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="pageTitle" value="완제품 등록"/>
<c:set var="sidebarTitle" value="기준정보관리"/>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<div class="container mt-5">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title mb-4">
                <i class="bi bi-pencil-square me-1"></i>완제품 등록
            </h5>

            <!-- 제품 등록 폼 -->
            <form:form modelAttribute="product" method="POST" action="/products/register" class="row g-3">

                <!-- BOM 선택 -->
                <div class="col-12">
                    <div class="form-floating mb-3">
                        <form:select path="bomId" id="bomId" class="form-control" required="true">
                            <option value="" selected disabled>-- 선택하세요 --</option>
                            <c:forEach var="bom" items="${bomList}">
                                <option value="${bom.bomId}">${bom.bomName}</option>
                            </c:forEach>
                        </form:select>
                        <label for="bomId">BOM 선택</label>
                    </div>
                    <small id="bomIdError" class="form-text text-danger" style="display: none;">BOM을 선택해주세요.</small>
                </div>

                <!-- 제품 코드 -->
                <div class="col-sm-5">
                    <div class="form-floating mb-3">
                        <form:input path="pCode" id="pCode" type="text" class="form-control" placeholder="예: P123" required="true" title="제품 코드를 입력하세요."/>
                        <label for="pCode">제품 코드</label>
                    </div>
                    <small id="pCodeError" class="form-text text-danger" style="display: none;">제품 코드를 입력해주세요.</small>
                </div>

                <!-- 제품명 -->
                <div class="col-md-7">
                    <div class="form-floating mb-3">
                        <form:input path="pName" id="pName" type="text" class="form-control" placeholder="예: 제품명" required="true" title="제품명을 입력하세요."/>
                        <label for="pName">제품명</label>
                    </div>
                    <small id="pNameError" class="form-text text-danger" style="display: none;">제품명을 입력해주세요.</small>
                </div>

                <!-- 제품 설명 -->
                <div class="col-12">
                    <div class="form-floating mb-3">
                        <textarea class="form-control" id="pDescription" name="pDescription" placeholder="제품 설명(최대 500자)" rows="4" autocomplete="off" style="height: 120px; resize: none; overflow-y: auto;" oninput="updateCharacterCount()" title="제품 설명을 입력하세요."></textarea>
                        <label for="pDescription">제품 설명</label>
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