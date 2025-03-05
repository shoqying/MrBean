package com.mrbean.rawmaterialsqualitycontrol;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbean.enums.QualityControlStatus;
import com.mrbean.finishedproductscontrol.FinishedProductsControlService;
import com.mrbean.finishedproductscontrol.FinishedProductsControlVO;
import com.mrbean.user.userVO;


@Controller
@RequestMapping(value = "/rmqcontrol/*")
public class RawMaterialsQualityControlController {

	private static final Logger logger = LoggerFactory.getLogger(RawMaterialsQualityControlController.class);
	
	@Autowired
	private RawMaterialsQualityControlService rawMaterialsQualityControlService;
	
	@Autowired
	private FinishedProductsControlService finishedProductsControlService;
	
	
	
	
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String rmqControlGET(HttpSession session, Model model) throws Exception {
		logger.info("rmqControlGET() 호출");
		userVO loggedInUser = (userVO) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // 로그인되지 않았을 경우 로그인 페이지로 리다이렉트
            return "redirect:/user/login";
        }
		
		List<RawMaterialsQualityControlVO> rawMaterialsQualityControlList 
		= rawMaterialsQualityControlService.getRawMaterialsQualityControlList();
		
		model.addAttribute("rawMaterialsQualityControlList", rawMaterialsQualityControlList);
		
		return "/rmqcontrol/main";
	}
	
	
	// 원자재 품질 검사 상태 업데이트
    @PostMapping("/updateQualityCheck")
    @ResponseBody
    public ResponseEntity<String> updateQualityCheck(@RequestBody RawMaterialsQualityControlVO vo) {
        try {
            rawMaterialsQualityControlService.updateQualityCheck(vo);
            if (QualityControlStatus.PENDING.equals(vo.getRqcQualityCheck())) {
        		rawMaterialsQualityControlService.deleteRawmaterialsDate(vo);
        		finishedProductsControlService.deleteFinishedProductControl(vo);
        	}
            return ResponseEntity.ok("품질 검사 상태가 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
        }
    }

    // 원자재 상태 업데이트
    @PostMapping("/updateStatus")
    @ResponseBody
    public ResponseEntity<String> updateStatus(@RequestBody RawMaterialsQualityControlVO vo) {
    	
    	try {
    		rawMaterialsQualityControlService.updateStatus(vo);
    		if(QualityControlStatus.PENDING.equals(vo.getRqcStatus())) {
    			finishedProductsControlService.deleteFinishedProductLot(vo);
    		}
    		if (QualityControlStatus.PASS.equals(vo.getRqcStatus())) {
    			logger.info("vo" + vo);
        		finishedProductsControlService.processAndInsertFinishedProducts(vo);
        	}
            return ResponseEntity.ok("상태가 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
        }
    }
    
    // 원자재 검사 목록 삭제
    @PostMapping("/deleteRawMaterial")
    @ResponseBody
    public ResponseEntity<String> deleteRawMaterial(@RequestParam int rqcBno) {
        try {
            rawMaterialsQualityControlService.deleteRawMaterial(rqcBno);
            return ResponseEntity.ok("상태가 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
        }
    }
	
	
} // class
