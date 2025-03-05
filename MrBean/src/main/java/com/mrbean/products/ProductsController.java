package com.mrbean.products;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mrbean.billofmaterials.domain.BomDropdownDTO;
import com.mrbean.domain.BreadcrumbItem;

import java.util.ArrayList;
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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) throws Exception {
        logger.info("registerPage() 호출");

        model.addAttribute("product", new ProductsVO());
        model.addAttribute("bomList", productsService.getBomListForDropdown());
        addBreadcrumb(model, "완제품 목록", "/products/list", "완제품 등록", "#");

        return "/products/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerProduct(@ModelAttribute ProductsVO product, RedirectAttributes rttr) {
        logger.info("registerProduct() 호출");

        try {
            productsService.registerProduct(product);
            rttr.addFlashAttribute("message", "✅ 완제품이 등록되었습니다.");
        } catch (Exception e) {
            logger.error("제품 등록 실패", e);
            rttr.addFlashAttribute("message", "❌ 등록에 실패했습니다. 다시 시도해주세요.");
        }

        return "redirect:/products/register";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage(Model model) throws Exception {
        logger.info("listPage() 호출");

        List<ProductsVO> productList = productsService.getProductList();
        List<BomDropdownDTO> bomList = productsService.getBomListForDropdown();

        model.addAttribute("productList", productList);
        model.addAttribute("bomList", bomList);
        addBreadcrumb(model, "완제품 목록", "#");

        return "/products/list";
    }

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

    @RequestMapping(value = "/getBomList", method = RequestMethod.GET)
    public @ResponseBody List<BomDropdownDTO> getBomListForDropdown() throws Exception {
        logger.info("getBomListForDropdown() 호출");

        List<BomDropdownDTO> bomList = productsService.getBomListForDropdown();
        logger.info("bomList = " + bomList);

        return bomList;
    }

    private void addBreadcrumb(Model model, String... items) {
        List<BreadcrumbItem> breadcrumbList = new ArrayList<>();
        for (int i = 0; i < items.length; i += 2) {
            boolean isActive = (i == items.length - 2);
            breadcrumbList.add(new BreadcrumbItem(items[i], items[i + 1], isActive));
        }
        model.addAttribute("breadcrumbList", breadcrumbList);
    }
}