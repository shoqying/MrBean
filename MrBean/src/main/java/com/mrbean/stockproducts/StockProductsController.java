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
	 
	 @RequestMapping(value = "/splist", method = RequestMethod.GET)
	 public String getStockMaterials(
	     @RequestParam(defaultValue = "1") int page,
	     @RequestParam(defaultValue = "sp_date") String sortColumn,
	     @RequestParam(defaultValue = "DESC") String sortDirection,
	     Model model) {

	     // System.out.printf("Request received for /splist with page: {}, sortColumn: {}, sortDirection: {}", page, sortColumn, sortDirection);

	     int limit = 10; // 한 페이지당 항목 수
	     int offset = (page - 1) * limit;

	     // 데이터 조회
	     List<StockProductsVO> stockProducts = stockProductsService.getStockProducts(sortColumn, sortDirection, limit, offset);
	     int totalCount = stockProductsService.getTotalCount();
	     int totalPages = (int) Math.ceil((double) totalCount / limit);

	     // Model에 데이터 추가
	     model.addAttribute("stockProducts", stockProducts);
	     model.addAttribute("page", page);
	     model.addAttribute("totalPages", totalPages);
	     model.addAttribute("sortColumn", sortColumn);
	     model.addAttribute("sortDirection", sortDirection);

	     logger.info("stockProducts: " + stockProducts);
	     
	     // list.jsp로 이동
	     return "stockP/splist";
	 
	 }

	 
}
