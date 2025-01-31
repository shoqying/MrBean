package com.mrbean.rawmaterialsqualitycontrol;

import java.util.List;

public interface RawMaterialsQualityControlService {
	
	// 원자재 검사 관리 목록
    List<RawMaterialsQualityControlVO> getRawMaterialsQualityControlList() throws Exception;
	
	// 원자재 품질 검사 상태 업데이트
    void updateQualityCheck(int rqcBno, String rqcQualityCheck) throws Exception;
	
	// 원자재 상태 업데이트
    void updateStatus(int rqcBno, String rqcStatus) throws Exception;
	
	// 원자재 검사 목록 삭제
    int deleteRawMaterial(int rqcBno) throws Exception;

}
