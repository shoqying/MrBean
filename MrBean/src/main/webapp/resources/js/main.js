/**
 * main.js
 * 메인 진입점
 * - 현재 페이지에 따라 필요한 모듈 초기화
 */
import { planModule } from './modules/plan.js';
import { workModule } from './modules/work.js';
import { Modal } from './components/modal.js';
import { SELECTORS } from './common/constants.js';  // SELECTORS import 추가


// 전역 객체로 모듈 추가
window.planModule = planModule;
window.workModule = workModule;


// DOM이 모두 로드된 후 초기화
$(document).ready(function() {

    const currentPage = window.location.pathname;
    
    if(currentPage.includes('/productionplan/plan')) {
        planModule.init();
    } else if(currentPage.includes('/workorders/work')) {
        workModule.init();
        Modal.init();
    }

});