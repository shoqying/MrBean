package com.mrbean.stockmaterials;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrbean.rawmaterialsreceiving.RawMaterialsReceivingVO;

@Service
public class StockMaterialsSeriveceImpl implements StockMaterialsService {
    private static final Logger logger = LoggerFactory.getLogger(StockMaterialsSeriveceImpl.class);

    @Autowired
    private StockMaterialsDAO stockMaterialsDAO;

    @Override
    public List<StockMaterialsVO> getStockMaterials(String sortColumn, String sortDirection, int pageSize, int offset) {
        return stockMaterialsDAO.getStockMaterials(sortColumn, sortDirection, pageSize, offset);
    }

    @Override
    public int getTotalCount() {
        return stockMaterialsDAO.getTotalCount();
    }

    @Override
    public List<RawMaterialsReceivingVO> getRawMaterialsWithPaging(int pageSize, int offset) {
        return stockMaterialsDAO.getRawMaterialsWithPaging(pageSize, offset);
    }

    @Override
    public int getTotalRawCount() {
        return stockMaterialsDAO.getTotalRawCount();
    }

    @Override
    public RawMaterialsReceivingVO getRawMaterialsReceiving(int rrNo) {
        return stockMaterialsDAO.selectRawMaterialsReceiving(rrNo);
    }
    
    @Override
    @Transactional
    public void insertStockMaterials(StockMaterialsVO stockVO) {
        logger.info("Service - insertStockMaterials called with: " + stockVO);
        try {
            if(isDuplicateStock(stockVO.getRrNo())) {
                throw new RuntimeException("이미 등록된 재고입니다.");
            }
            
            stockMaterialsDAO.insertStockMaterials(stockVO);
            stockMaterialsDAO.updateRegistrationStatus(stockVO.getRrNo());
            logger.info("Service - insert successful");
        } catch (Exception e) {
            logger.error("Service - Error during insert", e);
            throw e;
        }
    }

    @Override
    public boolean isDuplicateStock(String rrNo) {
        return stockMaterialsDAO.checkDuplicateStock(rrNo) > 0;
    }
}