package com.mrbean.productionplan;

import java.util.List;

public interface ProductionplanDAO {
	
	
	
	//생산계획번호 생성
	public String createProductionPlanNumber(String date);
	
	//생산계획 등록
	public void insertProductionPlan(ProductionPlanVO planVO);
	
	//생산계획목록 조회
	public List<ProductionPlanVO> createPlanList(ProductionPlanVO planVO);
	
	//생산계획목록 삭제
	public void deleteProductionPlan(int planId);
	
}//ProductionplanDAO
