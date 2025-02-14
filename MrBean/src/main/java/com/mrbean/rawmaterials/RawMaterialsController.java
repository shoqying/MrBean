package com.mrbean.rawmaterials;

import com.mrbean.domain.BreadcrumbItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(value = "/rawMaterials")
public class RawMaterialsController {

    private static final Logger logger = LoggerFactory.getLogger(RawMaterialsController.class);

    @Inject
    private RawMaterialsService rawMaterialsService;

    // 원자재 등록 페이지로 이동
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage(Model model) throws Exception {
        logger.info("원자재 등록 페이지로 이동");

        // 빈 원자재 객체를 모델에 추가하여 등록 폼에서 바인딩할 수 있도록 함
        model.addAttribute("rawMaterialsVO", new RawMaterialsVO());
        addBreadcrumb(model, "원자재 목록", "/rawMaterials/list", "원자재 등록", "#");

        return "rawMaterials/register";  // JSP 파일 경로
    }

    // 원자재 등록 처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerRawMaterial(@ModelAttribute("rawMaterialsVO") RawMaterialsVO rawMaterialsVO, RedirectAttributes rttr) throws Exception {
        logger.info("원자재 등록 요청: {}", rawMaterialsVO);

        try {
            // 제품 등록 처리
            rawMaterialsService.registerRawMaterial(rawMaterialsVO);

            // 성공 메시지 전달
            rttr.addFlashAttribute("message", "✅ 원자재가 등록되었습니다.");
        } catch (Exception e) {
            logger.error("원자재 등록 실패", e);

            // 실패 메시지 전달
            rttr.addFlashAttribute("message", "❌ 등록에 실패했습니다. 다시 시도해주세요.");
        }

        return "redirect:/rawMaterials/register";
    }

    // 원자재 리스트 페이지로 이동
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showRawMaterialsList(Model model) throws Exception {
        logger.info("원자재 리스트 페이지로 이동");

        // 원자재 리스트 조회
        List<RawMaterialsVO> rawMaterialsList = rawMaterialsService.getRawMaterialsList();
        model.addAttribute("rawMaterialsList", rawMaterialsList);
        addBreadcrumb(model, "원자재 목록", "#");

        return "rawMaterials/list";  // JSP 파일 경로
    }

    // 원자재 수정
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateRawMaterial(@ModelAttribute RawMaterialsVO rawMaterialsVO, RedirectAttributes rttr) throws Exception {
        logger.info("원자재 수정 요청: " + rawMaterialsVO);

        try {
            rawMaterialsService.updateRawMaterial(rawMaterialsVO);
            rttr.addFlashAttribute("message", "✅ 원자재 정보가 수정되었습니다.");
        } catch (Exception e) {
            logger.error("원자재 수정 실패", e);
            rttr.addFlashAttribute("message", "❌ 원자재 수정에 실패했습니다. 다시 시도해주세요.");
        }

        return "redirect:/rawMaterials/list";
    }

    // 원자재 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteRawMaterial(@RequestParam("rmCode") String rmCode, RedirectAttributes rttr) throws Exception {
        logger.info("원자재 삭제 요청: rmCode = {}", rmCode);

        try {
            rawMaterialsService.deleteRawMaterial(rmCode);
            rttr.addFlashAttribute("message", "🗑️ 원자재가 삭제되었습니다.");
        } catch (Exception e) {
            logger.error("원자재 삭제 실패", e);
            rttr.addFlashAttribute("message", "❌ 원자재 삭제에 실패했습니다. 다시 시도해주세요.");
        }

        return "redirect:/rawMaterials/list";
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