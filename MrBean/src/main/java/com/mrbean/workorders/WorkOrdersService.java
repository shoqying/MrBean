package com.mrbean.workorders;

import java.util.List;



public interface WorkOrdersService {
	
	// 작업지시 등록
    void insertWorkOrders(WorkOrdersVO workVO);
	
	// 작업지시 목록
    List<WorkOrdersVO> getWorkList(WorkOrdersVO workVO);
	
	// 작업지시 목록 삭제
    void deleteWork(int workId);
	
	// 작업지시 상태변경
    void updateWorkStatus(WorkOrdersVO workVO);
	
} //WorkOrdersService 

