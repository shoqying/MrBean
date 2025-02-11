<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>완제품 출고 목록</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .table th, .table td {
            text-align: center;
        }
        .btn-group {
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <h2>완제품 출고 목록</h2>
        <a href="addFinishedProductForm.jsp" class="btn btn-primary mb-3">완제품 출고 추가</a>
        
        <!-- 테이블 출력 -->
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>#</th>
                    <th>출고 번호 (fo_no)</th>
                    <th>Lot 번호 (fpc_lotbno)</th>
                    <th>완제품 번호 (fpl_no)</th>
                    <th>제품 코드 (p_code)</th>
                    <th>수량 (fo_quantity)</th>
                    <th>단위 (fo_unit)</th>
                    <th>출고 일자 (fo_date)</th>
                    <th>출고지 (fo_shipping_location)</th>
                    <th>작업장 코드 (w_code)</th>
                    <th>상세보기</th>
                    <th>수정</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody>
                <!-- list 가져오기 -->
                <c:forEach var="product" items="${finishedProducts}">
                    <tr>
                        <td>${product.foNo}</td>
                        <td>${product.foNo}</td>
                        <td>${product.fpcLotbno}</td>
                        <td>${product.fplNo}</td>
                        <td>${product.pCode}</td>
                        <td>${product.foQuantity}</td>
                        <td>${product.foUnit}</td>
                        <td><fmt:formatDate value="${product.foDate}" pattern="yyyy-MM-dd" /></td>
                        <td>${product.foShippingLocation}</td>
                        <td>${product.wCode}</td>
                        <td><a href="viewFinishedProduct.jsp?foNo=${product.foNo}" class="btn btn-info btn-sm">상세보기</a></td>
                        <td><a href="editFinishedProductForm.jsp?foNo=${product.foNo}" class="btn btn-warning btn-sm">수정</a></td>
                        <td>
                            <form action="deleteFinishedProduct" method="post" style="display:inline;">
                                <input type="hidden" name="foNo" value="${product.foNo}">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- JS 파일 (optional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
