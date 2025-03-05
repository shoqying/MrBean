package com.mrbean.warehouse;

import java.util.List;

public interface WarehouseService {

    // 창고 등록
    WarehouseVO registerWarehouse(WarehouseDTO warehouse) throws Exception;

    // 창고 코드가 존재하는지 체크
    boolean isWarehouseCodeExist(String wCode) throws Exception;

    // 창고 이름 중복 체크
    boolean isWarehouseNameExist(String wName) throws Exception;

    // 창고 목록 조회
    List<WarehouseVO> getWarehouseList() throws Exception;

    // 창고 정보 수정
    WarehouseVO updateWarehouse(WarehouseDTO warehouse) throws Exception;

    // 창고 정보 삭제
    void deleteWarehouse(String wCode) throws Exception;

}
