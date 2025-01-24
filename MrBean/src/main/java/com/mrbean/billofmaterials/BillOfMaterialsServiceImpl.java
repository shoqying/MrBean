package com.mrbean.billofmaterials;

import org.apache.ibatis.javassist.NotFoundException;
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

        billOfMaterialsRepository.insertBillOfMaterials(billOfMaterialsVO);
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

    public BillOfMaterialsDTO getBomDetails(String bomId) throws Exception {
        // Repository에서 VO 가져오기
        BillOfMaterialsVO billOfMaterialsVO = billOfMaterialsRepository.selectBomDetails(bomId);
        if (billOfMaterialsVO == null) {
            throw new NotFoundException("해당 BOM 정보를 찾을 수 없습니다.");
        }

        // VO를 DTO로 변환
        return new BillOfMaterialsDTO(
                billOfMaterialsVO.getBomId(),
                billOfMaterialsVO.getBomName(),
                billOfMaterialsVO.getRmCode(),
                billOfMaterialsVO.getBomRatio(),
                billOfMaterialsVO.getBomDescription()
        );
    }

    /**
     * BOM 정보 업데이트 로직
     */
    @Override
    @Transactional
    public void updateBillOfMaterials(BillOfMaterialsDTO billOfMaterialsDTO) throws Exception {
        BillOfMaterialsVO billOfMaterialsVO = BillOfMaterialsVO.builder()
                .bomId(billOfMaterialsDTO.getBomId())
                .rmCode(billOfMaterialsDTO.getRmCode())
                .bomName(billOfMaterialsDTO.getBomName())
                .bomRatio(billOfMaterialsDTO.getBomRatio())
                .bomDescription(billOfMaterialsDTO.getBomDescription())
                .build();

        validateBomRatio(billOfMaterialsVO.getBomRatio());

        billOfMaterialsRepository.updateBillOfMaterials(billOfMaterialsVO);
    }

    /**
     * BOM 비율 검증
     */
    private void validateBomRatio(int ratio) throws IllegalArgumentException {
        if (ratio < 0 || ratio > 100) {
            throw new IllegalArgumentException("BOM 비율은 0에서 100 사이여야 합니다.");
        }
    }
}
