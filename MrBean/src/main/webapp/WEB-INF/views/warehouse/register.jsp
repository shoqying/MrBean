<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>창고 등록</title>
</head>
<body>
    <div class="container">
        <button class="dashboard-btn" onclic="location.href='/dashboard';"> 대시보드로 돌아가기</button>
        <h1>창고 등록</h1>
        <form action="registerWarehouse" method="post">

        <label for="warehouseName">창고 이름</label>
        <input type="text" id="warehouseName" name="warehouseName" required autofocus>
		</form>
	</div>
</body>
</html>