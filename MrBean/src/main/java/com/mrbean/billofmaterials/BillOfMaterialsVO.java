package com.mrbean.billofmaterials;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class BillOfMaterialsVO {

	private final String bomId;         // BOM ID
	private final String rmCode;        // 원자재 코드
	private final String bomName;       // BOM 이름
	private final int bomRatio;         // BOM 비율
	private final String bomDescription; // BOM 설명

	// VO 생성 시 유효성 검증 수행
	public BillOfMaterialsVO(String bomId, String rmCode, String bomName, int bomRatio, String bomDescription) {
		if (bomId == null || bomId.isEmpty()) {
			throw new IllegalArgumentException("BOM ID는 필수입니다.");
		}
		if (bomRatio < 0 || bomRatio > 100) {
			throw new IllegalArgumentException("BOM 비율은 0 이상 100 이하이어야 합니다.");
		}
		this.bomId = bomId;
		this.rmCode = rmCode;
		this.bomName = bomName;
		this.bomRatio = bomRatio;
		this.bomDescription = bomDescription != null ? bomDescription : "No Description";
	}

}