package com.mrbean.workorders;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


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
		wdao.insertWorkOrders(workVO);
		
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

} //WorkOrdersServiceImpl
