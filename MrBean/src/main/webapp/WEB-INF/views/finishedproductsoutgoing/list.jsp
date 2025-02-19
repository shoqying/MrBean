<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>출고 목록</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>완제품 출고 목록</h2>
    
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
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>출고번호</th>
                <th>완제품 로트번호</th>
                <th>제품 코드</th>
                <th>출고 수량</th>
                <th>출고일</th>
                <th>출고 위치</th>
                <th>상세보기</th>
            </tr>
        </thead>
        <tbody>
            <!-- 출고 목록 반복 출력 -->
            <c:forEach var="finishedProduct" items="${finishedProducts}">
                <tr>
                    <td>${finishedProduct.foNo}</td>
                    <td>${finishedProduct.fplNo}</td>
                    <td>${finishedProduct.pCode}</td>
                    <td>${finishedProduct.foQuantity} ${finishedProduct.foUnit}</td>
                    <td>${finishedProduct.foDate}</td>
                    <td>${finishedProduct.foShippingLocationName} (${finishedProduct.foShippingLocation})</td>
                    <td>
                        <a href="/finishedproductsoutgoing/detail/${finishedProduct.foNo}" class="btn btn-primary btn-sm">상세보기</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <!-- 출고 등록 페이지 링크 -->
    <a href="/finishedproductsoutgoing/register" class="btn btn-success">출고 등록</a>

    <!-- 출고 목록이 없을 경우 메시지 -->
    <c:if test="${empty finishedProducts}">
        <div class="alert alert-warning mt-4">
            현재 출고된 제품이 없습니다.
        </div>
    </c:if>
</div>

</body>
</html>



<%@ include file="/WEB-INF/views/include/footer.jsp" %>










