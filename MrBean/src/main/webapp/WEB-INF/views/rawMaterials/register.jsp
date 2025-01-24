<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>원자재 등록</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* 폼 컨테이너 */
        .form-container {
            max-width: 600px; /* 폼 최대 너비 설정 */
            margin: 0 auto; /* 중앙 정렬 */
            padding: 30px; /* 내부 여백 */
            border: 1px solid #ddd; /* 테두리 추가 */
            border-radius: 8px; /* 모서리 둥글게 */
            background-color: #f9f9f9; /* 배경색 */
        }

        /* 각 입력 필드의 레이블 */
        .form-group label {
            font-size: 14px; /* 레이블 글자 크기 축소 */
        }

        /* 각 입력 필드의 스타일 */
        .form-group input {
            font-size: 14px; /* 입력 필드 글자 크기 축소 */
            padding: 10px; /* 패딩 추가 */
            border-radius: 4px; /* 입력 필드 둥글게 */
        }

        /* 버튼 크기 조정 */
        .btn {
            font-size: 14px; /* 버튼 글자 크기 축소 */
            padding: 10px 20px; /* 버튼 내부 여백 */
        }

        /* 폼 하단 버튼들 정렬 */
		.form-buttons {
		    display: flex; /* 버튼들을 플렉스박스로 배치 */
		    justify-content: space-between; /* 양쪽 끝으로 버튼 배치 */
		    margin-top: 20px; /* 버튼들 간의 간격 */
		}
		
		/* 대시보드 및 리스트 버튼 */
		.navigate-buttons {
		    margin-top: 10px; /* 버튼들 간격 */
		    display: flex;
		    justify-content: space-around; /* 중앙으로 배치 */
		}
		
		.navigate-buttons .btn {
		    font-size: 14px; /* 버튼 글자 크기 */
		    padding: 6px 12px; /* 버튼 내부 여백 줄이기 */
		    width: auto; /* 너비를 자동으로 설정 */
		    max-width: 45%; /* 최대 너비를 45%로 설정 */
		}

        /* 폼 하단의 버튼을 중앙 정렬하고, 양 끝에 맞추기 위해서 아래 추가 */
        .navigate-buttons a {
            width: 48%; /* 버튼들이 양쪽 끝으로 정렬되도록 너비 조정 */
        }
    </style>
</head>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <div class="container mt-5">
        <h2 class="text-center mb-4">원자재 등록</h2>

        <!-- 폼 컨테이너 -->
        <div class="form-container">
            <form:form modelAttribute="rawMaterialsVO" method="POST" action="/rawMaterials/register">
                <!-- 원자재 코드 입력 필드 -->
                <div class="form-group">
                    <label for="rmCode">원자재 코드:</label>
                    <form:input path="rmCode" id="rmCode" type="text" class="form-control" required="true" />
                </div>

                <!-- 원자재명 입력 필드 -->
                <div class="form-group">
                    <label for="rmName">원자재명:</label>
                    <form:input path="rmName" id="rmName" type="text" class="form-control" required="true" />
                </div>

                <!-- 원산지 입력 필드 -->
                <div class="form-group">
                    <label for="rmOrigin">원산지:</label>
                    <form:input path="rmOrigin" id="rmOrigin" type="text" class="form-control" required="true" />
                </div>

                <!-- 보관 방법 입력 필드 -->
                <div class="form-group">
                    <label for="rmStorageMethod">보관 방법:</label>
                    <form:input path="rmStorageMethod" id="rmStorageMethod" type="text" class="form-control" required="true" />
                </div>

                <!-- 폼 하단 버튼들 정렬 -->
                <div class="form-buttons">
                    <button type="submit" class="btn btn-primary">등록</button>
                    <button type="reset" class="btn btn-secondary">초기화</button>
                </div>
            </form:form>
        </div>

        <!-- 대시보드 및 리스트 페이지로 돌아가는 버튼 -->
        <div class="navigate-buttons">
            <a href="${pageContext.request.contextPath}/rawMaterials/list" class="btn btn-primary">리스트 페이지로 돌아가기</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</html>
