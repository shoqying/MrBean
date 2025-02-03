package com.mrbean.finishedproductscontrol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mrbean.rawmaterialsqualitycontrol.RawMaterialsQualityControlVO;

@Repository
public class FinishedProductsControlDAOImp implements FinishedProductsControlDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(FinishedProductsControlDAOImp.class);
	
	private static final String NAMESPACE = "com.mrbean.mappers.finishedProductsControlMapper.";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<FinishedProductsControlVO> selectFinishedProductsControl() throws Exception {
		
		logger.info("selectFinishedProductsControl() 호출");
		
		return sqlSession.selectList(NAMESPACE + "getFinishedProductsControlList");
	}

	@Override
	public void updateQualityCheck(int fpcBno, String fpcQualityCheck) throws Exception {
		logger.info("updateQualityCheck() 호출");
		
		Map<String, Object> params = new HashMap<>();
	    params.put("fpcBno", fpcBno);
	    params.put("fpcQualityCheck", fpcQualityCheck);
		
		sqlSession.update(NAMESPACE + "updateQualityCheck", params);
		
	}

	@Override
	public void updateStatus(int fpcBno, String fpcStatus) throws Exception {
		logger.info("updateStatus() 호출");
		
		Map<String, Object> params = new HashMap<>();
	    params.put("fpcBno", fpcBno);
	    params.put("fpcStatus", fpcStatus);
		
		sqlSession.update(NAMESPACE + "updateStatus", params);
		
	}

	@Override
	public int deleteFinishedProduct(int rqcBno) throws Exception {
		logger.info("deleteFinishedProduct() 호출");
		
		return sqlSession.delete(NAMESPACE + "deleteFinishedProduct", rqcBno);
	}

	@Override
	public void insertFinishedProductLot() throws Exception {
		logger.info("insertFinishedProductLot() 호출");
		sqlSession.insert(NAMESPACE + "insertFinishedProductLot");
	}
	
	@Override
	public void insertFinishedProductControl() throws Exception {
		logger.info("insertFinishedProductControl() 호출");
		sqlSession.insert(NAMESPACE + "insertFinishedProductControl");
	}
	
	
	

}
