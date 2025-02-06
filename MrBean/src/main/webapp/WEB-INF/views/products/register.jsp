<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>완제품 등록</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <div class="container mt-5">
        <h2 class="text-center mb-4">완제품 등록</h2>

        <!-- 폼 컨테이너 -->
        <div class="form-container">
            <!-- 메시지 처리 -->
            <c:if test="${not empty message}">
                <div class="alert alert-success" role="alert">${message}</div>
            </c:if>

            <!-- 제품 등록 폼 -->
            <form:form method="post" modelAttribute="product">
                <!-- 제품 코드 -->
                <div class="form-group">
                    <label for="pCode">제품 코드:</label>
                    <form:input path="pCode" id="pCode" class="form-control" required="true" maxlength="20"
                                pattern="^[a-zA-Z0-9]+$"
                                title="제품 코드는 필수 입력이며, 최대 20자까지만 입력 가능합니다." />
                </div>

                <!-- 제품명 -->
                <div class="form-group">
                    <label for="pName">제품명:</label>
                    <form:input path="pName" id="pName" class="form-control" required="true" maxlength="50"
                                pattern="^[a-zA-Z0-9가-힣\s]+$"
                                title="제품명은 필수 입력이며, 최대 50자까지만 입력 가능합니다." />
                </div>

                <!-- 제품 설명 -->
                <div class="form-group">
                    <label for="pDescription">제품 설명:</label>
                    <form:textarea path="pDescription" id="pDescription" class="form-control" required="true" maxlength="200"
                                   pattern="^[a-zA-Z0-9가-힣\s]+$"
                                   title="제품 설명은 필수 입력이며, 특수문자를 제외한 최대 200자까지만 입력 가능합니다."></form:textarea>
                </div>

                <!-- BOM 목록 드롭다운 -->
                <div class="form-group">
                    <label for="bomId">BOM 선택:</label>
                    <form:select path="bomId" id="bomId" class="form-control" required="true">
                        <!-- 빈 옵션 추가 -->
                        <option value="" selected disabled>-- 선택하세요 --</option>
                        <c:forEach var="bom" items="${bomList}">
                            <option value="${bom.bomId}">${bom.bomName}</option>
                        </c:forEach>
                    </form:select>
                </div>

                <!-- 제출 버튼 -->
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">등록</button>
            		<a href="/products/list" class="btn btn-primary">목록</a>
                </div>
            </form:form>
        </div>
    </div>
<script src="<c:url value='/resources/js/components/toast.js'/>"></script>
<script src="<c:url value='/resources/js/components/resetToast.js'/>"></script>
<script>
    window.onload = function() {
        <c:if test="${not empty message}">
            showToast("${message}");
        </c:if>
    };
</script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</html>
