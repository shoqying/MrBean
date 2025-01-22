package com.mrbean.rawmaterials;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "rawMaterials/register";  // JSP 파일 경로
    }

    // 원자재 등록 처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerRawMaterial(@ModelAttribute("rawMaterialsVO") RawMaterialsVO rawMaterialsVO) throws Exception {
        logger.info("원자재 등록 요청: {}", rawMaterialsVO);

        // 원자재 등록 서비스 호출
        rawMaterialsService.registerRawMaterial(rawMaterialsVO);
        logger.info("원자재 등록 완료: {}", rawMaterialsVO);

        return "redirect:/rawMaterials/list";  // 등록 후 원자재 리스트 페이지로 이동
    }
    
    // 원자재 리스트 페이지로 이동
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showRawMaterialsList(Model model) throws Exception {
        logger.info("원자재 리스트 페이지로 이동");

        // 원자재 리스트 조회
        List<RawMaterialsVO> rawMaterialsList = rawMaterialsService.getRawMaterialsList();
        model.addAttribute("rawMaterialsList", rawMaterialsList);

        return "rawMaterials/list";  // JSP 파일 경로
    }
    
    // 원자재 수정
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateRawMaterial(@ModelAttribute RawMaterialsVO rawMaterialsVO) throws Exception {
        logger.info("원자재 수정 요청: " + rawMaterialsVO);

        // 서비스에서 수정 처리
        rawMaterialsService.updateRawMaterial(rawMaterialsVO);

        // 수정 후 리스트 페이지로 이동
        return "redirect:/rawMaterials/list";  // 수정 후 목록 페이지로 리다이렉트
    }
    
    // 원자재 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteRawMaterial(@RequestParam("rmCode") String rmCode) throws Exception {
        logger.info("원자재 삭제 요청: rmCode = {}", rmCode);

        // 서비스에서 삭제 처리
        rawMaterialsService.deleteRawMaterial(rmCode);

        // 삭제 후 원자재 목록 페이지로 리다이렉트
        return "redirect:/rawMaterials/list";  // 삭제 후 목록 페이지로 리다이렉트
    }
}
