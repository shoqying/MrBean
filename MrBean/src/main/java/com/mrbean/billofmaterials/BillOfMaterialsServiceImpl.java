package com.mrbean.billofmaterials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class BillOfMaterialsServiceImpl implements BillOfMaterialsService {

    @Autowired
    private BillOfMaterialsRepository billOfMaterialsRepository;

    @Override
    @Transactional
    public BillOfMaterialsVO createBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception {
        // DTO를 VO로 변환 (Builder 패턴 사용)
        BillOfMaterialsVO billOfMaterialsVO = BillOfMaterialsVO.builder()
                .bomId(generateBomId()) // BOM ID 생성 로직
                .rmCode(billOfMaterialsDTO.getRmCode())
                .bomName(billOfMaterialsDTO.getBomName())
                .bomRatio(billOfMaterialsDTO.getBomRatio())
                .bomDescription(billOfMaterialsDTO.getBomDescription())
                .build();

        // VO를 DB에 저장
//        billOfMaterialsRepository.insertBillOfMaterials(billOfMaterialsVO);

        // 저장된 VO 반환
        return billOfMaterialsVO;
    }

    /**
     * BOM ID를 생성하는 메서드 (예: "BOM1", "BOM2" 형식)
     */
    private String generateBomId() {
//        int nextId = billOfMaterialsRepository.getNextBomId(); // 다음 ID를 가져오는 로직
//        return "BOM" + nextId;
        return null;
    }

}
