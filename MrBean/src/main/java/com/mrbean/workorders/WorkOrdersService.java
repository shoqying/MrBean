package com.mrbean.workorders;

import java.util.List;



public interface WorkOrdersService {
	
	// 생산 등록
	public void insertWorkOrders(WorkOrdersVO workVO);
	
	// 생산 목록
	public List<WorkOrdersVO> getWorkList(WorkOrdersVO workVO);
	
	// 생산 목록 삭제
	
	public void deleteWork(int workId);
	
} //WorkOrdersService


