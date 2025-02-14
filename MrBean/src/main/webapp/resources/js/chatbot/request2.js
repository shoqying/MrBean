document.addEventListener('DOMContentLoaded', function() {
    const chatbotButton = document.getElementById('chatbot-button');
    const chatbotWindow = document.getElementById('chatbot-window');
    const closeChatbot = document.getElementById('close-chatbot');
    const sendChatbot = document.getElementById('send-chatbot');
    const chatbotInput = document.getElementById('chatbot-input');
    const chatbotMessages = document.getElementById('chatbot-messages');
    const chatbotHeader = document.querySelector('.chatbot-header');

    let isChatbotOpen = false;
    let isDragging = false;
    let offsetX, offsetY;
    let isResizing = false;
    let initialWidth, initialHeight, initialX, initialY;

    // 대화창 초기 위치 및 크기 설정
    chatbotWindow.style.position = 'fixed';
    chatbotWindow.style.left = '80px';
    chatbotWindow.style.bottom = '0px';
    chatbotWindow.style.width = '400px';
    chatbotWindow.style.height = '500px';

    // 채팅창 열기/닫기
    chatbotButton.addEventListener('click', () => {
        isChatbotOpen = !isChatbotOpen;
        chatbotWindow.style.display = isChatbotOpen ? 'flex' : 'none';
    });

    closeChatbot.addEventListener('click', () => {
        isChatbotOpen = false;
        chatbotWindow.style.display = 'none';
    });

    // 메시지 전송
    sendChatbot.addEventListener('click', sendMessage);
    chatbotInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') sendMessage();
    });

    async function sendMessage() {
        const message = chatbotInput.value.trim();
        if (!message) return;

        addMessage('You', message);
        chatbotInput.value = '';
        sendChatbot.disabled = true;
        sendChatbot.style.width = '14%';
        sendChatbot.innerHTML = `
            <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
            <span class="visually-hidden">Loading...</span>
        `;

        await fetchChatbotResponse(message);

        sendChatbot.disabled = false;
        sendChatbot.innerHTML = '<i class="bi bi-arrow-return-left"></i>';
    }

    function addMessage(sender, message) {
        const messageElement = document.createElement('div');
        messageElement.classList.add('message', sender === 'You' ? 'user' : 'bot');
        messageElement.innerHTML = `<strong>${sender}:</strong> ${message}`;
        chatbotMessages.appendChild(messageElement);
        chatbotMessages.scrollTop = chatbotMessages.scrollHeight;
    }

    async function fetchChatbotResponse(message) {
        try {
            const response = await fetch('https://app.c7d2408t2p2.itwillbs.com/process_query/', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ query: message })
            });
            const data = await response.json();
            addMessage('Chatbot', data?.data ? formatResponse(data.data) : 'Invalid response format.');
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

    // 입력 텍스트에 따라 채팅창 크기 조정
    chatbotInput.addEventListener('input', function() {
        this.style.height = 'auto';
        this.style.height = this.scrollHeight + 'px';
    });

    // 대화창 드래그
    chatbotHeader.addEventListener('mousedown', function(e) {
        isDragging = true;
        offsetX = e.clientX - parseInt(chatbotWindow.style.left, 10);
        offsetY = e.clientY - parseInt(chatbotWindow.style.top, 10);
        document.addEventListener('mousemove', onMouseMove);
        document.addEventListener('mouseup', onMouseUp);
    });

    function onMouseMove(e) {
        if (isDragging) {
            e.preventDefault();
            chatbotWindow.style.left = `${e.clientX - offsetX}px`;
            chatbotWindow.style.top = `${e.clientY - offsetY}px`;
        }
    }

    function onMouseUp() {
        isDragging = false;
        document.removeEventListener('mousemove', onMouseMove);
        document.removeEventListener('mouseup', onMouseUp);
    }

    // 크기 조정
    document.getElementById('chatbot-resize-handle').addEventListener('mousedown', function(e) {
        isResizing = true;
        initialWidth = parseInt(chatbotWindow.style.width, 10);
        initialHeight = parseInt(chatbotWindow.style.height, 10);
        initialX = e.clientX;
        initialY = e.clientY;
        document.addEventListener('mousemove', onResize);
        document.addEventListener('mouseup', stopResize);
    });

    function onResize(e) {
        if (isResizing) {
            const dx = e.clientX - initialX;
            const dy = e.clientY - initialY;
            chatbotWindow.style.width = initialWidth + dx + 'px';
            chatbotWindow.style.height = initialHeight + dy + 'px';
        }
    }

    function stopResize() {
        isResizing = false;
        document.removeEventListener('mousemove', onResize);
        document.removeEventListener('mouseup', stopResize);
    }
});