<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>원자재 수정</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <div class="container">
        <h1 class="mt-5">원자재 수정</h1>

        <form action="${pageContext.request.contextPath}/rawMaterials/update" method="post">
            <input type="hidden" name="rmCode" value="${rawMaterial.rmCode}">
            <div class="form-group">
                <label for="rmName">원자재 이름:</label>
                <input type="text" id="rmName" name="rmName" class="form-control" value="${rawMaterial.rmName}" required>
            </div>
            <div class="form-group">
                <label for="rmOrigin">원산지:</label>
                <input type="text" id="rmOrigin" name="rmOrigin" class="form-control" value="${rawMaterial.rmOrigin}" required>
            </div>
            <div class="form-group">
                <label for="rmStorageMethod">보관 방법:</label>
                <input type="text" id="rmStorageMethod" name="rmStorageMethod" class="form-control" value="${rawMaterial.rmStorageMethod}" required>
            </div>
            <button type="submit" class="btn btn-success">수정</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</html>
