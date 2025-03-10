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

import com.mrbean.products.ProductsService;
import com.mrbean.products.ProductsVO;

@Controller
@RequestMapping(value = "/productionplan")
public class ProductionplanController {
	private static final Logger logger = LoggerFactory.getLogger(ProductionplanController.class);
		
	@Inject
	private ProductionplanService pps;
	@Inject
	private ProductsService ps;
	
		/**
		 * 생산계획등록 페이지(GET) - 등록 form 과 List 함께출력
		 * http://localhost:8088/productionplan/plan
		 * 
		 */
		@RequestMapping(value = "/plan", method = RequestMethod.GET)
		public String planRegisterGET(Model model, ProductionPlanVO planVO) throws Exception {
			logger.info("planRegisterGET 호출()");

			// 생산계획 목록을 조회해서 모델이 추가
			List<ProductionPlanVO>planList = pps.getPlanList(planVO);
			model.addAttribute("planList", planList);
			
			
			// 프로덕트에게 받은 목록 jsp에 뿌려주기
			List<ProductsVO> productCodes = ps.getProductList();
			model.addAttribute("productCodes", productCodes);
			
			logger.info("planList : "+ planList);
			return "productionplan/plan";
		}//
		

		
	
	

}//ProductionplanController
