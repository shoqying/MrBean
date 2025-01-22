package com.mrbean.stockproducts;

import java.util.List;

public interface StockProductsService {

	/**
     * 완제품 목록 조회 (정렬 및 페이징 처리 포함).
     * 
     * @param sortColumn 정렬 기준 컬럼
     * @param sortDirection 정렬 방향 (ASC/DESC)
     * @param pageNum 현재 페이지 번호
     * @param offset 조회 시작 위치
     * @return 완제품 목록
     */
    List<StockProductsVO> getStockProducts(String sortColumn, String sortDirection, int pageNum, int offset);

    /**
     * 완제품 목록의 총 개수 조회.
     * 
     * @return 전체 데이터 개수
     */
    int getTotalCount();
    
}
