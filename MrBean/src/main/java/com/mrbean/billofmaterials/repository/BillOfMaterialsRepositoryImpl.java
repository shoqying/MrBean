package com.mrbean.billofmaterials.repository;

import com.mrbean.billofmaterials.domain.BillOfMaterialsDTO;
import com.mrbean.billofmaterials.domain.BillOfMaterialsVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        sqlSession.insert(NAMESPACE + "insertBillOfMaterials", billOfMaterialsVO);
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

    @Override
    public BillOfMaterialsDTO selectBomDetails(String bomId) throws Exception{

        return sqlSession.selectOne(NAMESPACE + "selectBomDetails", bomId);
    }

    @Override
    public List<BillOfMaterialsDTO> findAll() {

        return sqlSession.selectList(NAMESPACE + "selectAllBillOfMaterials");
    }

    /**
     * 매퍼와 연결된 업데이트 메서드
     */
    @Override
    public void updateBillOfMaterials(BillOfMaterialsVO billOfMaterialsVO) {
        sqlSession.update(NAMESPACE + "updateBillOfMaterials", billOfMaterialsVO);
    }

    @Override
    public void deleteBillOfMaterials(String bomId) {
        sqlSession.delete(NAMESPACE + "deleteBillOfMaterials", bomId);
    }

}
