<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- SweetAlert2 추가 -->
    
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg border-0 rounded-4">
                <div class="card-header text-center" style="background: #f8c471; color: white;">
                    <h2 class="fw-bold mb-0">비밀번호 확인</h2>
                </div>
                <div class="card-body p-5">
                    <form action="${pageContext.request.contextPath}/user/passwordcheck" method="post">
                        <div class="form-floating mb-4">
                            <input type="password" id="password" name="password" class="form-control rounded-4" placeholder="비밀번호">
                            <label for="password">비밀번호</label>
                        </div>
                        <button class="btn btn-primary w-100 rounded-4" type="submit">확인</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    // URL에서 error 파라미터 확인
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('error')) {
        Swal.fire({
            icon: 'error', // 오류 아이콘
            title: '비밀번호 확인',
            text: '비밀번호가 일치하지 않습니다. 다시 시도해주세요.',
            confirmButtonColor: '#d33', // 확인 버튼 색상
            confirmButtonText: '확인' // 확인 버튼 텍스트
        });
    }
</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>