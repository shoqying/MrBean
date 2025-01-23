package com.mrbean.billofmaterials;

public interface BillOfMaterialsService {

    public void createBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception;

    public String generateBomId() throws Exception;
}
