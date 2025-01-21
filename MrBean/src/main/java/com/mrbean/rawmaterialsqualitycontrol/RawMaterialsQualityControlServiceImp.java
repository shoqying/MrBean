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
	RawMaterialsQualityControlDAO rawMaterialsQualityControlDAO;

	@Override
	public List<RawMaterialsQualityControlVO> getRawMaterialsQualityControlList() throws Exception {
		logger.info("getRawMaterialsQualityControlList() 호출");
		
		return rawMaterialsQualityControlDAO.selectRawMaterialsQualityControl();
	}
	
    public void updateQualityCheck(int rqcBno, String rqcQualityCheck) throws Exception {
    	logger.info("updateQualityCheck() 호출");
    	rawMaterialsQualityControlDAO.updateQualityCheck(rqcBno, rqcQualityCheck);
    }

    public void updateStatus(int rqcBno, String rqcStatus) throws Exception {
    	logger.info("updateStatus() 호출");
    	rawMaterialsQualityControlDAO.updateStatus(rqcBno, rqcStatus);
    }
    
    
    
    
    
    
    
    
    
}
