package com.mrbean.billofmaterials;

import lombok.Data;

@Data
public class BillOfMaterialVO {
	
	private int bomId; // BOM_ID
	private String rmCode; // 원자재코드
	private String bomName; // BOM_NAME
	private int bomRatio; // 원자재 비율
	private String bomCode; // BOM_CODE

}