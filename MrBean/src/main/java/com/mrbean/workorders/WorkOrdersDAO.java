package com.mrbean.workorders;

import java.util.List;


public interface WorkOrdersDAO {
	
	//작업지시번호 생성
    String createWorkOrderNumber(String date);
	
	//작업지시 등록
    void insertWorkOrders(WorkOrdersVO workVO);
	
	//작업지시목록 조회
    List<WorkOrdersVO> createOrderList(WorkOrdersVO workVO);
	
	//작업지시목록 삭제
    void deleteWorkOrders(int workId);
	
	//작업상태변경 수정
    void updateWorkStatus(WorkOrdersVO workVO);
	

} //WorkOrdersDAO
