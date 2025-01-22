package com.mrbean.stockproducts;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StockProductsDAOImpl implements StockProductsDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.mrbean.mappers.StockProductsMapper";

    @Override
    public List<StockProductsVO> getStockProducts(String sortColumn, String sortDirection, int limit, int offset) {
        Map<String, Object> params = new HashMap<>();
        params.put("sortColumn", sortColumn);
        params.put("sortDirection", sortDirection);
        params.put("limit", limit);
        params.put("offset", offset);

        // SQL 호출
        return sqlSession.selectList(NAMESPACE + ".getStockProducts", params);
    }

    @Override
    public int getTotalCount() {
        // SQL 호출
        return sqlSession.selectOne(NAMESPACE + ".getTotalCount");
    }
}
