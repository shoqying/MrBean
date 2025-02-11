package com.mrbean.warehouse;

import java.util.List;

public interface WarehouseRepository {

    // 창고 등록
    void insertWarehouse(WarehouseVO warehouse) throws Exception;

    // 창고 중복 확인
    boolean checkWarehouseCodeExists(String wCode) throws Exception;

    // 창고 이름 중복 확인
    boolean checkWarehouseNameExists(String wName);

    // 창고 코드로 창고 조회
    WarehouseVO selectWarehouseByCode(String wCode) throws Exception;

    // 창고 목록 조회
    List<WarehouseVO> selectWarehouseList();

    // 창고 정보 수정
    void updateWarehouse(WarehouseVO warehouseVO);

    // 창고 정보 삭제
    void deleteWarehouse(String wCode);

}
