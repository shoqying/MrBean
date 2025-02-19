package com.mrbean.finishedproductscontrol;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mrbean.enums.QualityControlStatus;
import com.mrbean.productionplan.ProductionPlanVO;
import com.mrbean.products.ProductsService;
import com.mrbean.products.ProductsVO;
import com.mrbean.rawmaterialsqualitycontrol.RawMaterialsQualityControlService;
import com.mrbean.rawmaterialsqualitycontrol.RawMaterialsQualityControlVO;
import com.mrbean.user.userVO;
import com.mrbean.warehouse.WarehouseService;
import com.mrbean.warehouse.WarehouseVO;
import com.mrbean.workorders.WorkOrdersVO;


@Controller
@RequestMapping(value = "/fpcontrol/*")
public class FinishedProductsControlController {
	
	private static final Logger logger = LoggerFactory.getLogger(FinishedProductsControlController.class);
	
	@Autowired
	private FinishedProductsControlService finishedProductsControlService;
	
	
	
	
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String fpControlGET(HttpSession session, Model model) throws Exception {
		logger.info("fpControlGET() 호출");
		
		userVO loggedInUser = (userVO) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // 로그인되지 않았을 경우 로그인 페이지로 리다이렉트
            return "redirect:/user/login";
        }
        
		List<FinishedProductsControlVO> finishedProductsControlList 
		= finishedProductsControlService.getFinishedProductsControlList();
		logger.info("{}" + finishedProductsControlList);
		
		model.addAttribute("finishedProductsControlList", finishedProductsControlList);
		
		return "/fpcontrol/main";
		
	}
	
	// 완제품 품질 검사 상태 업데이트

    @PostMapping("/updateQualityCheck")
    @ResponseBody
    public ResponseEntity<String> updateQualityCheck(@RequestBody FinishedProductsControlVO vo) {
        try {
        	finishedProductsControlService.updateQualityCheck(vo);
        	if (QualityControlStatus.COMPLETED.equals(vo.getFpcQualityCheck())) {
        		finishedProductsControlService.updateCheckDate(vo);
        	}
        	if (QualityControlStatus.PENDING.equals(vo.getFpcQualityCheck())) {
        		finishedProductsControlService.deleteCheckDate(vo);
        	}
            return ResponseEntity.ok("품질 검사 상태가 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
        }
    }
    
    // 완제품 상태 업데이트
	@PostMapping("/updateStatus")
    @ResponseBody

    public ResponseEntity<String> updateStatus(@RequestBody FinishedProductsControlVO fvo, RawMaterialsQualityControlVO rvo) {
        try {
        	finishedProductsControlService.updateStatus(fvo);
        	if (QualityControlStatus.PASS.equals(fvo.getFpcStatus()) || QualityControlStatus.FAIL.equals(fvo.getFpcStatus())) {
        		finishedProductsControlService.insertFinishedProductLot(rvo);
            }

        		return ResponseEntity.ok("상태가 업데이트되었습니다.");
	        } catch (Exception e) {
	        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
	        }

    }
    
    // 완재품 검사 목록 삭제
    @PostMapping("/deleteFinishedProduct")
    @ResponseBody
    public ResponseEntity<String> deleteFinishedProduct(@RequestParam int fpcBno) {
        try {
        	finishedProductsControlService.deleteFinishedProduct(fpcBno);
            return ResponseEntity.ok("상태가 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업데이트 실패");
        }
    }

} // class
