package com.mrbean.rawmaterialsreceiving;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.inject.Inject;

@Controller
@RequestMapping("/rawmaterialsreceiving")
public class RawMatarialsRecievingController {
     private static final Logger logger = LoggerFactory.getLogger(RawMaterialsReceivingService.class);

    
    @Inject
	private RawMaterialsReceivingService RawMaterialsReceivingService;
   
    // 1. 원자제 입고 목록 조회 http://localhost:8088/rawmaterialsreceiving/list
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listRawMaterialsGET(Model model, RawMaterialsReceivingVO rrVO) {
    	logger.info("listRawMaterialsGET 호출() - 원자제 입고 목록 조회");
    	
    	
    	List<RawMaterialsReceivingVO> rawMaterials = RawMaterialsReceivingService.getAllRawMaterials();
        model.addAttribute("rawMaterials", rawMaterials);
        return "rawmaterialsreceiving/list"; // list.jsp로 이동
    }

  
	}
    
    

