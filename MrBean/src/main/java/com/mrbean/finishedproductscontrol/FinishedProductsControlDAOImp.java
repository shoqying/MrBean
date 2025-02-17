package com.mrbean.finishedproductscontrol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mrbean.enums.QualityControlStatus;
import com.mrbean.rawmaterialsqualitycontrol.RawMaterialsQualityControlVO;
import com.mrbean.workorders.WorkOrdersVO;

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
	public void updateQualityCheck(FinishedProductsControlVO vo) throws Exception {
		logger.info("updateQualityCheck() 호출");
		
		sqlSession.update(NAMESPACE + "updateQualityCheck", vo);
		
	}

	@Override
	public void updateStatus(FinishedProductsControlVO vo) throws Exception {
		logger.info("updateStatus() 호출");
		
		sqlSession.update(NAMESPACE + "updateStatus", vo);
		
	}

	@Override
	public int deleteFinishedProduct(int fpcBno) throws Exception {
		logger.info("deleteFinishedProduct() 호출");
		
		return sqlSession.delete(NAMESPACE + "deleteFinishedProduct", fpcBno);
	}

	@Override
	public void insertFinishedProductLot(FinishedProductsControlVO vo) throws Exception {
		logger.info("insertFinishedProductLot() 호출");		
		sqlSession.insert(NAMESPACE + "insertFinishedProductLot", vo);
	}
	
	@Override
	public void deleteFinishedProductLot(RawMaterialsQualityControlVO vo) throws Exception {
		logger.info("deleteFinishedProductLot() 호출");
		sqlSession.update(NAMESPACE + "deleteFinishedProductLot", vo);
		
	}

	@Override
	public void insertFinishedProductControl() throws Exception {
		logger.info("insertFinishedProductControl() 호출");
		sqlSession.insert(NAMESPACE + "insertFinishedProductControl");
	}

	@Override
	public FinishedProductsControlVO getWorkOrdersNo() throws Exception {
		logger.info("getWorkOrdersNo() 호출");
		return sqlSession.selectOne(NAMESPACE + "getWorkOrdersNo");
		
	}

	@Override
	public int deleteFinishedProductControl(FinishedProductsControlVO vo) throws Exception {
		logger.info("deleteFinishedProductControl() 호출");
		return sqlSession.delete(NAMESPACE + "deleteFinishedProductControl", vo);
		
	}
	
	

}
