package com.mrbean.warehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public WarehouseVO registerWarehouse(WarehouseDTO warehouseDTO) throws Exception {
        // DTO를 VO로 변환 (불변 객체로 생성)
        // Builder 패턴을 사용하여 WarehouseVO 생성
        WarehouseVO warehouseVO = WarehouseVO.builder()
                .wCode(warehouseDTO.getWCode())
                .wName(warehouseDTO.getWName())
                .wRoadFullAddr(warehouseDTO.getWRoadFullAddr())
                .wAddrDetail(warehouseDTO.getWAddrDetail())
                .wZipNo(warehouseDTO.getWZipNo())
                .wDescription(Optional.ofNullable(warehouseDTO.getWDescription()).orElse("")) // Null 처리
                .build();

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

    @Override
    public boolean isWarehouseNameExist(String wName) throws Exception {
        try {
            return warehouseRepository.checkWarehouseNameExists(wName);  // MyBatis Mapper 호출
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WarehouseVO> getWarehouseList() throws Exception {
        try {
            return warehouseRepository.selectWarehouseList();  // MyBatis Mapper 호출
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WarehouseVO updateWarehouse(WarehouseDTO warehouseDTO) throws Exception {
        // DTO를 VO로 변환 (불변 객체로 생성)
        // Builder 패턴을 사용하여 WarehouseVO 생성
        WarehouseVO warehouseVO = WarehouseVO.builder()
                .wCode(warehouseDTO.getWCode())
                .wName(warehouseDTO.getWName())
                .wRoadFullAddr(warehouseDTO.getWRoadFullAddr())
                .wAddrDetail(warehouseDTO.getWAddrDetail())
                .wZipNo(warehouseDTO.getWZipNo())
                .wDescription(Optional.ofNullable(warehouseDTO.getWDescription()).orElse("")) // Null 처리
                .build();

        // WarehouseVO를 DB에 저장
        warehouseRepository.updateWarehouse(warehouseVO);

        // 저장된 WarehouseVO 반환
        return warehouseVO;
    }

    @Override
    public void deleteWarehouse(String wCode) throws Exception {
        try {
            warehouseRepository.deleteWarehouse(wCode);  // MyBatis Mapper 호출
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
