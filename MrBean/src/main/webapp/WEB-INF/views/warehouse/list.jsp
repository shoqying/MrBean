<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>창고 리스트</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <div class="container">
        <h1 class="mt-5 text-center">창고 목록</h1>

        <table class="table datatable">
        <thead class="text-center">
                <tr>
                    <th>창고 코드</th>
                    <th>창고 이름</th>
                    <th>전체 주소</th>
                    <th>상세 주소</th>
                    <th>우편 번호</th>
                    <th>창고 설명</th>
                </tr>
            </thead>
            <tbody>
                <!-- 창고 목록을 반복하여 출력 -->
                <c:forEach var="warehouse" items="${warehouseList}">
                    <tr class="text-center">
                        <td>${warehouse.wCode}</td>
                        <td>${warehouse.wName}</td>
                        <td>${warehouse.wRoadFullAddr}</td>  
                        <td>${warehouse.wAddrDetail}</td>  
                        <td>${warehouse.wZipNo}</td>  
                        <td>${warehouse.wDescription}</td>  
                        <td>
                            <!-- 수정 버튼 -->
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal"
                                    onclick="openEditModal('${warehouse.wCode}', '${warehouse.wName}', '${warehouse.wRoadFullAddr}', '${warehouse.wAddrDetail}', '${warehouse.wZipNo}', '${warehouse.wDescription}',)">
                                수정
                            </button>
                            <!-- 삭제 버튼 -->
                            <form action="${pageContext.request.contextPath}/warehouse/delete" method="post" style="display:inline;">
                                <input type="hidden" name="wCode" value="${warehouse.wCode}">
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
            <a href="${pageContext.request.contextPath}/warehouse/create" class="btn btn-primary">등록 페이지</a>
        </div>
    </div>

    <!-- 수정 모달 -->
    <div class="modal" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">창고 수정</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- 수정 폼 -->
                    <form id="updateForm" action="${pageContext.request.contextPath}/warehouse/update" method="post">
                        <div class="form-group">
                            <label for="wCode">창고 코드:</label>
                            <input type="text" id="wCode" name="wCode" value="" readonly class="form-control">
                        </div>

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

   
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
        function openEditModal(wCode, wName, wRoadFullAddr, wAddrDetail, wZipNo, wDescription) {
            // 모달에 창고 정보를 채운다
            document.getElementById("wCode").value = wCode;
            document.getElementById("wName").value = wName;
            document.getElementById("wRoadFullAddr").value = wRoadFullAddr;
            document.getElementById("wAddrDetail").value = wAddrDetail;
            document.getElementById("wZipNo").value = wZipNo;
            document.getElementById("wDescription").value = wDescription;
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
