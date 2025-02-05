<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
        <input type="text" id="uUserid" name="uUserid" class="form-control rounded-4" placeholder="아이디" required>
        <label for="uUserid">아이디</label>
    </div>

    <!-- 비밀번호 입력 -->
    <div class="form-floating mb-4">
        <input type="password" id="uPasswordhash" name="uPasswordhash" class="form-control rounded-4" placeholder="비밀번호" required>
        <label for="uPasswordhash">비밀번호</label>
    </div>

    <div class="d-grid">
        <button type="submit" class="btn btn-primary btn-lg rounded-4">로그인</button>
    </div>
</form>

                    <!-- 로그인 폼 끝 -->

                    <!-- 추가 옵션 -->
                    <div class="mt-3 text-center">
                        <a href="${pageContext.request.contextPath}/user/register" style="text-decoration: none; color: #5b86e5;">회원가입</a> |
                        <a href="${pageContext.request.contextPath}/user/changePassword" style="text-decoration: none; color: #5b86e5;">비밀번호 찾기</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    <%-- 성공 메시지 처리 --%>
    <c:if test="${not empty success}">
        Swal.fire({
            icon: 'success',
            title: '반갑습니다.',
            text: '로그인 되셨습니다.',
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = '${pageContext.request.contextPath}/user/main';
            }
        });
    </c:if>

    <%-- 실패 메시지 처리 --%>
    <c:if test="${not empty error}">
        Swal.fire({
            icon: 'error',
            title: '로그인 실패',
            text: '아이디, 비밀번호를 확인바랍니다.',
            confirmButtonColor: '#d33',
            confirmButtonText: '다시 시도'
        });
    </c:if>
</script>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>
