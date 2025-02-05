<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<%@ include file="/WEB-INF/views/include/header.jsp" %>


	<section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title"><b>원자재 품질 검사 관리 목록</b></h5>

              <!-- Table with stripped rows -->
              <table class="table datatable">
                <thead>
                  <tr>
					<th>순번</th>
		            <th>입고일</th>
		            <th>원자재 LOT 번호</th>
		            <th>원자재명</th>
		            <th>검사일자</th>
		            <th>품질 검사</th>
		            <th>상태</th>
		            <th>작업지시번호</th>
		            <th>검사량 (g)</th>
		            <th>삭제</th>
                  </tr>
	                <c:if test="${empty rawMaterialsQualityControlList}">
				        <tr>
				            <td colspan="12" style="text-align: center;">데이터가 없습니다</td>
				        </tr>
			    	</c:if>
                </thead>
                <tbody>
                	<c:forEach var="vo" items="${rawMaterialsQualityControlList}">
                  <tr id="row_${vo.rqcBno}">
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.rqcBno}">${vo.rqcBno}</c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.rawMaterialsLotList[0].rmlDate}"><fmt:formatDate value="${vo.rawMaterialsLotList[0].rmlDate}" pattern="yyyy-MM-dd" /></c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.rawMaterialsLotList[0].rmlNo}">${vo.rawMaterialsLotList[0].rmlNo}</c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.rawMaterialsList[0].rmName}">${vo.rawMaterialsList[0].rmName}</c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.rqcDate}"><fmt:formatDate value="${vo.rqcDate}" pattern="yyyy-MM-dd" /></c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.rqcQualityCheck}">
	                    		<select id="rqcQualityCheck_${vo.rqcBno}" onchange="updateQualityCheck(${vo.rqcBno}, this)">
				                    <option value="PENDING" ${vo.rqcQualityCheck == 'PENDING' ? 'selected' : ''}>대기중</option>
				                    <option value="COMPLETED" ${vo.rqcQualityCheck == 'COMPLETED' ? 'selected' : ''}>완료</option>
				                </select>
			                </c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.rqcStatus}">
                    			<select id="rqcStatus_${vo.rqcBno}" onchange="updateStatus(${vo.rqcBno}, this)">
				                    <option value="PENDING" ${vo.rqcStatus == 'PENDING' ? 'selected' : ''}>대기중</option>
				                    <option value="PASS" ${vo.rqcStatus == 'PASS' ? 'selected' : ''}>합격</option>
				                    <option value="FAIL" ${vo.rqcStatus == 'FAIL' ? 'selected' : ''}>불합격</option>
				                </select>
				            </c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.workOrdersList[0].workOrderNo}">${vo.workOrdersList[0].workOrderNo}</c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.workOrdersList[0].workQuantity}">${vo.workOrdersList[0].workQuantity}</c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                       <td><button type="button" class="btn btn-danger" onclick="confirmDelete(${vo.rqcBno})">삭제</button></td>                 
                  </tr>
					</c:forEach>
                </tbody>
              </table>
              <!-- End Table with stripped rows -->

            </div>
          </div>

        </div>
      </div>
    </section>
    
    
        


 
    
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

 	// 삭제 처리
    function confirmDelete(rqcBno) {
	    if (confirm("삭제하시겠습니까?")) {
	        $.ajax({
	            url: '/rmqcontrol/deleteRawMaterial',  // 삭제 서버 URL
	            type: 'POST',
	            data: { rqcBno: rqcBno },
	            success: function(response) {
	                alert("삭제되었습니다.");
	                
	                // 삭제된 항목을 DOM에서 제거
	                $("#row_" + rqcBno).remove(); 
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