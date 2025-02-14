<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>원자재 입고 등록</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<h1>원자재 입고 등록</h1>


<form action="<spring:url value='/rawmaterialsreceiving/register' />" method="post">
    <table>
        <tr>
            <td><label for="rmlNo">원자재 로트 번호:</label></td>
            <td><input type="text" id="rmlNo" name="rmlNo" required></td>
        </tr>
        <tr>
            <td><label for="rrNo">원자재 번호:</label></td>
            <td><input type="number" id="rrNo" name="rrNo" required></td>
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
            <td><input type="date" id="rrExpirydate" name="rrExpirydate"></td>
        </tr>
    </table>

    <button type="submit">입고 등록</button>
    
	</form>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
