<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>원자재 등록</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/toastStyle.css'/>">
</head>
<body>

<div class="container mt-5">
    <button class="btn btn-primary mb-3" onclick="location.href='/stock/list';">목록 페이지로</button>
    <h2 class="mb-4">원자재 등록</h2>

    <form action="/stock/stockRegister" method="post">
        <div class="form-group">
            <label for="rrQuantity">수량:</label>
            <input type="number" id="rrQuantity" name="rrQuantity" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="rrUnit">단위:</label>
            <input type="text" id="rrUnit" name="rrUnit" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="rmlDate">입고일:</label>
            <input type="date" id="rmlDate" name="rmlDate" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="wCode">창고 코드:</label>
            <input type="text" id="wCode" name="wCode" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="rmlNo">LOT 번호:</label>
            <input type="text" id="rmlNo" name="rmlNo" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="rmCode">원자재 코드:</label>
            <input type="text" id="rmCode" name="rmCode" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="rrExpirydate">유통기한:</label>
            <input type="date" id="rrExpirydate" name="rrExpirydate" class="form-control">
        </div>

        <button type="submit" class="btn btn-success">등록</button>
    </form>
</div>

</body>
</html>
