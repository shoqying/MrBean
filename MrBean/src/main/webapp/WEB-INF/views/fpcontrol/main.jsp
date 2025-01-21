<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>완제품 품질 검사 관리</title>
</head>
<body>

    <h1>완제품 품질 검사 관리 목록</h1>
    <table>
        <tr>
            <th>순번</th>
            <th>제조일</th>
            <th>LOT 번호</th>
            <th>완제품명</th>
            <th>유통기한</th>
            <th>검사일자</th>
            <th>품질 검사</th>
            <th>상태</th>
            <th>수율</th>
            <th>수량</th>
            <th>검사량 (g)</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="vo" items="${finishProductsControlControlList}">
        <tr>
		    <td>${vo.fpcBno}</td>
		    <td>${vo.fpcDate}</td>
		    <td>${vo.fpcLotbno}</td>
		    <td>${vo.pName}</td>
		    <td>${vo.fpcExpirydate}</td>
		    <td>${vo.fpcCheckdate}</td>
		    <td>
		        <select>
		            <option value="대기중" ${vo.fpcQualityCheck == 'PENDING' ? 'selected' : ''}>대기중</option>
		            <option value="완료" ${vo.fpcQualityCheck == 'COMPLETED' ? 'selected' : ''}>완료</option>
		        </select>
		    </td>
		    <td>
		        <select>
		            <option value="대기중" ${vo.fpcStatus == 'PENDING' ? 'selected' : ''}>대기중</option>
		            <option value="합격" ${vo.fpcStatus == 'PASS' ? 'selected' : ''}>합격</option>
		            <option value="불합격" ${vo.fpcStatus == 'FAIL' ? 'selected' : ''}>불합격</option>
		        </select>
		    </td>
		    <td>${vo.fpcYield}</td>
		    <td>${vo.planQty}</td>
		    <td>${vo.fpcQuantity}</td>
		    <td><button onclick="confirmDelete(this)">삭제</button></td>
		</tr>
    </c:forEach>
    </table>
    
	<script>
	// 삭제 확인 및 행 삭제 함수
        function confirmDelete(button) {
            if (confirm("삭제하시겠습니까?")) {
                alert("삭제되었습니다.");
            } else {
                alert("삭제가 취소되었습니다.");
            }
        }
	</script>

</body>
</html>