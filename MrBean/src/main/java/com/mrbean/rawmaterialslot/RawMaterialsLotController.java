//package com.mrbean.rawmaterialslot;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/rawmaterialslot")  // 기본 URL 경로
//public class RawMaterialsLotController {
//
//    @Autowired
//    private RawMaterialsLotService rawMaterialsLotService; // 서비스 계층 주입
//
//    // 원자재 로트번호 목록 조회
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String listRawMaterialsLot(Model model) {
//        List<RawMaterialsLotVO> rawMaterialsLotList = rawMaterialsLotService.getRawMaterialsLotList();
//        model.addAttribute("rawMaterialsLotList", rawMaterialsLotList);
//        return "rawmaterials/list";  // 뷰 이름을 반환. viewResolver가 이를 실제 JSP로 변환
//    }
//
//    // 원자재 로트번호 추가 폼 (GET 요청)
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String showAddForm(Model model) {
//        model.addAttribute("rawMaterialsLot", new RawMaterialsLotVO());
//        return "rawmaterials/add";  // 원자재 로트번호 추가 폼 뷰
//    }
//
//    // 원자재 로트번호 추가 처리 (POST 요청)
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String addRawMaterialsLot(RawMaterialsLotVO rawMaterialsLot) {
//        rawMaterialsLotService.addRawMaterialsLot(rawMaterialsLot);  // 서비스 호출
//        return "redirect:/rawmaterials/list";  // 원자재 로트번호 목록 페이지로 리다이렉트
//    }
//
//	/*
//	 * // 원자재 로트번호 수정 폼 (GET 요청)
//	 * 
//	 * @RequestMapping(value = "/update/{rmlNo}/{rmlBno}", method =
//	 * RequestMethod.GET) public String showUpdateForm(@PathVariable String
//	 * rmlNo, @PathVariable int rmlBno, Model model) { RawMaterialsLotVO
//	 * rawMaterialsLot = rawMaterialsLotService.getRawMaterialsLotById(rmlNo,
//	 * rmlBno); model.addAttribute("rawMaterialsLot", rawMaterialsLot); return
//	 * "rawmaterials/update"; // 원자재 로트번호 수정 폼 뷰 }
//	 */
//
//    // 원자재 로트번호 수정 처리 (POST 요청)
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String updateRawMaterialsLot(RawMaterialsLotVO rawMaterialsLot) {
//        rawMaterialsLotService.updateRawMaterialsLot(rawMaterialsLot);  // 서비스 호출
//        return "redirect:/rawmaterials/list";  // 수정 후 목록 페이지로 리다이렉트
//    }
//
//    // 원자재 로트번호 삭제 처리 (POST 요청)
//    @RequestMapping(value = "/delete/{rmlNo}/{rmlBno}", method = RequestMethod.POST)
//    public String deleteRawMaterialsLot(@PathVariable String rmlNo, @PathVariable int rmlBno) {
//        rawMaterialsLotService.deleteRawMaterialsLot(rmlNo, rmlBno);  // 서비스 호출
//        return "redirect:/rawmaterials/list";  // 삭제 후 목록 페이지로 리다이렉트
//    }
//}
