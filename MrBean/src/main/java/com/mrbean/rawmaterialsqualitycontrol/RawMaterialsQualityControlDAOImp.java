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
	public List<RawMaterialsQualityControlVO> selectRawMaterialsQualityControl() throws Exception {
		logger.info("selectRawMaterialsQualityControl() 호출");
		
		return sqlSession.selectList(NAMESPACE + "getRawMaterialsQualityControlList");
	}

	@Override
	public void updateQualityCheck(int rqcBno, String rqcQualityCheck) throws Exception {
		logger.info("updateQualityCheck() 호출");
		
		Map<String, Object> params = new HashMap<>();
	    params.put("rqcBno", rqcBno);
	    params.put("rqcQualityCheck", rqcQualityCheck);
	    
	    sqlSession.update(NAMESPACE + "updateQualityCheck", params);

	}

	@Override
	public void updateStatus(int rqcBno, String rqcStatus) throws Exception {
		logger.info("updateStatus() 호출");
		
		Map<String, Object> params = new HashMap<>();
	    params.put("rqcBno", rqcBno);
	    params.put("rqcStatus", rqcStatus);

	    sqlSession.update(NAMESPACE + "updateStatus", params);
	}

	@Override
	public int deleteRawMaterial(int rqcBno) throws Exception {
		logger.info("deleteRawMaterial() 호출");
		
		return sqlSession.delete(NAMESPACE + "deleteRawMaterial", rqcBno);
	}
	
	
	
	
}
