package com.mrbean.productionplan;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductionplanServiceImpl implements ProductionplanService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductionplanServiceImpl.class);
	@Inject
	private ProductionplanDAO pdao;
	
	
	
	@Override
	public List<ProductionVO> getPlanList() {
		logger.info("getPlanList() 호출");
		
		return pdao.createPlanList();
	}
	

	
	

}//ProductionplanServiceImpl
