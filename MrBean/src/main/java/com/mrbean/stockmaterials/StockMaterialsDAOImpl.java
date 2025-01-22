package com.mrbean.stockmaterials;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StockMaterialsDAOImpl implements StockMaterialsDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<StockMaterialsVO> getStockMaterials(String sortColumn, String sortDirection, int limit, int offset) {
        Map<String, Object> params = new HashMap<>();
        params.put("sortColumn", sortColumn);
        params.put("sortDirection", sortDirection);
        params.put("limit", limit);
        params.put("offset", offset);
        
        return sqlSession.selectList("com.mrbean.mappers.StockMaterialsMapper.getStockMaterials", params);
    }

    @Override
    public int getTotalCount() {
        return sqlSession.selectOne("com.mrbean.mappers.StockMaterialsMapper.getTotalCount");
    }
    
//    @Override
//    public void insertStockMaterials(StockMaterialsVO stockMaterialsVO) {
//        sqlSession.insert("com.mrbean.mappers.StockMaterialsMapper.insertStockMaterials", stockMaterialsVO);
//    }
}
