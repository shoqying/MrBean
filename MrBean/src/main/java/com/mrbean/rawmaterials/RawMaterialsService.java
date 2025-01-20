package com.mrbean.rawmaterials;

import java.util.List;

public interface RawMaterialsService {

	// 원자재 등록
    public void insertRawMaterials(RawMaterialsVO rawMaterialsVO) throws Exception;

    // 원자재 목록 조회
    public List<RawMaterialsVO> getRawMaterialsList() throws Exception;
    
    // 원자재 코드로 원자재 정보 조회
    public RawMaterialsVO getRawMaterialByCode(String rmCode) throws Exception;

    // 원자재 정보 수정
    public void updateRawMaterials(RawMaterialsVO rawMaterialsVO) throws Exception;
}
