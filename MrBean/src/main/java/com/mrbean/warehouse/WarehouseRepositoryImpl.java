package com.mrbean.warehouse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WarehouseRepositoryImpl implements WarehouseRepository {

    private static final String NAMESPACE = "com.mrbean.Mapper.WarehousesMapper.";
    private final SqlSession sqlSession;

    @Autowired
    public WarehouseRepositoryImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // 창고 등록
    @Override
    public void insertWarehouse(WarehouseVO warehouse) throws Exception {
        sqlSession.insert(NAMESPACE + "insertWarehouse", warehouse);
    }

    @Override
    public boolean checkWarehouseCodeExists(String wCode) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "checkWarehouseCodeExists", wCode);
    }

    @Override
    public WarehouseVO selectWarehouseByCode(String wCode) throws Exception {
        return sqlSession.selectOne(NAMESPACE + "selectWarehouseByCode", wCode);
    }

}
