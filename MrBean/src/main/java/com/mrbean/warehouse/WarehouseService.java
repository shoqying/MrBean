package com.mrbean.warehouse;

public interface WarehouseService {

    // 창고 등록
    public void registerWarehouse(WarehouseVO warehouse) throws Exception;

    // 창고 코드가 존재하는지 체크
    boolean isWarehouseCodeExist(String wCode) throws Exception;
}
