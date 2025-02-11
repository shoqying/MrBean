document.addEventListener('DOMContentLoaded', function() {
    const chatbotButton = document.getElementById('chatbot-button');
    const chatbotWindow = document.getElementById('chatbot-window');
    const closeChatbot = document.getElementById('close-chatbot');
    const sendChatbot = document.getElementById('send-chatbot');
    const chatbotInput = document.getElementById('chatbot-input');
    const chatbotMessages = document.getElementById('chatbot-messages');

    let isChatbotOpen = false;

    chatbotButton.addEventListener('click', () => {
        isChatbotOpen = !isChatbotOpen;
        chatbotWindow.style.display = isChatbotOpen ? 'flex' : 'none';
    });

    closeChatbot.addEventListener('click', function() {
        isChatbotOpen = false;
        chatbotWindow.style.display = 'none';
    });

    sendChatbot.addEventListener('click', sendMessage);
    chatbotInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            sendMessage();
        }
    });

    async function sendMessage() {
        const message = chatbotInput.value.trim();
        if (!message) return;

        addMessage('You', message);
        chatbotInput.value = '';
        sendChatbot.disabled = true; // 버튼 비활성화
        sendChatbot.style.width = '14%'; // 버튼 너비 설정
        sendChatbot.innerHTML = `
            <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
            <span class="visually-hidden">Loading...</span>
        `; // 스피너 표시

        await fetchChatbotResponse(message);

        sendChatbot.disabled = false; // 버튼 활성화
        sendChatbot.innerHTML = 'Send'; // 스피너 숨김
    }

    function addMessage(sender, message) {
        const messageElement = document.createElement('div');
        messageElement.classList.add('message', sender === 'You' ? 'user' : 'bot');
        messageElement.innerHTML = `<strong>${sender}:</strong> ${message}`;
        chatbotMessages.appendChild(messageElement);

        // 스크롤 자동 조정
        chatbotMessages.scrollTop = chatbotMessages.scrollHeight;
    }

    async function fetchChatbotResponse(message) {
        try {
            const response = await fetch('http://localhost:8000/process_query/', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ query: message })
            });
            const data = await response.json();

            if (data && data.data) {
                addMessage('Chatbot', formatResponse(data.data));
            } else {
                addMessage('Chatbot', 'Invalid response format.');
            }
        } catch (error) {
            addMessage('Chatbot', 'Unable to connect to server.');
        }
    }

    function formatResponse(responseData) {
        try {
            const parsedData = JSON.parse(responseData);
            if (Array.isArray(parsedData)) {
                return `<table border='1'><tr>${Object.keys(parsedData[0]).map(key => `<th>${key}</th>`).join('')}</tr>` +
                    parsedData.map(item => `<tr>${Object.values(item).map(value => `<td>${value}</td>`).join('')}</tr>`).join('') + '</table>';
            } else {
                return `<pre>${JSON.stringify(parsedData, null, 2)}</pre>`;
            }
        } catch (e) {
            return `<pre>${responseData}</pre>`;
        }
    }

    // 입력 텍스트 길이에 따라 채팅창 크기 조절
    chatbotInput.addEventListener('input', function() {
        this.style.height = 'auto';
        this.style.height = this.scrollHeight + 'px';
    });

    // 채팅창 기본 크기 증가
    chatbotWindow.style.width = '400px';
    chatbotWindow.style.height = '500px';
});