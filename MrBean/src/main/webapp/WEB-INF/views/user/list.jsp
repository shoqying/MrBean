<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Google Fonts 추가 -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans:wght@400;500;700&display=swap" rel="stylesheet">

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<style>
    body {
        font-family: 'Noto Sans', sans-serif;
    }
    .card-header h2 {
        font-weight: 700;
    }
    table th, table td {
        font-weight: 500;
    }
    button {
        font-weight: 500;
    }
</style>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <!-- 카드 스타일 -->
            <div class="card shadow-lg border-0 rounded-4" style="background: linear-gradient(145deg, #f5f7fa, #c3cfe2); padding: 20px;">
                <div class="card-header text-center" style="background: linear-gradient(90deg, #ff8a00, #e52e71); color: white; border-radius: 10px 10px 0 0; padding: 15px;">
                    <h2 class="fw-bold mb-0">회원정보 목록</h2>
                </div>
                <div class="card-body p-4">
                    <!-- 테이블 시작 -->
                    <div class="table-responsive">
                        <table class="table table-striped table-hover rounded-4" style="margin-bottom: 20px;">
                            <thead class="table-primary" style="background-color: #c3cfe2;">
                                <tr>
                                    <th scope="col" class="text-center">ID</th>
                                    <th scope="col" class="text-center">이름</th>
                                    <th scope="col" class="text-center">전화번호</th>
                                    <th scope="col" class="text-center">이메일</th>
                                    <th scope="col" class="text-center">직급</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- 사용자 리스트 반복 출력 -->
                                <c:forEach var="user" items="${userList}">
                                    <tr>
                                        <td class="text-center" style="padding: 12px;">${user.UUserid}</td>
                                        <td class="text-center" style="padding: 12px;">${user.UUsername}</td>
                                        <td class="text-center" style="padding: 12px;">${user.UPhonenumber}</td>
                                        <td class="text-center" style="padding: 12px;">${user.UEmail}</td>
                                        <td class="text-center" style="padding: 12px;">
                                            <span class="badge" 
                                                  style="padding: 10px; border-radius: 8px; font-size: 14px; background: <c:choose>
                                                        <c:when test='${user.URoleenum == "ADMIN"}'>#36d1dc</c:when>
                                                        <c:when test='${user.URoleenum == "MANAGER"}'>#5b86e5</c:when>
                                                        <c:when test='${user.URoleenum == "MEMBER"}'>#ff8a00</c:when>
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
                    <div class="text-center mt-4">
                        <!-- 버튼에 onclick 추가 -->
                        <button class="btn btn-primary btn-lg" style="padding: 10px 20px; border-radius: 8px;" onclick="location.href='main'">돌아가기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
