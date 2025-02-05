package com.mrbean.workorders;

import java.util.List;


public interface WorkOrdersDAO {
	
	//작업지시번호 생성
	public String createWorkOrderNumber(String date);
	
	//작업지시 등록
	public void insertWorkOrders(WorkOrdersVO workVO);
	
	//작업지시목록 조회
	public List<WorkOrdersVO> createOrderList(WorkOrdersVO workVO);
	
	//작업지시목록 삭제
	public void deleteWorkOrders(int workId);
	
	//작업상태변경 수정
	public void updateWorkStatus(WorkOrdersVO workVO);
	
    //생산계획 ID 로 작업목록 조회
   	public List<WorkOrdersVO> findByPlanId(int planId);
   	
    // id 조회
    public Integer getPlanIdByWorkId(int workId);
    
    
    
} //WorkOrdersDAO
