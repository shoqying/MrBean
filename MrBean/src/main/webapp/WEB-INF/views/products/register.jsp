<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>완제품 등록</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
</head>
<body>

    <div class="container mt-5">
        <h2 class="text-center mb-4">완제품 등록</h2>

        <!-- 폼 컨테이너 -->
        <div class="form-container">
            <!-- 메시지 처리 -->
            <c:if test="${not empty message}">
                <div style="color: green; font-weight: bold;">${message}</div>
            </c:if>

            <!-- 제품 등록 폼 -->
            <form:form method="post" modelAttribute="product">
                <div class="form-group">
                    <label for="pCode">제품 코드:</label>
                    <form:input path="pCode" id="pCode" class="form-control" required="true" />
                </div>
                <div class="form-group">
                    <label for="pName">제품명:</label>
                    <form:input path="pName" id="pName" class="form-control" required="true" />
                </div>
                <div class="form-group">
                    <label for="pDescription">제품 설명:</label>
                    <form:textarea path="pDescription" id="pDescription" class="form-control" required="true" />
                </div>

                <!-- BOM 목록 드롭다운 -->
                <div class="form-group">
                    <label for="bomId">BOM 선택:</label>
                    <form:select path="bomId" id="bomId" class="form-control">
                    <!-- 빈 옵션 추가 -->
                    <option value ="" selected disabled>-- 선택하세요 --</option>>
                        <c:forEach var="bom" items="${bomList}">
                            <option value="${bom.bomId}">${bom.bomName}</option>
                        </c:forEach>
                    </form:select>
                </div>

                <!-- 제출 버튼 -->
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">등록</button>
                </div>
            </form:form>
        </div>

        <!-- 대시보드 및 리스트 페이지로 돌아가는 버튼 -->
        <div class="navigate-buttons">
            <a href="/dashboard" class="btn btn-info">대시보드</a>
            <a href="/products/list" class="btn btn-primary">제품 목록으로 돌아가기</a>
        </div>
    </div>

</body>
</html>
