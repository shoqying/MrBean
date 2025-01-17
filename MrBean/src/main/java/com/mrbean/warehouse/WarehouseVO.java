package com.mrbean.warehouse;

import java.util.Optional;

import lombok.Data;

@Data
public class WarehouseVO {
	
    private String wCode; // 창고 코드
    private String wName; // 창고 이름
    private String wAddress; // 창고 주소
    private String wDescription; // 창고 설명

}
