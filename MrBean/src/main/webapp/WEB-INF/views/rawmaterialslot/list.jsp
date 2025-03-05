<!-- list.jsp -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>원자재 로트번호 목록</title>
</head>
<body>
    <h1>원자재 로트번호 목록</h1>
    
    <a href="/rawmaterials/add">원자재 로트번호 추가</a>
    
    <table border="1">
        <thead>
            <tr>
                <th>로트번호</th>
                <th>제품 코드</th>
                <th>창고 코드</th>
                <th>입고 날짜</th>
                <th>입고 배치</th>
                <th>입고 순번</th>
                <th>수정</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="rawMaterialsLot" items="${rawMaterialsLotList}">
                <tr>
                    <td>${rawMaterialsLot.rmlNo}</td>
                    <td>${rawMaterialsLot.rmCode}</td>
                    <td>${rawMaterialsLot.wCode}</td>
                    <td>${rawMaterialsLot.rmlDate}</td>
                    <td>${rawMaterialsLot.rmlBatch}</td>
                    <td>${rawMaterialsLot.rmlBno}</td>
                    <td><a href="/rawmaterials/update/${rawMaterialsLot.rmlNo}/${rawMaterialsLot.rmlBno}">수정</a></td>
                    <td>
                        <form action="/rawmaterials/delete/${rawMaterialsLot.rmlNo}/${rawMaterialsLot.rmlBno}" method="post">
                            <button type="submit">삭제</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
