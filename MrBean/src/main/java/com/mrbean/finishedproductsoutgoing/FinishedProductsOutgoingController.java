package com.mrbean.finishedproductsoutgoing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/finishedproductsoutgoing") // http://localhost:8088/finishedproductsoutgoing/list
public class FinishedProductsOutgoingController {

    @Autowired
    private FinishedProductsOutgoingService finishedProductsOutgoingService;

    // 1. 완제품 출고 목록 조회
    @RequestMapping("/list")
    public String finishedProductsList(Model model) {
        try {
            // 서비스에서 완제품 출고 목록을 가져옴
            List<FinishedProductsOutgoingVO> finishedProducts = finishedProductsOutgoingService.getAllFinishedProducts();
            
            // 모델에 데이터 추가
            model.addAttribute("finishedProducts", finishedProducts);
        } catch (Exception e) {
            // 오류 발생 시, 에러 메시지를 추가
            model.addAttribute("errorMessage", "완제품 출고 목록을 가져오는 중 오류가 발생했습니다.");
        }

        // "finishedproductsoutgoing" 경로 아래의 list.jsp로 이동
        return "finishedproductsoutgoing/list"; // JSP 경로 변경
    }

    // 2. 완제품 출고 등록 페이지로 이동
    @RequestMapping("/register")
    public String registerFinishedProductGET(Model model) {
        model.addAttribute("finishedProduct", new FinishedProductsOutgoingVO());
        return "finishedproductsoutgoing/register"; // 경로 변경
    }

    // 3. 완제품 출고 등록 처리
    @RequestMapping("/registerPost")
    public String registerFinishedProductPOST(FinishedProductsOutgoingVO finishedProduct) {
        try {
            finishedProductsOutgoingService.registerFinishedProduct(finishedProduct);
            return "redirect:/finishedproductsoutgoing/list"; // 등록 후 리스트 페이지로 이동
        } catch (Exception e) {
            return "redirect:/finishedproductsoutgoing/result";
        }
    }
}
