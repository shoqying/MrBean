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
	   
	    // 1. 원자재 재고 목록 조회 http://localhost:8088/rawmaterialsreceiving/list
	    @RequestMapping(value = "/list", method = RequestMethod.GET)
	    public String listRawMaterialsGET(Model model, RawMaterialsReceivingVO rrVO) {
	    	logger.info("listRawMaterialsGET 호출() - 원자재 입고 목록 조회");
	    	
	    	
	    	List<RawMaterialsReceivingVO> rawMaterials = RawMaterialsReceivingService.getAllRawMaterials();
	        model.addAttribute("rawMaterials", rawMaterials);
	        return "rawmaterialsreceiving/list"; // list.jsp로 이동
	    }
	
	 // 원자재 입고 등록 페이지로 이동 (GET 요청) // http://localhost:8088/rawmaterialsreceiving/register
	    @RequestMapping(value = "/register", method = RequestMethod.GET)
	    public String registerRawMaterialsGET(Model model) {
	        // 빈 RawMaterialsReceivingVO 객체를 모델에 추가
	        model.addAttribute("rawMaterial", new RawMaterialsReceivingVO());
	        return "rawmaterialsreceiving/register"; // register.jsp로 이동
	    }
	    
	

	   
	    
	  
		}
	    
	    

