<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>완제품 품질 검사 관리</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
<h1>완제품 품질 검사 관리 목록</h1>
    <table>
        <tr>
            <th>순번</th>
            <th>제조일</th>
            <th>LOT 번호</th>
            <th>BOM</th>
            <th>완제품명</th>
            <th>유통기한</th>
            <th>검사일자</th>
            <th>품질 검사</th>
            <th>상태</th>
            <th>수량</th>
            <th>수율</th>
            <th>검사량 (g)</th>
            <th>삭제</th>
        </tr>
        
        <c:if test="${empty finishedProductsControlList}">
	        <tr>
	            <td colspan="12" style="text-align: center;">데이터가 없습니다</td>
	        </tr>
    	</c:if>
        <c:forEach var="vo" items="${finishedProductsControlList}">
        <tr id="row_${vo.fpcBno}">
		    <td>${vo.fpcBno}</td>
		    <td><fmt:formatDate value="${vo.fpcDate}" pattern="yyyy-MM-dd" /></td>
		    <td>${vo.fpcLotbno}</td>
		    <td>${vo.productsList[0].bomId}</td>
		    <td>${vo.productsList[0].PName}</td>
		    <td><fmt:formatDate value="${vo.fpcExpirydate}" pattern="yyyy-MM-dd" /></td>
		    <td><fmt:formatDate value="${vo.fpcCheckdate}" pattern="yyyy-MM-dd" /></td>
		    <td>
		        <select id="fpcQualityCheck_${vo.fpcBno}" onchange="updateQualityCheck(${vo.fpcBno}, this)">
                    <option value="PENDING" ${vo.fpcQualityCheck == 'PENDING' ? 'selected' : ''}>대기중</option>
                    <option value="COMPLETED" ${vo.fpcQualityCheck == 'COMPLETED' ? 'selected' : ''}>완료</option>
                </select>
            </td>
            <td>
                <select id="fpcStatus_${vo.fpcBno}" onchange="updateStatus(${vo.fpcBno}, this)">
                    <option value="PENDING" ${vo.fpcStatus == 'PENDING' ? 'selected' : ''}>대기중</option>
                    <option value="PASS" ${vo.fpcStatus == 'PASS' ? 'selected' : ''}>합격</option>
                    <option value="FAIL" ${vo.fpcStatus == 'FAIL' ? 'selected' : ''}>불합격</option>
                </select>
		    </td>
		    <td>${vo.productionPlanList[0].planQuantity}</td>
		    <td>${vo.fpcYield}</td>
		    <td>${vo.fpcQuantity}</td>
		    <td><button onclick="confirmDelete(this)">삭제</button></td>
		</tr>
    </c:forEach>
    </table>
    
	<script>
    function updateQualityCheck(fpcBno, fpcQualityCheck) {
        var qualityCheckValue = fpcQualityCheck.value;
        $.ajax({
            url: '/fpcontrol/updateQualityCheck',  // 서버 URL (컨트롤러의 매핑 URL)
            type: 'POST',
            data: { fpcBno: fpcBno, fpcQualityCheck: qualityCheckValue },
            success: function(response) {
                alert("품질 검사 상태가 업데이트되었습니다.");
            },
            error: function() {
                alert("업데이트 실패");
            }
        });
    }

    function updateStatus(fpcBno, fpcStatus) {
        var statusValue = fpcStatus.value;
        $.ajax({
            url: '/fpcontrol/updateStatus',  // 서버 URL (컨트롤러의 매핑 URL)
            type: 'POST',
            data: { fpcBno: fpcBno, fpcStatus: statusValue },
            success: function(response) {
                alert("상태가 업데이트되었습니다.");
            },
            error: function() {
                alert("업데이트 실패");
            }
        });
    }

 	// 삭제 처리
    function confirmDelete(fpcBno) {
	    if (confirm("삭제하시겠습니까?")) {
	        $.ajax({
	            url: '/fpcontrol/deleteFinishedProduct',  // 삭제 서버 URL
	            type: 'POST',
	            data: { fpcBno: fpcBno },
	            success: function(response) {
	                alert("삭제되었습니다.");
	                
	                // 삭제된 항목을 DOM에서 제거
	                $("#row_" + fpcBno).remove(); 
	            },
	            error: function(xhr, status, error) {
	                console.error("Error:", error);
	                alert("삭제 실패");
	            }
	        });
	    } else {
	        alert("삭제가 취소되었습니다.");
	    }
	}
    </script>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>
    

</html>