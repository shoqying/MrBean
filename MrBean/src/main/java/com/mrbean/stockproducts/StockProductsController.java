package com.mrbean.stockproducts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mrbean.stockmaterials.StockMaterialsController;
import com.mrbean.stockmaterials.StockMaterialsService;
import com.mrbean.stockmaterials.StockMaterialsVO;

@Controller
@RequestMapping("/stockP")
public class StockProductsController {
	
	 private static final Logger logger = LoggerFactory.getLogger(StockProductsController.class);
	 
	 @Autowired
	    private StockProductsService stockProductsService;
	 
	 @RequestMapping("/splist")
	    public String getStockMaterials(
	            @RequestParam(defaultValue = "rml_date") String sortColumn,
	            @RequestParam(defaultValue = "DESC") String sortDirection,
	            @RequestParam(defaultValue = "1") int page,
	            Model model) {

	      

	        int pageSize = 20; // 한 페이지에 20개씩 출력
	        int offset = (page - 1) * pageSize;

	        // 원자재 목록 조회
	        List<StockProductsVO> stockProducts = stockProductsService.getStockProducts(sortColumn, sortDirection, pageSize, offset);

	        // 전체 데이터 개수 조회
	        int totalItems = stockProductsService.getTotalCount();

	        // 전체 페이지 수 계산
	        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

	        // 모델에 데이터 추가
	        model.addAttribute("stockProducts", stockProducts);
	        model.addAttribute("sortColumn", sortColumn);
	        model.addAttribute("sortDirection", sortDirection);
	        model.addAttribute("page", page);
	        model.addAttribute("totalPages", totalPages);
	        model.addAttribute("totalItems", totalItems);  // 전체 데이터 개수 추가

	        // list.jsp로 이동
	        return "stockP/splist";
	 }

	 
}
