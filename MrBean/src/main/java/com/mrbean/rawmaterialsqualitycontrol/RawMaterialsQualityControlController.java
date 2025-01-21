package com.mrbean.rawmaterialsqualitycontrol;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequestMapping(value = "/rmqcontrol/*")
public class RawMaterialsQualityControlController {

	private static final Logger logger = LoggerFactory.getLogger(RawMaterialsQualityControlController.class);
	
	@Autowired
	private RawMaterialsQualityControlService rawMaterialsQualityControlService;
	
	
	
	
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void rmqControlGET(/*@SessionAttribute("uUserid")*/ String uUserid, Model model) throws Exception {
		logger.info("rmqControlGET() 호출");
		
		List<RawMaterialsQualityControlVO> rawMaterialsQualityControlList 
		= rawMaterialsQualityControlService.getRawMaterialsQualityControlList();
		
		model.addAttribute("rawMaterialsQualityControlList", rawMaterialsQualityControlList);
	}
	
	
	// 원자재 품질 검사 상태 업데이트
    @PostMapping("/updateQualityCheck")
    @ResponseBody
    public ResponseEntity<String> updateQualityCheck(@RequestParam int rqcBno, @RequestParam String rqcQualityCheck) {
        try {
            rawMaterialsQualityControlService.updateQualityCheck(rqcBno, rqcQualityCheck);
            return ResponseEntity.ok("품질 검사 상태가 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
        }
    }

    // 원자재 상태 업데이트
    @PostMapping("/updateStatus")
    @ResponseBody
    public ResponseEntity<String> updateStatus(@RequestParam int rqcBno, @RequestParam String rqcStatus) {
        try {
            rawMaterialsQualityControlService.updateStatus(rqcBno, rqcStatus);
            return ResponseEntity.ok("상태가 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
        }
    }
	
	
} // class
