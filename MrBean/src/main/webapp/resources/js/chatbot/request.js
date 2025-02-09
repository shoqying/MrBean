async function fetchData() {
    const response = await fetch('http://localhost:8000/process_query/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ query: '수량이 20% 이하인 bom_id와 이름과 총 갯수를 알려주세요' })
    });
    const data = await response.json();
    displayData(data.data);
}

function displayData(data) {
    const parsedData = JSON.parse(data);
    const table = document.createElement('table');
    const thead = document.createElement('thead');
    const tbody = document.createElement('tbody');

    // Create table headers
    const headerRow = document.createElement('tr');
    parsedData.columns.forEach(column => {
        const th = document.createElement('th');
        th.textContent = column;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);

    // Create table rows
    parsedData.data.forEach(row => {
        const tr = document.createElement('tr');
        row.forEach(cell => {
            const td = document.createElement('td');
            td.textContent = cell;
            tr.appendChild(td);
        });
        tbody.appendChild(tr);
    });

    table.appendChild(thead);
    table.appendChild(tbody);
    document.getElementById('data-container').appendChild(table);
}

window.onload = fetchData;