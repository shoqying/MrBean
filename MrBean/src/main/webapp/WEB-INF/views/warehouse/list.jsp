<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="창고 목록"/>
<c:set var="sidebarTitle" value="기준정보관리"/>
<!DOCTYPE html>
<html lang="ko">
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
                        <a href="${pageContext.request.contextPath}/warehouses/create" class="btn btn-success">
                            창고 등록
                        </a>
                    </div>
                    <table class="table datatable table-hover">
                        <thead>
                            <tr>
                                <th>창고 코드</th>
                                <th>창고 이름</th>
                                <th>전체 주소</th>
                                <th>상세 주소</th>
                                <th>우편 번호</th>
                                <th>창고 설명</th>
                                <th>기능</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="warehouse" items="${warehouseList}">
                                <tr>
                                    <td>${warehouse.WCode}</td>
                                    <td>${warehouse.WName}</td>
                                    <td>${warehouse.WRoadFullAddr}</td>
                                    <td>${warehouse.WAddrDetail}</td>
                                    <td>${warehouse.WZipNo}</td>
                                    <td>${warehouse.WDescription}</td>
                                    <td>
                                        <button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#editModal"
                                                onclick="openEditModal('${warehouse.WCode}', '${warehouse.WName}', '${warehouse.WRoadFullAddr}', '${warehouse.WAddrDetail}', '${warehouse.WZipNo}', '${warehouse.WDescription}')">
                                            수정
                                        </button>
                                        <form action="${pageContext.request.contextPath}/warehouse/delete" method="post" style="display:inline;">
                                            <input type="hidden" name="wCode" value="${warehouse.WCode}">
                                            <button type="button" class="btn btn-danger btn-sm" onclick="deleteWarehouse('${warehouse.WCode}')">
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
                <h5 class="modal-title" id="editModalLabel">창고 수정</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="updateForm" action="${pageContext.request.contextPath}/warehouse/update" method="post">
                    <input type="hidden" id="wCode" name="wCode" value="">
                    <div class="form-group">
                        <label for="wName">창고 이름:</label>
                        <input type="text" id="wName" name="wName" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="wRoadFullAddr">전체 주소:</label>
                        <input type="text" id="wRoadFullAddr" name="wRoadFullAddr" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="wAddrDetail">상세 주소:</label>
                        <input type="text" id="wAddrDetail" name="wAddrDetail" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="wZipNo">우편 번호:</label>
                        <input type="text" id="wZipNo" name="wZipNo" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="wDescription">창고 설명:</label>
                        <input type="text" id="wDescription" name="wDescription" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-success">수정</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/components/toast.js'/>"></script>
<script src="<c:url value='/resources/js/components/resetToast.js'/>"></script>
<script src="<c:url value='/resources/js/warehouse/edit.js'/>"></script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</html>