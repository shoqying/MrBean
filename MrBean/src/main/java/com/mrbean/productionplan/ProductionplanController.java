package com.mrbean.productionplan;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mrbean.common.NumberGenerationService;

@Controller
@RequestMapping(value = "/productionplan")
public class ProductionplanController {
	private static final Logger logger = LoggerFactory.getLogger(ProductionplanController.class);
		
	@Inject 
	private NumberGenerationService ngs;
	@Inject
	private ProductionplanService pps;
	
		/**
		 * 생산계획등록 페이지(GET) - 등록 form 과 List 함께출력
		 * http://localhost:8088/productionplan/plan
		 * 
		 */
		@RequestMapping(value = "/plan", method = RequestMethod.GET)
		public String planRegisterGET(Model model) {
			logger.info("planRegisterGET 호출()");

			// 생산계획 목록을 조회해서 모델이 추가
			List<ProductionPlanVO>planList = pps.getPlanList();
			model.addAttribute("planList", planList);
			
			return "productionplan/plan";
		}
		
		/**
		 * 생산계획등록 페이지(POST)
		 * http://localhost:8088/productionplan/plan
		 * 
		 */
		@RequestMapping(value = "/plan", method = RequestMethod.POST)
		public String planRegisterPOST() {
			logger.info("planRegisterPOST 호출()");
			
			return "";
		}


		/**
		 * 번호생성 API(GET)
		 * http://localhost:8088/productionplan/generatePlanNumber
		 * 
		 */
		@RequestMapping(value = "/generatePlanNumber", method = RequestMethod.GET)
		public String generatePlanNumberGET() {
			logger.info("generatePlanNumberGET()");
			
			String planNumber = ngs.generateNumber("productionplan");
			logger.info("생산계획넘버 생성");
			
			return planNumber;
		}
	
	
		
	
	

}//ProductionplanController
