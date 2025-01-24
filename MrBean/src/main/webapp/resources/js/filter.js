  // 검색 필드의 keyup 이벤트를 감지
  document.getElementById('searchInput').addEventListener('keyup', function() {
    let keyword = this.value.toLowerCase();
    let rows = document.querySelectorAll('tbody tr');

    rows.forEach(function(row) {
      // 행 전체 텍스트를 소문자로 변환
      let rowText = row.innerText.toLowerCase();

      // 검색 키워드가 포함되면 표시, 아니면 숨김
      if (rowText.includes(keyword)) {
        row.style.display = '';
      } else {
        row.style.display = 'none';
      }
    });
  });