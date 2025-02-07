<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <!-- 비밀번호 찾기 카드 -->
            <div class="card shadow-lg border-0 rounded-4" style="background: linear-gradient(145deg, #f5f7fa, #c3cfe2);">
                <div class="card-header text-center" style="background: linear-gradient(90deg, #ff8a00, #e52e71); color: white; border-radius: 10px 10px 0 0;">
                    <h2 class="fw-bold mb-0">비밀번호 찾기</h2>
                </div>
                <div class="card-body p-5">
                    <!-- 비밀번호 찾기 폼 -->
                    <form method="POST" action="${pageContext.request.contextPath}/user/findPassword1">
                        <!-- 아이디 입력 -->
                        <div class="form-floating mb-4">
                            <input type="text" id="username" name="username" class="form-control rounded-4" placeholder="아이디" required>
                            <label for="username">아이디</label>
                        </div>

                        <!-- 이름 입력 -->
                        <div class="form-floating mb-4">
                            <input type="text" id="name" name="name" class="form-control rounded-4" placeholder="이름" required>
                            <label for="name">이름</label>
                        </div>

                        <!-- 이메일 입력 -->
                        <div class="form-floating mb-4">
                            <input type="email" id="email" name="email" class="form-control rounded-4" placeholder="이메일" required>
                            <label for="email">이메일</label>
                        </div>

                        <!-- 전화번호 입력 -->
                        <div class="form-floating mb-4">
                            <input type="text" id="phoneNumber" name="phoneNumber" class="form-control rounded-4" placeholder="전화번호" required>
                            <label for="phoneNumber">전화번호</label>
                        </div>

                        <!-- 정보조회 버튼 -->
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-lg rounded-4">정보조회</button>
                        </div>
                    </form>
                    <!-- 폼 끝 -->
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
