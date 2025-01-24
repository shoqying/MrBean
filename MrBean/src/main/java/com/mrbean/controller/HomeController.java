package com.mrbean.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.mrbean.billofmaterials.BillOfMaterialsDTO;
import com.mrbean.billofmaterials.BillOfMaterialsService;
import com.mrbean.domain.BreadcrumbItem;
import com.mrbean.rawmaterials.RawMaterialsDTO;
import com.mrbean.rawmaterials.RawMaterialsService;
import com.mrbean.rawmaterials.RawMaterialsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private final BillOfMaterialsService billOfMaterialsService;
	private final RawMaterialsService rawMaterialService;

	@Autowired
	public HomeController(BillOfMaterialsService billOfMaterialsService, RawMaterialsService rawMaterialService) {
		this.billOfMaterialsService = billOfMaterialsService;
		this.rawMaterialService = rawMaterialService;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	/**
	 * 창고 등록 페이지 이동 (GET)
	 * Example: GET http://localhost:8080/warehouses/create
	 */
	@GetMapping("/warehouses/create")
	public String CreateWarehouse(Model model) {
		// breadcrumbList 작성 예시
		List<BreadcrumbItem> breadcrumbList = new ArrayList<>();
		// Home (비활성 링크, 첫 번째 메뉴)
//		breadcrumbList.add(new BreadcrumbItem("Home", "/", false));
		// 상위 메뉴 (비활성 링크)
		breadcrumbList.add(new BreadcrumbItem("창고관리", "/", false));
		// 현재 페이지 (활성 표시)
		breadcrumbList.add(new BreadcrumbItem("창고 등록", "#", true));

		// 모델에 담아서 JSP로 전달
		model.addAttribute("breadcrumbList", breadcrumbList);

		return "warehouse/create"; // JSP 파일 반환
	}

	/**
	 * BOM 전체 리스트 조회 메인 페이지
	 *
	 * @param sortKey   정렬할 컬럼 (예: rm_code, bom_id, bom_name)
	 * @param sortOrder 정렬 방식 (ASC / DESC)
	 * @param model     View에 데이터를 전달하기 위한 Model 객체
	 * @return 뷰 페이지 이름
	 */
	@GetMapping("/billofmaterials")
	public String getAllBillOfMaterials(
			@RequestParam(required = false) String sortKey,
			@RequestParam(required = false) String sortOrder,
			Model model) throws Exception {

		// BOM 리스트 가져오기
		List<BillOfMaterialsDTO> bomList = billOfMaterialsService.getAllBoms(sortKey, sortOrder);

		// 뷰로 데이터 전달
		model.addAttribute("bomList", bomList);
		model.addAttribute("sortKey", sortKey);
		model.addAttribute("sortOrder", sortOrder);

		// 뷰 페이지 이름 반환
		return "billofmaterials/main"; // JSP 파일 이름
	}
	/**
	 * BOM 등록 페이지 이동 (GET)
	 * Example: GET http://localhost:8080/billofmaterials/create
	 */
	@GetMapping("/billofmaterials/create")
	public String createBOM(Model model) throws Exception {
		// Service 계층에서 다음 BOM ID를 생성하는 메서드 호출
		String nextBOMId = billOfMaterialsService.generateBomId();

		// 뷰에서 표시할 수 있도록 모델에 저장
		model.addAttribute("nextBOMId", nextBOMId);

		// 원자재 목록도 함께 가져와 모델에 담는다
//		List<RawMaterialsVO> rawMaterialsList = rawMaterialService.getRawMaterialsList();
//		model.addAttribute("rawMaterialsList", rawMaterialsList);

		// BOM 등록 페이지로 이동
		return "billofmaterials/create";
	}

}