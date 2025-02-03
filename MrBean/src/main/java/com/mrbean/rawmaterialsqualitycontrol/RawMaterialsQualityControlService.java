package com.mrbean.rawmaterialsqualitycontrol;

import java.util.List;

public interface RawMaterialsQualityControlService {
	
	// 원자재 검사 관리 데이터 조회, 저장
	public void processAndInsertRawMaterials() throws Exception;
	
	// 원자재 검사 관리 목록
    public List<RawMaterialsQualityControlVO> getRawMaterialsQualityControlList() throws Exception;
	
	// 원자재 품질 검사 상태 업데이트
    public void updateQualityCheck(int rqcBno, String rqcQualityCheck) throws Exception;
	
	// 원자재 상태 업데이트
    public void updateStatus(int rqcBno, String rqcStatus) throws Exception;
    
	// 원자재 검사 목록 삭제
    public int deleteRawMaterial(int rqcBno) throws Exception;

}
