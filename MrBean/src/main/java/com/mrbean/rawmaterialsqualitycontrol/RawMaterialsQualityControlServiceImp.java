package com.mrbean.rawmaterialsqualitycontrol;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RawMaterialsQualityControlServiceImp implements RawMaterialsQualityControlService {
	
	private static final Logger logger = LoggerFactory.getLogger(RawMaterialsQualityControlServiceImp.class);
	
	@Inject
	RawMaterialsQualityControlDAO RawMaterialsQualityControlDAO;

	@Override
	public List<RawMaterialsQualityControlVO> getRawMaterialsQualityControlList() throws Exception {
		logger.info("getRawMaterialsQualityControlList() 호출");
		
		return RawMaterialsQualityControlDAO.selectRawMaterialsQualityControl();
	}
	
	

}
