<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>MR.BEAN 직급 비율</title>

    <!-- Chart.js 라이브러리 -->
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

        /* 차트 컨테이너 */
        .chart-container {
            position: relative;
            height: 400px;
            width: 100%;
        }
    </style>
</head>

<body>
<div class="container">
    <!-- 카드 디자인 적용 -->
    <div class="chart-card">
        <h3 class="chart-title">MR.BEAN의 직급 비율</h3>
        <div class="chart-container">
            <canvas id="roleChart"></canvas>
        </div>
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
            maintainAspectRatio: false, // 비율 유지 해제
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
                    maxBarThickness: 50, // 막대 최대 두께 설정
                    borderRadius: 5 // 막대 모서리 둥글게
                }
            },
            layout: {
                padding: {
                    left: 50, // 좌측 여백 추가
                    right: 50 // 우측 여백 추가
                }
            },
            categoryPercentage: 0.7, // 막대 폭 비율
            barPercentage: 0.8 // 카테고리 내 막대 폭 비율
        }
    });
</script>
</body>
</html>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
