<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<meta charset="UTF-8">
<title>원자재 입고 목록</title>

<style>
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: center;
    }
    th {
        background-color: #f4f4f4;
    }
    td input {
        width: 100%;
        padding: 5px;
        border: 1px solid #ddd;
        box-sizing: border-box;
    }
    .button {
        padding: 5px 10px;
        margin: 5px;
        background-color: #007BFF;
        color: white;
        border: none;
        cursor: pointer;
    }
    .button:hover {
        background-color: #0056b3;
    }
</style>

<h1>원자재 입고 목록</h1>

<!-- 원자재 입고 목록을 테이블로 표시 -->
<table>
    <thead>
        <tr>
            <th>원자재 입고 번호</th>
            <th>원자재 로트 번호</th>
            <th>원자재</th>
            <th>원자재 코드</th>
            <th>원자재 수량</th>
            <th>원자재 유통기한</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
    </thead>
    <tbody>
        <!-- 원자재 입고 정보 목록 출력 -->
        <c:forEach var="item" items="${rawmaterialsReceiving}">
            <tr>
                <td>${item.rrNo}</td>
                <td>${item.rmlNo}</td>
                <td>${item.rmlBno}</td>
                <td>${item.rmCode}</td>
                <td>${item.rrQuantity}</td>
                <td>${item.rrExpirydate}</td>
                <td><a href="<c:url value='/rawmaterialsreceiving/updateForm/${item.rrNo}' />">수정</a></td>
                <td><a href="<c:url value='/rawmaterialsreceiving/delete/${item.rrNo}' />" onclick="return confirm('삭제하시겠습니까?')">삭제</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<br>
<a href="<c:url value='/rawmaterialsreceiving/register' />">원자재 입고 등록</a>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
