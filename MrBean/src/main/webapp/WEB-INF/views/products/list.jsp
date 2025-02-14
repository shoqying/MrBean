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
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">완제품 수정</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="updateForm" action="${pageContext.request.contextPath}/products/update" method="post">
                    <input type="hidden" id="pCode" name="pCode" value="">
                    <div class="form-group">
                        <label for="pName">완제품 이름:</label>
                        <input type="text" id="pName" name="pName" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="pDescription">상세 설명:</label>
                        <input type="text" id="pDescription" name="pDescription" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="bomId">BOM ID:</label>
                        <select id="bomId" name="bomId" class="form-control" required>
                            <c:forEach var="bom" items="${bomList}">
                                <option value="${bom.bomId}">${bom.bomId}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-success">수정</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- BOM 정보 모달 -->
<div class="modal fade" id="editBomModal" tabindex="-1" role="dialog" aria-labelledby="editBomModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editBomModalLabel">BOM 정보</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="bomInfoForm">
                    <div class="form-group">
                        <label for="bomName">BOM 이름:</label>
                        <input type="text" id="bomName" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                        <label for="rmCode">원자재 코드:</label>
                        <input type="text" id="rmCode" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                        <label for="bomRatio">비율:</label>
                        <input type="text" id="bomRatio" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                        <label for="bomDescription">설명:</label>
                        <input type="text" id="bomDescription" class="form-control" readonly>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
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
</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</html>
