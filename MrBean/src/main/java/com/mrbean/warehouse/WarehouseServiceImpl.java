package com.mrbean.warehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public WarehouseVO registerWarehouse(WarehouseDTO warehouseDTO) throws Exception {
        // DTO를 VO로 변환 (불변 객체로 생성)
        WarehouseVO warehouseVO = new WarehouseVO(
                warehouseDTO.getWCode(),
                warehouseDTO.getWName(),
                warehouseDTO.getWRoadFullAddr(),
                warehouseDTO.getWAddrDetail(),
                warehouseDTO.getWZipNo(),
                Optional.ofNullable(warehouseDTO.getWDescription()).orElse("") // Null 처리
        );

        // WarehouseVO를 DB에 저장
        warehouseRepository.insertWarehouse(warehouseVO);

        // 저장된 WarehouseVO 반환
        return warehouseVO;
    }

    @Override
    public boolean isWarehouseCodeExist(String wCode) throws Exception{
        try {
            return warehouseRepository.checkWarehouseCodeExists(wCode);  // MyBatis Mapper 호출
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
