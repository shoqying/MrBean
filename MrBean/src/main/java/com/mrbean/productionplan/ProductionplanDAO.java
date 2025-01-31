package com.mrbean.productionplan;

import java.util.List;

public interface ProductionplanDAO {
	
	//생산계획번호 생성
    String createProductionPlanNumber(String date);
	
	//생산계획 등록
    void insertProductionPlan(ProductionPlanVO planVO);
	
	//생산계획목록 조회
    List<ProductionPlanVO> createPlanList(ProductionPlanVO planVO);
	
	//생산계획목록 삭제
    void deleteProductionPlan(int planId);
	
}//ProductionplanDAO
