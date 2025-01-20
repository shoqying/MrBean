package com.mrbean.rawmaterialsqualitycontrol;

import java.util.List;

public interface RawMaterialsQualityControlService {
	
	// 원자재 검사 관리 목록
	public List<RawMaterialsQualityControlVO> getRawMaterialsQualityControlList() throws Exception;

}
