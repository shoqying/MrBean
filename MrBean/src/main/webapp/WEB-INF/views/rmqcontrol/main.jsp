<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>원자재 품질 검사 관리</title>
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
            <td>vo.rqcBno</td>
            <td>vo.rmlDate</td>
            <td>vo.rmlNo</td>
            <td>vo.rmCode</td>
            <td>vo.rqcDate</td>
            <td>
                <select>
                    <option value="대기중">vo.rqcQualityCheck</option>
                    <option value="완료">vo.rqcQualityCheck</option>
                </select>
            </td>
            <td>
                <select>
                	<option value="대기중">vo.rqcStatus</option>
                    <option value="합격">vo.rqcStatus</option>
                    <option value="불합격">vo.rqcStatus</option>
                </select>
            </td>
            <td>vo.workOrderNo</td>
            <td>vo.workQuantity</td>
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