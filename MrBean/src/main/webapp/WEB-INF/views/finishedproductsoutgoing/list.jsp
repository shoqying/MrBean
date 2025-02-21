<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>출고 목록</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* 상단 제목과 버튼을 flex로 정렬 */
        .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        th {
            cursor: pointer;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <!-- 제목과 출고 등록 버튼을 우측 상단에 배치 -->
    <div class="header-container">
        <h2>완제품 출고 목록</h2>
        <a href="/finishedproductsoutgoing/register" class="btn btn-success">출고 등록</a>
    </div>
    
    <!-- 성공 메시지 출력 -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">
            ${successMessage}
        </div>
    </c:if>

    <!-- 오류 메시지 출력 -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">
            ${errorMessage}
        </div>
    </c:if>

    <!-- 출고 목록 테이블 -->
    <table class="table table-bordered mt-4" id="outgoingTable">
        <thead>
            <tr>
                <th onclick="sortTable(0)">출고번호</th>
                <th onclick="sortTable(1)">작업지시번호</th>
                <th onclick="sortTable(2)">창고 코드</th>
                <th onclick="sortTable(3)">출고 수량</th>
                <th onclick="sortTable(4)">출고일</th>
                <th onclick="sortTable(5)">출고 위치</th>
            </tr>
        </thead>
        <tbody>
            <!-- 출고 목록 반복 출력 -->
            <c:forEach var="finishedProduct" items="${finishedProducts}">
                <tr>
                    <td>${finishedProduct.foNo}</td>
                    <td>${finishedProduct.workOrderNo}</td>
                    <td>${finishedProduct.WCode}</td>
                    <td>${finishedProduct.foQuantity} ${finishedProduct.foUnit}</td>
                    <td>${finishedProduct.foDate}</td>
                    <td>${finishedProduct.foShippingLocationName} (${finishedProduct.foShippingLocation})</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <!-- 출고 목록이 없을 경우 메시지 -->
    <c:if test="${empty finishedProducts}">
        <div class="alert alert-warning mt-4">
            현재 출고된 제품이 없습니다.
        </div>
    </c:if>
</div>

<script>
function sortTable(columnIndex) {
    const table = document.getElementById("outgoingTable");
    const rows = Array.from(table.rows).slice(1);  // 첫 번째 행은 헤더이므로 제외
    let isAscending = table.rows[0].cells[columnIndex].getAttribute("data-sort") === "asc";

    // 행을 정렬하는 함수
    rows.sort((rowA, rowB) => {
        const cellA = rowA.cells[columnIndex].textContent.trim();
        const cellB = rowB.cells[columnIndex].textContent.trim();
        
        if (!isNaN(cellA) && !isNaN(cellB)) { // 숫자 비교
            return isAscending ? cellA - cellB : cellB - cellA;
        } else {
            return isAscending ? cellA.localeCompare(cellB) : cellB.localeCompare(cellA);
        }
    });

    // 정렬된 행들을 다시 테이블에 삽입
    rows.forEach(row => table.appendChild(row));

    // 정렬 상태를 업데이트
    table.rows[0].cells[columnIndex].setAttribute("data-sort", isAscending ? "desc" : "asc");
}
</script>

</body>
</html>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
