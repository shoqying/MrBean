package com.mrbean.productionplan;

import java.util.List;

public interface ProductionplanDAO {

	public String createProductionPlanNumber(String date);
	
	public List<ProductionPlanVO> createPlanList();
	
	
}//ProductionplanDAO
