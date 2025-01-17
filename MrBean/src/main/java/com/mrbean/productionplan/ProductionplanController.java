package com.mrbean.productionplan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "mrbean/productionplan/*")
public class ProductionplanController {
	private static final Logger logger = LoggerFactory.getLogger(ProductionplanController.class);
		
	
		/**
		 * 생산계획등록 페이지(GET)
		 * http://localhost:8088/productionplan/plan
		 * 
		 */
	
		public String planRegisterGET() {
			
			
			return "productionplan/plan";
		}
	
	
	
	

}//ProductionplanController
