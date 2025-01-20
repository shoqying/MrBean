package com.mrbean.finishedproductscontrol;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mrbean.rawmaterialsqualitycontrol.RawMaterialsQualityControlService;
import com.mrbean.rawmaterialsqualitycontrol.RawMaterialsQualityControlVO;


@Controller
@RequestMapping(value = "/fpcontrol/*")
public class FinishedProductsControlController {
	
	private static final Logger logger = LoggerFactory.getLogger(FinishedProductsControlController.class);
	
	@Autowired
	private FinishedProductsControlService finishedProductsControlService;
	
	@RequestMapping(value = "/main")
	public void fpControlGET(@SessionAttribute("uUserid") String uUserid, Model model) throws Exception {
		logger.info("/fpcontrol/main 호출");
		
		List<FinishedProductsControlVO> finishedProductsControlList 
		= finishedProductsControlService.getFinishedProductsControlList();
		
		model.addAttribute("finishedProductsControlList", finishedProductsControlList);
		
		
		
	}



} // class
