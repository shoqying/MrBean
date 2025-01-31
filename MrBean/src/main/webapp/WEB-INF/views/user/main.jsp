<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>new page</title>

    <!-- SweetAlert2 라이브러리 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <!-- Chart.js 라이브러리 (버전에 따라 CDN 수정 가능) -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>

    <!-- 필요하다면 추가 CSS 작성 -->
    <style>
        /* 예시 스타일 */
        .chart-container {
            width: 80%;
            max-width: 900px;
            margin: 30px auto;
        }
    </style>
</head>
<body>
    <%-- 헤더 영역 include --%>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>

    <!-- SweetAlert2를 이용한 성공 및 에러 메시지 처리 -->
    <script>
        <c:if test="${not empty success}">
            const successMessage = '${success}'; 
            let messageTitle = '';
            let messageText = '';

            if (successMessage === 'login') {
                messageTitle = '반갑습니다.';
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
                confirmButtonColor: '#3085d6',
                confirmButtonText: '확인'
            });
        </c:if>

        <c:if test="${not empty error}">
            Swal.fire({
                icon: 'error',
                title: '접근 제한',
                text: '${error}', // Controller에서 전달된 에러 메시지
                confirmButtonColor: '#d33',
                confirmButtonText: '확인'
            });
        </c:if>
    </script>

    <hr />

    <!-- 생산계획 차트를 표시할 섹션 -->
    <div class="container">
        <h2>생산계획 차트</h2>

        <!-- 차트 캔버스를 감싸는 컨테이너 -->
        <div class="chart-container">
            <canvas id="planChart"></canvas>
        </div>
    </div>

    <!-- Chart.js 스크립트 -->
    <script>
        // Controller에서 planList를 전달받았다고 가정
        let planLabels = [];
        let planData = [];

        // JSTL을 통해 planList에서 planNumber, planQuantity를 가져온다
        <c:forEach var="plan" items="${planList}">
            planLabels.push('${plan.planNumber}');
            planData.push(${plan.planQuantity});
        </c:forEach>

        // 차트 그릴 컨텍스트
        const ctx = document.getElementById('planChart').getContext('2d');

        // 선 아래 영역을 그라디언트(Gradient)로 채울 설정
        const gradient = ctx.createLinearGradient(0, 0, 0, 400);
        gradient.addColorStop(0, 'rgba(54, 162, 235, 0.6)');  // 위쪽 색
        gradient.addColorStop(1, 'rgba(255, 255, 255, 0)');  // 아래쪽(투명)

        const data = {
            labels: planLabels,
            datasets: [{
                label: '생산계획 수량',
                data: planData,
                backgroundColor: gradient,             // 라인 아래 그라디언트 채우기
                borderColor: 'rgba(54, 162, 235, 1)',  // 라인 색상
                borderWidth: 2,
                fill: true,        // 라인 아래로 채우기
                tension: 0.3,      // 선 곡률 (0 ~ 1)
                pointRadius: 4,    // 각 데이터 지점의 원 크기
                pointBackgroundColor: 'rgba(54, 162, 235, 1)'
            }]
        };

        const config = {
            type: 'line',  // 라인 차트
            data: data,
            options: {
                responsive: true,
                plugins: {
                    title: {
                        display: true,
                        text: '생산계획 수량 차트'
                    },
                    tooltip: {
                        mode: 'index',
                        intersect: false
                    },
                    legend: {
                        display: true
                    }
                },
                interaction: {
                    mode: 'index',
                    intersect: false
                },
                scales: {
                    y: {
                        beginAtZero: true // Y축 0부터 시작
                    }
                }
            }
        };

        // 차트 생성
        new Chart(ctx, config);
    </script>

    <%-- 푸터 영역 include --%>
    <%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>
