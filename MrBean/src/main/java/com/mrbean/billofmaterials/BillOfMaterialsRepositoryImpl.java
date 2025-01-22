package com.mrbean.billofmaterials;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BillOfMaterialsRepositoryImpl implements BillOfMaterialsRepository {

    private static final String NAMESPACE = "com.mrbean.Mapper.BillOfMaterialsMapper.";
    private final SqlSession sqlSession;

    @Autowired
    public BillOfMaterialsRepositoryImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void insertBillOfMaterials(BillOfMaterialsVO billOfMaterialsVO) {
        sqlSession.insert(NAMESPACE + "insertWarehouse", billOfMaterialsVO);
    }

    @Override
    public String getLastBomId() throws Exception {
        // 매퍼 XML 또는 인터페이스에서 id="getLastBomId"로 등록된 쿼리를 호출
        String lastBomId = sqlSession.selectOne(NAMESPACE + "getLastBomId");

        // 필요 시 null 처리 로직 추가
        if (lastBomId == null) {
            // 예: 등록된 BOM이 하나도 없다면 "BOM1" 반환
            lastBomId = "BOM1";
        }
        return lastBomId;
    }

}
