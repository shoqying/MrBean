package com.mrbean.workorders;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrbean.common.NumberGenerationService;
import com.mrbean.enums.ProductionplanStatus;
import com.mrbean.enums.WorkOrdersStatus;
import com.mrbean.productionplan.ProductionPlanVO;
import com.mrbean.productionplan.ProductionplanService;
import com.mrbean.rawmaterialsqualitycontrol.RawMaterialsQualityControlService;

/**
 * 작업지시 REST 컨트롤러
 */
@RestController
@RequestMapping(value="/workorders/api")
public class WorkOrdersRestController {

    private static final Logger logger = LoggerFactory.getLogger(WorkOrdersRestController.class);
    
    @Inject
    private NumberGenerationService ngs;
    @Inject
    private WorkOrdersService wos;
    @Inject
    private ProductionplanService pps;
    @Inject
    private RawMaterialsQualityControlService rqcs;
    
    /**
     * 작업지시 등록
     * POST http://localhost:8088/workorders/api/work
     */
    @RequestMapping(value = "/work", method = RequestMethod.POST)
    public ResponseEntity<?> workordersRegisterPOST(@RequestBody WorkOrdersVO workVO) {
        logger.info("작업지시 등록 요청");
        logger.info("전체 요청 데이터: {}", workVO);
        
        try {
            // 데이터 로깅
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(workVO);
            logger.info("JSON 데이터: {}", jsonStr);
            
            // 재고 확인
            if (!wos.checkStockAvailability(workVO)) {
                return ResponseEntity.badRequest().body("필요한 재고가 부족합니다.");
            }
            
            // 작업지시 등록
            wos.insertWorkOrders(workVO);
            
            // 생산계획 상태 WAITING으로 업데이트
            ProductionPlanVO planVO = new ProductionPlanVO();
            planVO.setPlanId(workVO.getPlanId());
            planVO.setPlStatus(ProductionplanStatus.WAITING);
            pps.updatePlanStatus(planVO);
            
            // 작업지시 목록 조회
            List<WorkOrdersVO> workList = wos.getWorkList(workVO);
            logger.info("작업지시 등록 완료");
            
            return ResponseEntity.ok(workList);
            
        } catch (Exception e) {
            logger.error("작업지시 등록 실패", e);
            return ResponseEntity.status(500).body("작업지시 등록에 실패했습니다: " + e.getMessage());
        }
    }
    
    /**
     * 작업지시 번호 생성
     * GET http://localhost:8088/workorders/api/generateWorkNumber
     */
    @RequestMapping(value = "/generateWorkNumber", method = RequestMethod.GET)
    public ResponseEntity<?> generateWorkNumber() {
        try {
            String workNumber = ngs.generateNumber("workorders");
            return ResponseEntity.ok(workNumber);
        } catch (Exception e) {
            logger.error("작업지시 번호 생성 실패", e);
            return ResponseEntity.status(500).body("번호 생성에 실패했습니다.");
        }
    }
    
    /**
     * 생산계획 목록 조회
     * GET http://localhost:8088/workorders/api/plans
     */
    @RequestMapping(value = "/plans", method = RequestMethod.GET)
    public ResponseEntity<List<ProductionPlanVO>> getPlanList(ProductionPlanVO planVO) {
        try {
            List<ProductionPlanVO> planList = pps.getPlanListM(planVO);
            return ResponseEntity.ok(planList);
        } catch (Exception e) {
            logger.error("생산계획 목록 조회 실패", e);
            return ResponseEntity.status(500).build();
        }
    }
    
    /**
     * 작업지시 삭제
     * DELETE http://localhost:8088/workorders/api/work/{workId}
     */
    @RequestMapping(value = "/work/{workId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWork(@PathVariable int workId) {
        try {
            wos.deleteWork(workId);
            List<WorkOrdersVO> workList = wos.getWorkList(new WorkOrdersVO());
            return ResponseEntity.ok(workList);
        } catch (Exception e) {
            logger.error("작업지시 삭제 실패", e);
            return ResponseEntity.status(500).body("삭제에 실패했습니다: " + e.getMessage());
        }
    }
    
    /**
     * 작업상태 변경
     * PATCH http://localhost:8088/workorders/api/work/{workId}/status
     */
    @RequestMapping(value = "/work/{workId}/status", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateWorkStatus(@PathVariable int workId, @RequestBody WorkOrdersVO workVO) {
        try {
            workVO.setWorkId(workId);
            
            // planId 조회
            Integer planId = wos.getPlanIdByWorkId(workId);
            logger.info("상태변경 요청 - planId: {}, workVO: {}", planId, workVO);
            
            workVO.setPlanId(planId);
            
            // 생산계획 상태 연동
            if (workVO.isShouldUpdatePlan() && planId != null) {
                ProductionPlanVO planVO = new ProductionPlanVO();
                planVO.setPlanId(planId);
                
                switch(workVO.getWorkStatus()) {
                    case WAITING:
                        planVO.setPlStatus(ProductionplanStatus.WAITING);
                        break;
                    case IN_PROGRESS:
                        planVO.setPlStatus(ProductionplanStatus.IN_PROGRESS);
                        //rqcs.processAndInsertRawMaterials();
                        break;
                    case COMPLETED:
                        planVO.setPlStatus(ProductionplanStatus.COMPLETED);
                        break;
                    case STOPPED:
                        planVO.setPlStatus(ProductionplanStatus.STOPPED);
                        break;
                }
                
                pps.updatePlanStatus(planVO);
            }
            
            // 작업상태 업데이트
            wos.updateWorkStatus(workVO);
            
            // 작업 완료 시 추가 처리
            if (workVO.getWorkStatus() == WorkOrdersStatus.COMPLETED) {
                wos.completeWork(workVO);
            }
            
            List<WorkOrdersVO> workList = wos.getWorkList(workVO);
            logger.info("상태변경 완료");
            
            return ResponseEntity.ok(workList);
            
        } catch (Exception e) {
            logger.error("작업상태 변경 실패", e);
            return ResponseEntity.status(500).body("상태변경에 실패했습니다: " + e.getMessage());
        }
    }
    
    /**
     * LOT 이력 조회
     * GET http://localhost:8088/workorders/api/work/{workId}/lot-history
     */
//    @RequestMapping(value = "/work/{workId}/lot-history", method = RequestMethod.GET)
//    public ResponseEntity<?> getLotHistory(@PathVariable int workId) {
//        try {
//            List<Map<String, Object>> history = wos.getLotHistory(workId);
//            return ResponseEntity.ok(history);
//        } catch (Exception e) {
//            logger.error("LOT 이력 조회 실패", e);
//            return ResponseEntity.status(500).body("이력 조회에 실패했습니다: " + e.getMessage());
//        }
//    }
}