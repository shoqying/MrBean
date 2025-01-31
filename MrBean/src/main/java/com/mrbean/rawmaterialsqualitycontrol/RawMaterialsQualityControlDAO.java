package com.mrbean.rawmaterialsqualitycontrol;

import java.util.List;
import java.util.Map;

public interface RawMaterialsQualityControlDAO {
	

	// 원자재 검사 관리 데이터 조회
	public Map<String, Object> selectRawMaterialsData() throws Exception;
	
	// 원자재 검사 관리 목록 저장
	public void insertRawMaterialsQualityControl(Map<String, Object> params) throws Exception;
	
	// 원자재 검사 관리 목록
	public List<RawMaterialsQualityControlVO> selectRawMaterialsQualityControl() throws Exception;
	
	// 원자재 품질 검사 상태 업데이트
	public void updateQualityCheck(int rqcBno, String rqcQualityCheck) throws Exception;
	
	// 원자재 상태 업데이트
	public void updateStatus(int rqcBno, String rqcStatus) throws Exception;
	
	// 원자재 검사 목록 삭제
	public int deleteRawMaterial(int rqcBno) throws Exception;

}
