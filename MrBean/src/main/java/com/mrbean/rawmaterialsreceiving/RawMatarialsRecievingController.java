	package com.mrbean.rawmaterialsreceiving;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.*;

import com.mrbean.rawmaterials.RawMaterialsVO;

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
	    
	 // 원자재 입고 등록 처리 (POST 요청) // http://localhost:8088/rawmaterialsreceiving/register
	    @RequestMapping(value = "/register", method = RequestMethod.POST)
	    public String registerRawMaterialsPOST(@ModelAttribute RawMaterialsReceivingVO rawMaterial, Model model) {
	        logger.info(rawMaterial+"");
	        logger.info("registerRawMaterialsPOST 호출() - 원자재 입고 등록 처리");

	        try {
	            // RawMaterialsReceivingService를 통해 원자재 입고 등록 로직 호출
	            RawMaterialsReceivingService.registerRawMaterial(rawMaterial);
	            model.addAttribute("message", "원자재 입고가 성공적으로 등록되었습니다.");
	            return "redirect:/rawmaterialsreceiving/list"; // 등록 후 목록 페이지로 리다이렉트
	        } catch (Exception e) {
	            logger.error("원자재 입고 등록 중 오류 발생", e);
	            model.addAttribute("errorMessage", "원자재 입고 등록 중 오류가 발생했습니다.");
	            return "rawmaterialsreceiving/result"; // 에러 발생 시 등록 페이지로 돌아가기
	        }
	    }

	

	   
	    
	  
		}
	    
	    

