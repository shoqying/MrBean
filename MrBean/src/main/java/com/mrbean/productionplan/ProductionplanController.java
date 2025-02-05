package com.mrbean.productionplan;

import java.util.List;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbean.common.NumberGenerationService;
import com.mrbean.finishedproductscontrol.FinishedProductsControlService;

@Controller
@RequestMapping(value = "/productionplan")
public class ProductionplanController {
	private static final Logger logger = LoggerFactory.getLogger(ProductionplanController.class);
		
	@Inject 
	private NumberGenerationService ngs;
	@Inject
	private ProductionplanService pps;
	@Inject
	private FinishedProductsControlService fpcs;
	
		/**
		 * 생산계획등록 페이지(GET) - 등록 form 과 List 함께출력
		 * http://localhost:8088/productionplan/plan
		 * 
		 */
		@RequestMapping(value = "/plan", method = RequestMethod.GET)
		public String planRegisterGET(Model model, ProductionPlanVO planVO) {
			logger.info("planRegisterGET 호출()");

			// 생산계획 목록을 조회해서 모델이 추가
			List<ProductionPlanVO>planList = pps.getPlanList(planVO);
			model.addAttribute("planList", planList);
			logger.info("planList : "+ planList);
			return "productionplan/plan";
		}
		
		/**
		 * 생산계획등록 페이지(POST)
		 * http://localhost:8088/productionplan/plan
		 * 
		 */
		@RequestMapping(value = "/plan", 
						method = RequestMethod.POST,
						produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<?> planRegisterPOST(@RequestBody ProductionPlanVO planVO ) {
			logger.info("planRegisterPOST 호출()");
			try {
				// 생산계획 등록
				pps.insertProductionPlan(planVO);
				fpcs.insertFinishedProductControl();
				
				// 최신 목록 반환
				List<ProductionPlanVO> planList = pps.getPlanList(planVO);
				
				return ResponseEntity.ok(planList);  // 성공적으로 목록을 반환
				
			} catch (Exception e) {
		        logger.error("생산계획 등록 실패", e);
		        return ResponseEntity.status(500).body("계획 등록에 실패했습니다.");
			}
		}


		/**
		 * 번호생성 API(GET)
		 * http://localhost:8088/productionplan/generatePlanNumber
		 * 
		 */
		@RequestMapping(value = "/generatePlanNumber", method = RequestMethod.GET)
		@ResponseBody
		public String generatePlanNumberGET() {
			logger.info("generatePlanNumberGET()");
			
			String planNumber = ngs.generateNumber("productionplan");
			logger.info("생산계획넘버 생성");
			
			return planNumber;
		}
		/**
		 * 생산계획 목록 삭제
		 */
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> deletePlan(@RequestBody int planId){
			try {
				pps.deletePlan(planId);
		        List<ProductionPlanVO> planList = pps.getPlanList(new ProductionPlanVO());
		        logger.info("deletePlan 호출");
		        return ResponseEntity.ok(planList);				
			} catch (Exception e) {
		        logger.error("생산계획 삭제 실패", e);
		        return ResponseEntity.status(500).body("삭제에 실패했습니다.");				
			}
			
		}
		
	
	

}//ProductionplanController
