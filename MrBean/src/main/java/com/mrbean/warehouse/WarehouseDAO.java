package com.mrbean.warehouse;

public interface WarehouseDAO {

    // 창고 등록
    public void insertWarehouse(WarehouseVO warehouse) throws Exception;

    // 창고 중복 확인
    boolean checkWarehouseCodeExists(String wCode) throws Exception;

    WarehouseVO selectWarehouseByCode(String wCode) throws Exception;
}
