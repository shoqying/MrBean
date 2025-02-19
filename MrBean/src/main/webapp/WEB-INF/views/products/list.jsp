<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="완제품 목록"/>
<c:set var="sidebarTitle" value="기준정보관리"/>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<section class="section">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h5 class="card-title">
                            <i class="bi bi-list-check me-1"></i><b>목록</b>
                        </h5>
                        <a href="${pageContext.request.contextPath}/products/register" class="btn btn-success">
                            완제품 등록
                        </a>
                    </div>
                    <table class="table datatable table-hover">
                        <thead>
                            <tr>
                                <th>완제품 코드</th>
                                <th>완제품 이름</th>
                                <th>상세 설명</th>
                                <th>BOM ID</th>
                                <th>기능</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" items="${productList}">
                                <tr>
                                    <td>${product.PCode}</td>
                                    <td>${product.PName}</td>
                                    <td>${product.PDescription}</td>
                                    <td>
                                        <button type="button" class="btn btn-link" onclick="openBomInfoModal('${product.bomId}')">
                                            ${product.bomId}
                                        </button>
                                    </td>
                                    <td>
                                        <button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#editModal"
                                                onclick="openEditModal('${product.PCode}', '${product.PName}', '${product.PDescription}', '${product.bomId}')">
                                            수정
                                        </button>
                                        <form action="${pageContext.request.contextPath}/products/delete" method="post" style="display:inline;">
                                            <input type="hidden" name="pCode" value="${product.PCode}">
                                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('정말 삭제하시겠습니까?');">
                                                <i class="bi bi-trash"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- 수정 모달 -->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">완제품 수정</h5>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="pCode" class="form-label">완제품 코드</label>
                    <input type="text" class="form-control" id="pCode" name="pCode" readonly>
                </div>
                <div class="mb-3">
                    <label for="pName" class="form-label">완제품 이름</label>
                    <input type="text" class="form-control" id="pName" name="pName" required>
                </div>
                <div class="mb-3">
                    <label for="bomId" class="form-label">BOM ID</label>
                    <select id="bomId" name="bomId" class="form-control" readonly>
                        <c:forEach var="bom" items="${bomList}">
                            <option value="${bom.bomId}">${bom.bomId}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="pDescription" class="form-label">상세 설명</label>
                    <input type="text" class="form-control" id="pDescription" name="pDescription" required>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="editButton" onclick="submitEditForm()">수정</button>
            </div>
        </div>
    </div>
</div>

<!-- BOM 정보 모달 -->
<div class="modal fade" id="editBomModal" tabindex="-1" aria-labelledby="editBomModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content shadow-lg border-0 rounded-4">
            <div class="modal-header bg-primary text-white rounded-top-4">
                <h5 class="modal-title fw-bold" id="editBomModalLabel">BOM 정보</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body p-4">
                <form id="bomInfoForm">
                    <div class="mb-3">
                        <label for="bomName" class="form-label fw-semibold">BOM 이름</label>
                        <input type="text" id="bomName" class="form-control bg-light border-0 rounded-3" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="rmCode" class="form-label fw-semibold">원자재 코드</label>
                        <input type="text" id="rmCode" class="form-control bg-light border-0 rounded-3" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="bomRatio" class="form-label fw-semibold">비율</label>
                        <input type="text" id="bomRatio" class="form-control bg-light border-0 rounded-3" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="bomDescription" class="form-label fw-semibold">설명</label>
                        <input type="text" id="bomDescription" class="form-control bg-light border-0 rounded-3" readonly>
                    </div>
                </form>
            </div>
            <div class="modal-footer border-0 pb-4">
                <button type="button" class="btn btn-outline-secondary rounded-3 px-4" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/bom/infoModal.js'/>"></script>
<script src="<c:url value='/resources/js/components/toast.js'/>"></script>
<script src="<c:url value='/resources/js/components/resetToast.js'/>"></script>
<script>
    window.onload = function() {
        <c:if test="${not empty message}">
            showToast("${message}");
        </c:if>
    };

    function openEditModal(pCode, pName, pDescription, bomId) {
        document.getElementById('pCode').value = pCode;
        document.getElementById('pName').value = pName;
        document.getElementById('pDescription').value = pDescription;
        document.getElementById('bomId').value = bomId;
        $('#editModal').modal('show');
    }

    function closeEditModal() {
        $('#editModal').modal('hide');
    }
</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</html>
