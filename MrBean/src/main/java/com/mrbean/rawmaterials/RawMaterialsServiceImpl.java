package com.mrbean.rawmaterials;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RawMaterialsServiceImpl implements RawMaterialsService {

	@Inject
    private RawMaterialsDAO rawMaterialsDAO;

	// 원자재 등록
	@Transactional
	@Override
    public void insertRawMaterials(RawMaterialsVO rawMaterialsVO) throws Exception {
        try {
            // 원자재 등록 처리
            rawMaterialsDAO.insertRawMaterials(rawMaterialsVO);
        } catch (Exception e) {
            // 예외 발생 시 롤백 및 실패 메시지 던지기
            throw new Exception("원자재 등록에 실패했습니다.");
        }
    }
	
	// 원자재 목록 조회
	@Override
    public List<RawMaterialsVO> getRawMaterialsList() throws Exception {
        return rawMaterialsDAO.selectRawMaterialsList(); // DAO 호출하여 리스트 가져오기
    }
	
	// 원자재 코드로 원자재 정보 조회
    @Override
    public RawMaterialsVO getRawMaterialByCode(String rmCode) throws Exception {
        return rawMaterialsDAO.selectRawMaterialByCode(rmCode); // DAO를 통해 원자재 정보 조회
    }

    // 원자재 정보 수정
    @Override
    public void updateRawMaterials(RawMaterialsVO rawMaterialsVO) throws Exception {
        rawMaterialsDAO.updateRawMaterial(rawMaterialsVO); // DAO를 통해 원자재 정보 수정
    }
}
