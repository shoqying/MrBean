<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-header text-center" style="background: linear-gradient(90deg, #ff8a00, #e52e71); color: white;">
                    <h2 class="fw-bold mb-0">비밀번호 변경</h2>
                </div>
                <div class="card-body p-5">
                    <form method="POST" action="${pageContext.request.contextPath}/user/changePassword">
                        <div class="form-floating mb-4">
                            <input type="password" id="currentPassword" name="currentPassword" class="form-control rounded-4" placeholder="현재 비밀번호" required>
                            <label for="currentPassword">현재 비밀번호</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="password" id="newPassword" name="newPassword" class="form-control rounded-4" placeholder="새 비밀번호" required>
                            <label for="newPassword">새 비밀번호</label>
                        </div>
                        <button type="submit" class="btn btn-primary w-100 rounded-4">비밀번호 변경</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
