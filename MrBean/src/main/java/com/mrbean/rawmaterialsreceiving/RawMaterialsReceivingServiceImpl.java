package com.mrbean.rawmaterialsreceiving;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RawMaterialsReceivingServiceImpl implements RawMaterialsReceivingService {

    private static final Logger logger = LoggerFactory.getLogger(RawMaterialsReceivingServiceImpl.class);

    @Inject
    private RawMaterialsReceivingDAO rawMaterialsReceivingDAO;

    @Override
    public void createRawMaterial(RawMaterialsReceivingVO material) {
        logger.info("createRawMaterial 호출: " + material);
        rawMaterialsReceivingDAO.createRawMaterial(material);
    }

    @Override
    public String updateRawMaterial(RawMaterialsReceivingVO material) {
        logger.info("updateRawMaterial 호출: " + material);
        rawMaterialsReceivingDAO.updateRawMaterial(material);
        return "수정 완료";
    }

    @Override
    public void deleteRawMaterial(String rrNo) {
        logger.info("deleteRawMaterial 호출: rrNo = " + rrNo);
        rawMaterialsReceivingDAO.deleteRawMaterial(rrNo);
    }

    @Override
    public List<RawMaterialsReceivingVO> getAllRawMaterials() {
        logger.info("getAllRawMaterials 호출");
        return rawMaterialsReceivingDAO.getAllRawMaterials();
    }

    @Override
    public void registerRawMaterial(RawMaterialsReceivingVO rawMaterial) {
        logger.info("registerRawMaterial 호출: " + rawMaterial);
        rawMaterialsReceivingDAO.createRawMaterial(rawMaterial);
    }
}
