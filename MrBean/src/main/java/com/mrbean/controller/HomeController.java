package com.mrbean.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.mrbean.billofmaterials.domain.BillOfMaterialsDTO;
import com.mrbean.billofmaterials.service.BillOfMaterialsService;
import com.mrbean.domain.BreadcrumbItem;
import com.mrbean.rawmaterials.RawMaterialsService;
import com.mrbean.warehouse.WarehouseService;
import com.mrbean.warehouse.WarehouseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private final BillOfMaterialsService billOfMaterialsService;
	private final RawMaterialsService rawMaterialService;
	private final WarehouseService warehouseService;

	@Autowired
	public HomeController(BillOfMaterialsService billOfMaterialsService, RawMaterialsService rawMaterialService, WarehouseService warehouseService) {
		this.billOfMaterialsService = billOfMaterialsService;
		this.rawMaterialService = rawMaterialService;
		this.warehouseService = warehouseService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

	@GetMapping("/warehouses/create")
	public String createWarehouse(Model model) {
		addBreadcrumb(model, "창고 목록", "/warehouses", "창고 등록", "#");
		return "warehouse/create";
	}

	@GetMapping("/billofmaterials")
	public String getAllBillOfMaterials(Model model) throws Exception {
		List<BillOfMaterialsDTO> bomList = billOfMaterialsService.getAllBoms();
		model.addAttribute("bomList", bomList);
		addBreadcrumb(model, "BOM 목록", "#");
		return "billofmaterials/main";
	}

	@GetMapping("/billofmaterials/create")
	public String createBOM(Model model) throws Exception {
		String nextBOMId = billOfMaterialsService.generateBomId();
		model.addAttribute("nextBOMId", nextBOMId);
		addBreadcrumb(model, "BOM 목록", "/billofmaterials", "BOM 등록", "#");
		return "billofmaterials/create";
	}

	@GetMapping("/warehouses")
	public String getWarehouseList(Model model) {
		try {
			List<WarehouseVO> warehouseList = warehouseService.getWarehouseList();
			model.addAttribute("warehouseList", warehouseList);
			addBreadcrumb(model, "창고 목록", "#");
			return "warehouse/main";
		} catch (Exception e) {
			logger.error("창고 목록 조회 중 오류 발생", e);
			model.addAttribute("errorMessage", "창고 목록 조회 실패: " + e.getMessage());
			return "error";
		}
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