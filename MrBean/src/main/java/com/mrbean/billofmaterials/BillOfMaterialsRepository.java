package com.mrbean.billofmaterials;

import org.springframework.beans.factory.annotation.Autowired;

public interface BillOfMaterialsRepository {

    public void insertBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO);

    // 마지막 BOM ID 조회
    public String getLastBomId() throws Exception;
}
