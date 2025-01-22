package com.mrbean.stockmaterials;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockMaterialsSeriveceImpl implements StockMaterialsService {

    @Autowired
    private StockMaterialsDAO stockMaterialsDAO;

    @Override
    public List<StockMaterialsVO> getStockMaterials(String sortColumn, String sortDirection, int pageNum, int offset) {
        return stockMaterialsDAO.getStockMaterials(sortColumn, sortDirection, pageNum, offset);
    }

    @Override
    public int getTotalCount() {
        return stockMaterialsDAO.getTotalCount();
    }
    
    
//    @Override
//    public void insertStockMaterials(StockMaterialsVO stockMaterialsVO) {
//        // 원자재 등록 처리
//        stockMaterialsDAO.insertStockMaterials(stockMaterialsVO);
//    }

}