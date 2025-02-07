<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- SweetAlert2 추가 -->

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <!-- 비밀번호 변경 카드 -->
            <div class="card shadow-lg border-0 rounded-4" style="background: linear-gradient(145deg, #f5f7fa, #c3cfe2);">
                <div class="card-header text-center" style="background: linear-gradient(90deg, #ff8a00, #e52e71); color: white; border-radius: 10px 10px 0 0;">
                    <h2 class="fw-bold mb-0">비밀번호 변경</h2>
                </div>
                <div class="card-body p-5">
                    <!-- 비밀번호 변경 폼 -->
                    <form method="POST" action="${pageContext.request.contextPath}/user/changePassword" onsubmit="return validatePasswordMatch();">
                        <!-- 현재 비밀번호 -->
                        <div class="form-floating mb-4">
                            <input type="password" id="currentPassword" name="currentPassword" class="form-control rounded-4" placeholder="현재 비밀번호" required>
                            <label for="currentPassword">현재 비밀번호</label>
                        </div>

                        <!-- 새 비밀번호 -->
                        <div class="form-floating mb-4">
                            <input type="password" id="newPassword" name="newPassword" class="form-control rounded-4" placeholder="새 비밀번호" required>
                            <label for="newPassword">새 비밀번호</label>
                        </div>

                        <!-- 새 비밀번호 확인 -->
                        <div class="form-floating mb-4">
                            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control rounded-4" placeholder="새 비밀번호 확인" required>
                            <label for="confirmPassword">새 비밀번호 확인</label>
                        </div>

                        <!-- 비밀번호 변경 버튼 -->
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-lg rounded-4">비밀번호 변경</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    // 비밀번호 일치 확인
    function validatePasswordMatch() {
        const newPassword = document.getElementById('newPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (newPassword !== confirmPassword) {
            Swal.fire({
                icon: 'error',
                title: '비밀번호 변경 오류',
                text: '새 비밀번호가 일치하지 않습니다. 다시 확인해 주세요.',
                confirmButtonColor: '#d33',
                confirmButtonText: '확인'
            });
            return false;
        }
        return true;
    }

    <%-- 성공 메시지 처리 --%>
    <% if (request.getAttribute("success") != null) { %>
        Swal.fire({
            icon: 'success',
            title: '비밀번호 변경 완료',
            text: '비밀번호가 성공적으로 변경되었습니다.',
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인'
        });
    <% } else if (request.getAttribute("error") != null) { %>
        Swal.fire({
            icon: 'error',
            title: '오류 발생',
            text: '<%= request.getAttribute("error") %>',
            confirmButtonColor: '#d33',
            confirmButtonText: '확인'
        });
    <% } %>
</script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
