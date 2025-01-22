package com.mrbean.stockproducts;

import java.util.List;

public interface StockProductsDAO {
	
    List<StockProductsVO> selectStockProducts(String sortColumn, String sortDirection, int limit, int offset);
    int getTotalCount();
}
