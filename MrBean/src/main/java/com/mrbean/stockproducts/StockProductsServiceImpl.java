package com.mrbean.stockproducts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


	@Service
	public class StockProductsServiceImpl implements StockProductsService {

		 @Autowired
		    private StockProductsDAO stockProductsDAO;

		    @Override
		    public List<StockProductsVO> getStockProducts(String sortColumn, String sortDirection, int limit, int offset) {
		        return stockProductsDAO.selectStockProducts(sortColumn, sortDirection, limit, offset);
		    }

		    @Override
		    public int getTotalCount() {
		        return stockProductsDAO.getTotalCount();
		    }

}
