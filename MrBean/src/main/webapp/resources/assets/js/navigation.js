document.addEventListener('DOMContentLoaded', function() {
    // 현재 URL의 경로 부분을 가져오기
    const currentPath = window.location.pathname;
    
    // 페이지 매핑 정보
    const pageInfo = {
        '/productionplan/plan': {
            title: '생산계획 관리',
            category: '생산관리'
        },
        '/workorders/work': {
            title: '작업지시 관리',
            category: '생산관리'
        }
        // 다른 페이지들도 여기에 추가 하면됨 모르면 물어보셈
    };

    // 메뉴 활성화
    document.querySelectorAll('#sidebar-nav a').forEach(link => {
        const href = link.getAttribute('href');
        if (href && currentPath.includes(href)) {
            // 메뉴 항목 활성화
            link.classList.add('active');
            
            // 상위 collapse 메뉴 펼치기
            const parentNav = link.closest('.nav-content.collapse');
            if (parentNav) {
                parentNav.classList.add('show');
                const parentButton = parentNav.previousElementSibling;
                if (parentButton) {
                    parentButton.classList.remove('collapsed');
                }
            }
        }
    });

    // 페이지 제목과 breadcrumb 설정
    if (pageInfo[currentPath]) {
        // h1 제목 설정
        const pageTitle = document.querySelector('.pagetitle h1');
        if (pageTitle) {
            pageTitle.textContent = pageInfo[currentPath].title;
        }

        // breadcrumb 설정
        const breadcrumbCategory = document.querySelector('.breadcrumb-item:nth-child(2)');
        const breadcrumbActive = document.querySelector('.breadcrumb-item.active');
        
        if (breadcrumbCategory) {
            breadcrumbCategory.textContent = pageInfo[currentPath].category;
        }
        if (breadcrumbActive) {
            breadcrumbActive.textContent = pageInfo[currentPath].title;
        }
    }
});