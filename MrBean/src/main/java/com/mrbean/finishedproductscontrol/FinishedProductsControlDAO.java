package com.mrbean.finishedproductscontrol;

import java.util.List;
import java.util.Map;

import com.mrbean.enums.QualityControlStatus;
import com.mrbean.rawmaterialsqualitycontrol.RawMaterialsQualityControlVO;
import com.mrbean.workorders.WorkOrdersVO;

public interface FinishedProductsControlDAO {
	
	// 원자재 검사 관리 목록
    List<FinishedProductsControlVO> selectFinishedProductsControl() throws Exception;
	
	// 완제품 품질 검사 상태 업데이트
    void updateQualityCheck(FinishedProductsControlVO vo) throws Exception;
	
	// 완제품 상태 업데이트
    void updateStatus(FinishedProductsControlVO vo) throws Exception;
	
	// 완제품 검사 목록 삭제
    int deleteFinishedProduct(int fpcBno) throws Exception;
    
    // 완제품 LOT 번호 생성
    void insertFinishedProductLot(FinishedProductsControlVO vo) throws Exception;
    
    // 완제품 LOT 번호 삭제
    void deleteFinishedProductLot(RawMaterialsQualityControlVO vo) throws Exception;
    
    // 완제품 목록 저장
    public void insertFinishedProductControl() throws Exception;
    
    // 워크 오더 넘버 가져오기
    public FinishedProductsControlVO getWorkOrdersNo() throws Exception;
    
    // 원자재 대기중으로 변경시 완제품 삭제
    public int deleteFinishedProductControl(FinishedProductsControlVO vo) throws Exception;

}
