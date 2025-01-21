package com.mrbean.billofmaterials;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(value = "/billofmaterials")
public class BillOfMaterialsController {

	private static final Logger logger = LoggerFactory.getLogger(BillOfMaterialsController.class);
}
