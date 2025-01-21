package com.mrbean.rawmaterials;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RawMaterialsServiceImpl implements RawMaterialsService {

    private static final Logger logger = LoggerFactory.getLogger(RawMaterialsServiceImpl.class);

    @Inject
    private RawMaterialsDAO rawMaterialsDAO;

    // 원자재 등록 처리
    @Override
    public void registerRawMaterial(RawMaterialsVO rawMaterialsVO) throws Exception {
        try {
            rawMaterialsDAO.insertRawMaterial(rawMaterialsVO);
        } catch (Exception e) {
            logger.error("원자재 등록 중 오류 발생: {}", e.getMessage(), e);
            throw e;
        }
    }

    // 원자재 리스트 조회
    @Override
    public List<RawMaterialsVO> getRawMaterialsList() throws Exception {
        try {
            return rawMaterialsDAO.selectAllRawMaterials();
        } catch (Exception e) {
            logger.error("원자재 목록 조회 중 오류 발생: {}", e.getMessage(), e);
            throw e;
        }
    }
    
    // 원자재 수정 처리
    @Override
    public void updateRawMaterial(RawMaterialsVO rawMaterialsVO) throws Exception {
    	try {
    		rawMaterialsDAO.updateRawMaterial(rawMaterialsVO);
    	} catch (Exception e) {
    		logger.error("원자재 수정 중 오류 발생: {}", e.getMessage(), e);
    		throw e;
    	}
    }
    
    // 원자재 삭제
    @Override
    public void deleteRawMaterial(String rmCode) throws Exception {
        try {
        	rawMaterialsDAO.deleteRawMaterial(rmCode);
        } catch (Exception e) {
        	logger.error("원자재 삭제 중 오류 발생: {}", e.getMessage(), e);
        	throw e;
        }
    }
    
}
