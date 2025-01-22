package com.mrbean.billofmaterials;

public interface BillOfMaterialsService {

    public BillOfMaterialsVO createBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception;

    public String generateBomId() throws Exception;
}
