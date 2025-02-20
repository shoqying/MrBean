$(document).ready(function() {
    const chatSendBtn = $('#send-chatbot');
    const userInput = $('#chatbot-input');
    const chatMessages = $('#chatMessages');
    const chatbotSpinner = $('#chatbot-spinner');

    function addMessage(message, isUser, timestamp = null) {
        const messageTime = timestamp ? new Date(timestamp).toLocaleTimeString()
                                    : new Date().toLocaleTimeString();
        const messageHtml = `
            <div class="message ${isUser ? 'user-message' : 'bot-message'}">
                <div class="message-content">${message}</div>
                <div class="message-time">${messageTime}</div>
            </div>`;
        chatMessages.append(messageHtml);
        chatMessages.scrollTop(chatMessages[0].scrollHeight);
    }

    async function loadChatHistory() {
        try {
            const response = await fetch('https://app.c7d2408t2p2.itwillbs.com/chatbot/history');
            const data = await response.json();
            if (data.messages && Array.isArray(data.messages)) {
                data.messages.forEach(msg => {
                    addMessage(msg.message, msg.is_user, msg.timestamp);
                });
            }
        } catch (error) {
            console.error('채팅 내역 로딩 실패:', error);
        }
    }

    async function saveChatMessage(message, isUser) {
        try {
            const response = await fetch('https://app.c7d2408t2p2.itwillbs.com/chatbot/save', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    message: message,
                    is_user: isUser,
                    timestamp: new Date().toISOString()
                })
            });
            return await response.json();
        } catch (error) {
            console.error('메시지 저장 실패:', error);
            return null;
        }
    }

async function sendMessage() {
    const message = userInput.val().trim();
    if (!message) return;

    const savedMessage = await saveChatMessage(message, true);
    addMessage(message, true, savedMessage.timestamp);

    userInput.val('');
    chatSendBtn.prop('disabled', true);
    chatbotSpinner.show();
    chatSendBtn.find('i').hide();

    let botResponse = '';

    try {
        const response = await fetch('https://app.c7d2408t2p2.itwillbs.com/chatbot', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ query: message })
        });

        if (!response.ok) throw new Error('서버 응답 오류');

        const reader = response.body.getReader();
        const decoder = new TextDecoder();

        let isFirstChunk = true;
        while (true) {
          const { value, done } = await reader.read();
          if (done) break;

          const chunk = decoder.decode(value);
            const lines = chunk.split('\n');

            for (const line of lines) {
                if (line.startsWith('data: ')) {
                    try {
                        const data = JSON.parse(line.slice(6));
                        if (data.status === 'streaming' && data.content) {
                            if (isFirstChunk) {
                                addMessage('', false);
                                isFirstChunk = false;
                            }
                            botResponse += data.content;
                            $('.bot-message:last .message-content').text(botResponse);
                            chatMessages.scrollTop(chatMessages[0].scrollHeight);
                        }
                    } catch (e) {
                        console.error('스트리밍 데이터 파싱 오류:', e);
                    }
                }
            }
        }

        if (botResponse) {
            const savedBotMessage = await saveChatMessage(botResponse, false);
            if (savedBotMessage && savedBotMessage.timestamp) {
                $('.bot-message:last .message-time').text(
                    new Date(savedBotMessage.timestamp).toLocaleTimeString()
                );
            } else {
                $('.bot-message:last .message-time').text(
                    new Date().toLocaleTimeString()
                );
            }
        }

    } catch (error) {
        console.error('채팅 오류:', error);
        addMessage('서버와의 연결에 실패했습니다.', false);
    } finally {
        chatbotSpinner.hide();
        chatSendBtn.find('i').show();
        chatSendBtn.prop('disabled', false);
    }
}

    chatSendBtn.on('click', sendMessage);
    userInput.on('keypress', function(e) {
        if (e.key === 'Enter' && !e.shiftKey) {
            e.preventDefault();
            sendMessage();
        }
    });

    loadChatHistory();
    window.sendMessage = sendMessage;
});