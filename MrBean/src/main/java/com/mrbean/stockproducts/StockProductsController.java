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


@Controller
@RequestMapping("/stockP")
public class StockProductsController {
	
	 private static final Logger logger = LoggerFactory.getLogger(StockProductsController.class);
	 
	 @Autowired
	    private StockProductsService stockProductsService;
	 
	 @RequestMapping(value = "/splist", method = RequestMethod.GET)
	 public String getStockMaterials(
	     @RequestParam(defaultValue = "1") int page,
	     @RequestParam(defaultValue = "latest") String sortOption, // 최신순 기본값
	     Model model) {

	     int limit = 10; // 한 페이지당 항목 수
	     int offset = (page - 1) * limit;

	     // 정렬 옵션 처리
	     String sortColumn = "sp_date"; // 정렬 컬럼은 항상 입고일(sp_date)
	     String sortDirection = "DESC"; // 기본값은 최신순

	     if ("oldest".equals(sortOption)) {
	         sortDirection = "ASC"; // 오래된순일 경우 방향만 ASC로 변경
	     }

	     // 데이터 조회
	     List<StockProductsVO> stockProducts = stockProductsService.getStockProducts(sortColumn, sortDirection, limit, offset);
	     int totalCount = stockProductsService.getTotalCount();
	     int totalPages = (int) Math.ceil((double) totalCount / limit);

	     // Model에 데이터 추가
	     model.addAttribute("stockProducts", stockProducts);
	     model.addAttribute("page", page);
	     model.addAttribute("totalPages", totalPages);
	     model.addAttribute("sortOption", sortOption); // 선택된 정렬 옵션

	     // list.jsp로 이동
	     return "stockP/splist";
	 }


	 
	 }

	 

