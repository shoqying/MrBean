package com.mrbean.stockmaterials;

import java.util.List;

public interface StockMaterialsDAO {
	
	 /**
     * 원자재 목록을 정렬 및 페이징하여 조회합니다.
     * 
     * @param sortColumn 정렬 기준 컬럼
     * @param sortDirection 정렬 방향 (ASC/DESC)
     * @param limit 한 페이지에 표시할 원자재 개수
     * @param offset 조회 시작 위치
     * @return 정렬 및 페이징된 원자재 목록
     */
    List<StockMaterialsVO> getStockMaterials(String sortColumn, String sortDirection, int limit, int offset);

    /**
     * 원자재 테이블의 전체 개수를 조회합니다.
     * 
     * @return 원자재 총 개수
     */
    int getTotalCount();
    
    // 원자재 등록
    // void insertStockMaterials(StockMaterialsVO stockMaterialsVO);
}
