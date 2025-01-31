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
        Model model) {

        int limit = 10; // 한 페이지당 항목 수
        int offset = (page - 1) * limit;

        // 정렬 옵션 고정: 최신순으로 설정
        String sortColumn = "sp_date"; // 정렬 컬럼은 항상 입고일(sp_date)
        String sortDirection = "DESC"; // 최신순 기본값 (DESC)

        // 데이터 조회
        List<StockProductsVO> stockProducts = stockProductsService.getStockProducts(sortColumn, sortDirection, limit, offset);
        int totalCount = stockProductsService.getTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / limit);

        // Model에 데이터 추가
        model.addAttribute("stockProducts", stockProducts);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sortOption", "latest"); // 최신순 고정

        // list.jsp로 이동
        return "stockP/splist";
    }
}
