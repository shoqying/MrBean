package com.mrbean.rawmaterialsreceiving;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RawMaterialsReceivingServiceImpl implements RawMaterialsReceivingService {

    private static final Logger logger = LoggerFactory.getLogger(RawMaterialsReceivingServiceImpl.class);

    @Inject
    private RawMaterialsReceivingDAO rawMaterialsReceivingDAO;

    // 원자재 입고 등록
    @Override
    public void registerRawMaterial(RawMaterialsReceivingVO rawMaterial) throws Exception {
        try {
            logger.info("원자재 입고 등록 시작: " + rawMaterial);
            rawMaterialsReceivingDAO.createRawMaterial(rawMaterial);
            logger.info("원자재 입고 등록 성공: " + rawMaterial);
        } catch (Exception e) {
            logger.error("원자재 입고 등록 실패: " + rawMaterial, e);
            throw new Exception("원자재 입고 등록 중 오류가 발생했습니다.", e);
        }
    }

    // 원자재 입고 번호 생성
    @Override
    public String generateLotNumber() throws Exception {
        try {
            logger.info("로트 번호 생성 시작");
            // DB에서 오늘 날짜 기준 다음 번호를 생성한 결과를 받아옴
            String nextLotNo = rawMaterialsReceivingDAO.selectNextLotNumber();
            logger.info("생성된 로트 번호: " + nextLotNo);
            return nextLotNo;
        } catch (Exception e) {
            logger.error("로트 번호 생성 실패", e);
            throw new Exception("로트 번호 생성 중 오류가 발생했습니다.", e);
        }
    }

    // 로트 번호 rawmaterials_lot 테이블에 저장
    @Override
    public void saveLotNumberToRawMaterialsLot(String lotNo) throws Exception {
        try {
            logger.info("로트 번호 저장 시작: " + lotNo);
            rawMaterialsReceivingDAO.insertLotNumber(lotNo);
            logger.info("로트 번호 저장 성공: " + lotNo);
        } catch (Exception e) {
            logger.error("로트 번호 저장 실패: " + lotNo, e);
            throw new Exception("로트 번호 저장 중 오류가 발생했습니다.", e);
        }
    }
    
    // 원자재 입고 리스트 조회 (페이징, 정렬, 검색 포함)
    @Override
    public List<RawMaterialsReceivingVO> getRawMaterialsReceivingList(int page, String sortColumn, String sortOrder, String searchKeyword) throws Exception {
        try {
            int pageSize = 20;
            int offset = (page - 1) * pageSize;
            Map<String, Object> params = new HashMap<>();
            params.put("offset", offset);
            params.put("limit", pageSize);
            params.put("sortColumn", sortColumn);
            params.put("sortOrder", sortOrder);
            params.put("searchKeyword", searchKeyword);
            logger.info("원자재 입고 리스트 조회 시작, params: " + params);
            List<RawMaterialsReceivingVO> list = rawMaterialsReceivingDAO.selectRawMaterialsReceivingList(params);
            logger.info("원자재 입고 리스트 조회 성공, 리스트 크기: " + list.size());
            return list;
        } catch (Exception e) {
            logger.error("원자재 입고 리스트 조회 실패", e);
            throw new Exception("원자재 입고 리스트 조회 중 오류가 발생했습니다.", e);
        }
    }

    // 원자재 입고 수정
    @Override
    public void updateRawMaterialsReceiving(RawMaterialsReceivingVO rawMaterial) throws Exception {
        try {
            logger.info("원자재 입고 수정 시작: " + rawMaterial);
            rawMaterialsReceivingDAO.updateRawMaterialReceiving(rawMaterial);
            logger.info("원자재 입고 수정 성공: " + rawMaterial);
        } catch (Exception e) {
            logger.error("원자재 입고 수정 실패: " + rawMaterial, e);
            throw new Exception("원자재 입고 수정 중 오류가 발생했습니다.", e);
        }
    }
    
    // 원자재 입고 삭제
    @Override
    public void deleteRawMaterialReceiving(int rrNo) throws Exception {
        try {
            logger.info("원자재 입고 삭제 시작: rrNo = " + rrNo);
            rawMaterialsReceivingDAO.deleteRawMaterialReceiving(rrNo);
            logger.info("원자재 입고 삭제 성공: rrNo = " + rrNo);
        } catch (Exception e) {
            logger.error("원자재 입고 삭제 실패: rrNo = " + rrNo, e);
            throw new Exception("원자재 입고 삭제 중 오류가 발생했습니다.", e);
        }
    }
}

