package com.mrbean.workorders;

import java.util.List;



public interface WorkOrdersService {
	
	// 작업지시 등록
    public void insertWorkOrders(WorkOrdersVO workVO);
	
	// 작업지시 목록
    public List<WorkOrdersVO> getWorkList(WorkOrdersVO workVO);
	
	// 작업지시 목록 삭제
    public void deleteWork(int workId);
	
	// 작업지시 상태변경
    public void updateWorkStatus(WorkOrdersVO workVO);
    
    // id 조회
    public Integer getPlanIdByWorkId(int workId);
	
} //WorkOrdersService

