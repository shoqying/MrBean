package com.mrbean.products;

import lombok.Data;

@Data
public class ProductsVO {
	
	private String pCode; // 완제품코드
	private String p_name; // 완제품명
	private String p_description; // 설명
	private int bom_id; // BOM_ID

}