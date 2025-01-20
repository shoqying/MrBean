package com.mrbean.rawmaterialsqualitycontrol;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RawMaterialsQualityControlDAOImp implements RawMaterialsQualityControlDAO {

	private static final Logger logger = LoggerFactory.getLogger(RawMaterialsQualityControlDAOImp.class);
	
	private static final String NAMESPACE = "com.mrbean.rawmaterialsqualitycontrol.RawMaterialsQualityControlVO.";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<RawMaterialsQualityControlVO> selectRawMaterialsQualityControl() throws Exception {
		logger.info("selectRawMaterialsQualityControl() 호출");
		
		return sqlSession.selectList(NAMESPACE + "getRawMaterialsQualityControlList");
	}
	
	
}
