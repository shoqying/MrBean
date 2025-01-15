package com.mrbean.rawMaterials;

import lombok.Data;

@Data
public class RawMaterialsVO {
	
	private String rmCode; // 원자재코드
	private String rmName; // 원자재명
	private String rmOrigin; // 원산지
	private String rmStorageMethod; // 보관방법

}