package com.mrbean.workorders;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mrbean.enums.WorkOrdersStatus;


@Service
public class WorkOrdersServiceImpl implements WorkOrdersService {

	private static final Logger logger = LoggerFactory.getLogger(WorkOrdersServiceImpl.class);
	@Inject
	private WorkOrdersDAO wdao;
	
	/**
	 * 작업지시 등록
	 * 
	 */

	@Override
    public void insertWorkOrders(WorkOrdersVO workVO) {
        // 1. 작업지시 등록
        wdao.insertWorkOrders(workVO);
        
        // 2. 재고 업데이트
        updateStockTotal(workVO);
    }
	/**
	 * 
	 * 재고 업데이트
	 */
	
	
    @Override
    public void updateStockTotal(WorkOrdersVO workVO) {
        try {
            // 1. products 테이블에서 pName으로 bom_id 조회
            String bomId = wdao.getBomIdByPName(workVO.getPName());
            
            if (bomId == null) {
                logger.error("BOM ID not found for product: " + workVO.getPName());
                return;
            }
            
            // 2. bill_of_materials 테이블에서 bom_id로 정보 조회
            Map<String, Object> bomInfo = wdao.getBomInfoById(bomId);
            
            if (bomInfo == null) {
                logger.error("BOM information not found for BOM ID: " + bomId);
                return;
            }
            
            Integer bomRatio = (Integer) bomInfo.get("bom_ratio");
            String rmCode = (String) bomInfo.get("rm_code");
            
            // 3. 차감할 수량 계산
            int deductAmount = workVO.getWorkQuantity() * bomRatio;
            
            // 4. stock_total 테이블 업데이트
            Map<String, Object> params = new HashMap<>();
            params.put("rmCode", rmCode);
            params.put("deductAmount", deductAmount);
            
            int updatedRows = wdao.updateStockTotal(params);
            
            if (updatedRows == 0) {
                logger.error("Failed to update stock_total for rm_code: " + rmCode);
            }
            
        } catch (Exception e) {
            logger.error("Error updating stock_total", e);
            throw new RuntimeException("Failed to update stock total", e);
        }
    }
	
	
	/**
	 * 작업지시목록 조회
	 * 
	 */

	@Override
	public List<WorkOrdersVO> getWorkList(WorkOrdersVO workVO) {
		
		List<WorkOrdersVO> result = wdao.createOrderList(workVO);
		return result;
	}
	
	/**
	 * 작업지시목록 삭제
	 * 
	 */
	
	@Override
	public void deleteWork(int workId) {
		
		wdao.deleteWorkOrders(workId);
		
	}

	/**
	 * 
	 * 작업지시 상태 변경 
	 * 
	 */
	
	@Override
	public void updateWorkStatus(WorkOrdersVO workVO) {
		//workVO.setWorkUpdatedAt(new Date(System.currentTimeMillis()));
		
	/*	if(workVO.getWorkStatus() == WorkOrdersStatus.IN_PROGRESS) {
		   workVO.setWorkStartTime(new Timestamp(System.currentTimeMillis()));
		} else if (workVO.getWorkStatus() == WorkOrdersStatus.COMPLETED) {
	        workVO.setWorkEndTime(new Timestamp(System.currentTimeMillis()));
	}*/
		wdao.updateWorkStatus(workVO);
	}

	@Override
	public Integer getPlanIdByWorkId(int workId) {
		return wdao.getPlanIdByWorkId(workId);
	}
	
	
	

} //WorkOrdersServiceImpl
