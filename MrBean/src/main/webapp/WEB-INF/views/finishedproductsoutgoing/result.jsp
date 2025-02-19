<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>작업 결과</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/styles.css'/>">
</head>
<body>
    <h1>작업 결과</h1>

    <!-- 작업 결과 메시지 출력 -->
    <c:choose>
        <!-- 성공적인 출고 등록 -->
        <c:when test="${not empty successMessage}">
            <div class="alert success">
                <p>${successMessage}</p>
            </div>
        </c:when>

        <!-- 오류 발생 -->
        <c:when test="${not empty errorMessage}">
            <div class="alert error">
                <p>${errorMessage}</p>
            </div>
        </c:when>
    </c:choose>

    <!-- 목록으로 돌아가기 링크 -->
    <a href="<c:url value='/finishedproductsoutgoing/list'/>">목록으로 돌아가기</a>
    <br><br>
    <!-- 다시 출고 등록 페이지로 이동하는 링크 -->
    <a href="<c:url value='/finishedproductsoutgoing/register'/>">다시 등록하기</a>
</body>
</html>
