package com.mrbean.billofmaterials.service;

import com.mrbean.billofmaterials.domain.BillOfMaterialsDTO;
import java.util.List;

public interface BillOfMaterialsService {

    // BOM 정보 생성
    void createBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception;

    // BOM 이름 중복 체크
    boolean isBomNameExist(String bomName) throws Exception;

    // BOM ID 생성
    String generateBomId() throws Exception;

    // 모든 BOM 정보 조회
    List<BillOfMaterialsDTO> getAllBoms() throws Exception;

    // BOM 상세 정보 조회
    BillOfMaterialsDTO getBomDetails(String bomId) throws Exception;

    // BOM 정보 업데이트
    void updateBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception;

    // BOM 정보 삭제
    void deleteBillOfMaterials(String bomId) throws Exception;
}