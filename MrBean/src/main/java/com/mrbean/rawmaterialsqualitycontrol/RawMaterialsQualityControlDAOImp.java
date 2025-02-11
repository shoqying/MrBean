package com.mrbean.rawmaterialsqualitycontrol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RawMaterialsQualityControlDAOImp implements RawMaterialsQualityControlDAO {

	private static final Logger logger = LoggerFactory.getLogger(RawMaterialsQualityControlDAOImp.class);
	
	private static final String NAMESPACE = "com.mrbean.mappers.rawMaterialsQualityControlMapper.";
	
	@Inject
	private SqlSession sqlSession;
	
	
	
	

	@Override
	public Map<String, Object> selectRawMaterialsData() throws Exception {
		logger.info("selectRawMaterialsData() 호출");
		return sqlSession.selectOne(NAMESPACE + "selectRawMaterialsData");
	}

	@Override
	public void insertRawMaterialsQualityControl(Map<String, Object> params) throws Exception {
		logger.info("insertRawMaterialsQualityControl() 호출");
		sqlSession.insert(NAMESPACE + "insertRawMaterialsQualityControl", params);
	}

	@Override
	public List<RawMaterialsQualityControlVO> selectRawMaterialsQualityControl() throws Exception {
		logger.info("selectRawMaterialsQualityControl() 호출");
		
		return sqlSession.selectList(NAMESPACE + "getRawMaterialsQualityControlList");
	}

	@Override
	public void updateQualityCheck(RawMaterialsQualityControlVO vo) throws Exception {
		logger.info("updateQualityCheck() 호출");
	    
	    sqlSession.update(NAMESPACE + "updateQualityCheck", vo);

	}

	@Override
	public void updateStatus(RawMaterialsQualityControlVO vo) throws Exception {
		logger.info("updateStatus() 호출");

	    sqlSession.update(NAMESPACE + "updateStatus", vo);
	}

	@Override
	public void deleteRawmaterialsDate(RawMaterialsQualityControlVO vo) throws Exception {
		logger.info("deleteRawmaterialsDate() 호출");

	    sqlSession.update(NAMESPACE + "deleteRawmaterialsDate", vo);
		
	}

	@Override
	public int deleteRawMaterial(int rqcBno) throws Exception {
		logger.info("deleteRawMaterial() 호출");
		
		return sqlSession.delete(NAMESPACE + "deleteRawMaterial", rqcBno);
	}
	
	
	
	
}
