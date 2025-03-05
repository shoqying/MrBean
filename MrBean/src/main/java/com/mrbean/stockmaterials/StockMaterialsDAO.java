package com.mrbean.stockmaterials;

import java.util.List;

import com.mrbean.rawmaterialsreceiving.RawMaterialsReceivingVO;

public interface StockMaterialsDAO {
    // 재고 목록 조회 (정렬, 페이징 포함)
    List<StockMaterialsVO> getStockMaterials(String sortColumn, String sortDirection, int pageSize, int offset);
    
    // 전체 재고 수량 조회
    int getTotalCount();
    
    // 원자재 입고 목록 페이징 조회
    List<RawMaterialsReceivingVO> getRawMaterialsWithPaging(int pageSize, int offset);
    
    // 전체 원자재 입고 수량 조회
    int getTotalRawCount();
    
    // 특정 원자재 입고 정보 조회
    RawMaterialsReceivingVO selectRawMaterialsReceiving(int rrNo);
    
    // 재고 등록
    void insertStockMaterials(StockMaterialsVO stockVO);
    
    // 재고 중복 체크
    int checkDuplicateStock(String rrNo);
    
    // 재고 등록 상태 업데이트
    void updateRegistrationStatus(String rrNo);
    
    // 재고 total
    List<StockTotalVO> selectAllStockTotal();
}