package com.mrbean.productionplan;

import java.util.List;
import java.util.Map;

public interface ProductionplanService {
	
	// 생산 등록
	public void insertProductionPlan(ProductionPlanVO planVO);
	
	// 생산 목록
	public List<ProductionPlanVO> getPlanList(ProductionPlanVO planVO);
	
	
}//ProductionplanService
