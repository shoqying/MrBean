$(document).ready(function() {
    function addMessage(message, isUser) {
        const messageHtml = `
            <div class="message ${isUser ? 'user-message' : 'bot-message'}">
                <div class="message-content">${message}</div>
                <div class="message-time">${new Date().toLocaleTimeString()}</div>
            </div>`;
        $('#chatMessages').append(messageHtml);
        $('#chatMessages').scrollTop($('#chatMessages')[0].scrollHeight);
    }

    function sendMessage() {
        const userInput = $('#userInput').val().trim();
        if (!userInput) return;

        addMessage(userInput, true);  // Add user message
        $('#userInput').val('');
        $('.chat-send-btn').prop('disabled', true); // Disable the send button

        // Show typing indicator (spinner)
        $('#typingIndicator').show();

        $.ajax({
            url: '/openai',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ query: userInput }), // Ensure the request data is properly formatted
            success: function(response) {
                addMessage(response, false); // Add bot response
                $('#typingIndicator').hide(); // Hide spinner when response is received
            },
            error: function() {
                addMessage('서버와의 연결에 실패했습니다.', false); // Error message
                $('#typingIndicator').hide(); // Hide spinner on error
            },
            complete: function() {
                $('.chat-send-btn').prop('disabled', false); // Re-enable the send button
            }
        });
    }

    $('.chat-send-btn').on('click', sendMessage);
    $('#userInput').on('keypress', function(e) {
        if (e.key === 'Enter') {
            sendMessage();
        }
    });
});