<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>완제품 출고 등록</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/styles.css'/>">
</head>
<body>
    <h1>완제품 출고 등록</h1>

    <!-- 출고 등록 폼 -->
    <form action="<c:url value='/finishedproductsoutgoing/register'/>" method="post">
        
        <!-- 출고번호 (자동 생성되므로 사용자가 입력하지 않음) -->
        <label for="foNo">출고번호:</label>
        <input type="text" id="foNo" name="foNo" readonly="readonly" value="자동 생성됨"><br><br>

        <!-- 완제품 로트번호 (드롭다운) -->
        <label for="fplNo">완제품 로트번호:</label>
        <select id="fplNo" name="fplNo" required>
            <c:forEach var="lot" items="${lotNumbers}">
                <option value="${lot}">${lot}</option>
            </c:forEach>
        </select><br><br>

        <!-- 제품 코드 (드롭다운) -->
        <label for="pCode">완제품 코드:</label>
        <select id="pCode" name="pCode" required>
            <c:forEach var="code" items="${productCodes}">
                <option value="${code}">${code}</option>
            </c:forEach>
        </select><br><br>

        <!-- 출고 수량 -->
        <label for="foQuantity">출고 수량:</label>
        <input type="number" id="foQuantity" name="foQuantity" required min="1"><br><br>

        <!-- 출고 단위 (기본값 '개'로 설정됨) -->
        <label for="foUnit">출고 단위:</label>
        <input type="text" id="foUnit" name="foUnit" value="개" readonly="readonly"><br><br>

        <!-- 창고 코드 (드롭다운) -->
        <label for="wCode">창고 코드:</label>
        <select id="wCode" name="wCode" required>
            <c:forEach var="warehouse" items="${warehouseCodes}">
                <option value="${warehouse}">${warehouse}</option>
            </c:forEach>
        </select><br><br>

        <!-- 출고일 (자동으로 현재 날짜로 설정됨) -->
        <label for="foDate">출고일:</label>
        <input type="date" id="foDate" name="foDate" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" required><br><br>

        <!-- 출고 위치명 -->
        <label for="foShippingLocationName">출고 위치명:</label>
        <input type="text" id="foShippingLocationName" name="foShippingLocationName" required><br><br>

        <!-- 출고 위치 -->
        <label for="foShippingLocation">출고 위치:</label>
        <input type="text" id="foShippingLocation" name="foShippingLocation" required><br><br>

        <!-- 제출 버튼 -->
        <button type="submit">등록</button>
        <a href="<c:url value='/finishedproductsoutgoing/list'/>">목록으로</a>
    </form>
</body>
</html>
