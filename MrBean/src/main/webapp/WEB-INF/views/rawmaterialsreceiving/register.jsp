<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
    <title>원자재 입고 등록</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css">
</head>
<body>

<h2>원자재 입고 등록</h2>

<form action="<spring:url value='/rawmaterialsreceiving/register' />" method="post">
    <table>
        <tr>
            <td><label for="rmlNo">원자재 로트 번호:</label></td>
            <td><input type="text" id="rmlNo" name="rmlNo" required></td>
        </tr>
        <tr>
            <td><label for="rrBno">원자재 번호:</label></td>
            <td><input type="number" id="rrBno" name="rrBno" required></td>
        </tr>
        <tr>
            <td><label for="rmCode">원자재 코드:</label></td>
            <td><input type="text" id="rmCode" name="rmCode" required></td>
        </tr>
        <tr>
            <td><label for="rrQuantity">입고 수량:</label></td>
            <td><input type="number" id="rrQuantity" name="rrQuantity" required></td>
        </tr>
        <tr>
            <td><label for="rrUnit">단위:</label></td>
            <td>
                <select id="rrUnit" name="rrUnit">
                    <option value="g">g</option>
                    <option value="kg">kg</option>
                    <option value="ml">ml</option>
                    <option value="l">l</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="rrExpirydate">유통기한:</label></td>
            <td><input type="date" id="rrExpirydate" name="rrExpirydate" disabled></td>
        </tr>
    </table>

    <button type="submit">입고 등록</button>
</form>

</body>
</html>
