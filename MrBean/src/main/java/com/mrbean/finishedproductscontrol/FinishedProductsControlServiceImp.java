package com.mrbean.finishedproductscontrol;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class FinishedProductsControlServiceImp implements FinishedProductsControlService {
	
	private static final Logger logger = LoggerFactory.getLogger(FinishedProductsControlServiceImp.class);

	@Inject
	FinishedProductsControlDAO finishedProductsControlDAO;
	
	@Override
	public List<FinishedProductsControlVO> getFinishedProductsControlList() throws Exception {
		logger.info("getFinishedProductsControlList() 호출");
		
		return finishedProductsControlDAO.selectFinishedProductsControl();
	}

	@Override
	public void updateQualityCheck(int fpcBno, String fpcQualityCheck) throws Exception {
		logger.info("updateQualityCheck() 호출");
		finishedProductsControlDAO.updateQualityCheck(fpcBno, fpcQualityCheck);
		
	}

	@Override
	public void updateStatus(int fpcBno, String fpcStatus) throws Exception {
		logger.info("updateStatus() 호출");
		finishedProductsControlDAO.updateStatus(fpcBno, fpcStatus);
		
	}

	@Override
	public int deleteFinishedProduct(int rqcBno) throws Exception {
		logger.info("deleteFinishedProduct() 호출");
		return finishedProductsControlDAO.deleteFinishedProduct(rqcBno);
	}
	
	
	
	

}
