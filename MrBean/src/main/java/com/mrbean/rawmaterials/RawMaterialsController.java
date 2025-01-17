package com.mrbean.rawmaterials;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(value = "/rawMaterials/*")
public class RawMaterialsController {

	private static final Logger logger = LoggerFactory.getLogger(RawMaterialsController.class);
}
