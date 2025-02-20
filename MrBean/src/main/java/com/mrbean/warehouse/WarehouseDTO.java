package com.mrbean.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class WarehouseDTO {

    @JsonProperty("wCode")
    @NotBlank(message = "창고 코드는 필수 입력 사항입니다.\n")
    @Size(max = 10, message = "창고 코드는 10자를 넘을 수 없습니다.")
    private String wCode;

    @JsonProperty("wName")
    @NotBlank(message = "창고 이름은 필수 입력 사항입니다.\n")
    @Size(max = 25, message = "창고 이름은 25자를 넘을 수 없습니다.")
    private String wName;

    @JsonProperty("wRoadFullAddr")
    @NotBlank(message = "도로명 주소는 필수 입력 사항입니다.\n")
    @Size(max = 200, message = "도로명 주소는 200자를 넘을 수 없습니다.")
    private String wRoadFullAddr;

    @JsonProperty("wAddrDetail")
    @NotBlank(message = "상세 주소는 필수 입력 사항입니다.\n")
    @Size(max = 200, message = "상세 주소는 200자를 넘을 수 없습니다.")
    private String wAddrDetail;

    @JsonProperty("wZipNo")
    @NotBlank(message = "우편번호는 필수 입력 사항입니다.\n")
    @Size(max = 10, message = "우편번호는 10자를 넘을 수 없습니다.")
    private String wZipNo;

    @JsonProperty("wDescription")
    @Size(max = 500, message = "설명은 500자를 넘을 수 없습니다.\n")
    private String wDescription;

}
