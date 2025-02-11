<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>원자제 입고 등록 결과</title>
</head>
<body>

    <c:choose>
        <c:when test="${success}">
            <script type="text/javascript">
                alert("원자제 입고 등록이 성공적으로 처리되었습니다!");
                // 성공 시 목록 페이지로 리디렉션
                window.location.href = "${pageContext.request.contextPath}/rawmaterialsreceiving/list";
            </script>
        </c:when>
        <c:otherwise>
            <script type="text/javascript">
                alert("원자제 입고 등록에 실패했습니다. 다시 시도해 주세요.");
                // 실패 시 등록 페이지로 리디렉션
                window.location.href = "${pageContext.request.contextPath}/rawmaterialsreceiving/register";
            </script>
        </c:otherwise>
    </c:choose>

</body>
</html>
