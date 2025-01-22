package com.mrbean.stockproducts;

import java.util.List;

public interface StockProductsDAO {

	 /**
     * 정렬 및 페이징 처리를 적용한 완제품 목록 조회.
     * 
     * @param sortColumn 정렬 기준 컬럼
     * @param sortDirection 정렬 방향 (ASC/DESC)
     * @param limit 조회할 데이터 수
     * @param offset 조회 시작 위치
     * @return 완제품 목록
     */
    List<StockProductsVO> getStockProducts(String sortColumn, String sortDirection, int limit, int offset);

    /**
     * 완제품 테이블의 총 데이터 개수 조회.
     * 
     * @return 전체 데이터 개수
     */
    int getTotalCount();
    
}
