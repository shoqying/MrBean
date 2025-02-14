package com.mrbean.rawmaterialsreceiving;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class RawMaterialsReceivingDAOImpl implements RawMaterialsReceivingDAO {

    private static final String NAMESPACE = "com.mrbean.mappers.rawMaterialsReceivingMapper.";

    @Inject
    private SqlSession sqlSession;

    @Override
    public void createRawMaterial(RawMaterialsReceivingVO material) {
        sqlSession.insert(NAMESPACE + "createRawMaterial", material);
    }

    @Override
    public void updateRawMaterial(RawMaterialsReceivingVO material) {
        sqlSession.update(NAMESPACE + "updateRawMaterial", material);
    }

    @Override
    public void deleteRawMaterial(String rrNo) {
        sqlSession.delete(NAMESPACE + "deleteRawMaterial", rrNo);
    }

    @Override
    public List<RawMaterialsReceivingVO> getAllRawMaterials() {
        return sqlSession.selectList(NAMESPACE + "getAllRawMaterials");
    }
}
