<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header include -->
<%@ include file="/WEB-INF/views/include/header.jsp" %>    


</head>
<body>
<!-- JavaScript 파일 로드 -->
<script type="module" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
  <section class="section">
     <div class="row">
       <div class="col-lg-12">
         <div class="card">
           <div class="card-body">
            <h5 class="card-title"><i class="bi bi-pencil-square me-1"></i><b>작업지시 등록</b></h5>
             <!-- Floating Labels Form -->
             <form class="row g-3" id="workOrderForm">
             <!-- 첫번째 라인 -->
               <div class="col-md-6">
                 <div class="form-floating">
                   <input type="text" class="form-control" id="workNumber" placeholder="작업지시 번호" readonly>
                   <label for="workNumber">작업지시 번호</label>
                   <small class="text-muted">WO + YYYYMMDD + 일련번호(자동생성)</small>
                 </div>
               </div>
               
               <div class="col-md-6">
                 <div class="form-floating">
                   <input type="text" class="form-control" id="workPlanNo" placeholder="생산계획 선택" readonly>
                   <label for="workPlanNo">생산계획 번호</label>
                   <button type="button" class="btn btn-primary position-absolute end-0 top-0 mt-2 me-2" 
                           data-bs-toggle="modal" data-bs-target="#planSearchModal">
                     <i class="bi bi-search"></i> 계획선택
                   </button>
                 </div>
               </div>
               <!-- 둘째 줄 -->
               <div class="col-md-6">
                 <div class="form-floating">
                   <input type="text" class="form-control" id="planType" readonly>
                   <label for="planType">계획종류</label>
                 </div>
               </div>
   
               <div class="col-md-6">
                 <div class="form-floating">
                   <input type="text" class="form-control" id="pName" readonly>
                   <label for="pName">제품구분</label>
                 </div>
               </div>
               
               <!-- 셋째 줄 -->
               <div class="col-md-6">
                 <div class="form-floating">
                   <input type="date" class="form-control" id="workStartDate">
                   <label for="workStartDate">작업시작일</label>
                 </div>
               </div>
   
               <div class="col-md-6">
                 <div class="form-floating">
                   <input type="number" class="form-control" id="workQuantity" placeholder="작업수량">
                   <label for="workQuantity">작업수량(g)</label>
                   <small class="text-muted">계획수량: <span id="planQuantityDisplay">0</span>g</small>
                 </div>
               </div>	            
               
               <!-- 넷째 줄 -->
				<div class="col-md-12">
				  <div class="form-floating mb-3">
				    <select class="form-select" id="wName" aria-label="State">
				      <option value="">창고를 선택하세요</option>
				      <c:forEach var="warehouses" items="${warehouses}">
				        <option value="${warehouses.WName}" data-code="${warehouses.WCode}">${warehouses.WName}/${warehouses.WCode}</option>
				      </c:forEach>
				    </select>
				    <label for="wCode">창고구분</label>
				  </div>
				</div>
               
               
               
               <!-- 다섯째 줄 -->
               <div class="col-12">
                 <div class="form-floating">
                   <textarea class="form-control" placeholder="비고" id="workRemark" style="height: 100px"></textarea>
                   <label for="workRemark">비고</label>
                 </div>
               </div>
   
               <input type="hidden" class="workStatus" value="WAITING">
   
               <!-- 버튼 그룹 -->
               <div class="text-center">
                 <button type="button" class="btn btn-primary" id="insertBtn">
                   <i class="bi bi-check-circle me-1"></i>작업등록
                 </button>
                 <button type="button" class="btn btn-secondary" id="resetBtn">
                   <i class="bi bi-arrow-counterclockwise me-1"></i>초기화
                 </button>
               </div>
             </form>
           </div>
         </div>
       </div>
     </div>
   </section>
   
   <section class="section">
     <div class="row">
       <div class="col-lg-12">
         <div class="card">
           <div class="card-body">
             <h5 class="card-title">
               <i class="bi bi-list-check me-1"></i><b>작업지시 목록</b>
             </h5>

             <!-- Table with stripped rows -->
             <table class="table datatable">
               <thead>
                 <tr>
                   <th>작업지시번호</th>
                   <th>제품명</th>
                   <th>생산계획번호</th>
                   <th>작업시작일</th>
                   <th>작업수량</th>
                   <th>완료수량</th>
                   <th>작업상태</th>
                   <th>비고</th>
                   <th>등록자</th>
                   <th>작업관리</th>
                   <th>삭제</th>
                 </tr>
               </thead>
				<tbody>
				    <c:forEach var="work" items="${workList}">
				    <tr>
				        <td>${work.workOrderNo}</td>
				        <td>${work.PName}</td>
				        <td>${work.workPlanNo}</td>
				        <td>${work.workStartDate}</td>
				        <td>${work.workQuantity}</td>
				        <td>${work.completedQuantity}</td>
				        <td>
				        	<input type="hidden" class="plan-id" value="${work.planId}"> <!-- 히든요소 -->
				            <span class="badge ${work.workStatus == 'WAITING' ? 'bg-secondary' : 
				                             work.workStatus == 'IN_PROGRESS' ? 'bg-warning' : 
				                             work.workStatus == 'COMPLETED' ? 'bg-success' : 
				                             work.workStatus == 'STOPPED' ? 'bg-danger' : 'bg-light'}">
				                ${work.workStatus}
				            </span>
				        </td>
				        <td>${work.workRemark}</td>
				        <td>${work.workCreatedBy}</td>
				        <td>
				            <div class="btn-group" data-work-id="${work.workId}" data-work-status="${work.workStatus}">
				                <!-- 버튼들은 JavaScript에서 동적으로 생성됩니다 -->
				            </div>
				        </td>
				        <td>
				            <button type="button" class="btn btn-danger btn-sm delete-btn" data-work-id="${work.workId}">
				                <i class="bi bi-trash"></i>
				            </button>
				        </td>
				    </tr>
				    </c:forEach>
				</tbody>
             </table>
           </div>
         </div>
       </div>
     </div>
   </section>
   
<!-- 생산계획 검색 모달 -->

<!-- data-bs-backdrop="static"=> 외부클릭 닫힘 방지 기존은 true로 했었움 -->
<div class="modal fade" id="planSearchModal" data-bs-backdrop="true"> 
 <div class="modal-dialog modal-lg">
   <div class="modal-content">
     <div class="modal-header">
       <h5 class="modal-title">생산계획 검색</h5>
       <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
     </div>
     <div class="modal-body">
       <div class="row mb-3">
         <div class="col-md-4">
           <select id="searchPlanType" class="form-select">
             <option value="">계획종류 선택</option>
             <option value="일일">일일</option>
             <option value="주간">주간</option>
             <option value="월간">월간</option>
           </select>
         </div>
         <div class="col-md-4">
           <input type="text" id="searchProductName" class="form-control" placeholder="제품명">
         </div>
         <div class="col-md-4">
           <button type="button" id="searchPlanBtn" class="btn btn-primary">
             <i class="bi bi-search"></i> 검색
           </button>
         </div>
       </div>
       <table class="table table-bordered">
         <thead>
           <tr>
             <th>계획번호</th>
             <th>계획종류</th>
             <th>제품명</th>
             <th>계획수량</th>
             <th>시작일자</th>
             <th>종료일자</th>
             <th>상태</th>
           </tr>
         </thead>
         <tbody id="planSearchResults">
         <!-- 내용 동적으로 추가 -->
         </tbody>
       </table>
     </div>
   </div>
 </div>
</div>  
   
<!-- footer include -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>