package com.mrbean.workorders;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrbean.enums.WorkOrdersStatus;

@Service
public class WorkOrdersServiceImpl implements WorkOrdersService {

    private static final Logger logger = LoggerFactory.getLogger(WorkOrdersServiceImpl.class);
    
    @Inject
    private WorkOrdersDAO wdao;
    
    /**
     * 작업지시 등록
     */
    @Override
    @Transactional
    public void insertWorkOrders(WorkOrdersVO workVO) {
        try {
            // 1. 재고 확인
            if (!checkStockAvailability(workVO)) {
                throw new RuntimeException("필요한 재고가 부족합니다.");
            }
            
            // 2. 작업지시 기본정보 등록
            wdao.insertWorkOrders(workVO);
            int workId = workVO.getWorkId();  // 생성된 작업지시 ID
            logger.info("작업지시 등록 완료. workId: {}", workId);
            
            // 3. 재고 업데이트 및 LOT 이력 기록
            updateStockMaterials(workVO);
            logger.info("재고 업데이트 완료. workId: {}", workId);
            
        } catch (Exception e) {
            logger.error("작업지시 등록 중 오류 발생", e);
            throw new RuntimeException("작업지시 등록 실패: " + e.getMessage(), e);
        }
    }
    /**
     * 작업지시 목록 조회
     */
    @Override
    public List<WorkOrdersVO> getWorkList(WorkOrdersVO workVO) {
        try {
            return wdao.createOrderList(workVO);
        } catch (Exception e) {
            logger.error("작업지시 목록 조회 중 오류 발생", e);
            throw new RuntimeException("작업지시 목록 조회 실패", e);
        }
    }
    
    /**
     * 작업지시 삭제
     */
    @Override
    @Transactional
    public void deleteWork(int workId) {
        try {
            wdao.deleteWorkOrders(workId);
            logger.info("작업지시 삭제 완료. workId: {}", workId);
        } catch (Exception e) {
            logger.error("작업지시 삭제 중 오류 발생. workId: " + workId, e);
            throw new RuntimeException("작업지시 삭제 실패", e);
        }
    }

    /**
     * 작업지시 상태 변경
     */
    @Override
    @Transactional
    public void updateWorkStatus(WorkOrdersVO workVO) {
    	logger.info("&&***********************************************");
    	logger.info("workVO의 값!!!!!!!!!!!!!!!!!!" + workVO);
        try {
            if(workVO.getWorkStatus() == WorkOrdersStatus.IN_PROGRESS) {
                workVO.setWorkStartTime(new Timestamp(System.currentTimeMillis()));
            } else if (workVO.getWorkStatus() == WorkOrdersStatus.COMPLETED) {
                workVO.setWorkEndTime(new Timestamp(System.currentTimeMillis()));
            }
            wdao.updateWorkStatus(workVO);
            logger.info("작업상태 변경 완료. workId: {}, status: {}", workVO.getWorkId(), workVO.getWorkStatus());
        } catch (Exception e) {
            logger.error("작업상태 변경 중 오류 발생", e);
            throw new RuntimeException("작업상태 변경 실패", e);
        }
    }

    @Override
    public Integer getPlanIdByWorkId(int workId) {
        try {
            return wdao.getPlanIdByWorkId(workId);
        } catch (Exception e) {
            logger.error("생산계획 ID 조회 중 오류 발생. workId: " + workId, e);
            throw new RuntimeException("생산계획 ID 조회 실패", e);
        }
    }
    
    /**
     * 재고 확인
     */
    @Override
    public boolean checkStockAvailability(WorkOrdersVO workVO) {
        try {
            // 1. 원자재 코드 및 BOM 정보 조회
            String bomId = wdao.getBomIdByPName(workVO.getPName());
            if (bomId == null) {
                throw new RuntimeException("BOM 정보를 찾을 수 없습니다. 제품명: " + workVO.getPName());
            }
            
            Map<String, Object> bomInfo = wdao.getBomInfoById(bomId);
            if (bomInfo == null) {
                throw new RuntimeException("BOM 정보를 찾을 수 없습니다. bomId: " + bomId);
            }
            
            String rmCode = (String) bomInfo.get("rm_code");
            if (rmCode == null) {
                throw new RuntimeException("원자재 코드를 찾을 수 없습니다.");
            }
            
            // 2. 필요 수량 계산
            int requiredQuantity = workVO.getWorkQuantity() * ((Integer) bomInfo.get("bom_ratio"));
            logger.info("필요 수량: {}", requiredQuantity);
            
            // 3. 재고 목록 조회
            List<Map<String, Object>> stockMaterials = wdao.getStockMaterialsByRmCode(rmCode);
            
            // 4. 총 재고 수량 계산
            int totalAvailable = 0;
            for (Map<String, Object> stock : stockMaterials) {
                totalAvailable += (Integer) stock.get("sm_total");
            }
            logger.info("원자재코드: {}, 가용재고: {}", rmCode, totalAvailable);
            
            return totalAvailable >= requiredQuantity;
            
        } catch (Exception e) {
            logger.error("재고 확인 중 오류 발생", e);
            throw e;
        }
    }
    
    /**
     * 재고 자재 수량 업데이트 (FIFO 방식) 및 LOT 이력 기록
     */
    @Override
    @Transactional
    public void updateStockMaterials(WorkOrdersVO workVO) {
        try {
            // 1. 원자재 코드 및 BOM 정보 조회
            String bomId = wdao.getBomIdByPName(workVO.getPName());
            if (bomId == null) {
                throw new RuntimeException("BOM 정보를 찾을 수 없습니다. 제품명: " + workVO.getPName());
            }
            
            Map<String, Object> bomInfo = wdao.getBomInfoById(bomId);
            if (bomInfo == null) {
                throw new RuntimeException("BOM 정보를 찾을 수 없습니다. bomId: " + bomId);
            }
            
            String rmCode = (String) bomInfo.get("rm_code");
            if (rmCode == null) {
                throw new RuntimeException("원자재 코드를 찾을 수 없습니다.");
            }
            
            // 2. 필요 수량 계산
            int requiredQuantity = workVO.getWorkQuantity() * ((Integer) bomInfo.get("bom_ratio"));
            int remainingQuantity = requiredQuantity;
            
            // 3. 재고 목록 조회 (입고일 오름차순)
            List<Map<String, Object>> stockMaterials = wdao.getStockMaterialsByRmCode(rmCode);
            
            // 4. FIFO 방식으로 재고 차감
            for (Map<String, Object> stock : stockMaterials) {
                if (remainingQuantity <= 0) break;
                
                int smBno = (Integer) stock.get("sm_bno");
                int currentTotal = (Integer) stock.get("sm_total");
                String rmlNo = (String) stock.get("rml_no");
                
                // 차감할 수량 계산
                int quantityToDeduct = Math.min(currentTotal, remainingQuantity);
                int newTotal = currentTotal - quantityToDeduct;
                
                // 재고 업데이트
                Map<String, Object> updateParams = new HashMap<>();
                updateParams.put("smBno", smBno);
                updateParams.put("newTotal", newTotal);
                wdao.updateStockMaterials(updateParams);
                
                // LOT 이력 기록
                Map<String, Object> historyParams = new HashMap<>();
                historyParams.put("workId", workVO.getWorkId());
                historyParams.put("rmlNo", rmlNo);
                historyParams.put("quantityUsed", quantityToDeduct);
                wdao.insertLotHistory(historyParams);
                
                remainingQuantity -= quantityToDeduct;
                
                logger.info("LOT번호: {}, 차감수량: {}, 이력기록완료", rmlNo, quantityToDeduct);
            }
            
            // 5. 재고 부족 체크
            if (remainingQuantity > 0) {
                throw new RuntimeException(String.format("재고가 부족합니다. 부족수량: %d", remainingQuantity));
            }
            
            logger.info("재고 업데이트 완료 - 작업ID: {}, 총사용량: {}", workVO.getWorkId(), requiredQuantity);
            
        } catch (Exception e) {
            logger.error("재고 업데이트 중 오류 발생", e);
            throw new RuntimeException("재고 업데이트 실패: " + e.getMessage(), e);
        }
    }
    
    /**
     * 작업 완료 처리
     */
    @Override
    @Transactional
    public void completeWork(WorkOrdersVO workVO) {
        try {
            // DB에서 작업수량 조회
            int workQuantity = wdao.getWorkQuantityById(workVO.getWorkId());
            
            workVO.setWorkStatus(WorkOrdersStatus.COMPLETED);
            workVO.setWorkEndTime(new Timestamp(System.currentTimeMillis()));
            workVO.setCompletedQuantity(workQuantity);  // 조회한 작업수량으로 설정
            wdao.updateWorkStatus(workVO);
            
            logger.info("작업 완료 처리 완료. workId: {}, completedQuantity: {}", workVO.getWorkId(), workQuantity);
            
        } catch (Exception e) {
            logger.error("작업 완료 처리 중 오류 발생", e);
            throw new RuntimeException("작업 완료 처리 실패", e);
        }
    }

    /**
     * LOT 이력 조회
     */
//    @Override
//    public List<Map<String, Object>> getLotHistory(int workId) {
//        try {
//            return wdao.getLotHistory(workId);
//        } catch (Exception e) {
//            logger.error("LOT 이력 조회 중 오류 발생. workId: " + workId, e);
//            throw new RuntimeException("LOT 이력 조회 실패", e);
//        }
//    }
    
    
    
    @Override
    public int getWorkIdByWorkOrderNo(String workOrderNo) {
        try {
            return wdao.getWorkIdByWorkOrderNo(workOrderNo);
        } catch (Exception e) {
            logger.error("작업 ID 조회 중 오류 발생. workOrderNo: " + workOrderNo, e);
            throw new RuntimeException("작업 ID 조회 실패", e);
        }
    }
    
    
    
}