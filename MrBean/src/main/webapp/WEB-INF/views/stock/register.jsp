<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>원자재 등록</title>
    <style>
        .container {
            width: 50%;
            margin: auto;
        }
        .dashboard-btn {
            margin: 10px 0;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .dashboard-btn:hover {
            background-color: #45a049;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin: 10px 0 5px;
        }
        input, select {
            padding: 8px;
            margin-bottom: 10px;
            font-size: 14px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            padding: 10px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="container">
    <button class="dashboard-btn" onclick="location.href='/dashboard';">대시보드로 돌아가기</button>
    <h2>원자재 등록</h2>

    <form action="/register" method="post">
        <!-- 수량 -->
        <label for="rrQuantity">수량</label>
        <input type="number" id="rrQuantity" name="rrQuantity" required>

        <!-- LOT번호 -->
        <label for="rmlNo">LOT번호</label>
        <input type="text" id="rmlNo" name="rmlNo" required>

        <!-- 창고 코드 -->
        <label for="wCode">창고 코드</label>
        <input type="text" id="wCode" name="wCode" required>

        <!-- 입고일 -->
        <label for="rmlDate">입고일</label>
        <input type="date" id="rmlDate" name="rmlDate" required>

        <!-- 단위 -->
        <label for="rrUnit">단위</label>
        <select id="rrUnit" name="rrUnit" required>
            <option value="kg">kg</option>
            <option value="개">개</option>
            <option value="box">box</option>
        </select>

        <!-- 등록 버튼 -->
        <input type="submit" value="등록">
    </form>
</div>

</body>
</html>
