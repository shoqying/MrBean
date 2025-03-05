package com.mrbean.rawmaterialsqualitycontrol;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mrbean.lothistory.LotHistoryVO;
import com.mrbean.workorders.WorkOrdersVO;

@Service
public class RawMaterialsQualityControlServiceImp implements RawMaterialsQualityControlService {
	
	private static final Logger logger = LoggerFactory.getLogger(RawMaterialsQualityControlServiceImp.class);
	
	@Inject
	RawMaterialsQualityControlDAO rawMaterialsQualityControlDAO;
	
	

	@Override
	public void processAndInsertRawMaterials(WorkOrdersVO vo) throws Exception {
		logger.info("processAndInsertRawMaterials() 호출");
		logger.info("{}" + vo);
		Map<String, Object> params = rawMaterialsQualityControlDAO.selectRawMaterialsData(vo);
		logger.info("[]" + params);
		rawMaterialsQualityControlDAO.insertRawMaterialsQualityControl(params);
        
		
	}

	@Override
	public List<RawMaterialsQualityControlVO> getRawMaterialsQualityControlList() throws Exception {
		logger.info("getRawMaterialsQualityControlList() 호출");
		
		return rawMaterialsQualityControlDAO.selectRawMaterialsQualityControl();
	}
	
	@Override
    public void updateQualityCheck(RawMaterialsQualityControlVO vo) throws Exception {
    	logger.info("updateQualityCheck() 호출");
    	rawMaterialsQualityControlDAO.updateQualityCheck(vo);
    }

	@Override
    public void updateStatus(RawMaterialsQualityControlVO vo) throws Exception {
    	logger.info("updateStatus() 호출");
    	rawMaterialsQualityControlDAO.updateStatus(vo);
    }

	@Override
	public void deleteRawmaterialsDate(RawMaterialsQualityControlVO vo) throws Exception {
		logger.info("deleteRawmaterialsDate() 호출");
    	rawMaterialsQualityControlDAO.deleteRawmaterialsDate(vo);
		
	}

	@Override
	public int deleteRawMaterial(int rqcBno) throws Exception {
		logger.info("deleteRawMaterial() 호출");
		return rawMaterialsQualityControlDAO.deleteRawMaterial(rqcBno);
	}
    
    
    
    
    
    
    
    
    
}
