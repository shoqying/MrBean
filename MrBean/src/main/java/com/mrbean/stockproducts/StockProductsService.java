package com.mrbean.stockproducts;

import java.util.List;

import com.mrbean.finishedproductscontrol.FinishedProductsControlVO;
import com.mrbean.stockmaterials.StockTotalVO;

public interface StockProductsService {

	List<StockProductsVO> getStockProducts(String sortColumn, String sortDirection, int limit, int offset);
    int getTotalCount();
    
    void insertStockProducts(FinishedProductsControlVO VO);
    
 // 완제품 total
    List<StockpTotalVO> selectAllStockpTotal();
    
    // 완제품 총 재고
    void updateStockpTotal(FinishedProductsControlVO vo);
}
