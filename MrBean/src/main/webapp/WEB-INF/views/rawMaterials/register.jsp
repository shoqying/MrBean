<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>원자재 등록</title>
</head>
<body>
    <div class="container">
        <h2>원자재 등록</h2>

        <!-- 등록 폼 -->
        <form action="/rawMaterials/register" method="post" id="registerForm">
            <div>
                <label for="rmName">원자재명</label>
                <input type="text" id="rmName" name="rmName" required maxlength="50" placeholder="원자재명을 입력하세요.">
            </div>

            <div>
                <label for="rmCode">원자재 코드</label>
                <input type="text" id="rmCode" name="rmCode" required maxlength="20" placeholder="원자재 코드를 입력하세요.">
            </div>

            <div>
                <label for="rmOrigin">원산지</label>
                <input type="text" id="rmOrigin" name="rmOrigin" required maxlength="50" placeholder="원산지를 입력하세요.">
            </div>

            <div>
                <label for="storageMethod">보관 방법</label>
                <textarea id="storageMethod" name="rmStorageMethod" required maxlength="200" placeholder="보관 방법을 입력하세요."></textarea>
            </div>

            <div>
                <button type="submit">등록</button>
                <button type="button" id="resetButton">초기화</button>
            </div>
        </form>

        <!-- 등록 성공 시 알림 토스트 메시지 -->
        <div id="toast" class="toast"></div>
    </div>

    <script>
        $(document).ready(function(){
            // 초기화 버튼 기능
            $("#resetButton").click(function(){
                $("#registerForm")[0].reset();
            });

            // 등록 성공 시 토스트 메시지
            $("#registerForm").submit(function(event){
                event.preventDefault(); // 폼 제출을 막고 자바스크립트로 처리
                $.ajax({
                    url: '/rawMaterials/register',
                    type: 'POST',
                    data: $(this).serialize(),
                    success: function(response) {
                        showToast("원자재가 성공적으로 등록되었습니다.");
                    },
                    error: function() {
                        showToast("등록에 실패했습니다.");
                    }
                });
            });

            function showToast(message) {
                $("#toast").text(message).fadeIn(400).delay(2000).fadeOut(400);
            }
        });
    </script>
</body>
</html>