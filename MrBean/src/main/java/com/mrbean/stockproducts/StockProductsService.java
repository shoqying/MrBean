package com.mrbean.stockproducts;

import java.util.List;

public interface StockProductsService {

	List<StockProductsVO> getStockProducts(String sortColumn, String sortDirection, int limit, int offset);
    int getTotalCount();

}
