<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="pageTitle" value="원자재 입고 등록"/>
<c:set var="sidebarTitle" value="창고관리"/>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<h1>원자재 입고 등록</h1>

<!-- 원자재 입고 등록 폼 -->
<form:form method="POST" action="/rawmaterialsreceiving/register" modelAttribute="rawMaterial">

    <!-- 로트 번호 (자동 생성된 값 표시) -->
    <div>
        <label for="rmlNo">로트 번호</label>
        <input type="text" id="rmlNo" name="rmlNo" value="${lotNo}" readonly />
    </div>

    <!-- 원자재 코드 -->
    <div>
        <label for="rmCode">원자재 코드</label>
        <input type="text" id="rmCode" name="rmCode" required readonly />
        <!-- 원자재 선택 버튼 -->
	        <button
	         type="button"
	         class="btn btn-outline-secondary"
             data-toggle="modal"
             data-target="#rawMaterialModal"
             title="모달에서 원자재 목록을 확인하고 선택하세요.">
             원자재 선택
           </button>
    </div>

    <!-- 입고 수량 -->
    <div>
        <label for="rrQuantity">입고 수량</label>
        <input type="number" id="rrQuantity" name="rrQuantity" required />
    </div>

    <!-- 입고 단위 -->
    <div>
        <label for="rrUnit">입고 단위</label>
        <input type="text" id="rrUnit" name="rrUnit" required />
    </div>

<!--     유효기간 -->
<!--     <div> -->
<!--         <label for="rrExpirydate">유효기간</label> -->
<!--         <input type="date" id="rrExpirydate" name="rrExpirydate" required readonly value="now"/> -->
<!--     </div> -->

    <!-- 제출 버튼 -->
    <div>
        <button type="submit">입고 등록</button>
    </div>

</form:form>

<!-- 모달창 -->
<!-- 원자재 선택 모달 -->
<div
    class="modal fade"
    id="rawMaterialModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="rawMaterialModalLabel"
    aria-hidden="true"
>
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="rawMaterialModalLabel">원자재 선택</h5>
            </div>
            <div class="modal-body">
                <!-- 원자재 목록 테이블 -->
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>원자재 코드</th>
                            <th>원자재 이름</th>
                            <th>원산지</th>
                            <th>보관방법</th>
                            <th>선택</th>
                        </tr>
                    </thead>
                    <tbody id="rawMaterialTableBody">
                        <!-- Ajax 결과로 동적 생성 -->
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <!-- 모달 창 닫기 버튼 -->
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    닫기
                </button>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="<c:url value='/resources/js/components/toast.js'/>"></script>
<script src="<c:url value='/resources/js/components/resetToast.js'/>"></script>
<script src="<c:url value='/resources/js/bom/createModal.js'/>"></script>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>
