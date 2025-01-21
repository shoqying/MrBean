<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <!-- 로그인 카드 -->
            <div class="card shadow-lg border-0 rounded-4" style="background: linear-gradient(145deg, #f5f7fa, #c3cfe2);">
                <div class="card-header text-center" style="background: linear-gradient(90deg, #ff8a00, #e52e71); color: white; border-radius: 10px 10px 0 0;">
                    <h2 class="fw-bold mb-0">로그인</h2>
                </div>
                <div class="card-body p-5">
                    <!-- 로그인 폼 -->
                    <form action="${pageContext.request.contextPath}/user/login" method="post">
                        <!-- 아이디 입력 -->
                        <div class="form-floating mb-4">
                            <input type="text" id="userId" name="userId" class="form-control rounded-4" placeholder="아이디" required>
                            <label for="userId">아이디</label>
                        </div>

                        <!-- 비밀번호 입력 -->
                        <div class="form-floating mb-4">
                            <input type="password" id="password" name="password" class="form-control rounded-4" placeholder="비밀번호" required>
                            <label for="password">비밀번호</label>
                        </div>

                        <!-- 버튼 -->
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-lg rounded-4" style="background: linear-gradient(90deg, #36d1dc, #5b86e5); border: none;">로그인</button>
                        </div>
                    </form>
                    <!-- 로그인 폼 끝 -->

                    <!-- 추가 옵션 -->
                    <div class="mt-3 text-center">
                        <a href="${pageContext.request.contextPath}/user/signup" style="text-decoration: none; color: #5b86e5;">회원가입</a> |
                        <a href="${pageContext.request.contextPath}/user/forgot-password" style="text-decoration: none; color: #5b86e5;">비밀번호 찾기</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
