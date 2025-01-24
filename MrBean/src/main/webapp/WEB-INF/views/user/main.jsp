<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container">
    <h1>new page</h1>
    <p>이곳에 내용을 추가하세요.</p>
</div>

<script>
    <%-- 성공 메시지 처리 --%>
    <c:if test="${not empty success}">
        let messageTitle = '';
        let messageText = '';

        // success 변수 값에 따라 메시지 다르게 설정
        if ('${success}' === 'login') {
            messageTitle = '반갑습니다.';
            messageText = '로그인 되셨습니다.';
        } else if ('${success}' === 'logout') {
            messageTitle = '로그아웃 완료';
            messageText = '로그아웃 되었습니다.';
        } else if ('${success}' === 'update') {
            messageTitle = '수정 완료';
            messageText = '정보가 성공적으로 수정되었습니다.';
        } else if ('${success}' === 'register') {
            messageTitle = '회원가입 완료';
            messageText = '회원가입을 축하드립니다.';
        }

        Swal.fire({
            icon: 'success',
            title: messageTitle,
            text: messageText,
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인'
        });
    </c:if>
</script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
