package com.mrbean.billofmaterials;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class BillOfMaterialsVO {

	@JsonProperty("bomId")
	private String bomId; // BOM_ID

	@JsonProperty("rmCode")
	private String rmCode; // 원자재코드

	@JsonProperty("bomName")
	private String bomName; // BOM_NAME

	@JsonProperty("bomRatio")
	private int bomRatio; // 원자재 비율

	@JsonProperty("bomDescription")
	private String bomDescription; // BOM_DESCRIPTION

}