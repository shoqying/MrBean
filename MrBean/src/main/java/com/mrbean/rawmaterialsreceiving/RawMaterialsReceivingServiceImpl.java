package com.mrbean.rawmaterialsreceiving;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RawMaterialsReceivingServiceImpl implements RawMaterialsReceivingService {

    private RawMaterialsReceivingDAO rawMaterialsReceivingDAO;

    // 생성자를 통해 DAO를 주입
    public RawMaterialsReceivingServiceImpl(RawMaterialsReceivingDAO rawMaterialsReceivingDAO) {
        this.rawMaterialsReceivingDAO = rawMaterialsReceivingDAO;
    }

    // 원자재 입고 번호 생성
    @Override
    public String createRawMaterial(RawMaterialsReceivingVO material) {
        // 원자재 입고 번호 생성 및 데이터 삽입
        return rawMaterialsReceivingDAO.creatRawMaterial(material);
    }

    // 원자재 입고 정보 수정
    @Override
    public String updateRawMaterial(RawMaterialsReceivingVO material) {
        // 원자재 입고 정보 수정
        return rawMaterialsReceivingDAO.updateRawMaterial(material);
    }

    // 원자재 입고 정보 삭제
    @Override
    public void deleteRawMaterial(String rrNo) {
        // 원자재 입고 정보 삭제
        rawMaterialsReceivingDAO.deleteRawMaterial(rrNo);
    }

    // 원자재 입고 정보 전체 조회
    @Override
    public List<RawMaterialsReceivingVO> getAllRawMaterials() {
        // 모든 원자재 입고 정보 조회
        return rawMaterialsReceivingDAO.getAllRawMaterials();
    }
    
    // 원자재 입고 등록 (새로 추가된 메서드)
    @Override
    @Transactional
    public void registerRawMaterial(RawMaterialsReceivingVO rrVO) {
        // 원자재 입고 정보를 DB에 저장
        rawMaterialsReceivingDAO.createRawMaterial(rrVO);  // 적절한 DAO 메서드 호출
    }
    

}
