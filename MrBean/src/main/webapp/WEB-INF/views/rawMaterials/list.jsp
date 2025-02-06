<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>원자재 리스트</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <div class="container">
        <h1 class="mt-5">원자재 목록</h1>

        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>원자재 코드</th>
                    <th>원자재 이름</th>
                    <th>원산지</th>
                    <th>보관 방법</th>
                    <th>수정</th>
                </tr>
            </thead>
            <tbody>
                <!-- 원자재 목록을 반복하여 출력 -->
                <c:forEach var="rawMaterial" items="${rawMaterialsList}">
                    <tr>
                        <td>${rawMaterial.rmCode}</td>
                        <td>${rawMaterial.rmName}</td>
                        <td>${rawMaterial.rmOrigin}</td>
                        <td>${rawMaterial.rmStorageMethod}</td>
                        <td>
                            <!-- 수정 버튼 -->
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal"
                                    onclick="openEditModal('${rawMaterial.rmCode}', '${rawMaterial.rmName}', '${rawMaterial.rmOrigin}', '${rawMaterial.rmStorageMethod}')">
                                수정
                            </button>
                            <!-- 삭제 버튼 -->
                            <form action="${pageContext.request.contextPath}/rawMaterials/delete" method="post" style="display:inline;">
                                <input type="hidden" name="rmCode" value="${rawMaterial.rmCode}">
                                <button type="submit" class="btn btn-danger" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- 대시보드 버튼과 등록 페이지로 돌아가는 버튼을 폼 바로 밑에 배치 -->
        <div class="d-flex justify-content-between align-items-center mt-3">
            
            <!-- 등록 페이지로 돌아가는 버튼 -->
            <a href="${pageContext.request.contextPath}/rawMaterials/register" class="btn btn-primary">등록 페이지</a>
        </div>
    </div>

    <!-- 수정 모달 -->
    <div class="modal" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">원자재 수정</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- 수정 폼 -->
                    <form id="updateForm" action="${pageContext.request.contextPath}/rawMaterials/update" method="post">
                        <!-- 원자재 코드 -->
                        <input type="hidden" id="rmCode" name="rmCode" value="${rawMaterial.rmCode}"> <!-- VO 객체에서 가져온 값으로 초기화 -->
                        
                        <div class="form-group">
                            <label for="rmName">원자재 이름:</label>
                            <input type="text" id="rmName" name="rmName" class="form-control" value="${rawMaterial.rmName}" required> <!-- VO 객체에서 가져온 값으로 초기화 -->
                        </div>
                        
                        <div class="form-group">
                            <label for="rmOrigin">원산지:</label>
                            <input type="text" id="rmOrigin" name="rmOrigin" class="form-control" value="${rawMaterial.rmOrigin}" required> <!-- VO 객체에서 가져온 값으로 초기화 -->
                        </div>
                        
                        <div class="form-group">
                            <label for="rmStorageMethod">보관 방법:</label>
                            <input type="text" id="rmStorageMethod" name="rmStorageMethod" class="form-control" value="${rawMaterial.rmStorageMethod}" required> <!-- VO 객체에서 가져온 값으로 초기화 -->
                        </div>
                        
                        <button type="submit" class="btn btn-success">수정</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
        function openEditModal(rmCode, rmName, rmOrigin, rmStorageMethod) {
            // 모달에 원자재 정보를 채운다
            document.getElementById("rmCode").value = rmCode;
            document.getElementById("rmName").value = rmName;
            document.getElementById("rmOrigin").value = rmOrigin;
            document.getElementById("rmStorageMethod").value = rmStorageMethod;
        }
    </script>
    
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
