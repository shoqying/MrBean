$(document).ready(function() {
    const chatSendBtn = $('#send-chatbot');
    const userInput = $('#chatbot-input');
    const chatMessages = $('#chatMessages');
    const chatbotSpinner = $('#chatbot-spinner');

    function addMessage(message, isUser) {
        const messageHtml = `
            <div class="message ${isUser ? 'user-message' : 'bot-message'}">
                <div class="message-content">${message}</div>
                <div class="message-time">${new Date().toLocaleTimeString()}</div>
            </div>`;
        chatMessages.append(messageHtml);
        chatMessages.scrollTop(chatMessages[0].scrollHeight);
    }

    async function loadChatHistory() {
        try {
            const response = await fetch('http://localhost:8000/chatbot/history');
            const data = await response.json();
            if (data.messages && Array.isArray(data.messages)) {
                data.messages.forEach(msg => {
                    addMessage(msg.message, msg.is_user);
                });
            }
        } catch (error) {
            console.error('채팅 내역 로딩 실패:', error);
        }
    }

    async function saveChatMessage(message, isUser) {
        try {
            await fetch('http://localhost:8000/chatbot/save', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    message: message,
                    is_user: isUser
                })
            });
        } catch (error) {
            console.error('메시지 저장 실패:', error);
        }
    }

    async function sendMessage() {
        const message = userInput.val().trim();
        if (!message) return;

        addMessage(message, true);
        await saveChatMessage(message, true);

        userInput.val('');
        chatSendBtn.prop('disabled', true);
        chatbotSpinner.show(); // Show spinner
        chatSendBtn.find('i').hide(); // Hide send icon

        let botResponse = '';

        try {
            const response = await fetch('http://localhost:8000/chatbot/', {
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
                await saveChatMessage(botResponse, false);
            }

        } catch (error) {
            console.error('채팅 오류:', error);
            addMessage('서버와의 연결에 실패했습니다.', false);
        } finally {
            chatbotSpinner.hide(); // Hide spinner
            chatSendBtn.find('i').show(); // Show send icon
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