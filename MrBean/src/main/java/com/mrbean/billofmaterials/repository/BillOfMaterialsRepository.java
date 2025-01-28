package com.mrbean.billofmaterials.repository;

import com.mrbean.billofmaterials.domain.BillOfMaterialsDTO;
import com.mrbean.billofmaterials.domain.BillOfMaterialsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillOfMaterialsRepository {

    void insertBillOfMaterials(BillOfMaterialsVO billOfMaterialsVO);

    // 마지막 BOM ID 조회
    String getLastBomId() throws Exception;

    /**
     * 특정 BOM 정보를 조회합니다.
     *
     * @param bomId 조회할 BOM의 ID
     * @return BOM 상세 정보
     */
    BillOfMaterialsDTO selectBomDetails(@Param("bomId") String bomId) throws Exception;

    List<BillOfMaterialsDTO> findAll();

    void updateBillOfMaterials(BillOfMaterialsVO billOfMaterialsVO);

    void deleteBillOfMaterials(String bomId);
}
