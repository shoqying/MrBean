package com.mrbean.billofmaterials.service;

import com.mrbean.billofmaterials.domain.BillOfMaterialsDTO;

import java.util.List;

public interface BillOfMaterialsService {

    public void createBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception;

    public String generateBomId() throws Exception;

    public List<BillOfMaterialsDTO> getAllBoms() throws Exception;

    public BillOfMaterialsDTO getBomDetails(String bomId) throws Exception;

    // BOM 정보 업데이트
    void updateBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception;

    // BOM 정보 삭제
    void deleteBillOfMaterials(String bomId) throws Exception;
}
