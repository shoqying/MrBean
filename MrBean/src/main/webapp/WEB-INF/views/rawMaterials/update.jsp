<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>원자재 수정</title>
</head>
<body>
    <h2>원자재 수정</h2>
    
    <!-- 수정 폼 -->
    <form action="${pageContext.request.contextPath}/rawMaterials/update" method="POST">
        <!-- 원자재 코드 (수정 불가, readonly로 표시) -->
        <label for="rmCode">원자재 코드:</label>
        <input type="text" id="rmCode" name="rmCode" value="${rawMaterial.rmCode}" readonly /><br>
        
        <label for="rmName">원자재명:</label>
        <input type="text" id="rmName" name="rmName" value="${rawMaterial.rmName}" required maxlength="50" /><br>
        
        <label for="rmOrigin">원산지:</label>
        <input type="text" id="rmOrigin" name="rmOrigin" value="${rawMaterial.rmOrigin}" required maxlength="50" /><br>
        
        <label for="rmStorageMethod">보관 방법:</label>
        <textarea id="rmStorageMethod" name="rmStorageMethod" required maxlength="200">${rawMaterial.rmStorageMethod}</textarea><br>
        
        <input type="submit" value="수정 완료" />
    </form>
    
    <br>
    <a href="${pageContext.request.contextPath}/rawMaterials/list">목록으로 돌아가기</a>
</body>
</html>