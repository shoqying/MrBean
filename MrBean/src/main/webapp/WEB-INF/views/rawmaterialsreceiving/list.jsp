<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="pageTitle" value="원자재 입고 목록"/>
<c:set var="sidebarTitle" value="창고관리"/>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section class="section">
    <div class="container mt-4">
        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h5 class="mb-0">
                    <i class="bi bi-list-check me-1"></i>
                    <b>${pageTitle}</b>
                </h5>
                <a href="${pageContext.request.contextPath}/rawmaterialsreceiving/register" class="btn btn-success">
                    입고 등록
                </a>
            </div>
            <div class="card-body">
<!--                 검색 영역 (필요시 추가) -->
<%--                 <form class="form-inline mb-3" action="${pageContext.request.contextPath}/rawmaterialsreceiving/list" method="get"> --%>
<%--                     <input type="text" name="searchKeyword" class="form-control mr-2" placeholder="원자재 코드 검색" value="${param.searchKeyword}"> --%>
<!--                     <button type="submit" class="btn btn-primary">검색</button> -->
<!--                 </form> -->
                <!-- 테이블 (정렬 링크 포함) -->
                <table class="table datatable table-hover">
                    <thead>
                        <tr>
                            <th>
                                    원자재 코드
                            </th>
                            <th>
                                    수량
                            </th>
                            <th>
                            		단위
                            </th>
                            <th>
                                    LOT 번호
                            </th>
                            <th>
                                    유효기간
                            </th>
                            <th>
                            		기능
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${rawMaterialsReceivingList}">
                            <tr>
                                <td>${item.rmCode}</td>
                                <td>${item.rrQuantity}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty item.rrUnit}">
                                            ${item.rrUnit}
                                        </c:when>
                                        <c:otherwise>g</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${item.rmlNo}</td>
                                <td>
                                    <fmt:formatDate value="${item.rrExpirydate}" pattern="yyyy-MM-dd" />
                                </td>
                                <td>
                                    <!-- 수정 버튼: 모달창 오픈 (필수 primary key rrNo 추가) -->
                                    <button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#editModal"
                                        onclick="openEditModal('${item.rrNo}', '${item.rmCode}', '${item.rrQuantity}', '${item.rrUnit}', '${item.rmlNo}', '${item.rrExpirydate}')">
                                        수정
                                    </button>
                                    <!-- 삭제 버튼: 모달창 오픈 (rrNo 전달) -->
                                    <button class="btn btn-sm btn-danger" data-toggle="modal" data-target="#deleteModal"
                                        onclick="openDeleteModal('${item.rrNo}')">
                                        삭제
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
<!--                 페이징 영역 (컨트롤러에서 currentPage, startPage, endPage, totalPages 전달 필요) -->
<!--                 <nav aria-label="Page navigation"> -->
<!--                     <ul class="pagination"> -->
<%--                         <c:if test="${currentPage > 1}"> --%>
<!--                             <li class="page-item"> -->
<%--                                 <a class="page-link" href="${pageContext.request.contextPath}/rawmaterialsreceiving/list?page=${currentPage - 1}&sortColumn=${param.sortColumn}&sortOrder=${param.sortOrder}&searchKeyword=${param.searchKeyword}">이전</a> --%>
<!--                             </li> -->
<%--                         </c:if> --%>
<%--                         <c:forEach var="i" begin="${startPage}" end="${endPage}"> --%>
<%--                             <li class="page-item ${i == currentPage ? 'active' : ''}"> --%>
<%--                                 <a class="page-link" href="${pageContext.request.contextPath}/rawmaterialsreceiving/list?page=${i}&sortColumn=${param.sortColumn}&sortOrder=${param.sortOrder}&searchKeyword=${param.searchKeyword}">${i}</a> --%>
<!--                             </li> -->
<%--                         </c:forEach> --%>
<%--                         <c:if test="${currentPage < totalPages}"> --%>
<!--                             <li class="page-item"> -->
<%--                                 <a class="page-link" href="${pageContext.request.contextPath}/rawmaterialsreceiving/list?page=${currentPage + 1}&sortColumn=${param.sortColumn}&sortOrder=${param.sortOrder}&searchKeyword=${param.searchKeyword}">다음</a> --%>
<!--                             </li> -->
<%--                         </c:if> --%>
<!--                     </ul> -->
<!--                 </nav> -->
            </div>
        </div>
    </div>
</section>

<!-- 수정 모달 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <form id="updateForm" action="${pageContext.request.contextPath}/rawmaterialsreceiving/update" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">원자재 입고 수정</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="닫기">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- 원자재 입고 번호 (수정 시 필요한 primary key, 숨김) -->
                    <input type="hidden" id="editRrNo" name="rrNo">
                    <!-- 원자재 코드 (수정 불가) -->
                    <div class="form-group">
                        <label for="editRmCode">원자재 코드</label>
                        <input type="text" class="form-control" id="editRmCode" name="rmCode" readonly>
                    </div>
                    <!-- 수량 -->
                    <div class="form-group">
                        <label for="editRrQuantity">수량</label>
                        <input type="number" class="form-control" id="editRrQuantity" name="rrQuantity" required>
                    </div>
                    <!-- 단위 -->
                    <div class="form-group">
                        <label for="editRrUnit">단위</label>
                        <input type="text" class="form-control" id="editRrUnit" name="rrUnit" value="g" required>
                    </div>
                    <!-- LOT 번호 (수정 불가) -->
                    <div class="form-group">
                        <label for="editLotNo">LOT 번호</label>
                        <input type="text" class="form-control" id="editLotNo" name="rmlNo" readonly>
                    </div>
                    <!-- 유효기간 -->
                    <div class="form-group">
                        <label for="editExpiryDate">유효기간</label>
                        <input type="date" class="form-control" id="editExpiryDate" name="rrExpirydate" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">수정 저장</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- 삭제 모달 -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <form id="deleteForm" action="${pageContext.request.contextPath}/rawmaterialsreceiving/delete" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel">원자재 입고 삭제</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="닫기">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    정말로 이 원자재 입고 정보를 삭제하시겠습니까?
                    <!-- 삭제 시 primary key인 rrNo 전달 -->
                    <input type="hidden" id="deleteRrNo" name="rrNo">
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-danger">삭제</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- 스크립트: jQuery, Popper, Bootstrap JS 포함 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    // 수정 모달 열기 함수: 해당 행의 데이터를 모달에 채워 넣음 (rrNo 추가)
    function openEditModal(rrNo, rmCode, rrQuantity, rrUnit, rmlNo, rrExpirydate) {
        $('#editRrNo').val(rrNo);
        $('#editRmCode').val(rmCode);
        $('#editRrQuantity').val(rrQuantity);
        $('#editRrUnit').val(rrUnit ? rrUnit : 'g');
        $('#editLotNo').val(rmlNo);
        $('#editExpiryDate').val(rrExpirydate);
        $('#editModal').modal('show');
    }

    // 삭제 모달 열기 함수: 해당 행의 primary key(rrNo)를 삭제 모달에 설정
    function openDeleteModal(rrNo) {
        $('#deleteRrNo').val(rrNo);
        $('#deleteModal').modal('show');
    }
</script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</html>
