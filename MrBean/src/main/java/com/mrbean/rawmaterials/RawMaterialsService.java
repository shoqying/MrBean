package com.mrbean.rawmaterials;

import java.util.List;

public interface RawMaterialsService {

    // 원자재 등록
    void registerRawMaterial(RawMaterialsVO rawMaterialsVO) throws Exception;
    
    // 원자재 리스트 조회
    List<RawMaterialsVO> getRawMaterialsList() throws Exception;
    
    // 원자재 수정
    void updateRawMaterial(RawMaterialsVO rawMaterialsVO) throws Exception;
    
    // 원자재 삭제
    void deleteRawMaterial(String rmCode) throws Exception;
    
}