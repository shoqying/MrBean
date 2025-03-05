package com.mrbean.stockmaterials;

import java.util.List;

import com.mrbean.rawmaterialsreceiving.RawMaterialsReceivingVO;

// 재고 관리 서비스 인터페이스
public interface StockMaterialsService {
    // 재고 목록 조회 (정렬, 페이징 포함)
    List<StockMaterialsVO> getStockMaterials(String sortColumn, String sortDirection, int pageSize, int offset);
    
    // 전체 재고 수량 조회
    int getTotalCount();
    
    // 원자재 입고 목록 페이징 조회
    List<RawMaterialsReceivingVO> getRawMaterialsWithPaging(int pageSize, int offset);
    
    // 전체 원자재 입고 수량 조회
    int getTotalRawCount();
    
    // 특정 원자재 입고 정보 조회
    RawMaterialsReceivingVO getRawMaterialsReceiving(int rrNo);
    
    // 재고 등록
    void insertStockMaterials(StockMaterialsVO stockVO);
    
    // 재고 중복 체크
    boolean isDuplicateStock(String rrNo);
    
    // 원자재 total
    List<StockTotalVO> selectAllStockTotal();
}