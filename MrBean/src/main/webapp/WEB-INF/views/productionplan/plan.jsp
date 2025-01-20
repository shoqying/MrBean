<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- header include -->
<%@ include file="/WEB-INF/views/include/header.jsp" %>    

<!-- jQuery를 먼저 로드 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<!-- JavaScript 파일 로드 -->
<script src="${pageContext.request.contextPath}/resources/js/1.js"></script>
   <section class="section">
      <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">생산계획 등록</h5>
              <!-- Floating Labels Form -->
              <form class="row g-3" id="productPlanForm" action="/productionplan/plan" method="post">
                <div class="col-md-6">
                  <div class="form-floating">
                    <input type="text" class="form-control" id="planNumber" placeholder="생산계획 번호">
                    <label for="planNumber">생산계획 번호(WO + YYYYMMDD + 일련번호3자리 자동생성)</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-floating mb-3">
                    <select class="form-select" id="priority" aria-label="State">
                      <option selected>MEDIUM</option>
                      <option value="1">HIGH</option>
                      <option value="2">LOW</option>
                    </select>
                    <label for="priority">우선순위</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-floating mb-3">
                    <select class="form-select" id="planType" aria-label="State">
                      <option selected>일일</option>
                      <option value="1">주간</option>
                      <option value="2">분기</option>
                    </select>
                    <label for="planType">계획종류</label>
                  </div>
                </div>
                
                
                <div class="row mb-3">
                  <label for="planStartDate" class="col-sm-1 col-form-label">시작일자</label>
                  <div class="col-sm-5">
                    <input type="date" class="form-control" id="planStartDate">
                  </div>
                
                
                  <label for="planEndDate" class="col-sm-1 col-form-label">종료일자</label>
                  <div class="col-sm-5">
                    <input type="date" class="form-control" id="planEndDate">
                  </div>
                </div>
                
                
                
                <div class="col-md-6">
                  <div class="form-floating mb-3">
                    <select class="form-select" id="productCode" aria-label="State">
                      <option selected>케냐</option>
                      <option value="1">뉴욕</option>
                      <option value="2">아이티윌</option>
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
                  <button type="submit" class="btn btn-primary" id="submitBtn">계획등록</button>
                  <button type="reset" class="btn btn-secondary" id="resetBtn">초기화</button>
                </div>
              </form><!-- End floating Labels Form -->
            </div>
          </div>
        </div>
      </div>
    </section>
<!-- footer include -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>