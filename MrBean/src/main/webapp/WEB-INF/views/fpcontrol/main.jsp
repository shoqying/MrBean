<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title"><b>완제품 품질 검사 관리 목록</b></h5>

              <!-- Table with stripped rows -->
              <table class="table datatable">
                <thead>
                  <tr>
					<th>순번</th>
		            <th>제조일</th>
		            <th>완제품 LOT 번호</th>
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
                </thead>
                <tbody>
                	<c:forEach var="vo" items="${finishedProductsControlList}">
                  <tr id="row_${vo.fpcBno}">
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.fpcBno}">${vo.fpcBno}</c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.fpcDate}"><fmt:formatDate value="${vo.fpcDate}" pattern="yyyy-MM-dd" /></c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.fplNo}">${vo.fplNo}</c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.productsList[0].bomId}">${vo.productsList[0].bomId}</c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.productsList[0].PName}">${vo.productsList[0].PName}</c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.fpcExpirydate}"><fmt:formatDate value="${vo.fpcExpirydate}" pattern="yyyy-MM-dd" /></c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.fpcCheckdate}"><fmt:formatDate value="${vo.fpcCheckdate}" pattern="yyyy-MM-dd" /></c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.fpcQualityCheck}">
	                    		<select class="form-select" aria-label="Default select example" id="fpcQualityCheck_${vo.fpcBno}" onchange="updateQualityCheck(${vo.fpcBno}, this.value)">
				                    <option value="PENDING" ${vo.fpcQualityCheck == 'PENDING' ? 'selected' : ''}>대기중</option>
				                    <option value="COMPLETED" ${vo.fpcQualityCheck == 'COMPLETED' ? 'selected' : ''}>완료</option>
				                </select>
			                </c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
						    <c:when test="${not empty vo.fpcStatus and vo.fpcQualityCheck == 'COMPLETED'}">
						        <select class="form-select" aria-label="Default select example" id="fpcStatus_${vo.fpcBno}" onchange="updateStatus(${vo.fpcBno}, this.value)">
						            <option value="PENDING" ${vo.fpcStatus == 'PENDING' ? 'selected' : ''}>대기중</option>
						            <option value="PASS" ${vo.fpcStatus == 'PASS' ? 'selected' : ''}>합격</option>
						            <option value="FAIL" ${vo.fpcStatus == 'FAIL' ? 'selected' : ''}>불합격</option>
						        </select>
						    </c:when>
						    <c:otherwise>값이 없습니다</c:otherwise>
						</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.productionPlanList[0].planQuantity}">${vo.productionPlanList[0].planQuantity}</c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.fpcYield}">${vo.fpcYield * 100}%</c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                  	<td>
                    	<c:choose>
                    		<c:when test="${not empty vo.fpcQuantity}">${vo.fpcQuantity}</c:when>
                    		<c:otherwise>값이 없습니다</c:otherwise>
                    	</c:choose>
                    </td>
                       <td><button type="button" class="btn btn-danger" onclick="confirmDelete(${vo.fpcBno})">삭제</button></td>                  
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
    function updateQualityCheck(fpcBno, fpcQualityCheck) {
        $.ajax({
            url: '/fpcontrol/updateQualityCheck',  // 서버 URL (컨트롤러의 매핑 URL)
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ fpcBno: fpcBno, fpcQualityCheck: fpcQualityCheck }),
            success: function(response) {
                alert("품질 검사 상태가 업데이트되었습니다.");
                location.reload();  // 페이지 새로고침
            },
            error: function() {
                alert("업데이트 실패");
                location.reload();  // 페이지 새로고침
            }
        });
    }

    function updateStatus(fpcBno, fpcStatus) {
        $.ajax({
            url: '/fpcontrol/updateStatus',  // 서버 URL (컨트롤러의 매핑 URL)
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ fpcBno: fpcBno, fpcStatus: fpcStatus }),
            success: function(response) {
                alert("상태가 업데이트되었습니다.");
                location.reload();  // 페이지 새로고침
                
            },
            error: function() {
                alert("업데이트 실패\n품질검사를 먼저 완료로 변경해 주세요");
                location.reload();  // 페이지 새로고침
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