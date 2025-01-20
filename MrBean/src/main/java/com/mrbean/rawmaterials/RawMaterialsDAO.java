package com.mrbean.rawmaterials;

import java.util.List;

public interface RawMaterialsDAO {

    // 원자재 등록
    public void insertRawMaterials(RawMaterialsVO rawMaterialsVO) throws Exception;
    
    // 원자재 목록 조회
    public List<RawMaterialsVO> selectRawMaterialsList() throws Exception;
    
    // 원자재 코드로 원자재 정보를 조회하는 메서드
    public RawMaterialsVO selectRawMaterialByCode(String rmCode) throws Exception;

    // 원자재 정보를 수정하는 메서드
    public void updateRawMaterial(RawMaterialsVO rawMaterialsVO) throws Exception;
}

