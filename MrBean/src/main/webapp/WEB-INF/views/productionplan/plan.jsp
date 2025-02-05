<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header include -->
<%@ include file="/WEB-INF/views/include/header.jsp" %>    

<!-- jQuery를 먼저 로드 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<!-- JavaScript 파일 로드 -->
<script type="module" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
   <section class="section">
      <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title"><i class="bi bi-pencil-square me-1"></i><b>생산계획 등록</b></h5>
              <!-- Floating Labels Form -->
              <form class="row g-3" id = "planForm"> <!-- 아이디 확인  -->
                <div class="col-md-6">
                  <div class="form-floating">
                    <input type="text" class="form-control" id="planNumber" placeholder="생산계획 번호" readonly="readonly">
                    <label for="planNumber">생산계획 번호(PO + YYYYMMDD + 일련번호3자리 자동생성)</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-floating mb-3">
                    <select class="form-select" id="planType" aria-label="State">
                      <option selected>일일</option>
                      <option>주간</option>
                      <option>월간</option>
                    </select>
                    <label for="planType">계획종류</label>
                  </div>
                </div>
                
                
                <div class="row mb-3">
                  <label for="planStartDate" class="col-sm-1 col-form-label">시작 예정일</label>
                  <div class="col-sm-5">
                    <input type="date" class="form-control" id="planStartDate">
                  </div>
                
                
                  <label for="planEndDate" class="col-sm-1 col-form-label">종료 예정일</label>
                  <div class="col-sm-5">
                    <input type="date" class="form-control" id="planEndDate">
                  </div>
                </div>
				<div class="col-md-6">
				  <div class="form-floating mb-3">
				    <select class="form-select" id="productCode" aria-label="State">
				      <option value="">제품을 선택하세요</option>
				      <c:forEach var="product" items="${productCodes}">
				        <option value="${product.PName}">${product.PName}</option>
				      </c:forEach>
				    </select>
				    <label for="productCode">제품구분</label>
				  </div>
				</div>
                <div class="col-md-6">
                  <div class="form-floating">
                    <input type="number" class="form-control" id="planQuantity" placeholder="계획수량(g)">
                    <label for="planQuantity">계획수량(g)</label>
                  </div>
                </div>
                <div class="col-12">
                  <div class="form-floating">
                    <textarea class="form-control" placeholder="비고" id="remark" style="height: 100px;"></textarea>
                    <label for="remark">비고</label>
                  </div>
                </div>
                <div class="text-center">
                  <button type="button" class="btn btn-primary" id="insertBtn">계획등록</button>

                  <button type="button" class="btn btn-secondary" id="resetBtn">초기화</button>
                </div>
              </form><!-- End floating Labels Form -->
            </div>
          </div>
        </div>
      </div>
      <input type="hidden" class= "plStatus" value="PLANNED">
    </section>
    
    
    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">
	            <i class="bi bi-list-check me-1"></i><b>생산계획 목록</b>
	          </h5>

              <!-- Table with stripped rows -->
              <table class="table datatable">
                <thead>
                  <tr>
                    <th>생산계획 번호</th>
                    <th>계획종류</th>
                    <th>시작예정일</th>
                    <th>종료예정일</th>
                    <th>계획상태</th>
                    <th>제품구분</th>
                    <th>계획수량(g)</th>
                    <th>비고</th>
                    <th>등록자</th>
                    <th>삭제</th>
                  </tr>
                </thead>
				<tbody>
				    <c:forEach var="plan" items="${planList}">
				    <tr>
				        <td>${plan.planNumber}</td>
				        <td>${plan.planType}</td>
				        <td>${plan.planStartDate}</td>
				        <td>${plan.planEndDate}</td>
				        <td>
				            <span class="badge ${plan.plStatus == 'PLANNED' ? 'bg-primary' : 
				            				 plan.plStatus == 'WAITING' ? 'bg-secondary' :
				                             plan.plStatus == 'IN_PROGRESS' ? 'bg-warning' : 
				                             plan.plStatus == 'COMPLETED' ? 'bg-success' : 
				                             plan.plStatus == 'STOPPED' ? 'bg-danger' : 'bg-light'}">
				                ${plan.plStatus}
				            </span>
				        </td>
				        <td>${plan.productCode}</td>
				        <td>${plan.planQuantity}</td>
				        <td>${plan.remark}</td>
				        <td>${plan.createdBy}</td>
				        <td>
				            <button type="button" class="btn btn-danger btn-sm" 
				                    onclick="window.planModule.delete('${plan.planId}')">
				                <i class="bi bi-trash"></i>
				            </button>
				        </td>
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
    
<!-- footer include -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>