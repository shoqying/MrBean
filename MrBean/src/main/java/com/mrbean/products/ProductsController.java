package com.mrbean.products;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mrbean.billofmaterials.domain.BomDropdownDTO;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(value = "/products")
public class ProductsController {

    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
    
    @Inject
    private ProductsService productsService;
    
    // http://localhost:8088/products/register
    // 등록 페이지 이동
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) throws Exception {
        logger.info("registerPage() 호출");
        
        // product 객체를 Model에 추가
        model.addAttribute("product", new ProductsVO());

        // BOM 목록을 드롭다운에 표시할 데이터 가져오기
        model.addAttribute("bomList", productsService.getBomListForDropdown());
        logger.info("bomList = "+ productsService.getBomListForDropdown());
        
        return "/products/register"; // JSP로 전달
    }
    
    // 등록 처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerProduct(@ModelAttribute ProductsVO product, RedirectAttributes rttr) {
        logger.info("registerProduct() 호출");
        
        try {
            // 제품 등록 처리
            productsService.registerProduct(product);
            
            // 성공 메시지 전달
            rttr.addFlashAttribute("message", "✅ 완제품이 등록되었습니다.");
        } catch (Exception e) {
            logger.error("제품 등록 실패", e);
            
            // 실패 메시지 전달
            rttr.addFlashAttribute("message", "❌ 등록에 실패했습니다. 다시 시도해주세요.");
        }

        return "redirect:/products/register";
    }
    
    // http://localhost:8088/products/list
    // 완제품 리스트 페이지 처리
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage(Model model) throws Exception {
        logger.info("listPage() 호출");

        // 완제품 목록을 가져오기
        List<ProductsVO> productList = productsService.getProductList();
        logger.info("productList = {}", productList);
        
        // BOM 목록을 가져오기
        List<BomDropdownDTO> bomList = productsService.getBomListForDropdown();
        logger.info("bomList = {}", bomList);
        
        // 모델에 완제품 리스트와 BOM 목록 추가
        model.addAttribute("productList", productList);
        model.addAttribute("bomList", bomList);

        return "/products/list"; // JSP로 전달
    }
	    
	// 완제품 수정
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute ProductsVO products, RedirectAttributes rttr) throws Exception {
        logger.info("완제품 수정 요청: " + products);

        try {
            productsService.updateProduct(products);
            rttr.addFlashAttribute("message", "✅ 제품 정보가 수정되었습니다.");
        } catch (Exception e) {
            logger.error("제품 수정 실패", e);
            rttr.addFlashAttribute("message", "❌ 제품 수정에 실패했습니다. 다시 시도해주세요.");
        }

        return "redirect:/products/list";
    }
    
    // 완제품 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteProduct(@RequestParam("pCode") String pCode, RedirectAttributes rttr) throws Exception {
        logger.info("완제품 삭제 요청: pCode = {}", pCode);

        try {
            productsService.deleteProduct(pCode);
            rttr.addFlashAttribute("message", "🗑️ 제품이 삭제되었습니다.");
        } catch (Exception e) {
            logger.error("제품 삭제 실패", e);
            rttr.addFlashAttribute("message", "❌ 제품 삭제에 실패했습니다. 다시 시도해주세요.");
        }

        return "redirect:/products/list";
    }
    
    // BOM 목록을 드롭다운용으로 가져오는 메서드
    @RequestMapping(value = "/getBomList", method = RequestMethod.GET)
    public @ResponseBody List<BomDropdownDTO> getBomListForDropdown() throws Exception {
        logger.info("getBomListForDropdown() 호출");

        // BOM 목록을 가져오는 서비스 호출
        List<BomDropdownDTO> bomList = productsService.getBomListForDropdown();
        logger.info("bomList = " + bomList);

        // BOM 목록 반환 (JSON 형태로 자동 변환됨)
        return bomList;
    }

}
