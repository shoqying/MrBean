<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>원자재 목록</title>
</head>
<body>
    <div>
        <h2>원자재 목록</h2>
        
        <!-- 원자재 목록 테이블 -->
        <table border="1" cellpadding="10" cellspacing="0">
            <thead>
                <tr>
                    <th>원자재 코드</th>
                    <th>원자재명</th>
                    <th>원산지</th>
                    <th>보관 방법</th>
                    <th>수정</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody>
                <!-- 원자재 목록 반복 출력 -->
                <c:forEach var="rawMaterial" items="${rawMaterialsList}">
                    <tr>
                        <td>${rawMaterial.rmCode}</td>
                        <td>${rawMaterial.rmName}</td>
                        <td>${rawMaterial.rmOrigin}</td>
                        <td>${rawMaterial.rmStorageMethod}</td>
                        <!-- 수정 버튼 추가 -->
                        <td>
                            <button onclick="openEditModal('${rawMaterial.rmCode}', '${rawMaterial.rmName}', '${rawMaterial.rmOrigin}', '${rawMaterial.rmStorageMethod}')">수정</button>
                        </td>
                        <td>
                            <button onclick="openDeleteModal('${rawMaterial.rmCode}')">삭제</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- 뒤로 가기 버튼 -->
        <div>
            <a href="/rawMaterials/register">원자재 등록 페이지로 돌아가기</a>
        </div>
    </div>

    <!-- 수정 모달 -->
    <div id="editModal" style="display: none;">
        <div>
            <h3>원자재 수정</h3>
            <form action="/rawMaterials/update" method="post">
                <input type="hidden" id="editRmCode" name="rmCode">
                <div>
                    <label for="editRmName">원자재명</label>
                    <input type="text" id="editRmName" name="rmName">
                </div>
                <div>
                    <label for="editRmOrigin">원산지</label>
                    <input type="text" id="editRmOrigin" name="rmOrigin">
                </div>
                <div>
                    <label for="editRmStorageMethod">보관 방법</label>
                    <input type="text" id="editRmStorageMethod" name="rmStorageMethod">
                </div>
                <div>
                    <button type="submit">수정</button>
                    <button type="button" onclick="closeEditModal()">취소</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        // 수정 모달을 여는 함수
        function openEditModal(code, name, origin, storageMethod) {
            document.getElementById("editRmCode").value = code;
            document.getElementById("editRmName").value = name;
            document.getElementById("editRmOrigin").value = origin;
            document.getElementById("editRmStorageMethod").value = storageMethod;
            document.getElementById("editModal").style.display = "block";
        }

        // 수정 모달을 닫는 함수
        function closeEditModal() {
            document.getElementById("editModal").style.display = "none";
        }
    </script>
</body>
</html>