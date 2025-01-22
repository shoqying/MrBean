package com.mrbean.products;

import lombok.Data;

@Data
public class ProductsVO {
	
	private String pCode; // 완제품코드
	private String pName; // 완제품명
	private String pDescription; // 설명
	private int bomId; // BOM_ID
	private String rmCode;
	
}