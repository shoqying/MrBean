package com.mrbean.products;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping(value = "/products/*")
public class ProductsController {

	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
}
