package com.mrbean.workorders;

import java.util.List;


public interface WorkOrdersDAO {
	
	//생산계획번호 생성
	public String createWorkOrderNumber(String date);
	
	//생산계획 등록
	public void insertWorkOrders(WorkOrdersVO workVO);
	
	//생산계획목록 조회
	public List<WorkOrdersVO> createOrderList(WorkOrdersVO workVO);
	
	//생산계획목록 삭제
	public void deleteWorkOrders(int wordId);

} //WorkOrdersDAO
