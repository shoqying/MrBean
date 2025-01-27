package com.mrbean.rawmaterialsreceiving;

import java.util.List;

public interface RawMaterialsReceivingDAO {

    // 원자제 입고 번호 생성
	public String creatRawMaterial(RawMaterialsReceivingVO material);

    // 원자제 입고 정보 수정
    public String updateRawMaterial(RawMaterialsReceivingVO material);

    // 원자제 입고 정보 삭제
    public void deleteRawMaterial(String rrNo);

    // 원자제 입고 정보 전체 조회
    List<RawMaterialsReceivingVO> getAllRawMaterials();
}
