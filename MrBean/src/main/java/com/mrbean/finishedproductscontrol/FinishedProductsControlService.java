package com.mrbean.finishedproductscontrol;

import java.util.List;

public interface FinishedProductsControlService {

	// 완제품 검사 관리 목록
	public List<FinishedProductsControlVO> getFinishedProductsControlList() throws Exception;
	
	// 완제품 품질 검사 상태 업데이트
	public void updateQualityCheck(int fpcBno, String fpcQualityCheck) throws Exception;
		
	// 완제품 상태 업데이트
	public void updateStatus(int fpcBno, String fpcStatus) throws Exception;
	
	// 완제품 검사 목록 삭제
	public int deleteFinishedProduct(int rqcBno) throws Exception;

	// 완제품 LOT 번호 생성
	void insertFinishedProductLot() throws Exception;

}
