package com.mrbean.stockproducts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


	@Service
	public class StockProductsServiceImpl implements StockProductsService {

	    @Autowired
	    private StockProductsDAO stockProductsDAO;

	    @Override
	    public List<StockProductsVO> getStockProducts(String sortColumn, String sortDirection, int pageNum, int offset) {
	        return stockProductsDAO.getStockProducts(sortColumn, sortDirection, pageNum, offset);
	    }

	    @Override
	    public int getTotalCount() {
	        return stockProductsDAO.getTotalCount();
	    }

}
