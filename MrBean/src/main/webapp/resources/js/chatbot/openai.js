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
            $('#chatMessages').empty();

            const response = await fetch('https://app.c7d2408t2p2.itwillbs.com/chatbot/history');
            const data = await response.json();

            if (data.messages && Array.isArray(data.messages)) {
                // 타임스탬프 기준으로 정확하게 정렬
                const sortedMessages = data.messages.sort((a, b) => {
                    const timeA = new Date(a.timestamp).getTime();
                    const timeB = new Date(b.timestamp).getTime();
                    return timeA - timeB;
                });

                // 이전 메시지와 1초 이내의 중복 메시지 필터링
                const filteredMessages = sortedMessages.filter((msg, index) => {
                    if (index === 0) return true;

                    const prevMsg = sortedMessages[index - 1];
                    const timeDiff = Math.abs(
                        new Date(msg.timestamp).getTime() -
                        new Date(prevMsg.timestamp).getTime()
                    );

                    return !(
                        msg.message === prevMsg.message &&
                        msg.is_user === prevMsg.is_user &&
                        timeDiff < 1000  // 1초 이내 중복 제거
                    );
                });

                // 메시지 표시
                filteredMessages.forEach(msg => {
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
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify({
                    message: message,
                    is_user: isUser
                })
            });

            if (!response.ok) {
                const errorData = await response.json();
                console.error('서버 응답 오류:', errorData);
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            return await response.json();
        } catch (error) {
            console.error('메시지 저장 실패:', error);
            throw error;
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
        const response = await fetch('https://app.c7d2408t2p2.itwillbs.com/chatbot/', {
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