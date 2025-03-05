package com.mrbean.rawmaterialsreceiving;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RawMaterialsReceivingDAOImpl implements RawMaterialsReceivingDAO {

    private static final Logger logger = LoggerFactory.getLogger(RawMaterialsReceivingDAOImpl.class);
    private static final String NAMESPACE = "com.mrbean.mappers.rawMaterialsReceivingMapper.";

    @Inject
    private SqlSession sqlSession;

    // 원자재 입고 등록
    @Override
    public void createRawMaterial(RawMaterialsReceivingVO rawMaterial) throws Exception {
        try {
            sqlSession.insert(NAMESPACE + "createRawMaterial", rawMaterial);
            logger.info("원자재 입고 등록 성공: " + rawMaterial);
        } catch (Exception e) {
            logger.error("원자재 입고 등록 중 오류 발생", e);
            throw new Exception("원자재 입고 등록 중 오류가 발생했습니다.", e);
        }
    }

    // 로트 번호 자동 생성
    @Override
    public String selectNextLotNumber() throws Exception {
        try {
            String lotNo = sqlSession.selectOne(NAMESPACE + "selectNextLotNumber");
            logger.info("생성된 로트 번호: " + lotNo);
            return lotNo;
        } catch (Exception e) {
            logger.error("로트 번호 생성 실패", e);
            throw new Exception("로트 번호 생성 중 오류가 발생했습니다.", e);
        }
    }

    // 로트 번호 중복 체크
    @Override
    public boolean checkDuplicateLotNumber(String lotNoPrefix) throws Exception {
        try {
            Integer count = sqlSession.selectOne(NAMESPACE + "checkDuplicateLotNumber", lotNoPrefix);
            logger.info("로트 번호 중복 체크 완료: " + lotNoPrefix + ", 중복 개수: " + count);
            return count > 0;
        } catch (Exception e) {
            logger.error("로트 번호 중복 체크 실패", e);
            throw new Exception("로트 번호 중복 체크 중 오류가 발생했습니다.", e);
        }
    }

    // 로트 번호 저장
    @Override
    public void insertLotNumber(String lotNo) throws Exception {
        try {
            sqlSession.insert(NAMESPACE + "insertLotNumber", lotNo);
            logger.info("로트 번호 저장 성공: " + lotNo);
        } catch (Exception e) {
            logger.error("로트 번호 저장 실패", e);
            throw new Exception("로트 번호 저장 중 오류가 발생했습니다.", e);
        }
    }
    
    // 원자재 입고 리스트 조회
    @Override
    public List<RawMaterialsReceivingVO> selectRawMaterialsReceivingList(Map<String, Object> params) throws Exception {
        try {
            List<RawMaterialsReceivingVO> list = sqlSession.selectList(NAMESPACE + "selectRawMaterialsReceivingList", params);
            logger.info("원자재 입고 리스트 조회 성공, 리스트 크기: " + list.size());
            return list;
        } catch (Exception e) {
            logger.error("원자재 입고 리스트 조회 실패", e);
            throw new Exception("원자재 입고 리스트 조회 중 오류가 발생했습니다.", e);
        }
    }

    // 원자재 입고 수정
    @Override
    public void updateRawMaterialReceiving(RawMaterialsReceivingVO rawMaterial) throws Exception {
        try {
            sqlSession.update(NAMESPACE + "updateRawMaterialReceiving", rawMaterial);
            logger.info("원자재 입고 수정 성공: " + rawMaterial);
        } catch (Exception e) {
            logger.error("원자재 입고 수정 실패", e);
            throw new Exception("원자재 입고 수정 중 오류가 발생했습니다.", e);
        }
    }

    // 원자재 입고 삭제
    @Override
    public void deleteRawMaterialReceiving(int rrNo) throws Exception {
        try {
            sqlSession.delete(NAMESPACE + "deleteRawMaterialReceiving", rrNo);
            logger.info("원자재 입고 삭제 성공: rrNo = " + rrNo);
        } catch (Exception e) {
            logger.error("원자재 입고 삭제 실패: rrNo = " + rrNo, e);
            throw new Exception("원자재 입고 삭제 중 오류가 발생했습니다.", e);
        }
    }

}
