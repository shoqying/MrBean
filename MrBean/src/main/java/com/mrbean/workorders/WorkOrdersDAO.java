package com.mrbean.workorders;

import java.util.List;
import java.util.Map;

public interface WorkOrdersDAO {
    
    /**
     * 작업지시번호 생성
     */
    public String createWorkOrderNumber(String date);
    
    /**
     * 작업지시 등록
     */
    public void insertWorkOrders(WorkOrdersVO workVO);
    
    /**
     * 작업지시 목록 조회
     */
    public List<WorkOrdersVO> createOrderList(WorkOrdersVO workVO);
    
    /**
     * 작업지시 삭제
     */
    public void deleteWorkOrders(int workId);
    
    /**
     * 작업상태 변경
     */
    public void updateWorkStatus(WorkOrdersVO workVO);
    
    /**
     * 생산계획 ID로 작업목록 조회
     */
    public List<WorkOrdersVO> findByPlanId(int planId);
    
    /**
     * 작업지시 ID로 생산계획 ID 조회
     */
    public Integer getPlanIdByWorkId(int workId);
    
    /**
     * 제품명으로 BOM ID 조회
     */
    public String getBomIdByPName(String pName);
    
    /**
     * BOM ID로 BOM 정보 조회
     */
    public Map<String, Object> getBomInfoById(String bomId);
    
    /**
     * 작업지시 ID로 원자재 코드 조회
     */
    public String getRmCodeFromWorkOrder(int workId);
    
    /**
     * 원자재 코드로 재고 현황 조회
     */
    public List<Map<String, Object>> getStockMaterialsByRmCode(String rmCode);
    
    /**
     * 재고 수량 업데이트
     */
    public void updateStockMaterials(Map<String, Object> params);
    
    /**
     * LOT 이력 등록
     */
    public void insertLotHistory(Map<String, Object> params);
    /**
     * workno로 id 찾기..ㅠㅠ
     */
    public int getWorkIdByWorkOrderNo(String workOrderNo);
    
    /**
     * 아이디에 해당하는 db 조회
     * 
     */
    /**
     * workId에 해당하는 작업수량 조회
     */
    public int getWorkQuantityById(int workId);
}