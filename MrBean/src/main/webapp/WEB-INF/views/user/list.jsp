<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <!-- 카드 스타일 -->
            <div class="card shadow-lg border-0 rounded-4" style="background: linear-gradient(145deg, #f5f7fa, #c3cfe2);">
                <div class="card-header text-center" style="background: linear-gradient(90deg, #ff8a00, #e52e71); color: white; border-radius: 10px 10px 0 0;">
                    <h2 class="fw-bold mb-0">회원정보 목록</h2>
                </div>
                <div class="card-body p-5">
                    <!-- 테이블 시작 -->
                    <div class="table-responsive">
                        <table class="table table-striped table-hover rounded-4" id="userTable">
                            <thead class="table-primary" style="background: #e3e6ea; font-weight: bold;">
                                <tr>
                                    <th scope="col" class="text-center" onclick="sortTable(0)">ID</th>
                                    <th scope="col" class="text-center" onclick="sortTable(1)">이름</th>
                                    <th scope="col" class="text-center" onclick="sortTable(2)">전화번호</th>
                                    <th scope="col" class="text-center" onclick="sortTable(3)">이메일</th>
                                    <th scope="col" class="text-center" onclick="sortTable(4)">직급</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- 사용자 리스트 반복 출력 -->
                                <c:forEach var="user" items="${userList}">
                                    <tr>
                                        <td class="text-center">${user.UUserid}</td>
                                        <td class="text-center">${user.UUsername}</td>
                                        <td class="text-center">${user.UPhonenumber}</td>
                                        <td class="text-center">${user.UEmail}</td>
                                        <td class="text-center">
                                            <span class="badge" 
                                                  style="padding: 8px 12px; border-radius: 12px; font-size: 14px; background-color: 
                                                        <c:choose>
                                                            <c:when test='${user.URoleenum == "ADMIN"}'>#36d1dc</c:when>
                                                            <c:when test='${user.URoleenum == "MANAGER"}'>#5b86e5</c:when>
                                                            <c:otherwise>#ff8a00</c:otherwise>
                                                        </c:choose>; color: white;">
                                                ${user.URoleenum}
                                            </span>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- 테이블 끝 -->

                    <!-- 돌아가기 버튼 -->
                    <div class="d-grid mt-4">
                        <button class="btn btn-primary btn-lg rounded-4" onclick="location.href='${pageContext.request.contextPath}/user/main'">돌아가기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    let currentSortedColumn = -1;
    let ascending = true;

    function sortTable(columnIndex) {
        const table = document.getElementById("userTable");
        const rows = Array.from(table.rows).slice(1); // 헤더 제외

        // 정렬 방향 변경
        if (currentSortedColumn === columnIndex) {
            ascending = !ascending;
        } else {
            ascending = true;
            currentSortedColumn = columnIndex;
        }

        // 정렬
        rows.sort((rowA, rowB) => {
            const cellA = rowA.cells[columnIndex].innerText.trim();
            const cellB = rowB.cells[columnIndex].innerText.trim();

            if (ascending) {
                return cellA.localeCompare(cellB, undefined, { numeric: true });
            } else {
                return cellB.localeCompare(cellA, undefined, { numeric: true });
            }
        });

        // 정렬된 행 재배치
        const tbody = table.tBodies[0];
        tbody.innerHTML = "";
        rows.forEach(row => tbody.appendChild(row));
    }
</script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
