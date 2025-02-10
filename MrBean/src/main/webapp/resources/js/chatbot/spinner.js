document.addEventListener('DOMContentLoaded', function() {
    const chatbotButton = document.getElementById('chatbot-button');
    const chatbotWindow = document.getElementById('chatbot-window');
    const closeChatbot = document.getElementById('close-chatbot');
    const sendChatbot = document.getElementById('send-chatbot');
    const chatbotInput = document.getElementById('chatbot-input');
    const chatbotMessages = document.getElementById('chatbot-messages');
    const chatbotSpinner = document.getElementById('chatbot-spinner'); // Spinner element

    let isChatbotOpen = false;

    chatbotButton.addEventListener('click', () => {
        isChatbotOpen = !isChatbotOpen;
        chatbotWindow.style.display = isChatbotOpen ? 'flex' : 'none';
        chatbotWindow.style.opacity = isChatbotOpen ? '1' : '0';
        chatbotWindow.style.transform = isChatbotOpen ? 'translateY(0)' : 'translateY(20px)';
    });

    closeChatbot.addEventListener('click', function() {
        isChatbotOpen = false;
        chatbotWindow.style.opacity = '0';
        chatbotWindow.style.transform = 'translateY(20px)';
        setTimeout(() => {
            chatbotWindow.style.display = 'none';
        }, 500);
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
        sendChatbot.disabled = true; // Disable send button during processing
        chatbotSpinner.style.display = 'block'; // Show spinner

        await fetchChatbotResponse(message);

        sendChatbot.disabled = false; // Enable send button after response
        chatbotSpinner.style.display = 'none'; // Hide spinner
    }

    function addMessage(sender, message) {
        const messageElement = document.createElement('div');
        messageElement.innerHTML = `<strong>${sender}:</strong> ${message}`;
        chatbotMessages.appendChild(messageElement);
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

            if (data.status === 'success') {
                const formattedResponse = formatResponse(data.data);
                addMessage('Chatbot', formattedResponse);
            } else {
                addMessage('Chatbot', 'Error: Unable to fetch response.');
            }
        } catch (error) {
            addMessage('Chatbot', 'Error: Unable to connect to server.');
        }
    }

    function formatResponse(responseData) {
        if (responseData.type === 'dataframe') {
            const { columns, data } = responseData.value;
            let tableHTML = '<table border="1" style="width:100%; border-collapse: collapse;">';
            tableHTML += '<thead><tr>' + columns.map(col => `<th style="padding: 8px; background-color: #f2f2f2;">${col}</th>`).join('') + '</tr></thead>';
            tableHTML += '<tbody>';
            data.forEach(row => {
                tableHTML += '<tr>' + row.map(cell => `<td style="padding: 8px; text-align: center;">${cell}</td>`).join('') + '</tr>';
            });
            tableHTML += '</tbody></table>';
            return tableHTML;
        }
        return JSON.stringify(responseData, null, 2);
    }

    chatbotButton.addEventListener('click', () => {
        isChatbotOpen = !isChatbotOpen;
        chatbotWindow.classList.toggle('show', isChatbotOpen);
    });
});