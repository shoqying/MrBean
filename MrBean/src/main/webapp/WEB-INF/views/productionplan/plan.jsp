<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery를 먼저 로드 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<!-- JavaScript 파일 로드 -->
<script src="${pageContext.request.contextPath}/resources/js/1.js"></script>
   <form id="productPlanForm" action="/productionplan/plan" method="post">
	   <section class="section">
	      <div class="row">
	        <div class="col-lg-12">
	          <div class="card">
	            <div class="card-body">
	              <h5 class="card-title">생산계획 등록</h5>
	              <!-- Floating Labels Form -->
	              <form class="row g-3">
	                <div class="col-md-6">
	                  <div class="form-floating">
	                    <input type="text" class="form-control" id="ProductPlanNo" placeholder="생산계획 번호">
	                    <label for="floatingName">생산계획 번호(WO + YYYYMMDD + 일련번호3자리 자동생성)</label>
	                  </div>
	                </div>
	                <div class="col-md-6">
	                  <div class="form-floating mb-3">
	                    <select class="form-select" id="Priority" aria-label="State">
	                      <option selected>MEDIUM</option>
	                      <option value="1">HIGH</option>
	                      <option value="2">LOW</option>
	                    </select>
	                    <label for="floatingSelect">우선순위</label>
	                  </div>
	                </div>
	                <div class="col-md-6">
	                  <div class="form-floating mb-3">
	                    <select class="form-select" id="PlanType" aria-label="State">
	                      <option selected>일일</option>
	                      <option value="1">주간</option>
	                      <option value="2">분기</option>
	                    </select>
	                    <label for="floatingSelect">계획종류</label>
	                  </div>
	                </div>
	                <div class="row mb-3">
	                  <label for="inputDate" class="col-sm-2 col-form-label" id="startDate">시작일자</label>
	                  <div class="col-sm-10">
	                    <input type="date" class="form-control">
	                  </div>
	                </div>
	                <div class="row mb-3">
	                  <label for="inputDate" class="col-sm-2 col-form-label"id="endDate">종료일자</label>
	                  <div class="col-sm-10">
	                    <input type="date" class="form-control">
	                  </div>
	                </div>
	                <div class="col-md-6">
	                  <div class="form-floating mb-3">
	                    <select class="form-select" id="ProductCode" aria-label="State">
	                      <option selected>케냐</option>
	                      <option value="1">뉴욕</option>
	                      <option value="2">아이티윌</option>
	                    </select>
	                    <label for="floatingSelect">제품구분</label>
	                  </div>
	                </div>
	                <div class="col-md-6">
	                  <div class="form-floating">
	                    <input type="number" class="form-control" id="PlanQuantity" placeholder="계획수량(g)">
	                    <label for="floatingPassword">계획수량(g)</label>
	                  </div>
	                </div>
	                <div class="col-12">
	                  <div class="form-floating">
	                    <textarea class="form-control" placeholder="비고" id="PlanTextarea" style="height: 100px;"></textarea>
	                    <label for="PlanTextarea">비고</label>
	                  </div>
	                </div>
	                <div class="text-center">
	                  <button type="submit" class="btn btn-primary">계획등록</button>
	                  <button type="reset" class="btn btn-secondary">초기화</button>
	                </div>
	              </form><!-- End floating Labels Form -->
	            </div>
	          </div>
	        </div>
	      </div>
	    </section>
	</form>
</body>
</html>