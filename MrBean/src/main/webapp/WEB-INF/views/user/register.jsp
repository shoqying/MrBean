<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

 <h1>회원가입</h1>
    <form action="${pageContext.request.contextPath}/user/signup" method="post">
        <label>이름:</label>
        <input type="text" name="uUsername" required><br>
        <label>이메일:</label>
        <input type="email" name="uEmail" required><br>
        <label>비밀번호:</label>
        <input type="password" name="uPasswordhash" required><br>
        <button type="submit">회원가입</button>
    </form>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>




