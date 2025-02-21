<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>완제품 출고 등록</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>완제품 출고 등록</h2>

    <!-- 오류 메시지 출력 -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">
            ${errorMessage}
        </div>
    </c:if>

    <!-- 출고 등록 폼 -->
    <form action="<c:url value='/finishedproductsoutgoing/register'/>" method="post">
        
        <!-- 출고번호 (자동 생성) -->
        <div class="form-group">
            <label for="foNo">출고번호:</label>
            <input type="text" id="foNo" name="foNo" class="form-control" readonly="readonly" placeholder="자동 생성됨">
        </div>

        <!-- 작업지시 번호 (COMPLETED 상태만) -->
        <div class="form-group">
            <label for="workOrderNo">작업지시 번호:</label>
            <select id="workOrderNo" name="workOrderNo" class="form-control" required>
                <option value="">작업지시 번호 선택</option>
                <c:forEach var="workOrderNo" items="${workOrderNumbers}">
                    <option value="${workOrderNo}">${workOrderNo}</option>
                </c:forEach>
            </select>
        </div>

       

     

        <!-- 출고 수량 -->
        <div class="form-group">
            <label for="foQuantity">출고 수량:</label>
            <input type="number" id="foQuantity" name="foQuantity" class="form-control" required min="1">
        </div>

        <!-- 출고 단위 -->
        <div class="form-group">
            <label for="foUnit">출고 단위:</label>
            <input type="text" id="foUnit" name="foUnit" class="form-control" value="개" required>
        </div>

   <!-- 창고 코드 (wCode) 드롭다운 추가 -->
<div class="form-group">
    <label for="wCode">창고 코드:</label>
    <select id="wCode" name="wCode" class="form-control" required>
        <option value="">창고 코드 선택</option>
        <c:forEach var="warehouseCode" items="${warehouseCodes}">
            <option value="${warehouseCode}">${warehouseCode}</option>
        </c:forEach>
    </select>
</div>

      

        <!-- 출고 위치명 -->
        <div class="form-group">
            <label for="foShippingLocationName">출고 위치명:</label>
            <input type="text" id="foShippingLocationName" name="foShippingLocationName" class="form-control" required>
        </div>

        <!-- 출고 위치 -->
        <div class="form-group">
            <label for="foShippingLocation">출고 위치:</label>
            <input type="text" id="foShippingLocation" name="foShippingLocation" class="form-control" required>
        </div>

        <!-- 출고일 -->
        <div class="form-group">
            <label for="foDate">출고일:</label>
            <input type="date" id="foDate" name="foDate" class="form-control" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" required>
        </div>

        <!-- 제출 버튼 -->
        <button type="submit" class="btn btn-primary">출고 등록</button>
        <a href="<c:url value='/finishedproductsoutgoing/list'/>" class="btn btn-secondary">목록으로</a>
    </form>
</div>

</body>
</html>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
