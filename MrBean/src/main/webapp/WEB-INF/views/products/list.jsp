<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="완제품 리스트"/>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <div class="container">
        <h1 class="mt-5 text-center">완제품 목록</h1>

        <table class="table datatable">
        <thead class="text-center">
                <tr>
                    <th>완제품 코드</th>
                    <th>완제품 이름</th>
                    <th>상세 설명</th>
                    <th>BOM ID</th>
                    <th>처리</th>
                </tr>
            </thead>
            <tbody>
                <!-- 완제품 목록을 반복하여 출력 -->
                <c:forEach var="product" items="${productList}">
                    <tr class="text-center">
                        <td>${product.PCode}</td>
                        <td>${product.PName}</td>
                        <td>${product.PDescription}</td>  
                        <td>
                            <!-- BOM ID 텍스트 클릭 시 모달 띄우기 -->
                            <button type="button" class="btn btn-link" onclick="openBomInfoModal('${product.bomId}')">
    							${product.bomId}
							</button>
                        </td>
                        <td>
                            <!-- 수정 버튼 -->
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal"
                                    onclick="openEditModal('${product.PCode}', '${product.PName}', '${product.PDescription}', '${product.bomId}')">
                                수정
                            </button>
                            <!-- 삭제 버튼 -->
                            <form action="${pageContext.request.contextPath}/products/delete" method="post" style="display:inline;">
                                <input type="hidden" name="pCode" value="${product.PCode}">
                                <button type="submit" class="btn btn-danger" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- 대시보드 버튼과 등록 페이지로 돌아가는 버튼을 폼 바로 밑에 배치 -->
        <div class="d-flex justify-content-between align-items-center mt-3">
<!--             대시보드 버튼 -->
<%--             <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-info">대시보드</a> --%>
            
            <!-- 등록 페이지로 돌아가는 버튼 -->
            <a href="${pageContext.request.contextPath}/products/register" class="btn btn-primary">등록 페이지</a>
        </div>
    </div>

    <!-- 수정 모달 -->
    <div class="modal" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">완제품 수정</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- 수정 폼 -->
                    <form id="updateForm" action="${pageContext.request.contextPath}/products/update" method="post">
                        <div class="form-group">
                            <label for="pCode">완제품 코드:</label>
                            <input type="text" id="pCode" name="pCode" value="" readonly class="form-control">
                        </div>

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
                                    <option value="${bom.bomId}" ${bom.bomId == product.bomId ? 'selected' : ''}>${bom.bomId}</option>
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
<div class="modal" id="editBomModal" tabindex="-1" role="dialog" aria-labelledby="editBomModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editBomModalLabel">BOM 정보</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- BOM 정보 폼 -->
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
