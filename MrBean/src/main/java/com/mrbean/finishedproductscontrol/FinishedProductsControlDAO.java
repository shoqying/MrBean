package com.mrbean.finishedproductscontrol;

import java.util.List;

public interface FinishedProductsControlDAO {
	
	// 원자재 검사 관리 목록
	public List<FinishedProductsControlVO> selectFinishedProductsControl() throws Exception;
	
	// 완제품 품질 검사 상태 업데이트
	public void updateQualityCheck(int fpcBno, String fpcQualityCheck) throws Exception;
	
	// 완제품 상태 업데이트
	public void updateStatus(int fpcBno, String fpcStatus) throws Exception;

}
