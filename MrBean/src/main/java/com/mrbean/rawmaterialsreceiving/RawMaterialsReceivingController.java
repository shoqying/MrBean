package com.mrbean.rawmaterialsreceiving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mrbean.domain.BreadcrumbItem;
import com.mrbean.user.userVO;

@Controller
@RequestMapping(value = "/rawmaterialsreceiving")
public class RawMaterialsReceivingController {

    private static final Logger logger = LoggerFactory.getLogger(RawMaterialsReceivingController.class);

    @Inject
    private RawMaterialsReceivingService rawMaterialsReceivingService;

    // http://localhost:8088/rawmaterialsreceiving/register
    // 원자재 입고 등록 페이지
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(HttpSession session,Model model) throws Exception {
        logger.info("registerForm 호출");
        
        userVO loggedInUser = (userVO) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
        // 로그인되지 않았을 경우 로그인 페이지로 리다이렉트
        return "redirect:/user/login";
        }
        
        // 로트 번호 자동 생성 (수정된 서비스 호출)
        String lotNo = rawMaterialsReceivingService.generateLotNumber();
        
        // 생성된 로트 번호를 모델에 전달하여 JSP에서 출력
        model.addAttribute("lotNo", lotNo);

        return "rawmaterialsreceiving/register";
    }

    // 원자재 입고 등록 처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RawMaterialsReceivingVO rawMaterial) throws Exception {
        logger.info("register 호출: " + rawMaterial);
        
        // 원자재 입고 등록 처리
        rawMaterialsReceivingService.registerRawMaterial(rawMaterial);
        
        // 로트 번호 저장
        String lotNo = rawMaterial.getRmlNo();  // 이미 생성된 로트 번호 사용
        rawMaterialsReceivingService.saveLotNumberToRawMaterialsLot(lotNo);
        
        // 등록 후 입고 리스트 페이지로 리다이렉트
        return "redirect:/rawmaterialsreceiving/list";
    }
    
    // http://localhost:8088/rawmaterialsreceiving/list
    // 원자재 입고 리스트 페이지
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listRawMaterialsReceiving(Model model, HttpServletRequest request) throws Exception {
        logger.info("원자재 입고 리스트 조회 요청");

        // 검색, 정렬, 페이징 파라미터 받아오기
        String searchKeyword = request.getParameter("searchKeyword");
        String sortColumn = request.getParameter("sortColumn");
        String sortOrder = request.getParameter("sortOrder");
        int page = 1;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                logger.warn("페이지 번호 파싱 실패. 기본값 1로 사용합니다.");
            }
        }

        // 서비스 단에서 해당 조건에 맞는 리스트와 페이징 정보를 조회
        // (서비스 메서드에서 리스트, 총페이지 수, 시작/끝 페이지 등을 계산하여 DTO나 Map 형태로 반환하는 방식으로 구현할 수 있음)
        // 여기서는 예제로 단순 리스트만 반환한다고 가정
        List<RawMaterialsReceivingVO> list = rawMaterialsReceivingService.getRawMaterialsReceivingList(page, sortColumn, sortOrder, searchKeyword);
        model.addAttribute("rawMaterialsReceivingList", list);

        // 페이징 관련 dummy data (실제 구현 시 서비스에서 계산하여 전달)
        model.addAttribute("currentPage", page);
        model.addAttribute("startPage", 1);
        model.addAttribute("endPage", 10);
        model.addAttribute("totalPages", 5);

        // Breadcrumb (필요 시)
        addBreadcrumb(model, "원자재 입고 목록", "/rawmaterialsreceiving/list");

        return "rawmaterialsreceiving/list";
    }

    // 원자재 입고 수정 처리
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateRawMaterialsReceiving(@ModelAttribute RawMaterialsReceivingVO rawMaterial,
                                               RedirectAttributes rttr) throws Exception {
        logger.info("원자재 입고 수정 요청: " + rawMaterial);

        try {
            rawMaterialsReceivingService.updateRawMaterialsReceiving(rawMaterial);
            rttr.addFlashAttribute("message", "✅ 원자재 입고 정보가 수정되었습니다.");
        } catch (Exception e) {
            logger.error("원자재 입고 수정 실패", e);
            rttr.addFlashAttribute("message", "❌ 원자재 입고 수정에 실패했습니다. 다시 시도해주세요.");
        }

        return "redirect:/rawmaterialsreceiving/list";
    }

    // 원자재 입고 삭제 처리
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteRawMaterialsReceiving(@RequestParam("rrNo") int rrNo,
                                               RedirectAttributes rttr) throws Exception {
        logger.info("원자재 입고 삭제 요청: rrNo = {}", rrNo);

        try {
            rawMaterialsReceivingService.deleteRawMaterialReceiving(rrNo);
            rttr.addFlashAttribute("message", "🗑️ 원자재 입고 정보가 삭제되었습니다.");
        } catch (Exception e) {
            logger.error("원자재 입고 삭제 실패", e);
            rttr.addFlashAttribute("message", "❌ 원자재 입고 삭제에 실패했습니다. 다시 시도해주세요.");
        }

        return "redirect:/rawmaterialsreceiving/list";
    }

    // Breadcrumb 생성 도우미 메서드
    private void addBreadcrumb(Model model, String... items) {
        List<BreadcrumbItem> breadcrumbList = new ArrayList<>();
        for (int i = 0; i < items.length; i += 2) {
            boolean isActive = (i == items.length - 2);
            breadcrumbList.add(new BreadcrumbItem(items[i], items[i + 1], isActive));
        }
        model.addAttribute("breadcrumbList", breadcrumbList);
    }
}
    

