package com.mrbean.stockmaterials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockMaterialsController {

    private static final Logger logger = LoggerFactory.getLogger(StockMaterialsController.class);

    @Autowired
    private StockMaterialsService stockMaterialsService;
    
    
    @RequestMapping("/list")
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
        String sortColumn = "rml_date";  // 고정된 컬럼으로 설정

        if ("latest".equals(sortOption)) {
            sortDirection = "DESC"; // 최신순
        } else if ("oldest".equals(sortOption)) {
            sortDirection = "ASC"; // 오래된순
        }

        int pageSize = 10;
        int offset = (page - 1) * pageSize;

        // 원자재 목록 조회
        List<StockMaterialsVO> stockMaterials = stockMaterialsService.getStockMaterials(sortColumn, sortDirection, pageSize, offset);

        // 전체 데이터 개수 조회
        int totalItems = stockMaterialsService.getTotalCount();

        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        // 모델에 데이터 추가
        model.addAttribute("stockMaterials", stockMaterials);
        model.addAttribute("sortOption", sortOption); // 사용자가 선택한 정렬 옵션을 모델에 추가
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);

        return "stock/list";
    }
    
    // ** AJAX 요청을 처리하는 API (자동 갱신)**
    @GetMapping("/list/update")
    @ResponseBody
    public List<StockMaterialsVO> updateStockMaterials(
            @RequestParam(defaultValue = "latest") String sortOption,
            @RequestParam(defaultValue = "1") int page) {

        String sortColumn = "rml_date"; 
        String sortDirection = "DESC"; 

        if ("oldest".equals(sortOption)) {
            sortDirection = "ASC";
        }

        int pageSize = 10;
        int offset = (page - 1) * pageSize;

        return stockMaterialsService.getStockMaterials(sortColumn, sortDirection, pageSize, offset);
    }


    }
    
    
    
