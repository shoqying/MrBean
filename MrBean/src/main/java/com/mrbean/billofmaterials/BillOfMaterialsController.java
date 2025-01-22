package com.mrbean.billofmaterials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(value = "/billofmaterials")
public class BillOfMaterialsController {

	private static final Logger logger = LoggerFactory.getLogger(BillOfMaterialsController.class);

	private final BillOfMaterialsService billOfMaterialsService;

	@Autowired
	public BillOfMaterialsController(BillOfMaterialsService billOfMaterialsService) {
		this.billOfMaterialsService = billOfMaterialsService;
	}

	@GetMapping("")
	public String create(BillOfMaterialsDTO billOfMaterialsDTO) {

		return "null";
	}
}
