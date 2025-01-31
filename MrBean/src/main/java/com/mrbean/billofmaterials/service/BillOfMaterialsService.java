package com.mrbean.billofmaterials.service;

import com.mrbean.billofmaterials.domain.BillOfMaterialsDTO;
import java.util.List;

public interface BillOfMaterialsService {

    void createBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception;

    String generateBomId() throws Exception;

    List<BillOfMaterialsDTO> getAllBoms() throws Exception;

    BillOfMaterialsDTO getBomDetails(String bomId) throws Exception;

    // BOM 정보 업데이트
    void updateBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception;

    // BOM 정보 삭제
    void deleteBillOfMaterials(String bomId) throws Exception;
}