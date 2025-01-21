package com.mrbean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WarehouseVO {

    @JsonProperty("wCode")
    private final String wCode;

    @JsonProperty("wName")
    private final String wName;

    @JsonProperty("wRoadFullAddr")
    private final String wRoadFullAddr;

    @JsonProperty("wAddrDetail")
    private final String wAddrDetail;

    @JsonProperty("wZipNo")
    private final String wZipNo;

    @JsonProperty("wDescription")
    private final String wDescription;

    // 생성자
    public WarehouseVO(String wCode, String wName, String wRoadFullAddr,
                       String wAddrDetail, String wZipNo, String wDescription) {
        this.wCode = wCode;
        this.wName = wName;
        this.wRoadFullAddr = wRoadFullAddr;
        this.wAddrDetail = wAddrDetail;
        this.wZipNo = wZipNo;
        this.wDescription = (wDescription != null) ? wDescription : ""; // null 체크
    }
}