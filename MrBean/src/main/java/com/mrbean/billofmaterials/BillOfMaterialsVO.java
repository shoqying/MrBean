package com.mrbean.billofmaterials;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BillOfMaterialsVO {

	@JsonProperty("bomId")
	private int bomId; // BOM_ID

	@JsonProperty("rmCode")
	private String rmCode; // 원자재코드

	@JsonProperty("bomName")
	private String bomName; // BOM_NAME

	@JsonProperty("bomRatio")
	private int bomRatio; // 원자재 비율

	@JsonProperty("bomDescription")
	private String bomDescription; // BOM_DESCRIPTION

}