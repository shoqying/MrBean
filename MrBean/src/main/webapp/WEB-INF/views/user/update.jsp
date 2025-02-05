<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-header text-center" style="background: #f8c471; color: white;">
                    <h2 class="fw-bold mb-0">내 정보 수정</h2>
                </div>
                <div class="card-body p-5">
                    <form action="${pageContext.request.contextPath}/user/update" method="post">
                        <div class="form-floating mb-4">
                            <input type="text" id="uUserid" name="uUserid" 
                                   class="form-control rounded-4" placeholder="아이디" 
                                   value="${user.UUserid}" readonly>
                            <label for="uUserid">아이디</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="text" id="uUsername" name="uUsername" 
                                   class="form-control rounded-4" placeholder="이름" 
                                   value="${user.UUsername}">
                            <label for="uUsername">이름</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="email" id="uEmail" name="uEmail" 
                                   class="form-control rounded-4" placeholder="이메일" 
                                   value="${user.UEmail}">
                            <label for="uEmail">이메일</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="text" id="uPhonenumber" name="uPhonenumber" 
                                   class="form-control rounded-4" placeholder="전화번호" 
                                   value="${user.UPhonenumber}">
                            <label for="uPhonenumber">전화번호</label>
                        </div>
                        <div class="form-floating mb-4">
                            <select id="uRoleenum" name="uRoleenum" class="form-select rounded-4">
                                <option value="ADMIN" ${user.URoleenum == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                                <option value="MANAGER" ${user.URoleenum == 'MANAGER' ? 'selected' : ''}>MANAGER</option>
                                <option value="MEMBER" ${user.URoleenum == 'MEMBER' ? 'selected' : ''}>MEMBER</option>
                            </select>
                            <label for="uRoleenum">역할</label>
                        </div>
                        <button class="btn btn-primary w-100 rounded-4" type="submit">수정</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>

<script>
    // 서버에서 전달한 alertMessage가 있는 경우 알림 표시 후 main.jsp로 리다이렉트
    const alertMessage = "${alertMessage}";
    if (alertMessage) {
        alert(alertMessage);
        window.location.href = "${pageContext.request.contextPath}/user/main";
    }
</script>
