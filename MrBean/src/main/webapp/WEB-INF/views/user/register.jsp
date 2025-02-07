<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <!-- 회원가입 카드 -->
            <div class="card shadow-lg border-0 rounded-4" style="background: linear-gradient(145deg, #f5f7fa, #c3cfe2);">
                <div class="card-header text-center" style="background: linear-gradient(90deg, #ff8a00, #e52e71); color: white; border-radius: 10px 10px 0 0;">
                    <h2 class="fw-bold mb-0">회원가입</h2>
                </div>
                <div class="card-body p-5">
                    <!-- 폼 시작 -->
                    <form action="${pageContext.request.contextPath}/user/register" method="post">
                        <!-- 아이디 -->
                        <div class="form-floating mb-4">
                            <input type="text" id="uUserid" name="uUserid" class="form-control rounded-4" placeholder="아이디" required>
                            <label for="uUserid">아이디</label>
                        </div>

                        <!-- 이름 -->
                        <div class="form-floating mb-4">
                            <input type="text" id="uUsername" name="uUsername" class="form-control rounded-4" placeholder="이름" required>
                            <label for="uUsername">이름</label>
                        </div>

                        <!-- 이메일 -->
                        <div class="form-floating mb-4">
                            <input type="email" id="uEmail" name="uEmail" class="form-control rounded-4" placeholder="이메일" required>
                            <label for="uEmail">이메일</label>
                        </div>

                        <!-- 전화번호 -->
                        <div class="form-floating mb-4">
                            <input type="text" id="uPhonenumber" name="uPhonenumber" class="form-control rounded-4" placeholder="전화번호" required>
                            <label for="uPhonenumber">전화번호</label>
                        </div>

                        <!-- 비밀번호 -->
                        <div class="form-floating mb-4">
                            <input type="password" id="uPasswordhash" name="uPasswordhash" class="form-control rounded-4" placeholder="비밀번호" required>
                            <label for="uPasswordhash">비밀번호</label>
                        </div>

                        <!-- 비밀번호 확인 -->
                        <div class="form-floating mb-4">
                            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control rounded-4" placeholder="비밀번호 확인" required>
                            <label for="confirmPassword">비밀번호 확인</label>
                        </div>

                        <!-- 역할 -->
                        <div class="form-floating mb-4">
                            <select id="uRoleenum" name="uRoleenum" class="form-select rounded-4" required>
                                <option value="" disabled selected>역할을 선택해 주세요</option>
                                <option value="ADMIN">관리자</option>
                                <option value="MANAGER">매니저</option>
                                <option value="MEMBER">회원</option>
                            </select>
                            <label for="uRoleenum">역할</label>
                        </div>

                        <!-- 회원가입 버튼 -->
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-lg rounded-4">회원가입</button>
                        </div>
                    </form>
                    <!-- 폼 끝 -->
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    <%-- 서버에서 전달된 에러 메시지 처리 --%>
    <c:if test="${not empty errorType}">
        <script>
            let errorMessage = '';
            switch ('${errorType}') {
                case 'passwordMismatch':
                    errorMessage = '비밀번호와 비밀번호 확인이 일치하지 않습니다.';
                    break;
                case 'userIdDuplicate':
                    errorMessage = '이미 사용 중인 아이디입니다.';
                    break;
                case 'invalidRole':
                    errorMessage = '유효하지 않은 역할 값입니다.';
                    break;
                default:
                    errorMessage = '알 수 없는 오류가 발생했습니다.';
            }
            Swal.fire({
                icon: 'error',
                title: '회원가입 실패',
                text: errorMessage,
                confirmButtonColor: '#d33',
                confirmButtonText: '확인'
            });
        </script>
    </c:if>

    <%-- 성공 메시지 처리 --%>
    <c:if test="${not empty success}">
        <script>
            Swal.fire({
                icon: 'success',
                title: '회원가입 성공',
                text: '${success}',
                confirmButtonColor: '#3085d6',
                confirmButtonText: '확인'
            });
        </script>
    </c:if>
</script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
