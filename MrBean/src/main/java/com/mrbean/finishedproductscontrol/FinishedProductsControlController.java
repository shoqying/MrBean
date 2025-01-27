package com.mrbean.finishedproductscontrol;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public void fpControlGET(/*@SessionAttribute("uUserid")*/ String userId, Model model) throws Exception {
		logger.info("fpControlGET() 호출");
		
		List<FinishedProductsControlVO> finishedProductsControlList 
		= finishedProductsControlService.getFinishedProductsControlList();
		
		model.addAttribute("finishedProductsControlList", finishedProductsControlList);
	}
	
	// 완제품 품질 검사 상태 업데이트
    @PostMapping("/updateQualityCheck")
    @ResponseBody
    public ResponseEntity<String> updateQualityCheck(@RequestParam int fpcBno, 
    												@RequestParam String fpcQualityCheck) {
        try {
        	finishedProductsControlService.updateQualityCheck(fpcBno, fpcQualityCheck);
        	finishedProductsControlService.insertFinishedProductLot();
            return ResponseEntity.ok("품질 검사 상태가 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
        }
    }

    // 완제품 상태 업데이트
    @PostMapping("/updateStatus")
    @ResponseBody
    public ResponseEntity<String> updateStatus(@RequestParam int fpcBno, @RequestParam String fpcStatus) {
        try {
        	finishedProductsControlService.updateStatus(fpcBno, fpcStatus);
            return ResponseEntity.ok("상태가 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
        }
    }
    
    // 완재품 검사 목록 삭제
    @PostMapping("/deleteFinishedProduct")
    @ResponseBody
    public ResponseEntity<String> deleteFinishedProduct(/*@SessionAttribute("userId")*/ @RequestParam int rqcBno) {
        try {
        	finishedProductsControlService.deleteFinishedProduct(rqcBno);
            return ResponseEntity.ok("상태가 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
        }
    }



} // class
