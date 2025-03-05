package com.mrbean.rawmaterials;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RawMaterialsDAOImpl implements RawMaterialsDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(RawMaterialsDAOImpl.class);
	
	@Inject
    private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.mrbean.rawmaterials.RawMaterialsMapper";
	
	
	// 원자재 등록
	@Override
	public void insertRawMaterial(RawMaterialsVO rawMaterialsVO) throws Exception {
		logger.info("원자재 등록: {}", rawMaterialsVO);
        sqlSession.insert(NAMESPACE + ".insertRawMaterial", rawMaterialsVO);
	}
		
	// 원자재 목록 조회
    @Override
    public List<RawMaterialsVO> selectAllRawMaterials() {
        logger.info("원자재 목록 조회");
        return sqlSession.selectList(NAMESPACE + ".selectAllRawMaterials");
    }
    
    // 원자재 수정
    @Override
    public void updateRawMaterial(RawMaterialsVO rawMaterialsVO) {
        logger.info("원자재 수정 요청: {}", rawMaterialsVO);
        sqlSession.update(NAMESPACE + ".updateRawMaterial", rawMaterialsVO);
    }
    
    // 원자재 삭제
    @Override
    public void deleteRawMaterial(String rmCode) throws Exception {
        sqlSession.delete(NAMESPACE + ".deleteRawMaterial", rmCode);
    }
	
}