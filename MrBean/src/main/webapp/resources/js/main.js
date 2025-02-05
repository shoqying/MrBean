/**
 * main.js
 * 애플리케이션 진입점
 * - 페이지별 모듈 초기화 관리
 * - 전역 이벤트 핸들러 관리
 */
import { planModule } from './modules/plan.js';
import { workModule } from './modules/work.js';
import { Modal } from './components/modal.js';
import { SELECTORS } from './common/constants.js';

const AppInitializer = {
    // 모듈 참조 저장
    modules: {
        plan: planModule,
        work: workModule,
        modal: Modal
    },

    /**
     * 현재 페이지 타입 확인
     */
    getCurrentPageType() {
        const path = window.location.pathname;
        if(path.includes('/productionplan/plan')) return 'plan';
        if(path.includes('/workorders/work')) return 'work';
        return null;
    },

    /**
     * 페이지별 필요한 모듈 초기화
     */
    initializePageModules() {
        const pageType = this.getCurrentPageType();
        
        try {
            // 전역 객체에 모듈 등록
            window.planModule = this.modules.plan;
            window.workModule = this.modules.work;

            if(pageType === 'plan') {
                this.modules.plan.init();
                console.log('Plan module initialized');
            }
            else if(pageType === 'work') {
                this.modules.work.init();
                this.modules.modal.init();
                console.log('Work module and Modal initialized');
            }
            else {
                console.warn('Unrecognized page type');
            }
        } catch(error) {
            console.error('Module initialization failed:', error);
            this.handleInitError(error);
        }
    },

    /**
     * 전역 이벤트 리스너 설정
     */
    setupGlobalListeners() {
        // AJAX 에러 핸들링
        $(document).ajaxError((event, jqXHR, settings) => {
            console.error('API Call Failed:', {
                url: settings.url,
                status: jqXHR.status,
                error: jqXHR.statusText
            });
            
            let message = '서버 통신 중 오류가 발생했습니다.';
            switch(jqXHR.status) {
                case 404: 
                    message = '요청한 리소스를 찾을 수 없습니다.'; 
                    break;
                case 500: 
                    message = '서버 내부 오류가 발생했습니다.'; 
                    break;
            }
            alert(message);
        });

        // Form submit 기본 동작 방지
        $('form').on('submit', (e) => {
            e.preventDefault();
            return false;
        });
    },

    /**
     * 초기화 에러 처리
     */
    handleInitError(error) {
        const errorMap = {
            'Plan module not found': '생산계획 모듈을 찾을 수 없습니다.',
            'Work module not found': '작업지시 모듈을 찾을 수 없습니다.',
            'Modal module not found': '모달 모듈을 찾을 수 없습니다.'
        };

        const message = errorMap[error.message] || '모듈 초기화 중 오류가 발생했습니다.';
        console.error(message);
        alert(message);
    },

    /**
     * 애플리케이션 초기화
     */
    init() {
        try {
            this.setupGlobalListeners();
            this.initializePageModules();
        } catch(error) {
            console.error('Application initialization failed:', error);
            alert('애플리케이션 초기화 중 오류가 발생했습니다.');
        }
    }
};

// DOM 로드 완료 시 초기화
$(document).ready(() => {
    AppInitializer.init();
});

// 외부에서 필요한 경우를 위해 모듈 export
export { AppInitializer };