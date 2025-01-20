package com.mrbean.warehouse;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WarehouseVO {

    @JsonProperty("wCode")
    private String wCode;

    @JsonProperty("wName")
    private String wName;

    @JsonProperty("wRoadFullAddr")
    private String wRoadFullAddr;

    @JsonProperty("wAddrDetail")
    private String wAddrDetail;

    @JsonProperty("wZipNo")
    private String wZipNo;

    @JsonProperty("wDescription")
    private String wDescription;

}
