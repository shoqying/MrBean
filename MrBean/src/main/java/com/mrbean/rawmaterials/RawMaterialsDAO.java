package com.mrbean.rawmaterials;

import java.util.List;

public interface RawMaterialsDAO {

    // 원자재 등록
    public void insertRawMaterial(RawMaterialsVO rawMaterialsVO) throws Exception;
    
    // 원자재 리스트 조회
    public List<RawMaterialsVO> selectAllRawMaterials() throws Exception;
    
    // 원자재 수정
    public void updateRawMaterial(RawMaterialsVO rawMaterialsVO) throws Exception;
    
    // 원자재 삭제
    public void deleteRawMaterial(String rmCode) throws Exception;
    
}
