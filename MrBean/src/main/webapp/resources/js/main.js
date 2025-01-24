import { planModule } from './modules/plan.js';
import { workModule } from './modules/work.js';
import { Modal } from './components/modal.js';

window.planModule = planModule; // planModule을 전역으로 추가
window.workModule = workModule; // 동일

$(document).ready(function() {
   const currentPage = window.location.pathname;
   
   if(currentPage.includes('/productionplan/plan')) {
       planModule.init();
   } else if(currentPage.includes('/workorders/work')) {
       workModule.init();
       Modal.init();
   }
});