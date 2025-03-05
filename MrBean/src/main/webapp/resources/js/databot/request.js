document.addEventListener('DOMContentLoaded', function() {
    const databotButton = document.getElementById('databot-button');
    const databotWindow = document.getElementById('databot-window');
    const closeDatabot = document.getElementById('close-databot');
    const sendDatabot = document.getElementById('send-databot');
    const databotInput = document.getElementById('databot-input');
    const databotMessages = document.getElementById('databot-messages');
    const databotHeader = document.querySelector('.databot-header');

    let isDatabotOpen = false;
    let isDragging = false;
    let offsetX, offsetY;
    let isResizing = false;
    let initialWidth, initialHeight, initialX, initialY;

    // 대화창 초기 위치 및 크기 설정
    databotWindow.style.position = 'fixed';
    databotWindow.style.left = '80px';
    databotWindow.style.bottom = '0px';
    databotWindow.style.width = '400px';
    databotWindow.style.height = '500px';

    // 채팅창 열기/닫기
    databotButton.addEventListener('click', () => {
        isDatabotOpen = !isDatabotOpen;
        databotWindow.style.display = isDatabotOpen ? 'flex' : 'none';
    });

    closeDatabot.addEventListener('click', () => {
        isDatabotOpen = false;
        databotWindow.style.display = 'none';
    });

    // 메시지 전송
    sendDatabot.addEventListener('click', sendMessage);
    databotInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') sendMessage();
    });

    async function sendMessage() {
        const message = databotInput.value.trim();
        if (!message) return;

        addMessage('You', message);
        databotInput.value = '';
        sendDatabot.disabled = true;
        sendDatabot.style.width = '14%';
        sendDatabot.innerHTML = `
            <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
            <span class="visually-hidden">Loading...</span>
        `;

        await fetchDatabotResponse(message);

        sendDatabot.disabled = false;
        sendDatabot.innerHTML = '<i class="bi bi-arrow-return-left"></i>';
    }

    function addMessage(sender, message) {
        const messageElement = document.createElement('div');
        messageElement.classList.add('message', sender === 'You' ? 'user' : 'bot');
        messageElement.innerHTML = `<strong>${sender}:</strong> ${message}`;
        databotMessages.appendChild(messageElement);
        databotMessages.scrollTop = databotMessages.scrollHeight;
    }

    async function fetchDatabotResponse(message) {
        try {
            const response = await fetch('https://app.c7d2408t2p2.itwillbs.com/databot/', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ query: message })
            });
            const data = await response.json();
            addMessage('Databot', data?.data ? formatResponse(data.data) : 'Invalid response format.');
        } catch (error) {
            addMessage('Databot', 'Unable to connect to server.');
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
    databotInput.addEventListener('input', function() {
        this.style.height = 'auto';
        this.style.height = this.scrollHeight + 'px';
    });

    // 대화창 드래그
    databotHeader.addEventListener('mousedown', function(e) {
        isDragging = true;
        offsetX = e.clientX - parseInt(databotWindow.style.left, 10);
        offsetY = e.clientY - parseInt(databotWindow.style.top, 10);
        document.addEventListener('mousemove', onMouseMove);
        document.addEventListener('mouseup', onMouseUp);
    });

    function onMouseMove(e) {
        if (isDragging) {
            e.preventDefault();
            databotWindow.style.left = `${e.clientX - offsetX}px`;
            databotWindow.style.top = `${e.clientY - offsetY}px`;
        }
    }

    function onMouseUp() {
        isDragging = false;
        document.removeEventListener('mousemove', onMouseMove);
        document.removeEventListener('mouseup', onMouseUp);
    }

    // 크기 조정
    document.getElementById('databot-resize-handle').addEventListener('mousedown', function(e) {
        isResizing = true;
        initialWidth = parseInt(databotWindow.style.width, 10);
        initialHeight = parseInt(databotWindow.style.height, 10);
        initialX = e.clientX;
        initialY = e.clientY;
        document.addEventListener('mousemove', onResize);
        document.addEventListener('mouseup', stopResize);
    });

    function onResize(e) {
        if (isResizing) {
            const dx = e.clientX - initialX;
            const dy = e.clientY - initialY;
            databotWindow.style.width = initialWidth + dx + 'px';
            databotWindow.style.height = initialHeight + dy + 'px';
        }
    }

    function stopResize() {
        isResizing = false;
        document.removeEventListener('mousemove', onResize);
        document.removeEventListener('mouseup', stopResize);
    }
});