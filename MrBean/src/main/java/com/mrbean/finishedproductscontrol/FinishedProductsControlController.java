package com.mrbean.finishedproductscontrol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/fpcontrol/*")
public class FinishedProductsControlController {
	
	private static final Logger logger = LoggerFactory.getLogger(FinishedProductsControlController.class);
	
	
	@RequestMapping(value = "/main")
	public void fpControlGET() throws Exception {
		logger.info("/fpcontrol/main 호출");
		
		
		
	}



} // class
