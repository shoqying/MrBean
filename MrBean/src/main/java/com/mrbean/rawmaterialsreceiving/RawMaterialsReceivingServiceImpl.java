package com.mrbean.rawmaterialsreceiving;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RawMaterialsReceivingServiceImpl implements RawMaterialsReceivingService {
	
	

    @Autowired
    private RawMaterialsReceivingDAO rawMaterialsReceivingDAO;

    // 원자재 입고 번호 생성
    @Override
    public void createRawMaterial(RawMaterialsReceivingVO material) {
        rawMaterialsReceivingDAO.creatRawMaterial(material);
    }

    // 원자재 입고 정보 수정
    @Override
    public String updateRawMaterial(RawMaterialsReceivingVO material) {
        return rawMaterialsReceivingDAO.updateRawMaterial(material);
    }

    // 원자재 입고 정보 삭제
    @Override
    public void deleteRawMaterial(String rrNo) {
        rawMaterialsReceivingDAO.deleteRawMaterial(rrNo);
    }

    // 원자재 입고 정보 전체 조회
    @Override
    public List<RawMaterialsReceivingVO> getAllRawMaterials() {
        return rawMaterialsReceivingDAO.getAllRawMaterials();
    }

    // 원자재 입고 등록 (구현)
    @Override
    public void registerRawMaterial(RawMaterialsReceivingVO rawMaterial) {
        try {
            rawMaterialsReceivingDAO.registerRawMaterial(rawMaterial);
        } catch (Exception e) {
            // 예외 처리
            throw new RuntimeException("원자재 입고 등록 중 오류 발생", e);
        }
    }
}
