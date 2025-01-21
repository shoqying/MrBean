package com.mrbean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
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

}