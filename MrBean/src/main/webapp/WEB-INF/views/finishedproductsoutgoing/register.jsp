<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>완제품 출고 등록</title>
</head>
<body>
    <h1>완제품 출고 등록</h1>
    
    <!-- 출고 등록 폼 -->
    <form action="${pageContext.request.contextPath}/finishedproductsoutgoing/registerPost" method="post">
        <label for="fpcLotbno">로트 번호:</label>
        <input type="text" id="fpcLotbno" name="fpcLotbno" value="${finishedProduct.fpcLotbno}" required /><br>

        <label for="fplNo">출고 품목 번호:</label>
        <input type="text" id="fplNo" name="fplNo" value="${finishedProduct.fplNo}" required /><br>

        <label for="pCode">제품 코드:</label>
        <input type="text" id="pCode" name="pCode" value="${finishedProduct.pCode}" required /><br>

        <label for="foQuantity">출고 수량:</label>
        <input type="number" id="foQuantity" name="foQuantity" value="${finishedProduct.foQuantity}" required /><br>

        <label for="wCode">창고 코드:</label>
        <input type="text" id="wCode" name="wCode" value="${finishedProduct.wCode}" required /><br>

        <label for="foDate">출고일:</label>
        <input type="date" id="foDate" name="foDate" value="${finishedProduct.foDate}" required /><br>

        <label for="foShippingLocationName">출고 위치명:</label>
        <input type="text" id="foShippingLocationName" name="foShippingLocationName" value="${finishedProduct.foShippingLocationName}" required /><br>

        <label for="foShippingLocation">출고 위치:</label>
        <input type="text" id="foShippingLocation" name="foShippingLocation" value="${finishedProduct.foShippingLocation}" required /><br>

        <label for="foBno">출고 번호:</label>
        <input type="number" id="foBno" name="foBno" value="${finishedProduct.foBno}" required /><br>

        <button type="submit">등록</button>
    </form>

    <!-- 뒤로 가기 링크 -->
    <a href="${pageContext.request.contextPath}/finishedproductsoutgoing/list">목록으로 돌아가기</a>
</body>
</html>
