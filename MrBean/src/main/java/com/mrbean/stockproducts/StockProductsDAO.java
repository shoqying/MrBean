package com.mrbean.stockproducts;

import java.util.List;

import com.mrbean.finishedproductscontrol.FinishedProductsControlVO;

public interface StockProductsDAO {
	
    List<StockProductsVO> selectStockProducts(String sortColumn, String sortDirection, int limit, int offset);
    int getTotalCount();
    
    void insertStockProducts(FinishedProductsControlVO vo);
}
