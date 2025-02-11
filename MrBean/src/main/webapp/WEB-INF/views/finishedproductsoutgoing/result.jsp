<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>결과</title>
</head>
<body>
    <h1>완제품 출고 결과</h1>
    
    <!-- 성공 메시지 -->
    <c:if test="${not empty message}">
        <div style="color: green; font-size: 20px;">${message}</div>
    </c:if>

    <!-- 에러 메시지 -->
    <c:if test="${not empty errorMessage}">
        <div style="color: red; font-size: 20px;">${errorMessage}</div>
    </c:if>

    <!-- 뒤로 가기 링크 -->
    <a href="${pageContext.request.contextPath}/finishedproductsoutgoing/list">목록으로 돌아가기</a>
</body>
</html>
