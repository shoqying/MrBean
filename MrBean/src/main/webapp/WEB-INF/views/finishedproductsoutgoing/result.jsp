<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
</head>
<body>
    <h1>출고 등록 성공</h1>

    <c:choose>
        <c:when test="${not empty successMessage}">
        <!-- 성공적인 출고 등록 -->
            <div class="alert alert-success">
                <p>${successMessage}</p>
            </div>
            
            <!-- 등록된 출고 정보 출력 -->
            <table class="table table-bordered">
                <tr><th>출고번호</th><td>${finishedProduct.foNo}</td></tr>
                <tr><th>작업지시 번호</th><td>${finishedProduct.workOrderNo}</td></tr>
                <tr><th>출고 수량</th><td>${finishedProduct.foQuantity} ${finishedProduct.foUnit}</td></tr>
                <tr><th>창고 코드</th><td>${finishedProduct.wCode}</td></tr> 
                <tr><th>출고 위치</th><td>${finishedProduct.foShippingLocationName} (${finishedProduct.foShippingLocation})</td></tr>
                <tr><th>출고일</th><td>${finishedProduct.foDate}</td></tr>
            </table>
        </c:when>

        <c:when test="${not empty errorMessage}">
        <!-- 오류 발생 -->
            <div class="alert alert-danger">
                <p>${errorMessage}</p>
            </div>
        </c:when>
    </c:choose>

    <!-- 목록으로 돌아가기 -->
    <a href="<c:url value='/finishedproductsoutgoing/list'/>" class="btn btn-secondary">목록으로 돌아가기</a>
    <br><br>
    <!-- 다시 출고 등록 페이지로 이동 -->
    <a href="<c:url value='/finishedproductsoutgoing/register'/>" class="btn btn-primary">다시 등록</a>
</body>
</html>
