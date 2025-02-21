<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>원자재 재고 목록</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/resources/css/toastStyle.css'/>">
<style>
.form-select {
   width: 44%;
}
.sort-form {
   float: right;
   display: flex;
   align-items: center;
   gap: 7px;
   width: 17%;
}
#form_f {
     position: absolute;
    right: 7.7%;
    top: 361px;
}
.raw-materials-list {
   margin-bottom: 40px;
}
.stock-materials-list {
   margin-top: 20px;
}
.pagination {
    justify-content: center;
    margin-top: 20px;
}
.page-link {
    color: #333;
}
.page-item.active .page-link {
    background-color: #6c757d;
    border-color: #6c757d;
}
</style>
</head>
<body>
<h1>원자재 재고 관리</h1>

<!-- 메시지 표시 영역 -->
<c:if test="${not empty successMessage}">
    <div class="alert alert-success">
        ${successMessage}
    </div>
</c:if>
<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">
        ${errorMessage}
    </div>
</c:if>

<!-- 정렬 옵션 -->
<%-- <form action="/stock/list" method="get" id="form_f" class="sort-form">
   <select name="sortOption" id="sortOption" class="form-select">
       <option value="latest" ${sortOption == 'latest' ? 'selected="selected"' : ''}>최신순</option>
       <option value="oldest" ${sortOption == 'oldest' ? 'selected="selected"' : ''}>오래된순</option>
   </select>
   <button type="submit" class="btn btn-secondary">정렬</button>
</form> --%>

<!-- 재고 목록 -->
<div class="stock-total-list">
   <h3>총 재고량</h3>
   <table class="table datatable">
       <thead>
           <tr>
               <th>순번</th>
               <th>원자재코드</th>
               <th>총 재고량</th>
 
           </tr>
       </thead>
       <tbody>
           <c:forEach var="total" items="${total}">
               <tr>
                   <td>${total.stBno}</td>
                   <td>${total.rmCode}</td>
                   <td>${total.stTotal}</td>
               </tr>
           </c:forEach>
       </tbody>
   </table>
   </div>

<!-- 원자재 입고 목록 -->
<div class="raw-materials-list">
   <h3>원자재 입고 목록</h3>
   <table class="table datatable">
       <thead>
           <tr>
               <th>입고번호</th>
               <th>LOT번호</th>
               <th>원자재코드</th>
               <th>수량</th>
               <th>단위</th>
               <th>유통기한</th>
               <th>작업</th>
           </tr>
       </thead>
       <tbody>
           <c:forEach var="raw" items="${rawList}">
               <tr>
                   <td>${raw.rrNo}</td>
                   <td>${raw.rmlNo}</td>
                   <td>${raw.rmCode}</td>
                   <td>${raw.rrQuantity}</td>
                   <td>${raw.rrUnit}</td>
                   <td><fmt:formatDate value="${raw.rrExpirydate}" pattern="yyyy-MM-dd" /></td>
                   <td>
                       <c:choose>
                           <c:when test="${raw.isRegistered == 0}">
                               <form action="/stock/list" method="POST" class="register-form">
                                   <input type="hidden" name="rr_no" value="${raw.rrNo}"/>
                                   <button type="submit" class="btn btn-primary btn-sm register-btn">재고등록</button>
                               </form>
                           </c:when>
                           <c:otherwise>
                               <span class="badge badge-success">등록완료</span>
                           </c:otherwise>
                       </c:choose>
                   </td>
               </tr>
           </c:forEach>
       </tbody>
   </table>
   
   <!-- 원자재 입고 목록 페이징 -->
   <nav aria-label="Raw Materials Page Navigation">
       <ul class="pagination">
           <c:if test="${currentRawPage > 1}">
               <li class="page-item">
                   <a class="page-link" href="/stock/list?rawPage=${currentRawPage-1}&stockPage=${currentStockPage}">이전</a>
               </li>
           </c:if>
           
           <c:forEach begin="1" end="${totalRawPages}" var="pageNum">
               <li class="page-item ${pageNum == currentRawPage ? 'active' : ''}">
                   <a class="page-link" href="/stock/list?rawPage=${pageNum}&stockPage=${currentStockPage}">${pageNum}</a>
               </li>
           </c:forEach>
           
           <c:if test="${currentRawPage < totalRawPages}">
               <li class="page-item">
                   <a class="page-link" href="/stock/list?rawPage=${currentRawPage+1}&stockPage=${currentStockPage}">다음</a>
               </li>
           </c:if>
       </ul>
   </nav>
</div>

<!-- 재고 목록 -->
<div class="stock-materials-list">
   <h3>재고 목록</h3>
   <table class="table datatable">
       <thead>
           <tr>
               <th>순번</th>
               <th>입고일</th>
               <th>단위</th>
               <th>LOT번호</th>
               <th>원자재 코드</th>
               <th>유통기한</th>
               <th>입고수량</th>
               <th>실제수량</th>
           </tr>
       </thead>
       <tbody>
           <c:forEach var="stockList" items="${stockList}">
               <tr>
                   <td>${stockList.smBno}</td>
                   <td>${stockList.rmlDate}</td>
                   <td>${stockList.rrUnit}</td>
                   <td>${stockList.rmlNo}</td>
                   <td>${stockList.rmCode}</td>
                   <td><fmt:formatDate value="${stockList.rrExpirydate}" pattern="yyyy-MM-dd" /></td>
                   <td>${stockList.rrQuantity}</td>
                   <td>${stockList.smTotal}</td>
               </tr>
           </c:forEach>
       </tbody>
   </table>
   
   <!-- 재고 목록 페이징 -->
   <nav aria-label="Stock Materials Page Navigation">
       <ul class="pagination">
           <c:if test="${currentStockPage > 1}">
               <li class="page-item">
                   <a class="page-link" href="/stock/list?stockPage=${currentStockPage-1}&rawPage=${currentRawPage}">이전</a>
               </li>
           </c:if>
           
           <c:forEach begin="1" end="${totalStockPages}" var="pageNum">
               <li class="page-item ${pageNum == currentStockPage ? 'active' : ''}">
                   <a class="page-link" href="/stock/list?stockPage=${pageNum}&rawPage=${currentRawPage}">${pageNum}</a>
               </li>
           </c:forEach>
           
           <c:if test="${currentStockPage < totalStockPages}">
               <li class="page-item">
                   <a class="page-link" href="/stock/list?stockPage=${currentStockPage+1}&rawPage=${currentRawPage}">다음</a>
               </li>
           </c:if>
       </ul>
   </nav>
</div>

<!-- JavaScript 코드 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function() {
    // 폼 제출 처리
    $('.register-form').on('submit', function(e) {
        e.preventDefault();
        const form = $(this);
        const btn = form.find('.register-btn');
        const td = btn.closest('td');
        
        $.ajax({
            url: form.attr('action'),
            method: 'POST',
            data: form.serialize(),
            success: function(response) {
                // 버튼을 등록완료 배지로 교체
                td.html('<span class="badge badge-success">등록완료</span>');
                // 성공 메시지 표시
                alert('재고 등록이 완료되었습니다.');
                // 페이지 새로고침
                location.reload();
            },
            error: function(xhr) {
                alert('재고 등록에 실패했습니다.');
            }
        });
    });
    
    // 5분마다 목록 갱신
    setInterval(function() {
        $.ajax({
            url: '/stock/list/update',
            method: 'GET',
            data: {
                page: ${currentStockPage},
                sortOption: $('#sortOption').val()
            },
            success: function(data) {
                // 재고 목록 갱신
                updateStockTable(data);
            }
        });
        
        $.ajax({
            url: '/stock/raw/update',
            method: 'GET',
            data: {
                page: ${currentRawPage}
            },
            success: function(data) {
                // 원자재 입고 목록 갱신
                updateRawTable(data);
            }
        });
    }, 300000); // 5분
});

function updateStockTable(data) {
    const tbody = $('.datatable tbody');
    tbody.empty();
    
    data.forEach(function(item) {
        tbody.append(`
            <tr>
                <td>${item.smBno}</td>
                <td>${item.rmlDate}</td>
                <td>${item.rrQuantity}</td>
                <td>${item.rrUnit}</td>
                <td>${item.rmlNo}</td>
                <td>${item.rmCode}</td>
                <td>${item.rrExpirydate}</td>
                <td>${item.smTotal}</td>
            </tr>
        `);
    });
}

function updateRawTable(data) {
    // 원자재 입고 목록 테이블 갱신 로직
    const tbody = $('.raw-materials-list table tbody');
    tbody.empty();
    
    data.forEach(function(item) {
        const button = item.isRegistered == 0 
            ? `<form action="/stock/list" method="POST" class="register-form">
                   <input type="hidden" name="rr_no" value="${item.rrNo}"/>
                   <button type="submit" class="btn btn-primary btn-sm register-btn">재고등록</button>
               </form>`
            : '<span class="badge badge-success">등록완료</span>';
            
        tbody.append(`
            <tr>
                <td>${item.rrNo}</td>
                <td>${item.rmlNo}</td>
                <td>${item.rmCode}</td>
                <td>${item.rrQuantity}</td>
                <td>${item.rrUnit}</td>
                <td>${item.rrExpirydate}</td>
                <td>${button}</td>
            </tr>
        `);
    });
}
</script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
</html>