<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="Chat Bot"/>
<c:set var="sidebarTitle" value="Chat Bot"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/chatBotStyle.css'/>">
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
</head>

<div class="chat-container">
    <div class="chat-messages" id="chatMessages">
        <!-- Messages will be added here -->
    </div>

    <div id="chatbot-response" class="chatbot-response">
        <input type="text" id="chatbot-input" class="chatbot-input" placeholder="Type a message...">
        <button id="send-chatbot" class="send-chatbot">
            <i class="bx bx-paper-plane"></i>
            <!-- Spinner for loading -->
            <div id="chatbot-spinner" class="spinner-border spinner-border-sm text-light" role="status" style="display: none;">
                <span class="visually-hidden">Loading...</span>
            </div>
        </button>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="<c:url value='/resources/js/chatbot/openai.js'/>"></script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>