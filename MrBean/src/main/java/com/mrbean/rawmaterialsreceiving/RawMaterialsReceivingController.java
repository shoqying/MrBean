package com.mrbean.rawmaterialsreceiving;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/rawmaterialsreceiving")
public class RawMaterialsReceivingController {

    private static final Logger logger = LoggerFactory.getLogger(RawMaterialsReceivingController.class);

    @Inject
    private RawMaterialsReceivingService rawMaterialsReceivingService;

    // 원자재 입고 등록 페이지
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm() {
        logger.info("registerForm 호출");
        return "rawmaterialsreceiving/register";
    }

    // 원자재 입고 등록 처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RawMaterialsReceivingVO rawMaterial) {
        logger.info("register 호출: " + rawMaterial);
        rawMaterialsReceivingService.registerRawMaterial(rawMaterial);
        return "redirect:/rawmaterialsreceiving/list";
    }

    // 원자재 입고 목록 조회
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("getAllRawMaterials 호출");
        List<RawMaterialsReceivingVO> materials = rawMaterialsReceivingService.getAllRawMaterials();
        model.addAttribute("rawmaterialsReceiving", materials);
        return "rawmaterialsreceiving/list";
    }

    // 원자재 입고 정보 수정
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute RawMaterialsReceivingVO rawMaterial) {
        logger.info("update 호출: " + rawMaterial);
        rawMaterialsReceivingService.updateRawMaterial(rawMaterial);
        return "redirect:/rawmaterialsreceiving/list";
    }

    // 원자재 입고 정보 삭제
    @RequestMapping(value = "/delete/{rrNo}", method = RequestMethod.POST)
    public String delete(@PathVariable("rrNo") String rrNo) {
        logger.info("delete 호출: rrNo = " + rrNo);
        rawMaterialsReceivingService.deleteRawMaterial(rrNo);
        return "redirect:/rawmaterialsreceiving/list";
    }
}
