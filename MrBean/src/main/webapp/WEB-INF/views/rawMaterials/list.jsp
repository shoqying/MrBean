<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="원자재 목록"/>
<c:set var="sidebarTitle" value="기준정보관리"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>원자재 목록</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
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
                        <a href="${pageContext.request.contextPath}/rawMaterials/register" class="btn btn-success">
                            원자재 등록
                        </a>
                    </div>
                    <table class="table datatable table-hover">
                        <thead>
                            <tr>
                                <th>원자재 코드</th>
                                <th>원자재 이름</th>
                                <th>원산지</th>
                                <th>보관 방법</th>
                                <th>기능</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="rawMaterial" items="${rawMaterialsList}">
                                <tr>
                                    <td>${rawMaterial.rmCode}</td>
                                    <td>${rawMaterial.rmName}</td>
                                    <td>${rawMaterial.rmOrigin}</td>
                                    <td>${rawMaterial.rmStorageMethod}</td>
                                    <td>
                                        <button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#editModal"
                                                onclick="openEditModal('${rawMaterial.rmCode}', '${rawMaterial.rmName}', '${rawMaterial.rmOrigin}', '${rawMaterial.rmStorageMethod}')">
                                            수정
                                        </button>
                                        <form action="${pageContext.request.contextPath}/rawMaterials/delete" method="post" style="display:inline;">
                                            <input type="hidden" name="rmCode" value="${rawMaterial.rmCode}">
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
                <h5 class="modal-title" id="editModalLabel">원자재 수정</h5>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="rmCode" class="form-label">원자재 코드</label>
                    <input type="text" class="form-control" id="rmCode" name="rmCode" readonly>
                </div>
                <div class="mb-3">
                    <label for="rmName" class="form-label">원자재 이름</label>
                    <input type="text" class="form-control" id="rmName" name="rmName" required>
                </div>
                <div class="mb-3">
                    <label for="rmOrigin" class="form-label">원산지</label>
                    <input type="text" class="form-control" id="rmOrigin" name="rmOrigin" required>
                </div>
                <div class="mb-3">
                    <label for="rmStorageMethod" class="form-label">보관 방법</label>
                    <input type="text" class="form-control" id="rmStorageMethod" name="rmStorageMethod" required>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="editButton" onclick="submitEditForm()">수정</button>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function openEditModal(rmCode, rmName, rmOrigin, rmStorageMethod) {
        document.getElementById("rmCode").value = rmCode;
        document.getElementById("rmName").value = rmName;
        document.getElementById("rmOrigin").value = rmOrigin;
        document.getElementById("rmStorageMethod").value = rmStorageMethod;
    }
</script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</html>