package com.mrbean.warehouse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WarehouseDAOImpl implements WarehouseDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.mrbean.mapper.WarehouseMapper.";

    // 창고 등록
    @Override
    public void insertWarehouse(WarehouseVO warehouse) throws Exception {
        sqlSession.insert(NAMESPACE + "insertWarehouse", warehouse);
    }
}
