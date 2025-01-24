<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>BOM</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/toastStyle.css'/>">
    <script src="https://cdn.jsdelivr.net/npm/bs5-toast@1.0.0"></script>
</head>


<%@ include file="/WEB-INF/views/include/header.jsp" %>
    <!-- 헤더와 검색창을 옆으로 배치 -->
    <div class="d-flex justify-content-between align-items-center">
        <h1>BOM</h1>
        <!-- 검색 입력 필드 -->
        <div class="form-inline">
            <input type="text" class="form-control" id="searchInput" placeholder="검색어 입력...">
        </div>
    </div>

<table class="table datatable table-hover">
        <thead>
        <tr>
            <th>BOM ID</th>
            <th>원자재 코드</th>
            <th>BOM 이름</th>
            <th>BOM 비율</th>
            <th>BOM Description</th>
            <th>RM Name</th>
            <th>기능</th>
        </tr>
        </thead>
        <tbody>
            <!-- 예시: Controller에서 전달받은 bomList -->
            <c:forEach var="bom" items="${bomList}">
                <tr>
                    <td>${bom.bomId}</td>
                    <td>${bom.rmCode}</td>
                    <td>${bom.bomName}</td>
                    <td>${bom.bomRatio}</td>
                    <td>${bom.bomDescription}</td>
                    <td>${bom.rmName}</td>
                    <td>
                        <button class="btn btn-sm btn-primary">수정</button>
                        <button class="btn btn-sm btn-danger">삭제</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="mt-2">
        <button class="btn btn-success" onclick="goToCreatePage()">등록</button>
    </div>
</div>
    <!-- 수정 모달 -->
    <div class="modal fade" id="editModal" tabindex="-1" role="dialog"
         aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <h5 id="editModalLabel" class="modal-title">BOM 수정</h5>
                    <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body">
                    <!-- BOM 수정에 필요한 필드 -->
                    <input type="hidden" id="editBomId">

                    <div class="form-group">
                        <label for="editBomName">BOM Name</label>
                        <input type="text" class="form-control" id="editBomName">
                    </div>

                    <div class="form-group">
                        <label for="editRmCode">RM Code</label>
                        <input type="text" class="form-control" id="editRmCode">
                    </div>

                    <div class="form-group">
                        <label for="editBomRatio">BOM Ratio</label>
                        <input type="number" class="form-control" id="editBomRatio">
                    </div>

                    <div class="form-group">
                        <label for="editBomDescription">BOM Description</label>
                        <textarea class="form-control" id="editBomDescription" rows="3"></textarea>
                    </div>
                </div>

                <div class="modal-footer">
                    <button
                        type="button"
                        class="btn btn-secondary"
                        data-dismiss="modal">
                        닫기
                    </button>
                    <button
                        type="button"
                        class="btn btn-primary"
                        onclick="submitEditForm()">
                        수정
                    </button>
                </div>

            </div>
        </div>
    </div>
<script type="text/javascript">
function goToCreatePage() {
  // billofmaterials/create로 이동
  window.location.href = '<c:url value="/billofmaterials/create"/>';
}
</script>
<!-- JQuery, Bootstrap JS (Modal에 필요) -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/toast.js'/>"></script>
<!-- 모달 창 스크립트 -->
<script src="<c:url value='/resources/js/billOfMaterialsModal.js'/>"></script>
<!-- 정렬 스크립트 -->
<script src="<c:url value='/resources/js/sort.js'/>"></script>
<!-- 필터 스크립트 -->
<script src="<c:url value='/resources/js/filter.js'/>"></script>
<!-- 유효성 검사 스크립트 -->
<script src="<c:url value='/resources/js/bomModal.js'/>"></script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</html>