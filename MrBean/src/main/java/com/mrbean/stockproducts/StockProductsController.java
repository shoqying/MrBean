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
    		 @RequestParam(defaultValue = "latest") String sortOption, // 기본값을 'latest'로 설정
             @RequestParam(defaultValue = "DESC") String sortDirection, // 정렬 방향 (DESC로 기본값 설정)
             @RequestParam(defaultValue = "1") int page, // 페이지 번호
             Model model) {

         // sortOption 검증 (기본값을 'latest'로 설정)
         if (!"latest".equals(sortOption) && !"oldest".equals(sortOption)) {
             sortOption = "latest"; // 기본값 설정
         }

         // sortDirection 검증
         if (!"ASC".equals(sortDirection) && !"DESC".equals(sortDirection)) {
             sortDirection = "DESC"; // 기본값 설정
         }

         // 정렬 기준 설정 (latest -> DESC, oldest -> ASC)
         String sortColumn = "sp_date";  // 고정된 컬럼으로 설정

         if ("latest".equals(sortOption)) {
             sortDirection = "DESC"; // 최신순
         } else if ("oldest".equals(sortOption)) {
             sortDirection = "ASC"; // 오래된순
         }

         int pageSize = 10;
         int offset = (page - 1) * pageSize;

        // 데이터 조회
        List<StockProductsVO> stockProducts = stockProductsService.getStockProducts(sortColumn, sortDirection, pageSize, offset);
        int totalCount = stockProductsService.getTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        // Model에 데이터 추가
        // 모델에 데이터 추가
        model.addAttribute("stockProducts", stockProducts);
        model.addAttribute("sortOption", sortOption); // 사용자가 선택한 정렬 옵션을 모델에 추가
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);

        // list.jsp로 이동
        return "stockP/splist";
    }
}
