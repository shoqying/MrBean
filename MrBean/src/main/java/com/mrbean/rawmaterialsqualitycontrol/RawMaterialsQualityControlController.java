package com.mrbean.rawmaterialsqualitycontrol;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/rmqcontrol/*")
public class RawMaterialsQualityControlController {

	private static final Logger logger = LoggerFactory.getLogger(RawMaterialsQualityControlController.class);
	
	
	@RequestMapping(value = "/main")
	public void rmqControlGET() throws Exception {
		logger.info("/rmqcontrol/main 호출");
		
//		List<RawMaterialsQualityVO> RMQCList = 
		
		
		
	}
	
	
} // class
