<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <!-- 비밀번호 재설정 카드 -->
            <div class="card shadow-lg border-0 rounded-4" style="background: linear-gradient(145deg, #f5f7fa, #c3cfe2);">
                <div class="card-header text-center" style="background: linear-gradient(90deg, #ff8a00, #e52e71); color: white; border-radius: 10px 10px 0 0;">
                    <h2 class="fw-bold mb-0">비밀번호 재설정</h2>
                </div>
                <div class="card-body p-5">
                    <!-- 임시 비밀번호 출력 -->
                    <div class="text-center mb-4">
                        <p>아래의 임시 비밀번호를 사용하여 로그인해주세요.</p>
                        <div class="alert alert-primary rounded-4 fw-bold" style="font-size: 18px;">
                            ${tempPassword}
                        </div>
                    </div>
                    <!-- 버튼 -->
                    <div class="d-grid">
                        <a href="${pageContext.request.contextPath}/user/login" class="btn btn-primary btn-lg rounded-4">로그인 페이지로 이동</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
