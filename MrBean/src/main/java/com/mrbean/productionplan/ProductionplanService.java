package com.mrbean.productionplan;

import java.util.List;
import java.util.Map;

public interface ProductionplanService {
	
	// 생산 등록
    void insertProductionPlan(ProductionPlanVO planVO);
	
	// 생산 목록
    List<ProductionPlanVO> getPlanList(ProductionPlanVO planVO);
	
	// 생산 목록 삭제
	
	void deletePlan(int planId);
	
}//ProductionplanService
