<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>원자재 재고 목록</title>
</head>
<body>

<h1>원자재 재고 목록</h1>

<!-- 정렬 옵션 -->
<form action="/stock/list" method="get">
    <label for="sortColumn">정렬 기준:</label>
    <select name="sortColumn" id="sortColumn">
        <option value="rml_date" ${sortColumn == 'rml_date' ? 'selected="selected"' : ''}>입고일</option>
        <option value="rr_quantity" ${sortColumn == 'rr_quantity' ? 'selected="selected"' : ''}>수량</option>
    </select>

    <label for="sortDirection">정렬 방향:</label>
    <select name="sortDirection" id="sortDirection">
        <option value="ASC" ${sortDirection == 'ASC' ? 'selected="selected"' : ''}>오름차순</option>
        <option value="DESC" ${sortDirection == 'DESC' ? 'selected="selected"' : ''}>내림차순</option>
    </select>

    <button type="submit">정렬</button>
</form>

<!-- 원자재 목록 테이블 -->
<table border="1">
    <thead>
        <tr>
            <th>순번</th>
            <th>입고일</th>
            <th>수량</th>
            <th>단위</th>
            <th>창고 코드</th>
            <th>LOT번호</th>
            <th>원자재 코드</th>
            <th>유통기한</th>
     
        </tr>
    </thead>
    <tbody>
        <c:forEach var="stockMaterial" items="${stockMaterials}">
            <tr>
                <td>${stockMaterial.smBno}</td>
                <td>${stockMaterial.rmlDate}</td>
                <td>${stockMaterial.rrQuantity}</td>
                <td>${stockMaterial.rrUnit}</td>
                <td>${stockMaterial.wCode}</td>
                <td>${stockMaterial.rmlNo}</td>
                <td>${stockMaterial.rmCode}</td>
                <td>${stockMaterial.rrExpirydate}</td>
            
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- 페이지네이션 -->
<div class="pagination">
    <c:if test="${page > 1}">
        <a href="/stock/list?page=${page - 1}&sortColumn=${sortColumn}&sortDirection=${sortDirection}">◁ 이전</a>
    </c:if>

    <span>페이지 ${page} / ${totalPages}</span>

    <c:if test="${page < totalPages}">
        <a href="/stock/list?page=${page + 1}&sortColumn=${sortColumn}&sortDirection=${sortDirection}">다음 ▷</a>
    </c:if>
</div>

</body>
</html>
