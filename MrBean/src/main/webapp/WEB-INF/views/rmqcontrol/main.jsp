<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>원자재 품질 검사 관리</title>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

    <h1>원자재 품질 검사 관리 목록</h1>
    <table>
        <tr>
            <th>순번</th>
            <th>입고일</th>
            <th>LOT 번호</th>
            <th>원자재명</th>
            <th>검사일자</th>
            <th>품질 검사</th>
            <th>상태</th>
            <th>작업지시번호</th>
            <th>검사량 (g)</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="vo" items="${rawMaterialsQualityControlList}">
        <tr>
            <td>${vo.rqcBno}</td>
			<td>${vo.rmlDate}</td>
			<td>${vo.rmlNo}</td>
			<td>${vo.rmCode}</td>
			<td>${vo.rqcDate}</td>
			<td>
			    <select id="rqcQualityCheck_${vo.rqcBno}" onchange="updateQualityCheck(${vo.rqcBno}, this)">
                    <option value="PENDING" ${vo.rqcQualityCheck == 'PENDING' ? 'selected' : ''}>대기중</option>
                    <option value="COMPLETED" ${vo.rqcQualityCheck == 'COMPLETED' ? 'selected' : ''}>완료</option>
                </select>
            </td>
            <td>
                <select id="rqcStatus_${vo.rqcBno}" onchange="updateStatus(${vo.rqcBno}, this)">
                    <option value="PENDING" ${vo.rqcStatus == 'PENDING' ? 'selected' : ''}>대기중</option>
                    <option value="PASS" ${vo.rqcStatus == 'PASS' ? 'selected' : ''}>합격</option>
                    <option value="FAIL" ${vo.rqcStatus == 'FAIL' ? 'selected' : ''}>불합격</option>
                </select>
			</td>
			<td>${vo.workOrderNo}</td>
			<td>${vo.workQuantity}</td>
			<td><button onclick="confirmDelete(this)">삭제</button></td>
        </tr>
        </c:forEach>
    </table>
    
	<script>
    function updateQualityCheck(rqcBno, rqcQualityCheck) {
        var qualityCheckValue = rqcQualityCheck.value;
        $.ajax({
            url: '/rmqcontrol/updateQualityCheck',  // 서버 URL (컨트롤러의 매핑 URL)
            type: 'POST',
            data: { rqcBno: rqcBno, rqcQualityCheck: qualityCheckValue },
            success: function(response) {
                alert("품질 검사 상태가 업데이트되었습니다.");
            },
            error: function() {
                alert("업데이트 실패");
            }
        });
    }

    function updateStatus(rqcBno, rqcStatus) {
        var statusValue = rqcStatus.value;
        $.ajax({
            url: '/rmqcontrol/updateStatus',  // 서버 URL (컨트롤러의 매핑 URL)
            type: 'POST',
            data: { rqcBno: rqcBno, rqcStatus: statusValue },
            success: function(response) {
                alert("상태가 업데이트되었습니다.");
            },
            error: function() {
                alert("업데이트 실패");
            }
        });
    }

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