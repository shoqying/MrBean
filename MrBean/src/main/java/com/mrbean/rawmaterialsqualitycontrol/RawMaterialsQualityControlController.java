package com.mrbean.rawmaterialsqualitycontrol;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequestMapping(value = "/rmqcontrol/*")
public class RawMaterialsQualityControlController {

	private static final Logger logger = LoggerFactory.getLogger(RawMaterialsQualityControlController.class);
	
	@Autowired
	private RawMaterialsQualityControlService rawMaterialsQualityControlService;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void rmqControlGET(@SessionAttribute("uUserid") String uUserid, Model model) throws Exception {
		logger.info("/rmqcontrol/main 호출");
		
		List<RawMaterialsQualityControlVO> rawMaterialsQualityControlList 
		= rawMaterialsQualityControlService.getRawMaterialsQualityControlList();
		
		model.addAttribute("rawMaterialsQualityControlList", rawMaterialsQualityControlList);
		
	}
	
	
} // class
