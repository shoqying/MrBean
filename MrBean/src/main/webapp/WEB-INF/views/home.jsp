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
<body>
    <div class="chat-container">
        <div class="chat-messages" id="chatMessages">
            <!-- Messages will be added here -->
        </div>

        <div class="typing-indicator" id="typingIndicator" style="display: none;">
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
        </div>

        <div class="chat-input-container">
            <input type="text"
                   class="chat-input"
                   id="userInput"
                   placeholder="Type your message...">
            <button class="chat-send-btn" onclick="sendMessage()">
                <span>Send</span>
                <div class="spinner"></div> <!-- Spinner element inside the button -->
            </button>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<c:url value='/resources/js/chatbot/openai.js'/>"></script>
    <%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>