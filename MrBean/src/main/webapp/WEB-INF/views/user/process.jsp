<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>생산 계획 및 진행도</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .container {
            max-width: 900px;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 1.8rem;
        }
        canvas {
            margin: 20px auto;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>생산 계획 및 진행도</h1>

    <!-- 그래프가 표시될 영역 -->
    <canvas id="productionProgressChart" width="800" height="400"></canvas>
</div>

<script>
    // JSP에서 데이터 전달
    const planLabels = [
        <c:forEach var="plan" items="${planList}">
            '${plan.planNumber}',
        </c:forEach>
    ];

    const planQuantities = [
        <c:forEach var="plan" items="${planList}">
            ${plan.planQuantity},
        </c:forEach>
    ];

    const progressQuantities = [
        <c:forEach var="plan" items="${planList}">
            ${plan.progressQuantity}, // 실제 진행 수량 (progressQuantity 속성 필요)
        </c:forEach>
    ];

    // Chart.js 그래프 생성
    const ctx = document.getElementById('productionProgressChart').getContext('2d');
    const productionProgressChart = new Chart(ctx, {
        type: 'bar', // 막대 그래프
        data: {
            labels: planLabels, // 생산 계획 번호
            datasets: [
                {
                    label: '계획 수량',
                    data: planQuantities,
                    backgroundColor: 'rgba(54, 162, 235, 0.5)', // 파란색
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1,
                },
                {
                    label: '진행된 수량',
                    data: progressQuantities,
                    backgroundColor: 'rgba(75, 192, 192, 0.5)', // 녹색
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1,
                },
            ],
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    display: true,
                    position: 'top',
                },
                title: {
                    display: true,
                    text: '생산 계획 및 진행도 비교',
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
