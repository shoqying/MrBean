<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Role Distribution Graph</title>

    <!-- Chart.js 라이브러리 -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <style>
        /* 전체 스타일 */
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        canvas {
            display: block;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>MR.BEAN의 직급 비율</h1>
        <canvas id="roleChart" width="400" height="400"></canvas>
    </div>

    <script>
        // JSP에서 전달된 데이터
        const adminCount = <c:out value="${adminCount}" />;
        const managerCount = <c:out value="${managerCount}" />;
        const memberCount = <c:out value="${memberCount}" />;

        // Chart.js로 파이 차트 생성
        const ctx = document.getElementById('roleChart').getContext('2d');
        new Chart(ctx, {
            type: 'pie',
            data: {
                labels: ['Admin', 'Manager', 'Member'],
                datasets: [{
                    data: [adminCount, managerCount, memberCount],
                    backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                    hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top'
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                let total = context.dataset.data.reduce((sum, val) => sum + val, 0);
                                let value = context.raw;
                                let percentage = ((value / total) * 100).toFixed(2);
                                return `${context.label}: ${value} (${percentage}%)`;
                            }
                        }
                    }
                }
            }
        });
    </script>
</body>
</html>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
