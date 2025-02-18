package com.mrbean.stockmaterials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mrbean.rawmaterialsreceiving.RawMaterialsReceivingVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Timestamp;
import java.util.List;

// 재고 관리 컨트롤러
@Controller
@RequestMapping("/stock")
public class StockMaterialsController {
    private static final Logger logger = LoggerFactory.getLogger(StockMaterialsController.class);

    @Autowired
    private StockMaterialsService stockMaterialsService;
    
    // AJAX 요청 처리 (재고 목록 자동 갱신)
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

    // 원자재 입고 목록 AJAX 갱신
    @GetMapping("/raw/update")
    @ResponseBody
    public List<RawMaterialsReceivingVO> updateRawMaterials(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        int offset = (page - 1) * pageSize;
        return stockMaterialsService.getRawMaterialsWithPaging(pageSize, offset);
    }

    // 재고 목록 페이지 표시
    @RequestMapping("/list")
    public String stockMaterialsListGET(
            Model model, 
            StockMaterialsVO stockVO,
            @RequestParam(defaultValue = "1") int rawPage,
            @RequestParam(defaultValue = "1") int stockPage,
            @RequestParam(defaultValue = "10") int pageSize) throws Exception {
        
        // 재고 목록 페이징
        int stockOffset = (stockPage - 1) * pageSize;
        List<StockMaterialsVO> stockList = stockMaterialsService.getStockMaterials("rml_date", "DESC", pageSize, stockOffset);
        int totalStockCount = stockMaterialsService.getTotalCount();
        int totalStockPages = (int) Math.ceil((double) totalStockCount / pageSize);
        
        // 원자재 입고 목록 페이징
        int rawOffset = (rawPage - 1) * pageSize;
        List<RawMaterialsReceivingVO> rawList = stockMaterialsService.getRawMaterialsWithPaging(pageSize, rawOffset);
        int totalRawCount = stockMaterialsService.getTotalRawCount();
        int totalRawPages = (int) Math.ceil((double) totalRawCount / pageSize);
        
        // 모델에 데이터 추가
        model.addAttribute("stockList", stockList);
        model.addAttribute("rawList", rawList);
        model.addAttribute("currentRawPage", rawPage);
        model.addAttribute("currentStockPage", stockPage);
        model.addAttribute("totalRawPages", totalRawPages);
        model.addAttribute("totalStockPages", totalStockPages);
        model.addAttribute("pageSize", pageSize);
        
        return "stock/list";
    }

    // 재고 등록 처리
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String registerSList(StockMaterialsVO stockVO, 
                              @RequestParam(value = "rr_no", required = false) Integer rrNo,
                              RedirectAttributes rttr) {
        logger.info("=== Stock Registration Process Start ===");
        try {
            if (rrNo != null) {
                if (stockMaterialsService.isDuplicateStock(String.valueOf(rrNo))) {
                    rttr.addFlashAttribute("errorMessage", "이미 등록된 재고입니다.");
                    return "redirect:/stock/list";
                }

                RawMaterialsReceivingVO rrVO = stockMaterialsService.getRawMaterialsReceiving(rrNo);
                if (rrVO != null) {
                    stockVO.setRrQuantity(rrVO.getRrQuantity());
                    stockVO.setRrUnit(rrVO.getRrUnit());
                    stockVO.setRmlNo(rrVO.getRmlNo());
                    stockVO.setRmCode(rrVO.getRmCode());
                    stockVO.setRrExpirydate(rrVO.getRrExpirydate());
                    stockVO.setRrNo(String.valueOf(rrVO.getRrNo()));
                    stockVO.setSmTotal(rrVO.getRrQuantity());
                    stockVO.setRmlDate(new Timestamp(System.currentTimeMillis()));
                    
                    stockMaterialsService.insertStockMaterials(stockVO);
                    rttr.addFlashAttribute("successMessage", "재고 등록이 완료되었습니다.");
                }
            }
        } catch (Exception e) {
            logger.error("Error during stock registration process", e);
            rttr.addFlashAttribute("errorMessage", "재고 등록 중 오류가 발생했습니다.");
        }
        return "redirect:/stock/list";
    }
}