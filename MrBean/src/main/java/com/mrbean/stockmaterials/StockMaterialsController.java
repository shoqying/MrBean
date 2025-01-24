package com.mrbean.stockmaterials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.RequestMapping;
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
    
    
    @RequestMapping("/list")
    public String getStockMaterials(
            @RequestParam(defaultValue = "rml_date") String sortColumn,
            @RequestParam(defaultValue = "DESC") String sortDirection,
            @RequestParam(defaultValue = "1") int page,
            Model model) {

        // sortColumn 검증
        if (!"rml_date".equals(sortColumn) && !"rr_quantity".equals(sortColumn)) {
            sortColumn = "rml_date"; // 기본값 설정
        }

        // sortDirection 검증
        if (!"ASC".equals(sortDirection) && !"DESC".equals(sortDirection)) {
            sortDirection = "DESC"; // 기본값 설정
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
        model.addAttribute("sortColumn", sortColumn);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);

        return "stock/list";
    }

    }
    
    
    
