package com.mrbean.stockmaterials;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrbean.rawmaterialsreceiving.RawMaterialsReceivingVO;

@Repository
public class StockMaterialsDAOImpl implements StockMaterialsDAO {
    private static final Logger logger = LoggerFactory.getLogger(StockMaterialsDAOImpl.class);

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<StockMaterialsVO> getStockMaterials(String sortColumn, String sortDirection, int pageSize, int offset) {
        Map<String, Object> params = new HashMap<>();
        params.put("sortColumn", sortColumn);
        params.put("sortDirection", sortDirection);
        params.put("pageSize", pageSize);
        params.put("offset", offset);
        return sqlSession.selectList("com.mrbean.mappers.StockMaterialsMapper.getStockMaterials", params);
    }

    @Override
    public int getTotalCount() {
        return sqlSession.selectOne("com.mrbean.mappers.StockMaterialsMapper.getTotalCount");
    }

    @Override
    public List<RawMaterialsReceivingVO> getRawMaterialsWithPaging(int pageSize, int offset) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageSize", pageSize);
        params.put("offset", offset);
        return sqlSession.selectList("com.mrbean.mappers.StockMaterialsMapper.getRawMaterialsWithPaging", params);
    }

    @Override
    public int getTotalRawCount() {
        return sqlSession.selectOne("com.mrbean.mappers.StockMaterialsMapper.getTotalRawCount");
    }

    @Override
    public RawMaterialsReceivingVO selectRawMaterialsReceiving(int rrNo) {
        return sqlSession.selectOne("com.mrbean.mappers.StockMaterialsMapper.selectRawMaterialsReceiving", rrNo);
    }
    
    @Override
    public void insertStockMaterials(StockMaterialsVO stockVO) {
        logger.info("DAO - insertStockMaterials called with: " + stockVO);
        try {
            sqlSession.insert("com.mrbean.mappers.StockMaterialsMapper.insertList", stockVO);
            logger.info("DAO - insert successful");
        } catch (Exception e) {
            logger.error("DAO - Error during insert", e);
            throw e;
        }
    }

    @Override
    public int checkDuplicateStock(String rrNo) {
        return sqlSession.selectOne("com.mrbean.mappers.StockMaterialsMapper.checkDuplicateStock", rrNo);
    }

    @Override
    public void updateRegistrationStatus(String rrNo) {
        sqlSession.update("com.mrbean.mappers.StockMaterialsMapper.updateRegistrationStatus", rrNo);
    }
}