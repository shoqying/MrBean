package com.mrbean.finishedproductsoutgoing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/finishedproductsoutgoing")
public class FinishedProductsOutgoingController {

    @Autowired
    private FinishedProductsOutgoingService finishedProductsOutgoingService;  // 서비스 주입

 // 출고 등록 페이지 (GET 요청)
    @GetMapping("/register")
    public String registerForm(Model model) {
        // COMPLETED 상태의 작업지시 번호 조회
        model.addAttribute("workOrderNumbers", finishedProductsOutgoingService.getCompletedWorkOrderNumbers());
        
        // 로트 번호, 제품 코드, 창고 코드 조회
        model.addAttribute("lotNumbers", finishedProductsOutgoingService.getLotNumbers());
        model.addAttribute("productCodes", finishedProductsOutgoingService.getProductCodes());
        model.addAttribute("warehouseCodes", finishedProductsOutgoingService.getWarehouseCodes());

        return "finishedproductsoutgoing/register";  // register.jsp로 이동
    }

    @PostMapping("/register")
    public String registerFinishedProduct(@ModelAttribute FinishedProductsOutgoingVO finishedProduct, Model model) {
        try {
        	
        	System.out.println(finishedProduct);
            // 출고번호 자동 생성
            String generatedFoNo = "FO-" + System.currentTimeMillis();
            finishedProduct.setFoNo(generatedFoNo);

            // 출고 정보 등록 서비스 호출
            finishedProductsOutgoingService.registerFinishedProduct(finishedProduct);

            // ✅ 성공 메시지와 등록된 정보 세션에 저장 (리다이렉트 후에도 데이터 유지)
            model.addAttribute("successMessage", "완제품 출고가 성공적으로 등록되었습니다. 출고번호: " + generatedFoNo);
            model.addAttribute("finishedProduct", finishedProduct);

            // ✅ 성공 후 result 페이지로 리다이렉트
            return "redirect:/finishedproductsoutgoing/result";
        } catch (Exception e) {
            // 예외 발생 시 오류 메시지 모델에 추가
            e.printStackTrace();
            model.addAttribute("errorMessage", "출고 등록 중 오류가 발생했습니다: " + e.getMessage());

            // ✅ 실패 시 result 페이지로 이동 (redirect 필요 없음)
            return "finishedproductsoutgoing/result";
        }
    }

    
    
    
    
    // 출고 목록 페이지 (GET 요청)
    @GetMapping("/list")
    public String listFinishedProducts(Model model) {
        // 출고 목록 조회 서비스 호출
        java.util.List<FinishedProductsOutgoingVO> finishedProducts = finishedProductsOutgoingService.getAllFinishedProducts();
        
        // 모델에 출고 목록을 추가
        model.addAttribute("finishedProducts", finishedProducts);
        
        // 출고 목록 페이지로 이동 (list.jsp)
        return "finishedproductsoutgoing/list";
    }

    // 출고 번호로 상세 조회 페이지 (GET 요청)
    @GetMapping("/detail/{foNo}")
    public String viewFinishedProductDetail(@PathVariable String foNo, Model model) {
        // 출고번호로 출고 정보 조회 서비스 호출
        FinishedProductsOutgoingVO finishedProduct = finishedProductsOutgoingService.getFinishedProductByFoNo(foNo);
        
        // 모델에 출고 정보 추가
        model.addAttribute("finishedProduct", finishedProduct);
        
        // 출고 상세 페이지로 이동 (detail.jsp)
        return "finishedproductsoutgoing/detail";
    }

    // 출고 수정 페이지 (GET 요청)
    @GetMapping("/update/{foNo}")
    public String updateForm(@PathVariable String foNo, Model model) {
        // 출고번호로 출고 정보 조회 서비스 호출
        FinishedProductsOutgoingVO finishedProduct = finishedProductsOutgoingService.getFinishedProductByFoNo(foNo);
        
        // 모델에 출고 정보 추가
        model.addAttribute("finishedProduct", finishedProduct);
        
        // 출고 수정 페이지로 이동 (update.jsp)
        return "finishedproductsoutgoing/update";
    }

    // 출고 수정 처리 (POST 요청)
    @PostMapping("/update")
    public String updateFinishedProduct(@ModelAttribute FinishedProductsOutgoingVO finishedProduct, Model model) {
        try {
            // 출고 정보 수정 서비스 호출
            finishedProductsOutgoingService.updateFinishedProduct(finishedProduct);

            // 성공 메시지 모델에 추가
            model.addAttribute("successMessage", "출고 정보가 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            // 예외 발생 시 오류 메시지 모델에 추가
            model.addAttribute("errorMessage", "출고 정보 수정 중 오류가 발생했습니다.");
        }

        // 결과 페이지로 이동 (result.jsp)
        return "finishedproductsoutgoing/result";
    }

    // 출고 삭제 처리 (POST 요청)
    @PostMapping("/delete/{foNo}")
    public String deleteFinishedProduct(@PathVariable String foNo, Model model) {
        try {
            // 출고 정보 삭제 서비스 호출
            finishedProductsOutgoingService.deleteFinishedProduct(foNo);

            // 성공 메시지 모델에 추가
            model.addAttribute("successMessage", "출고 정보가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            // 예외 발생 시 오류 메시지 모델에 추가
            model.addAttribute("errorMessage", "출고 정보 삭제 중 오류가 발생했습니다.");
        }

        // 결과 페이지로 이동 (result.jsp)
        return "finishedproductsoutgoing/result";
    }

    // 완제품 로트번호 목록 조회
    @GetMapping("/lot-numbers")
    public String getLotNumbers(Model model) {
        model.addAttribute("lotNumbers", finishedProductsOutgoingService.getLotNumbers());
        return "finishedproductsoutgoing/lotNumbers";  // 로트번호 조회 페이지
    }

    // 제품 코드 목록 조회
    @GetMapping("/product-codes")
    public String getProductCodes(Model model) {
        model.addAttribute("productCodes", finishedProductsOutgoingService.getProductCodes());
        return "finishedproductsoutgoing/productCodes";  // 제품 코드 조회 페이지
    }

    // 창고 코드 목록 조회
    @GetMapping("/warehouse-codes")
    public String getWarehouseCodes(Model model) {
        model.addAttribute("warehouseCodes", finishedProductsOutgoingService.getWarehouseCodes());
        return "finishedproductsoutgoing/warehouseCodes";  // 창고 코드 조회 페이지
    }
    
    @GetMapping("/result")
    public String showResultPage(Model model) {
        return "finishedproductsoutgoing/result";
    }


}
