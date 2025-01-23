<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>완제품 재고 목록</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/resources/css/toastStyle.css'/>">
<style>
body {
    background-color: #f8f9fa;
    margin: 0;
    padding: 0;
}

/* 제목 스타일 */
h1 {
    text-align: center;
    margin-top: 30px;
    color: #495057;
}

/* 폼 레이아웃 */
form {
    margin: 20px auto;
    padding: 20px;
    max-width: 600px;
    background-color: #ffffff;
    border: 1px solid #ddd;
    border-radius: 8px;
}

/* 폼 요소 스타일 */
form label {
    font-weight: bold;
    margin-right: 10px;
}

form select, form button {
    margin-bottom: 10px;
    padding: 8px;
    font-size: 14px;
}

form button {
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

form button:hover {
    background-color: #0056b3;
}

/* 테이블 스타일 */
table {
    width: 100%;
    border-collapse: collapse;
    background-color: white;
}

th, td {
    padding: 12px;
    text-align: center;
    border: 1px solid #ddd;
}

th {
    background-color: #007bff;
    color: white;
}

tbody tr:hover {
    background-color: #f1f1f1;
}

/* 버튼 스타일 */
button.btn {
    margin: 20px 0;
    padding: 10px 20px;
    font-size: 16px;
}

button.btn-primary {
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
}

button.btn-primary:hover {
    background-color: #0056b3;
}

/* 페이지네이션 스타일 */
/*  */
.pagination {
    display: flex;
    justify-content: center;
    margin-top: 15px;
    margin-bottom: 15px;
}

.pagination a {
    padding: 8px 15px;
    background-color: #f1f1f1;
    color: #007bff;
    text-decoration: none;
    border: 1px solid #ddd;
    margin: 0 5px;
    border-radius: 4px;
}

.pagination a:hover {
    background-color: #007bff;
    color: white;
}

.pagination span {
    padding: 8px 15px;
    margin: 0 5px;
    border-radius: 4px;
    background-color: #f1f1f1;;
}

</style>
</head>
<body>

<h1>완제품 재고 목록</h1>

<!-- 정렬 옵션 -->
<form action="/stockP/splist" method="get">
    <label for="sortColumn">정렬 기준:</label>
    <select name="sortColumn" id="sortColumn">
        <option value="sp_date" ${sortColumn == 'sp_date' ? 'selected="selected"' : ''}>입고일</option>
        <option value="plan_quantity" ${sortColumn == 'plan_quantity' ? 'selected="selected"' : ''}>수량</option>
    </select>

    <label for="sortDirection">정렬 방향:</label>
    <select name="sortDirection" id="sortDirection">
        <option value="ASC" ${sortDirection == 'ASC' ? 'selected="selected"' : ''}>오름차순</option>
        <option value="DESC" ${sortDirection == 'DESC' ? 'selected="selected"' : ''}>내림차순</option>
    </select>

    <button type="submit">정렬</button>
    
</form>

<button class="btn btn-primary" onclick="location.href='/stock/list'">원자재 목록 페이지</button>

<table id="stockProductTable">
    <thead>
        <tr>
            <th>순번</th>
            <th>수량</th>
            <th>단위</th>
            <th>입고일</th>
            <th>창고 코드</th>
            <th>LOT번호</th>
            <th>제품 코드</th>
            <th>유통기한</th>
          
        
        </tr>
    </thead>
    <tbody>
        <c:forEach var="stockProducts" items="${stockProducts}">
            <tr>
                <td>${stockProducts.spBno}</td>
                <td>${stockProducts.planQuantity}</td>
                <td>${stockProducts.spUnit}</td>
                <td>${stockProducts.spDate}</td>           
                <td>${stockProducts.WCode}</td>
                <td>${stockProducts.fpcLotbno}</td>
                <td>${stockProducts.PCode}</td>
                <td>${stockProducts.fpcExpirydate}</td>
               
            </tr>
        </c:forEach>
       <c:if test="${empty stockProducts}">
		    <tr>
		        <td colspan="8">데이터가 없습니다.</td>
		    </tr>
		</c:if>
    </tbody>
</table>




<!-- 페이지네이션 -->
<div class="pagination">
    <c:if test="${page > 1}">
        <a href="/stockP/splist?page=${page - 1}&sortColumn=${sortColumn}&sortDirection=${sortDirection}">◁ 이전</a>
    </c:if>

    <span>페이지 ${page} / ${totalPages}</span>

    <c:if test="${page < totalPages}">
        <a href="/stockP/splist?page=${page + 1}&sortColumn=${sortColumn}&sortDirection=${sortDirection}">다음 ▷</a>
    </c:if>
</div>

<!-- JavaScript 코드 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    // 테이블을 5초마다 갱신하는 함수
    function fetchUpdatedList() {
        $.ajax({
            url: '/stockP/splist',
            method: 'GET',
            success: function(data) {
                $('#stockProductTable').html(data); // 테이블 내용 업데이트
            }
        });
    }

    // 일정 시간마다 새로고침
    setInterval(fetchUpdatedList, 100000); // 1분마다 호출
</script>



</body>
</html>

