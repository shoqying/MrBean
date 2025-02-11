<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<meta charset="UTF-8">
<title>완제품 출고 목록</title>
</head>
<body>

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

<h1>완제품 출고 목록</h1>
<!-- 완제품 출고 목록을 테이블로 표시 -->
<table>
    <thead>
        <tr>
            <th>출고번호</th>
            <th>로트번호</th>
            <th>출고 품목 번호</th>
            <th>제품 코드</th>
            <th>출고 수량</th>
            <th>단위</th>
            <th>출고일</th>
            <th>출고 위치명</th>
            <th>출고 위치</th>
            <th>출고 번호</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
    </thead>
    <tbody>
        <!-- 완제품 출고 정보 목록 출력 -->
        <c:forEach var="item" items="${finishedProducts}">
            <tr>
                <td>${item.foNo}</td>
                <td>${item.fpcLotbno}</td>
                <td>${item.fplNo}</td>
                <td>${item.pCode}</td>
                <td>${item.foQuantity}</td>
                <td>${item.foUnit}</td>
                <td>${item.foDate}</td>
                <td>${item.foShippingLocationName}</td>
                <td>${item.foShippingLocation}</td>
                <td>${item.foBno}</td>
                <td><a href="<c:url value='/finishedproductsoutgoing/updateForm/${item.foNo}' />">수정</a></td>
                <td><a href="<c:url value='/finishedproductsoutgoing/delete/${item.foNo}' />" onclick="return confirm('삭제하시겠습니까?')">삭제</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<br>
<a href="<c:url value='/finishedproductsoutgoing/register' />">완제품 출고 등록</a>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
