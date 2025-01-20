package com.mrbean.rawmaterialsqualitycontrol;

import java.util.List;

public interface RawMaterialsQualityControlDAO {
	
	// 원자재 검사 관리 목록
	public List<RawMaterialsQualityControlVO> selectRawMaterialsQualityControl() throws Exception;
	
	

}
