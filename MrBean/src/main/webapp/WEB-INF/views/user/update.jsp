<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <!-- 내 정보 수정 카드 -->
            <div class="card shadow-lg border-0 rounded-4" style="background: linear-gradient(145deg, #f5f7fa, #c3cfe2);">
                <div class="card-header text-center" style="background: linear-gradient(90deg, #ff8a00, #e52e71); color: white; border-radius: 10px 10px 0 0;">
                    <h2 class="fw-bold mb-0">내 정보 수정</h2>
                </div>
                <div class="card-body p-5">
                    <form action="${pageContext.request.contextPath}/user/update" method="post">
                        <!-- 아이디 (읽기 전용) -->
                        <div class="form-floating mb-4">
                            <input type="text" id="uUserid" name="uUserid" 
                                   class="form-control rounded-4" placeholder="아이디" 
                                   value="${user.UUserid}" readonly>
                            <label for="uUserid">아이디</label>
                        </div>

                        <!-- 이름 -->
                        <div class="form-floating mb-4">
                            <input type="text" id="uUsername" name="uUsername" 
                                   class="form-control rounded-4" placeholder="이름" 
                                   value="${user.UUsername}">
                            <label for="uUsername">이름</label>
                        </div>

                        <!-- 이메일 -->
                        <div class="form-floating mb-4">
                            <input type="email" id="uEmail" name="uEmail" 
                                   class="form-control rounded-4" placeholder="이메일" 
                                   value="${user.UEmail}">
                            <label for="uEmail">이메일</label>
                        </div>

                        <!-- 전화번호 -->
                        <div class="form-floating mb-4">
                            <input type="text" id="uPhonenumber" name="uPhonenumber" 
                                   class="form-control rounded-4" placeholder="전화번호" 
                                   value="${user.UPhonenumber}">
                            <label for="uPhonenumber">전화번호</label>
                        </div>

                        <!-- 역할 -->
                        <div class="form-floating mb-4">
                            <select id="uRoleenum" name="uRoleenum" class="form-select rounded-4">
                                <option value="ADMIN" ${user.URoleenum == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                                <option value="MANAGER" ${user.URoleenum == 'MANAGER' ? 'selected' : ''}>MANAGER</option>
                                <option value="MEMBER" ${user.URoleenum == 'MEMBER' ? 'selected' : ''}>MEMBER</option>
                            </select>
                            <label for="uRoleenum">역할</label>
                        </div>

                        <!-- 수정 버튼 -->
                        <div class="d-grid">
                            <button class="btn btn-primary btn-lg rounded-4" type="submit">수정</button>
                        </div>
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
        Swal.fire({
            icon: 'success',
            title: '수정 완료',
            text: alertMessage,
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인'
        }).then(() => {
            window.location.href = "${pageContext.request.contextPath}/user/main";
        });
    }
</script>
