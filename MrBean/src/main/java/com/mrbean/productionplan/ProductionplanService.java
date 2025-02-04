package com.mrbean.productionplan;

import java.util.List;
import java.util.Map;

import com.mrbean.enums.WorkOrdersStatus;

public interface ProductionplanService {
	
	// 생산 등록
	public void insertProductionPlan(ProductionPlanVO planVO);
	
	// 생산 목록
	public List<ProductionPlanVO> getPlanList(ProductionPlanVO planVO);
	
	// 생산 목록 삭제

	public void deletePlan(int planId);

	// 생산목록 상태변경
	public void updatePlanStatus(ProductionPlanVO planVO);

	
}//ProductionplanService
