document.addEventListener('DOMContentLoaded', function() {
    const databotButton = document.getElementById('databot-button');
    const databotWindow = document.getElementById('databot-window');
    const closeDatabot = document.getElementById('close-databot');
    const sendDatabot = document.getElementById('send-databot');
    const databotInput = document.getElementById('databot-input');
    const databotMessages = document.getElementById('databot-messages');
    const databotSpinner = document.getElementById('databot-spinner'); // Spinner element

    let isDatabotOpen = false;

    databotButton.addEventListener('click', () => {
        isDatabotOpen = !isDatabotOpen;
        databotWindow.style.display = isDatabotOpen ? 'flex' : 'none';
        databotWindow.style.opacity = isDatabotOpen ? '1' : '0';
        databotWindow.style.transform = isDatabotOpen ? 'translateY(0)' : 'translateY(20px)';
    });

    closeDatabot.addEventListener('click', function() {
        isDatabotOpen = false;
        databotWindow.style.opacity = '0';
        databotWindow.style.transform = 'translateY(20px)';
        setTimeout(() => {
            databotWindow.style.display = 'none';
        }, 500);
    });

    sendDatabot.addEventListener('click', sendMessage);
    databotInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            sendMessage();
        }
    });

    async function sendMessage() {
        const message = databotInput.value.trim();
        if (!message) return;

        addMessage('You', message);
        databotInput.value = '';
        sendDatabot.disabled = true; // Disable send button during processing
        databotSpinner.style.display = 'block'; // Show spinner

        await fetchDatabotResponse(message);

        sendDatabot.disabled = false; // Enable send button after response
        databotSpinner.style.display = 'none'; // Hide spinner
    }

    function addMessage(sender, message) {
        const messageElement = document.createElement('div');
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

            if (data.status === 'success') {
                const formattedResponse = formatResponse(data.data);
                addMessage('Databot', formattedResponse);
            } else {
                addMessage('Databot', 'Unable to fetch response.');
            }
        } catch (error) {
            addMessage('Databot', 'Unable to connect to server.');
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
});