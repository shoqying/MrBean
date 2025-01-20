package com.mrbean.finishedproductscontrol;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mrbean.rawmaterialsqualitycontrol.RawMaterialsQualityControlVO;

@Repository
public class FinishedProductsControlDAOImp implements FinishedProductsControlDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(FinishedProductsControlDAOImp.class);
	
	private static final String NAMESPACE = "com.mrbean.finishedproductscontrol.FinishedProductsControlVO.";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<FinishedProductsControlVO> selectFinishedProductsControl() throws Exception {
		
		logger.info("selectFinishedProductsControl() 호출");
		
		return sqlSession.selectList(NAMESPACE + "getFinishedProductsControlList");
	}
	
	

}
