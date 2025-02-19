package com.mrbean.billofmaterials.service;

import com.mrbean.billofmaterials.domain.BomValidator;
import com.mrbean.billofmaterials.repository.BillOfMaterialsRepository;
import com.mrbean.billofmaterials.domain.BillOfMaterialsDTO;
import com.mrbean.billofmaterials.domain.BillOfMaterialsVO;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BillOfMaterialsServiceImpl implements BillOfMaterialsService {

    private final BillOfMaterialsRepository billOfMaterialsRepository;
    private final BomValidator bomValidator;

    @Autowired
    public BillOfMaterialsServiceImpl(BillOfMaterialsRepository billOfMaterialsRepository, BomValidator bomValidator) {
        this.billOfMaterialsRepository = billOfMaterialsRepository;
        this.bomValidator = bomValidator;
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
        bomValidator.validateRatio(billOfMaterialsVO.getBomRatio());

        // 3. 저장
        billOfMaterialsRepository.insertBillOfMaterials(billOfMaterialsVO);
    }

    @Override
    public boolean isBomNameExist(String bomName) throws Exception {
        return billOfMaterialsRepository.checkBillOfMaterialsNameExists(bomName);
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

    @Override
    public List<BillOfMaterialsDTO> getAllBoms() {
        return billOfMaterialsRepository.findAll();
    }


    public BillOfMaterialsDTO getBomDetails(String bomId) throws Exception {
        BillOfMaterialsDTO billOfMaterialsDTO = billOfMaterialsRepository.selectBomDetails(bomId);
        if (billOfMaterialsDTO == null) {
            throw new NotFoundException("해당 BOM 정보를 찾을 수 없습니다.");
        }
        return billOfMaterialsDTO;
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

        bomValidator.validateRatio(billOfMaterialsVO.getBomRatio());

        billOfMaterialsRepository.updateBillOfMaterials(billOfMaterialsVO);
    }

    /**
     * BOM 정보 삭제 로직
     */
    @Override
    @Transactional
    public void deleteBillOfMaterials(String bomId) throws Exception {
        billOfMaterialsRepository.deleteBillOfMaterials(bomId);
    }
}
