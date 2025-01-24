package com.mrbean.billofmaterials;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

public interface BillOfMaterialsRepository {

    public void insertBillOfMaterials(BillOfMaterialsVO billOfMaterialsVO);

    // 마지막 BOM ID 조회
    public String getLastBomId() throws Exception;

    /**
     * 특정 BOM 정보를 조회합니다.
     *
     * @param bomId 조회할 BOM의 ID
     * @return BOM 상세 정보
     */
    BillOfMaterialsVO selectBomDetails(@Param("bomId") String bomId) throws Exception;

    public void updateBillOfMaterials(BillOfMaterialsVO billOfMaterialsVO);
}
