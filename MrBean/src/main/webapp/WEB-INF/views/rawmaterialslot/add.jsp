<!-- add.jsp -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>원자재 로트번호 추가</title>
</head>
<body>
    <h1>원자재 로트번호 추가</h1>
    
    <form action="/rawmaterials/add" method="post">
        <label for="rmlNo">로트번호:</label>
        <input type="text" id="rmlNo" name="rmlNo"><br>
        
        <label for="rmCode">제품 코드:</label>
        <input type="text" id="rmCode" name="rmCode"><br>
        
        <label for="wCode">창고 코드:</label>
        <input type="text" id="wCode" name="wCode"><br>
        
        <label for="rmlDate">입고 날짜:</label>
        <input type="date" id="rmlDate" name="rmlDate"><br>
        
        <label for="rmlBatch">입고 배치:</label>
        <input type="text" id="rmlBatch" name="rmlBatch"><br>
        
        <label for="rmlBno">입고 순번:</label>
        <input type="number" id="rmlBno" name="rmlBno"><br>
        
        <button type="submit">추가</button>
    </form>
    
    <br>
    <a href="/rawmaterials/list">목록으로 돌아가기</a>
</body>
</html>
