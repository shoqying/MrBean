package com.mrbean.productionplan;

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

/**
 *	REST방식으로 게시판CRUD 구현 설계(참조자료)
 *  
 *   Create - 생성(POST)
 *   Read - 조회(GET)
 *   Update - 수정(PUT)/ 부분수정(PATCH)
 *   Delete - 삭제(DELETE)
 * 
 *   URI 요청형태
 *   /작업명/기본키+메서드+데이터
 *   
 *   작업명 : 요청하는 작업의 종류
 *   기본키 : 요청하는 작업에 해당하는 대상의 기본키
 *   메서드 : 요청하는 기능
 *   데이터 : 기능 수행을 위한 JSON데이터
 *   
 *   ----------------------------------------------
 *   				 URI			 	메서드형태
 *   글 작성하기   /boards + 데이터	 		POST
 *   글 조회하기(all) /boards/all	 		GET		
 *   글 조회하기(bno) /boards/100	  		GET		
 *   글 수정하기	/boards/100 + 데이터	PUT
 *   글 삭제하기	/boards/100				DELETE
 *
 */

@RestController
@RequestMapping(value="/productionplan/api")
public class ProductionplanRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductionplanRestController.class);
	
	@Inject
    private ProductionplanService pps;
    
    @Inject 
    private NumberGenerationService ngs;
    
    
    
    
    
    /**
     * 생산계획등록
     * http://localhost:8088/productionplan/api/plan
     */
    @RequestMapping(value = "/plan",method = RequestMethod.POST)
    public ResponseEntity<?> planRegisterPOST(@RequestBody ProductionPlanVO planVO) {
    	logger.info("planRegisterPOST");
        try {
        	// 생산계획 등록
            pps.insertProductionPlan(planVO);
            
         // 최신 목록 반환
            List<ProductionPlanVO> planList = pps.getPlanList(planVO);
            
            return ResponseEntity.ok(planList);
            
        } catch (Exception e) {
            logger.error("생산계획 등록 실패", e);
            return ResponseEntity.status(500).body("계획 등록에 실패했습니다.");
        }
    }

    /**
     * 생산계획 번호 생성
     * http://localhost:8088/productionplan/api/generatePlanNumber
     */
    @RequestMapping(value = "/generatePlanNumber", method = RequestMethod.GET)
    public String generatePlanNumber() {
    	logger.info("generatePlanNumberGET()");
    	
    	String planNumber = ngs.generateNumber("productionplan");
		logger.info("생산계획넘버 생성");
        return planNumber;
    }

    
    
    /**
     * 생산계획 삭제
     * http://localhost:8088/productionplan/api/plan/{planId}
     */
    @RequestMapping(value = "/plan/{planId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePlan(@PathVariable int planId) {
        try {
            pps.deletePlan(planId);
            List<ProductionPlanVO> planList = pps.getPlanList(new ProductionPlanVO());
            return ResponseEntity.ok(planList);
        } catch (Exception e) {
            logger.error("생산계획 삭제 실패", e);
            return ResponseEntity.status(500).body("삭제에 실패했습니다.");
        }
    }
    
    
    /**
     * 생산계획 상태 업데이트
     * http://localhost:8088/productionplan/api/plan/{planId}/status
     */
    @RequestMapping(value = "/plan/{planId}/status", method = RequestMethod.PATCH)
    public ResponseEntity<?> updatePlanStatus(@PathVariable int planId, @RequestBody ProductionPlanVO planVO) {
    	
        try {
            planVO.setPlanId(planId);
            pps.updatePlanStatus(planVO);
            List<ProductionPlanVO> planList = pps.getPlanList(planVO);
            logger.info("상태변경 실행");
            return ResponseEntity.ok(planList);
        } catch (Exception e) {
            logger.error("생산계획 상태 업데이트 실패", e);
            return ResponseEntity.status(500).body("상태 업데이트에 실패했습니다.");
        }
    }
    
    
    
}// ProductionplanRestController

