package com.mrbean.products;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
        
        return "products/register"; // JSP로 전달
    }
    
    // 등록 처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerProduct(@ModelAttribute ProductsVO product, Model model) throws Exception {
        logger.info("registerProduct() 호출, 제품 코드: " + product.getPCode());
        
        // 제품 등록 처리
        productsService.registerProduct(product);
        
        // 메시지 전달 후 등록 페이지로 리다이렉트
        model.addAttribute("message", "완제품이 등록되었습니다.");
        return "redirect:/products/register";
    }
}
