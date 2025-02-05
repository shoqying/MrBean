package com.mrbean.finishedproductscontrol;

import java.util.List;
import java.util.Map;

public interface FinishedProductsControlDAO {
	
	// 원자재 검사 관리 목록
    List<FinishedProductsControlVO> selectFinishedProductsControl() throws Exception;
	
	// 완제품 품질 검사 상태 업데이트
    void updateQualityCheck(int fpcBno, String fpcQualityCheck) throws Exception;
	
	// 완제품 상태 업데이트
    void updateStatus(int fpcBno, String fpcStatus) throws Exception;
	
	// 완제품 검사 목록 삭제
    int deleteFinishedProduct(int rqcBno) throws Exception;
	
	// 완제품 LOT 번호 생성
    void insertFinishedProductLot() throws Exception;
    
    // 완제품 목록 저장
    public void insertFinishedProductControl() throws Exception;

}
