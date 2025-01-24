<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>원자재 삭제</title>
</head>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <h2>원자재 삭제 확인</h2>

    <!-- 알림 메시지 -->
    <c:if test="${not empty message}">
        <div style="color: green; font-weight: bold;">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div style="color: red; font-weight: bold;">${error}</div>
    </c:if>

    <!-- 삭제 확인 폼 -->
    <form action="${pageContext.request.contextPath}/rawMaterials/delete" method="POST">
        <input type="hidden" name="rmCode" value="${rawMaterialsVO.rmCode}" />
        
        <!-- 원자재 정보 표시 -->
        <p>원자재명: ${rawMaterialsVO.rmName}</p>
        <p>원산지: ${rawMaterialsVO.rmOrigin}</p>
        <p>보관 방법: ${rawMaterialsVO.rmStorageMethod}</p>

        <!-- 삭제 확인 -->
        <p>정말로 이 원자재를 삭제하시겠습니까?</p>
        
        <!-- 삭제 버튼 -->
        <input type="submit" value="삭제" />
        
        <!-- 취소 버튼 -->
        <a href="${pageContext.request.contextPath}/rawMaterials/list">취소</a>
    </form>

    <!-- 목록으로 돌아가기 링크 -->
    <br>
    <a href="${pageContext.request.contextPath}/rawMaterials/list">목록으로 돌아가기</a>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</html>