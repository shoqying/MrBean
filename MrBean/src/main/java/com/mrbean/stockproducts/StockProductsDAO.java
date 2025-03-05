package com.mrbean.stockproducts;

import java.util.List;

import com.mrbean.finishedproductscontrol.FinishedProductsControlVO;
import com.mrbean.stockmaterials.StockTotalVO;

public interface StockProductsDAO {
	
    List<StockProductsVO> selectStockProducts(String sortColumn, String sortDirection, int limit, int offset);
    int getTotalCount();
    
    void insertStockProducts(FinishedProductsControlVO vo);
    
    // 재고 total
    List<StockpTotalVO> selectAllStockpTotal();
    
    // 완제품 총 재고
    void updateStockpTotal(FinishedProductsControlVO vo);
    
    
}
