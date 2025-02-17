package com.mrbean.workorders;

import java.util.List;
import java.util.Map;

/**
 * 작업지시 서비스 인터페이스
 */
public interface WorkOrdersService {
    
    /**
     * 작업지시 등록
     * - 작업지시 기본정보 등록
     * - 재고 차감
     * - LOT 이력 기록
     * @param workVO 등록할 작업지시 정보
     */
    public void insertWorkOrders(WorkOrdersVO workVO);
    
    /**
     * 작업지시 목록 조회
     * @param workVO 조회 조건을 담은 VO
     * @return 작업지시 목록
     */
    public List<WorkOrdersVO> getWorkList(WorkOrdersVO workVO);
    
    /**
     * 작업지시 삭제
     * @param workId 삭제할 작업지시 ID
     */
    public void deleteWork(int workId);
    
    /**
     * 작업지시 상태 변경
     * - 작업상태 업데이트
     * - 시작/종료 시간 기록
     * @param workVO 변경할 작업상태 정보
     */
    public void updateWorkStatus(WorkOrdersVO workVO);
    
    /**
     * 작업지시 ID로 생산계획 ID 조회
     * @param workId 작업지시 ID
     * @return 생산계획 ID
     */
    public Integer getPlanIdByWorkId(int workId);
    
    /**
     * 재고 확인
     * - BOM 정보 조회
     * - 필요 수량 계산
     * - 가용 재고 확인
     * @param workVO 작업지시 정보
     * @return 재고 충분 여부
     */
    public boolean checkStockAvailability(WorkOrdersVO workVO);
    
    /**
     * 재고 자재 수량 업데이트
     * - FIFO 방식으로 재고 차감
     * - LOT 이력 기록
     * @param workVO 작업지시 정보
     */
    public void updateStockMaterials(WorkOrdersVO workVO);
    
    /**
     * 작업 완료 처리
     * - 작업상태 완료로 변경
     * - 완료시간 기록
     * - 완료수량 업데이트
     * @param workVO 작업지시 정보
     */
    public void completeWork(WorkOrdersVO workVO);
    
    /**
     * LOT 이력 조회
     * 특정 작업지시의 LOT 사용 이력 조회
     * @param workId 작업지시 ID
     * @return LOT 이력 목록
     */
    //public List<Map<String, Object>> getLotHistory(int workId);
}