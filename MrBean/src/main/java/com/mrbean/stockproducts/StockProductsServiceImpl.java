package com.mrbean.stockproducts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrbean.finishedproductscontrol.FinishedProductsControlController;
import com.mrbean.finishedproductscontrol.FinishedProductsControlVO;
import com.mrbean.stockmaterials.StockTotalVO;


	@Service
	public class StockProductsServiceImpl implements StockProductsService {
		
		private static final Logger logger = LoggerFactory.getLogger(StockProductsServiceImpl.class);

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

			@Override
			public void insertStockProducts(FinishedProductsControlVO VO) {
				logger.info("######################################3"+VO);
				
				stockProductsDAO.insertStockProducts(VO);
				
			}
		    
		    
			@Override
			public List<StockpTotalVO> selectAllStockpTotal() {
				return stockProductsDAO.selectAllStockpTotal();
			}

			@Override
			public void updateStockpTotal(FinishedProductsControlVO vo) {
				stockProductsDAO.updateStockpTotal(vo);
				
			}
		    
			
		    

}
