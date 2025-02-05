package com.mrbean.warehouse;

import java.util.List;

public interface WarehouseRepository {

    // 창고 등록
    void insertWarehouse(WarehouseVO warehouse) throws Exception;

    // 창고 중복 확인
    boolean checkWarehouseCodeExists(String wCode) throws Exception;

    WarehouseVO selectWarehouseByCode(String wCode) throws Exception;

    // 창고 목록 조회
    List<WarehouseVO> selectWarehouseList();
}
