<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 원하는 제목과 타이틀 설정 -->
<c:set var="pageTitle" value="자재 명세서"/>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
<section class="section">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">
                        <i class="bi bi-list-check me-1"></i><b>목록</b>
                    </h5>
                    <div class="col-lg-6 text-end">
                        <button class="btn btn-success" onclick="goToCreatePage()">
                            등록
                        </button>
                    </div>
                    <table class="table datatable table-hover">
                        <thead>
                            <tr>
                                <th>BOM ID</th>
                                <th>BOM명</th>
                                <th>원자재 코드</th>
                                <th>원자재 이름</th>
                                <th>원자재 비율</th>
                                <th>설명</th>
                                <th>기능</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="bom" items="${bomList}">
                                <tr>
                                    <td>${bom.bomId}</td>
                                    <td>${bom.bomName}</td>
                                    <td>${bom.rmCode}</td>
                                    <td>${bom.rmName}</td>
                                    <td>${bom.bomRatio}</td>
                                    <td>${bom.bomDescription}</td>
                                    <td>
                                       <!-- 수정 버튼 -->
                                       <button
                                         class="btn btn-sm btn-primary"
                                         onclick="editBOM('${bom.bomId}')">
                                         수정
                                       </button>
                                       <!-- 삭제 버튼 -->
                                       <button
                                         class="btn btn-sm btn-danger"
                                         onclick="deleteBom('${bom.bomId}')">
                                         삭제
                                       </button>
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

<section class="section">
</section>

<!-- 수정 모달 -->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">

      <div class="modal-header">
        <h5 class="modal-title" id="editModalLabel">BOM 수정</h5>
      </div>

      <!-- 모달 바디 -->
      <div class="modal-body">
        <!-- 필요한 필드들 -->
        <!-- 여기서부터는 자유롭게 원하시는 입력 양식을 구성하시면 됩니다. -->
        <div class="mb-3">
          <label for="editBomId" class="form-label">BOM ID</label>
          <input type="text" class="form-control" id="editBomId" readonly>
        </div>
        <div class="mb-3">
          <label for="editBomName" class="form-label">BOM명</label>
          <input type="text" class="form-control" id="editBomName">
        </div>
        <div class="mb-3">
          <label for="editRmName" class="form-label">원자재명</label>
          <select class="form-select" id="editRmName">
            <option selected disabled>원자재를 선택하세요</option>
            <!-- 서버에서 불러온 데이터로 옵션을 추가 -->
          </select>
        </div>
        <div class="mb-3">
          <label for="editBomRatio" class="form-label">원자재 비율</label>
          <input type="number" class="form-control" id="editBomRatio">
        </div>
        <div class="mb-3">
          <label for="editBomDescription" class="form-label">설명</label>
          <textarea class="form-control" id="editBomDescription" rows="3"></textarea>
        </div>
      </div>

      <!-- 모달 풋터 -->
      <div class="modal-footer">
        <button
          type="button"
          class="btn btn-secondary"
          data-bs-dismiss="modal"
        >
          닫기
        </button>
        <button
          type="button"
          class="btn btn-primary"
          onclick="submitEditForm()"
        >
          수정
        </button>
      </div>

    </div>
  </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="<c:url value='/resources/js/components/toast.js'/>"></script>
<script src="<c:url value='/resources/js/components/resetToast.js'/>"></script>
<script src="<c:url value='/resources/js/bom/editModal.js'/>"></script>
<script type="text/javascript">
function goToCreatePage() {
  // billofmaterials/create로 이동
  window.location.href = '<c:url value="/billofmaterials/create"/>';
}
</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</html>