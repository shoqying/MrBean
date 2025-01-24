<!-- update.jsp -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>원자재 로트번호 수정</title>
</head>
<body>
    <h1>원자재 로트번호 수정</h1>
    
    <form action="/rawmaterials/update" method="post">
        <input type="hidden" name="rmlNo" value="${rawMaterialsLot.rmlNo}">
        <input type="hidden" name="rmlBno" value="${rawMaterialsLot.rmlBno}">
        
        <label for="rmCode">제품 코드:</label>
        <input type="text" id="rmCode" name="rmCode" value="${rawMaterialsLot.rmCode}"><br>
        
        <label for="wCode">창고 코드:</label>
        <input type="text" id="wCode" name="wCode" value="${rawMaterialsLot.wCode}"><br>
        
        <label for="rmlDate">입고 날짜:</label>
        <input type="date" id="rmlDate" name="rmlDate" value="${rawMaterialsLot.rmlDate}"><br>
        
        <label for="rmlBatch">입고 배치:</label>
        <input type="text" id="rmlBatch" name="rmlBatch" value="${rawMaterialsLot.rmlBatch}"><br>
        
        <label for="rmlBno">입고 순번:</label>
        <input type="number" id="rmlBno" name="rmlBno" value="${rawMaterialsLot.rmlBno}" readonly><br>
        
        <button type="submit">수정</button>
    </form>
    
    <br>
    <a href="/rawmaterials/list">목록으로 돌아가기</a>
</body>
</html>
