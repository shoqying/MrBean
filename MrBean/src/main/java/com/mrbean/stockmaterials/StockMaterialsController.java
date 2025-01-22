package com.mrbean.stockmaterials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockMaterialsController {

    private static final Logger logger = LoggerFactory.getLogger(StockMaterialsController.class);

    @Autowired
    private StockMaterialsService stockMaterialsService;
    
	/*
	 * // 원자재 등록 페이지
	 * 
	 * @RequestMapping(value = "/stockRegister", method = RequestMethod.GET) public
	 * String showRegisterPage(Model model) { model.addAttribute("stockMaterial",
	 * new StockMaterialsVO()); return "stock/stockRegister"; // stock/register.jsp로
	 * 이동 }
	 * 
	 * // 원자재 등록 처리
	 * 
	 * @RequestMapping(value = "/stockRegister", method = RequestMethod.POST) public
	 * String registerStockMaterial(@ModelAttribute StockMaterialsVO
	 * stockMaterialsVO, Model model) { try {
	 * 
	 * // 원자재 등록 요청 받은 객체를 로그로 출력 logger.info("Received stock material: {}",
	 * stockMaterialsVO);
	 * 
	 * // 원자재 등록 처리 stockMaterialsService.insertStockMaterials(stockMaterialsVO);
	 * 
	 * // 성공 시 목록 페이지로 리디렉션 return "redirect:/stock/list";
	 * 
	 * } catch (Exception e) { logger.error("원자재 등록 실패", e);
	 * model.addAttribute("errorMessage", "원자재 등록에 실패했습니다. 다시 시도해주세요."); return
	 * "stock/stockRegister"; // 실패 시 등록 페이지로 돌아가기 } }
	 */

    @RequestMapping("/list")
    public String getStockMaterials(
            @RequestParam(defaultValue = "rml_date") String sortColumn,
            @RequestParam(defaultValue = "DESC") String sortDirection,
            @RequestParam(defaultValue = "1") int page,
            Model model) {

      

        int pageSize = 20; // 한 페이지에 20개씩 출력
        int offset = (page - 1) * pageSize;

        // 원자재 목록 조회
        List<StockMaterialsVO> stockMaterials = stockMaterialsService.getStockMaterials(sortColumn, sortDirection, pageSize, offset);

        // 전체 데이터 개수 조회
        int totalItems = stockMaterialsService.getTotalCount();

        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        // 모델에 데이터 추가
        model.addAttribute("stockMaterials", stockMaterials);
        model.addAttribute("sortColumn", sortColumn);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);  // 전체 데이터 개수 추가

        // list.jsp로 이동
        return "stock/list";
    }
    
    
    
}
