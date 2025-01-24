import { planModule } from './modules/plan.js';
import { workModule } from './modules/work.js';
import { Modal } from './components/modal.js';

// BOM 관련 모듈
import { Toast } from './components/toast.js';
import { CreateModal } from './bom/createModal.js';
import { EditModal } from './bom/editModal.js';
import { Validation } from './bom/validation.js';

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

     // BOM 관련 페이지
     if (currentPage.includes('/billofmaterials/')) {
       // BOM 관련 기능들 초기화
       Toast.init();
       CreateModal.init();
       EditModal.init();
       Validation.init();
     }

});