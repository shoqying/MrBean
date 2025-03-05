<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>생산 계획 상태별 진행도</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <style>
        /* 전체 컨테이너 스타일 */
        .container {
            max-width: 1000px;
            margin: 40px auto;
            padding: 20px;
            background-color: transparent; /* 배경색 투명 처리 */
            border-radius: 10px;
            box-shadow: none; /* 불필요한 테두리 제거 */
        }

        /* 카드 스타일 */
        .chart-card {
            background: white;
            border: none; /* 불필요한 테두리 제거 */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 부드러운 그림자 */
            padding: 30px;
            border-radius: 12px;
        }

        /* 제목 스타일 */
        .chart-title {
            text-align: center;
            font-size: 1.8rem;
            font-weight: bold;
            margin-bottom: 20px;
        }
    </style>
</head>

<body>
<div class="container">
    <!-- 카드 디자인 적용 -->
    <div class="chart-card">
        <h3 class="chart-title">생산 계획 상태별 진행도</h3>
        <canvas id="statusChart" width="800" height="400"></canvas>
    </div>
</div>

<script>
    // JSP에서 데이터 전달
    const waitingQuantities = [
        <c:forEach var="plan" items="${planList}">
            <c:if test="${plan.plStatus == 'WAITING'}">
                ${plan.planQuantity},
            </c:if>
        </c:forEach>
    ];

    const plannedQuantities = [
        <c:forEach var="plan" items="${planList}">
            <c:if test="${plan.plStatus == 'PLANNED'}">
                ${plan.planQuantity},
            </c:if>
        </c:forEach>
    ];

    const inProgressQuantities = [
        <c:forEach var="plan" items="${planList}">
            <c:if test="${plan.plStatus == 'IN_PROGRESS'}">
                ${plan.planQuantity},
            </c:if>
        </c:forEach>
    ];

    const completedQuantities = [
        <c:forEach var="plan" items="${planList}">
            <c:if test="${plan.plStatus == 'COMPLETED'}">
                ${plan.planQuantity},
            </c:if>
        </c:forEach>
    ];

    // Chart.js로 그래프 생성
    const ctx = document.getElementById('statusChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['WAITING', 'PLANNED', 'IN_PROGRESS', 'COMPLETED'], // 상태별
            datasets: [
                {
                    label: '상태별 수량',
                    data: [
                        waitingQuantities.reduce((a, b) => a + b, 0), // WAITING 총합
                        plannedQuantities.reduce((a, b) => a + b, 0), // PLANNED 총합
                        inProgressQuantities.reduce((a, b) => a + b, 0), // IN_PROGRESS 총합
                        completedQuantities.reduce((a, b) => a + b, 0), // COMPLETED 총합
                    ],
                    backgroundColor: [
                        'rgba(153, 102, 255, 0.5)', // WAITING 색상
                        'rgba(54, 162, 235, 0.5)',  // PLANNED 색상
                        'rgba(255, 206, 86, 0.5)',  // IN_PROGRESS 색상
                        'rgba(75, 192, 192, 0.5)',  // COMPLETED 색상
                    ],
                    borderColor: [
                        'rgba(153, 102, 255, 1)', // WAITING 색상
                        'rgba(54, 162, 235, 1)',  // PLANNED 색상
                        'rgba(255, 206, 86, 1)',  // IN_PROGRESS 색상
                        'rgba(75, 192, 192, 1)',  // COMPLETED 색상
                    ],
                    borderWidth: 1,
                },
            ],
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    display: false,
                },
                title: {
                    display: true,
                    text: '상태별 생산 계획 진행도 비교',
                },
            },
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: '수량',
                    },
                },
            },
        },
    });
</script>
</body>
</html>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
