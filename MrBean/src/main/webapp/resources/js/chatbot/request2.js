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
            sendChatbot.disabled = true; // Disable button during sending
            await fetchChatbotResponse(message);
            sendChatbot.disabled = false; // Enable button after response
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
                return generateTable(responseData.value);
            }

            // Handle non-table responses (JSON objects)
            if (typeof responseData === 'object') {
                return generateCollapsibleJSON(responseData);
            }

            // For simple string responses
            return `<div style="white-space: pre-wrap; background-color: #f4f4f4; padding: 10px; border-radius: 5px;">${decodeHTML(responseData)}</div>`;
        }

        function generateTable(responseData) {
            const { columns, data } = responseData;
            let tableHTML = '<table style="width: 100%; border-collapse: collapse; margin-bottom: 20px;">';
            tableHTML += '<thead><tr>';
            columns.forEach(col => {
                tableHTML += `<th style="padding: 8px; border: 1px solid #ddd; background-color: #f4f4f4; text-align: left;">${col}</th>`;
            });
            tableHTML += '</tr></thead><tbody>';
            data.forEach(row => {
                tableHTML += '<tr>';
                row.forEach(cell => {
                    tableHTML += `<td style="padding: 8px; border: 1px solid #ddd; text-align: left;">${decodeHTML(cell)}</td>`;
                });
                tableHTML += '</tr>';
            });
            tableHTML += '</tbody></table>';
            return tableHTML;
        }

        function generateCollapsibleJSON(jsonData) {
            let html = '<details style="margin-bottom: 20px;">';
            html += '<summary class="json-toggle" style="cursor: pointer; font-weight: bold;">Click to view detailed response</summary>';
            html += `<pre style="white-space: pre-wrap; background-color: #f4f4f4; padding: 10px; border-radius: 5px; font-family: monospace; margin: 0;">${JSON.stringify(jsonData, null, 2)}</pre>`;
            html += '</details>';
            return html;
        }

        function decodeHTML(html) {
            var element = document.createElement('div');
            if (html) {
                element.innerHTML = html;
                return element.textContent || element.innerText;
            }
            return '';
        }
    });