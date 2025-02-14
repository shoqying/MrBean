package com.mrbean.rawmaterialsreceiving;

import java.util.List;

public interface RawMaterialsReceivingDAO {

    // 원자재 입고 정보 등록
    void createRawMaterial(RawMaterialsReceivingVO material);

    // 원자재 입고 정보 수정
    void updateRawMaterial(RawMaterialsReceivingVO material);

    // 원자재 입고 정보 삭제
    void deleteRawMaterial(String rrNo);

    // 원자재 입고 목록 조회
    List<RawMaterialsReceivingVO> getAllRawMaterials();
}
