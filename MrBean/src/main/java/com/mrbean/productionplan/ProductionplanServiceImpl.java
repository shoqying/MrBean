package com.mrbean.productionplan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductionplanServiceImpl implements ProductionplanService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductionplanServiceImpl.class);
	@Inject
	private ProductionplanDAO pdao;
	
	/**
	 * 생산계획 등록
	 * 
	 */
	
	@Override
	public void insertProductionPlan(ProductionPlanVO planVO) {
		pdao.insertProductionPlan(planVO);
		
	}
	
	/**
	 * 생산계획목록 조회
	 */
	@Override
	public List<ProductionPlanVO> getPlanList(ProductionPlanVO planVO) {
		logger.info("getPlanList() 호출");
		
		List<ProductionPlanVO> result = pdao.createPlanList(planVO);
		return result;
		
	}
	
	/**
	 * 생산계획 목록 삭제
	 * 
	 */
	@Override
	public void deletePlan(int planId) {
		logger.info("deletePlan 호출");
		
		pdao.deleteProductionPlan(planId);
		
	}
	
	/**
	 * 생산계획 상태 변경
	 * 
	 */
	
	@Override
	public void updatePlanStatus(ProductionPlanVO planVO) {

		pdao.updatePlanStatus(planVO);
		
	}


	
	

}//ProductionplanServiceImpl
