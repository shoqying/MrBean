package com.mrbean.stockproducts;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mrbean.finishedproductscontrol.FinishedProductsControlVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StockProductsDAOImpl implements StockProductsDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.mrbean.mapper.StockProductsMapper";

    @Override
    public List<StockProductsVO> selectStockProducts(String sortColumn, String sortDirection, int limit, int offset) {
        Map<String, Object> params = new HashMap<>();
        params.put("sortColumn", sortColumn);
        params.put("sortDirection", sortDirection);
        params.put("limit", limit);
        params.put("offset", offset);
        
        // 수정된 부분: NAMESPACE와 쿼리문을 올바르게 결합하여 사용
        return sqlSession.selectList(NAMESPACE + ".selectStockProducts", params);
    }

    @Override
    public int getTotalCount() {
        return sqlSession.selectOne(NAMESPACE + ".getTotalCount");
    }

	@Override
	public void insertStockProducts(FinishedProductsControlVO vo) {
		sqlSession.insert(NAMESPACE + ".insertStockProducts", vo);
		
	}
    
    
}
