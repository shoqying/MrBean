package com.mrbean.stockproducts;

import java.util.List;

import com.mrbean.finishedproductscontrol.FinishedProductsControlVO;

public interface StockProductsService {

	List<StockProductsVO> getStockProducts(String sortColumn, String sortDirection, int limit, int offset);
    int getTotalCount();
    
    void insertStockProducts(FinishedProductsControlVO VO);
}
