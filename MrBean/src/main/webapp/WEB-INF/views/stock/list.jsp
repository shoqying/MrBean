<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>원자재 재고 목록</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/resources/css/toastStyle.css'/>">

<style>
/* #intsertBtn{
	position: absolute;
	    right: 262px;
	    top: 270px;
} */

.form-select{
	width:44%;
}

form {
	float:right;
    display: flex;
    align-items: center; /* 수직 정렬을 가운데로 */
    gap: 7px; /* 각 요소 사이에 10px 간격 추가 */
    width:17%;
}


#form_f{
	position: absolute;
    right: 9.7%;
    top: 260px
}

#bbtn {
    margin-left: auto;
    margin-right: 12px;
    margin-top: -34px;
    width: 18%;
    display: block;
}





</style>

</head>
<body>

<h1>원자재 재고 목록</h1>



<!-- 정렬 옵션 -->

<form action="/stock/list" method="get" id="form_f">
   
    <select name="sortOption" id="sortOption" class="form-select">
        <option value="latest" ${sortOption == 'latest' ? 'selected="selected"' : ''}>최신순</option>
        <option value="oldest" ${sortOption == 'oldest' ? 'selected="selected"' : ''}>오래된순</option>
    </select>
    <button type="submit" class="btn btn-secondary">정렬</button>
 
</form>



<!-- 원자재 목록 테이블 -->
<table class="table datatable">
    <thead>
        <tr>
            <th>순번</th>
            <th>입고일</th>
            <th>수량</th>
            <th>단위</th>
            <th>창고 코드</th>
            <th>LOT번호</th>
            <th>원자재 코드</th>
            <th>유통기한</th>
            <th>총 재고량<th>
     
        </tr>
    </thead>
    <tbody>
        <c:forEach var="stockMaterial" items="${stockMaterials}">
            <tr>
                <td>${stockMaterial.smBno}</td>
                <td>${stockMaterial.rmlDate}</td>
                <td>${stockMaterial.rrQuantity}</td>
                <td>${stockMaterial.rrUnit}</td>
                <td>${stockMaterial.WCode}</td>
                <td>${stockMaterial.rmlNo}</td>
                <td>${stockMaterial.rmCode}</td>
                <td>${stockMaterial.rrExpirydate}</td>
                <td>${stockMaterial.smTotal}</td>
            
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- JavaScript 코드 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    // 테이블을 5초마다 갱신하는 함수
    function fetchUpdatedList() {
        $.ajax({
            url: '/stock/list',
            method: 'GET',
            success: function(data) {
                $('.table datatable').html(data); // 테이블 내용 업데이트
            }
        });
    }

    // 일정 시간마다 새로고침
    setInterval(fetchUpdatedList, 100000); // 1분마다 호출
</script>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
</html>
