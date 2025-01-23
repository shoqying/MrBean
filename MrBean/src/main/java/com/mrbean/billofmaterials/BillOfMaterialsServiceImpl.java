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
    public void createBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception {
        // 1. DTO → VO 변환 및 VO 생성 시 비즈니스 검증 수행
        BillOfMaterialsVO billOfMaterialsVO = BillOfMaterialsVO.builder()
                .bomId(billOfMaterialsDTO.getBomId())
                .rmCode(billOfMaterialsDTO.getRmCode())
                .bomName(billOfMaterialsDTO.getBomName())
                .bomRatio(billOfMaterialsDTO.getBomRatio())
                .bomDescription(billOfMaterialsDTO.getBomDescription())
                .build();

        // 2. 필요에 따라 비즈니스 로직 처리 (예: 비율 체크)
        validateBomRatio(billOfMaterialsVO.getBomRatio());

        // 3. VO → DTO 변환 후 Mapper 호출로 DB 저장
        BillOfMaterialsDTO updatedDto = BillOfMaterialsDTO.builder()
                .bomId(billOfMaterialsVO.getBomId())
                .rmCode(billOfMaterialsVO.getRmCode())
                .bomName(billOfMaterialsVO.getBomName())
                .bomRatio(billOfMaterialsVO.getBomRatio())
                .bomDescription(billOfMaterialsVO.getBomDescription())
                .build();

        billOfMaterialsRepository.insertBillOfMaterials(updatedDto);
    }

    /**
     * BOM 비율 검증
     */
    private void validateBomRatio(int ratio) throws IllegalArgumentException {
        if (ratio < 0 || ratio > 100) {
            throw new IllegalArgumentException("BOM 비율은 0에서 100 사이여야 합니다.");
        }
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
