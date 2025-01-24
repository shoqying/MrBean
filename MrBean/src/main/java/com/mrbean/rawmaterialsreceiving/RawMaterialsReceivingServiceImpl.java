package com.mrbean.rawmaterialsreceiving;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RawMaterialsReceivingServiceImpl implements RawMaterialsReceivingService {

    private RawMaterialsReceivingDAO rawMaterialsReceivingDAO;

    // 생성자를 통해 DAO를 주입
    public RawMaterialsReceivingServiceImpl(RawMaterialsReceivingDAO rawMaterialsReceivingDAO) {
        this.rawMaterialsReceivingDAO = rawMaterialsReceivingDAO;
    }

    // 원자제 입고 번호 생성
    @Override
    public String createRawMaterial(RawMaterialsReceivingVO material) {
        // 원자제 입고 번호 생성 및 데이터 삽입
        return rawMaterialsReceivingDAO.creatRawMaterial(material);
    }

    // 원자제 입고 정보 수정
    @Override
    public String updateRawMaterial(RawMaterialsReceivingVO material) {
        // 원자제 입고 정보 수정
        return rawMaterialsReceivingDAO.updateRawMaterial(material);
    }

    // 원자제 입고 정보 삭제
    @Override
    public void deleteRawMaterial(String rrNo) {
        // 원자제 입고 정보 삭제
        rawMaterialsReceivingDAO.deleteRawMaterial(rrNo);
    }

    // 원자제 입고 정보 전체 조회
    @Override
    public List<RawMaterialsReceivingVO> getAllRawMaterials() {
        // 모든 원자제 입고 정보 조회
        return rawMaterialsReceivingDAO.getAllRawMaterials();
    }
}
