<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>직급 비율 그래프</title>

    <!-- Chart.js 라이브러리 -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
        }

        .container {
            max-width: 900px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            margin-bottom: 20px;
        }

        .chart-container {
            position: relative;
            height: 400px; /* 고정 높이 */
            width: 100%; /* 전체 너비 */
        }

        canvas {
            display: block;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>MR.BEAN의 직급 비율</h1>
        <div class="chart-container">
            <canvas id="roleChart"></canvas>
        </div>
    </div>

    <script>
        // JSP에서 전달된 데이터
        const adminCount = ${adminCount != null ? adminCount : 0};
        const managerCount = ${managerCount != null ? managerCount : 0};
        const memberCount = ${memberCount != null ? memberCount : 0};

        console.log("Admin Count:", adminCount);
        console.log("Manager Count:", managerCount);
        console.log("Member Count:", memberCount);

        // Chart.js로 막대 그래프 생성
        const ctx = document.getElementById('roleChart').getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['관리자 (Admin)', '매니저 (Manager)', '회원 (Member)'],
                datasets: [{
                    label: '직급별 인원 수',
                    data: [adminCount, managerCount, memberCount],
                    backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                    borderColor: ['#FF6384', '#36A2EB', '#FFCE56'],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true, // 반응형 활성화
                maintainAspectRatio: false, // 반응형에서 비율 유지 해제
                plugins: {
                    legend: {
                        display: true,
                        position: 'top'
                    },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                return `${context.label}: ${context.raw}명`;
                            }
                        }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: '인원 수'
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: '직급'
                        },
                        ticks: {
                            maxRotation: 0, // 라벨 회전 제거
                            minRotation: 0
                        },
                        grid: {
                            display: false // X축 그리드 제거
                        }
                    }
                },
                elements: {
                    bar: {
                        maxBarThickness: 50, // 막대의 최대 두께를 더 두껍게 설정
                        borderRadius: 5 // 막대 모서리 둥글게
                    }
                },
                layout: {
                    padding: {
                        left: 50, // 좌측 여백 추가
                        right: 50 // 우측 여백 추가
                    }
                },
                categoryPercentage: 0.7, // 막대 폭 비율 (0~1, 기본 0.8)
                barPercentage: 0.8 // 카테고리 내 막대 폭 비율 (0~1, 기본 0.9)
            }
        });
    </script>
</body>
</html>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
