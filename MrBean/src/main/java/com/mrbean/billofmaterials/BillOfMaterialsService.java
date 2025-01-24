package com.mrbean.billofmaterials;

import java.util.List;

public interface BillOfMaterialsService {

    public void createBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception;

    public String generateBomId() throws Exception;

    public List<BillOfMaterialsDTO> getAllBoms(String sortKey, String sortOrder) throws Exception;

    public BillOfMaterialsDTO getBomDetails(String bomId) throws Exception;

    // BOM 정보 업데이트
    void updateBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception;

}
