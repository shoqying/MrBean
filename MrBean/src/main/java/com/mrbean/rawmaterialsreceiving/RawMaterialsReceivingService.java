package com.mrbean.rawmaterialsreceiving;

import java.util.List;

public interface RawMaterialsReceivingService {

    // 원자제 입고 번호 생성
    String createRawMaterial(RawMaterialsReceivingVO material);

    // 원자제 입고 정보 수정
    String updateRawMaterial(RawMaterialsReceivingVO material);

    // 원자제 입고 정보 삭제
    void deleteRawMaterial(String rrNo);

    // 원자제 입고 정보 전체 조회
    List<RawMaterialsReceivingVO> getAllRawMaterials();
}
