<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-header text-center" style="background: linear-gradient(90deg, #ff8a00, #e52e71); color: white;">
                    <h2 class="fw-bold mb-0">내 정보</h2>
                </div>
                <div class="card-body p-5">
                    <c:choose>
                        <c:when test="${not empty user}">
                            <div class="form-floating mb-4">
                                <input type="text" id="uUserid" name="uUserid" class="form-control rounded-4" value="${user.UUserid}" readonly>
                                <label for="uUserid">아이디</label>
                            </div>
                            <div class="form-floating mb-4">
                                <input type="text" id="uUsername" name="uUsername" class="form-control rounded-4" value="${user.UUsername}" readonly>
                                <label for="uUsername">이름</label>
                            </div>
                            <div class="form-floating mb-4">
                                <input type="email" id="uEmail" name="uEmail" class="form-control rounded-4" value="${user.UEmail}" readonly>
                                <label for="uEmail">이메일</label>
                            </div>
                            <div class="form-floating mb-4">
                                <input type="text" id="uPhonenumber" name="uPhonenumber" class="form-control rounded-4" value="${user.UPhonenumber}" readonly>
                                <label for="uPhonenumber">전화번호</label>
                            </div>
                            <div class="form-floating mb-4">
                                <input type="text" id="uRoleenum" name="uRoleenum" class="form-control rounded-4" value="${user.URoleenum}" readonly>
                                <label for="uRoleenum">역할</label>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <p>사용자 정보가 없습니다. 로그인이 필요합니다.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
