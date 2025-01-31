package com.mrbean.workorders;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mrbean.common.NumberGenerationService;
import com.mrbean.productionplan.ProductionPlanVO;
import com.mrbean.productionplan.ProductionplanService;


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
	
	 
	
    /**
     * 작업지시 등록
     * http://localhost:8088/workorders/api/work
     */
    @RequestMapping(value = "/work",method = RequestMethod.POST)
    public ResponseEntity<?> workordersRegisterPOST(@RequestBody WorkOrdersVO workVO) {
    	logger.info("workordersPOST");
        try {
            wos.insertWorkOrders(workVO);
            List<WorkOrdersVO> workList = wos.getWorkList(workVO);
            logger.info("등록완료");
            return ResponseEntity.ok(workList);
        } catch (Exception e) {
            logger.error("생산계획 등록 실패", e);
            return ResponseEntity.status(500).body("계획 등록에 실패했습니다.");
        }
    }
	
    
    /**
     * 작업지시계획 번호 생성
     * http://localhost:8088/WorkOrders/api/generateWorkNumber
     */
    @RequestMapping(value = "/generateWorkNumber", method = RequestMethod.GET)
    public String generatePlanNumber() {
        return ngs.generateNumber("workorders");
    }
	
    /**
     * 
     * 생산계획 목록 조회 API
     * 
     */
    
    @RequestMapping(value = "/plans", method = RequestMethod.GET)
    public ResponseEntity<List<ProductionPlanVO>> getPlanList(ProductionPlanVO planVO) {
        try {
            List<ProductionPlanVO> planList = pps.getPlanList(planVO);
            return ResponseEntity.ok(planList);
        } catch (Exception e) {
            logger.error("생산계획 목록 조회 실패", e);
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * 작업지시계획 삭제
     * http://localhost:8088/productionplan/api/work/{workId}
     */
    @RequestMapping(value = "/work/{workId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWork(@PathVariable int workId) {
        try {
            wos.deleteWork(workId);
            List<WorkOrdersVO> workList = wos.getWorkList(new WorkOrdersVO());
            return ResponseEntity.ok(workList);
        } catch (Exception e) {
            logger.error("작업지시계획 삭제 실패", e);
            return ResponseEntity.status(500).body("삭제에 실패했습니다.");
        }
    }
    
    /**
     * 상태값 변경 api
     * 
     * 
     */
    @RequestMapping(value = "/work/{workId}/status",method = RequestMethod.PATCH)
    public ResponseEntity<?> updateWorkStatus(@PathVariable int workId, @RequestBody WorkOrdersVO workVO){
    	try {
    		workVO.setWorkId(workId);
    		wos.updateWorkStatus(workVO);
    		List<WorkOrdersVO> workList = wos.getWorkList(workVO);
    		return ResponseEntity.ok(workList);
    		
    	} catch(Exception e) {
    		logger.error("작업상태변경 실패", e);
    		return ResponseEntity.status(500).body("작업상태변경 실패");	
    	}
    	
    }
    
    
	
}//WorkOrdersRestController
