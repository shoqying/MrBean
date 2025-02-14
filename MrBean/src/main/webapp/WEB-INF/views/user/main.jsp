<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Animated Captions - Coffee Theme</title>

    <!-- SweetAlert2 라이브러리 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <style>
        /* 전체 스타일 */
        body {
            font-family: 'Lobster', 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(to bottom, #f3eacb, #d6c3a2);
            color: #333;
            overflow-x: hidden;
        }

        /* 이미지 컨테이너 */
        .image-container {
            position: relative;
            text-align: center;
            margin: 20px auto;
            padding: 30px;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 15px;
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
            animation: fadeIn 2s ease-in-out;
        }

        .image-container img {
            width: 50%;
            height: auto;
            max-height: 80vh;
            border-radius: 15px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }

        /* 문구 스타일 */
        .caption {
            position: absolute;
            top: 12%; /* 글자를 조금 더 위로 이동 */
            left: 50%;
            transform: translateX(-50%);
            display: flex;
            gap: 8px;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
        }

        .caption span {
            display: inline-block;
            font-size: 1vw; /* 글자 크기 */
            font-weight: 600;
            color: #f8f5f2;
            padding: 0.5vw 1vw; /* 반응형 패딩 */
            border-radius: 5px;
            background: linear-gradient(to right, #A67B5B, #6F4E37);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
            opacity: 0;
            transform: translateY(100px);
            animation: flyIn 2s ease-in-out forwards;
        }

        /* 각 글자 애니메이션 딜레이 */
        .caption span:nth-child(1) { animation-delay: 0s; }
        .caption span:nth-child(2) { animation-delay: 0.5s; }
        .caption span:nth-child(3) { animation-delay: 1s; }
        .caption span:nth-child(4) { animation-delay: 1.5s; }
        .caption span:nth-child(5) { animation-delay: 2s; }
        .caption span:nth-child(6) { animation-delay: 2.5s; }
        .caption span:nth-child(7) { animation-delay: 3s; }

        /* 애니메이션 */
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes flyIn {
            from {
                opacity: 0;
                transform: translateY(100px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        /* 반응형 스타일 */
        @media (max-width: 1024px) {
            .image-container img {
                width: 70%;
            }
            .caption span {
                font-size: 1.5vw;
                padding: 0.6vw 1.2vw;
            }
        }

        @media (max-width: 768px) {
            .image-container img {
                width: 80%;
            }
            .caption span {
                font-size: 1.8vw;
                padding: 0.8vw 1.6vw;
            }
        }

        @media (max-width: 480px) {
            .image-container img {
                width: 90%;
            }
            .caption span {
                font-size: 2.2vw;
                padding: 1vw 2vw;
            }
        }
    </style>
</head>
<body>
    <%-- 헤더 영역 include --%>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>

    <%-- SweetAlert2 메시지 처리 --%>
    <script>
        <c:if test="${not empty success}">
            const successMessage = '${success}';
            let messageTitle = '회원가입 ';
            let messageText = '회원가입 진심으로 축하드립니다.';

            if (successMessage === 'login') {
                messageTitle = '반갑습니다!';
                messageText = '로그인 되셨습니다.';
            } else if (successMessage === 'logout') {
                messageTitle = '로그아웃 완료';
                messageText = '로그아웃 되었습니다.';
            } else if (successMessage === 'update') {
                messageTitle = '수정 완료';
                messageText = '정보가 성공적으로 수정되었습니다.';
            } else if (successMessage === 'register') {
                messageTitle = '회원가입 완료';
                messageText = '회원가입을 축하드립니다.';
            } else if (successMessage === 'passwordChange') {
                messageTitle = '비밀번호 변경 완료';
                messageText = '비밀번호가 성공적으로 변경되었습니다.';
            }

            Swal.fire({
                icon: 'success',
                title: messageTitle,
                text: messageText,
                confirmButtonColor: '#6F4E37',
                confirmButtonText: '확인'
            });
        </c:if>

        <c:if test="${not empty error}">
            Swal.fire({
                icon: 'error',
                title: '접근 제한',
                text: '${error}',
                confirmButtonColor: '#A67B5B',
                confirmButtonText: '확인'
            });
        </c:if>
    </script>

    <%-- 이미지 및 문구 --%>
    <div class="image-container">
        <img src="${pageContext.request.contextPath}/resources/assets/img/mr.bean.png" alt="Bean Image">
        <div class="caption">
            <span>Mr.김</span>
            <span>Ms.박</span>
            <span>Mr.장</span>
            <span>Mr.정</span>
            <span>Mr.조</span>
            <span>Mr.허</span>
            <span>Mr.황</span>
        </div>
    </div>

    <%-- 푸터 영역 include --%>
    <%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>
