package com.mrbean.finishedproductscontrol;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mrbean.products.ProductsVO;
import com.mrbean.rawmaterialsqualitycontrol.RawMaterialsQualityControlVO;
import com.mrbean.workorders.WorkOrdersVO;


@Service
public class FinishedProductsControlServiceImp implements FinishedProductsControlService {
	
	private static final Logger logger = LoggerFactory.getLogger(FinishedProductsControlServiceImp.class);

	@Inject
	FinishedProductsControlDAO finishedProductsControlDAO;
	
	
	
	
	@Override
	public void processAndInsertFinishedProducts() throws Exception {
		logger.info("processAndInsertFinishedProducts() 호출");
		FinishedProductsControlVO vo = finishedProductsControlDAO.getWorkOrdersNo();
		logger.info("{}" + vo);
		finishedProductsControlDAO.insertFinishedProductLot(vo);
		finishedProductsControlDAO.insertFinishedProductControl();
		
	}

	@Override
	public List<FinishedProductsControlVO> getFinishedProductsControlList() throws Exception {
		logger.info("getFinishedProductsControlList() 호출");
		
		return finishedProductsControlDAO.selectFinishedProductsControl();
	}

	@Override
	public void updateQualityCheck(FinishedProductsControlVO vo) throws Exception {
		logger.info("updateQualityCheck() 호출");
		finishedProductsControlDAO.updateQualityCheck(vo);
		
	}

	@Override
	public void updateStatus(FinishedProductsControlVO vo) throws Exception {
		logger.info("updateStatus() 호출");
		finishedProductsControlDAO.updateStatus(vo);
		
	}

	@Override
	public int deleteFinishedProduct(int fpcBno) throws Exception {
		logger.info("deleteFinishedProduct() 호출");
		return finishedProductsControlDAO.deleteFinishedProduct(fpcBno);
	}
	
	@Override
	public void insertFinishedProductLot(FinishedProductsControlVO vo) throws Exception {
		logger.info("insertFinishedProductLot() 호출");
		finishedProductsControlDAO.insertFinishedProductLot(finishedProductsControlDAO.getWorkOrdersNo());
	
	}

	@Override
	public void deleteFinishedProductLot(RawMaterialsQualityControlVO vo) throws Exception {
		logger.info("deleteFinishedProductLot() 호출");
		finishedProductsControlDAO.deleteFinishedProductLot(vo);
		
	}

	@Override
	public void insertFinishedProductControl(RawMaterialsQualityControlVO vo) throws Exception {
		logger.info("insertFinishedProductControl() 호출");
		finishedProductsControlDAO.insertFinishedProductControl();
		
	}

	@Override
	public FinishedProductsControlVO getWorkOrdersNo() throws Exception {
		logger.info("getWorkOrdersNo() 호출");
		return finishedProductsControlDAO.getWorkOrdersNo();
		
	}

	@Override
	public int deleteFinishedProductControl() throws Exception {
		logger.info("deleteFinishedProductControl");
		FinishedProductsControlVO vo = new FinishedProductsControlVO();
		vo = finishedProductsControlDAO.getWorkOrdersNo();
		finishedProductsControlDAO.insertFinishedProductLot(vo);
		return finishedProductsControlDAO.deleteFinishedProductControl(vo);
		
	}
	
	
	
	
	
	
	
}
