document.getElementById('searchForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const query = document.querySelector('input[name="query"]').value;

    fetch('/search', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ query: query })
    })
    .then(response => response.json())
    .then(data => {
        console.log('ChatGPT Response:', data);
        // Display the response in the popup
        const resultsContainer = document.getElementById('searchResultsContainer');
        resultsContainer.innerHTML = `<pre>${JSON.stringify(data, null, 2)}</pre>`;
        resultsContainer.style.display = 'block';
    })
    .catch(error => {
        console.error('Error:', error);
    });
});