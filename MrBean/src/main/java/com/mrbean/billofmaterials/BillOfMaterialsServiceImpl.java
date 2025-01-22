package com.mrbean.billofmaterials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillOfMaterialsServiceImpl implements BillOfMaterialsService {

    private final BillOfMaterialsRepository billOfMaterialsRepository;

    @Autowired
    public BillOfMaterialsServiceImpl(BillOfMaterialsRepository billOfMaterialsRepository) {
        this.billOfMaterialsRepository = billOfMaterialsRepository;
    }

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
        billOfMaterialsRepository.insertBillOfMaterials(billOfMaterialsVO);

        // 저장된 VO 반환
        return billOfMaterialsVO;
    }

    /**
     * 마지막 BOM ID를 조회한 뒤 숫자 부분을 추출해 +1
     */
    @Override
    public String generateBomId() throws Exception {
        // "BOM3" 등 문자열로 리턴
        String lastBomId = billOfMaterialsRepository.getLastBomId();

        // 1) 만약 null 또는 비어있다면 첫 BOM "BOM1" 시작
        if (lastBomId == null || lastBomId.isBlank()) {
            return "BOM1";
        }

        // 2) 숫자 부분만 추출, 변환
        int numericPart = Integer.parseInt(lastBomId.replaceAll("\\D+", ""));

        // 3) 다음 ID
        int nextId = numericPart + 1;

        return "BOM" + nextId;
    }

}
