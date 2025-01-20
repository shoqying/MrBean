package com.mrbean.rawmaterials;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(value = "/rawMaterials/*")
public class RawMaterialsController {

    private static final Logger logger = LoggerFactory.getLogger(RawMaterialsController.class);

    @Inject
    private RawMaterialsService rawMaterialsService;

    // http://localhost:8088/rawMaterials/register
    // 원자재 등록 페이지 (GET)
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerRawMaterialForm() {
        return "rawMaterials/register"; // 등록 폼 JSP로 이동
    }

    // 원자재 등록 처리 (POST)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerRawMaterial(RawMaterialsVO rawMaterialsVO) throws Exception {
        rawMaterialsService.insertRawMaterials(rawMaterialsVO); // 서비스 계층을 통해 처리
        return "redirect:/rawMaterials/list"; // 등록 후 리스트 페이지로 리디렉션
    }
    
    // http://localhost:8088/rawMaterials/list
    // 원자재 목록 페이지 (GET)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listRawMaterials(Model model) throws Exception {
        List<RawMaterialsVO> rawMaterialsList = rawMaterialsService.getRawMaterialsList();
        model.addAttribute("rawMaterialsList", rawMaterialsList); // 리스트를 JSP로 전달
        return "rawMaterials/list"; // 리스트 페이지로 이동
    }
    
    // 원자재 수정 페이지 (GET)
    @RequestMapping(value = "/update/{rmCode}", method = RequestMethod.GET)
    public String updateRawMaterialForm(@PathVariable("rmCode") String rmCode, Model model) throws Exception {
        RawMaterialsVO rawMaterialsVO = rawMaterialsService.getRawMaterialByCode(rmCode); // 서비스에서 원자재 정보 조회
        model.addAttribute("rawMaterialsVO", rawMaterialsVO); // 수정 폼으로 전달
        return "rawMaterials/update"; // 수정 폼으로 이동
    }

    // 원자재 수정 처리 (POST)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateRawMaterial(RawMaterialsVO rawMaterialsVO) throws Exception {
        rawMaterialsService.updateRawMaterials(rawMaterialsVO); // 서비스 계층을 통해 처리
        return "redirect:/rawMaterials/list"; // 수정 후 리스트 페이지로 리디렉션
    }
}

