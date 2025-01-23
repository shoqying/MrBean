package com.mrbean.billofmaterials;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor // 기본 생성자 생성
@AllArgsConstructor // 모든 필드가 포함된 생성자 생성
public class BillOfMaterialsDTO {

    @JsonProperty("bomId")
    @NotNull(message = "BOM ID는 필수입니다.")
    @Pattern(regexp = "^BOM[1-9][0-9]{0,2}$", message = "BOM ID는 'BOM1'부터 'BOM999'까지의 형식이어야 합니다.")
    private String bomId; // BOM_ID

    @JsonProperty("bomName")
    @NotNull(message = "BOM 이름은 필수입니다.")
    @Size(max = 100, message = "BOM 이름은 100자 이하로 입력해야 합니다.")
    private String bomName;

    // 원자재 코드 (필수, 알파벳과 숫자로 이루어져야 함)
    @JsonProperty("rmCode")
    @NotNull(message = "원자재 코드는 필수입니다.")
    @Pattern(regexp = "^[A-Z]{3}$", message = "원자재 코드는 대문자 3글자로만 구성되어야 합니다.")
    private String rmCode;

    // BOM 비율 (필수, 0 이상 100 이하)
    @JsonProperty("bomRatio")
    @NotNull(message = "BOM 비율은 필수입니다.")
    @Min(value = 0, message = "BOM 비율은 0 이상이어야 합니다.")
    @Max(value = 100, message = "BOM 비율은 100 이하이어야 합니다.")
    private Integer bomRatio;

    // BOM 설명 (선택 사항, 500자 이하)
    @JsonProperty("bomDescription")
    @Size(max = 500, message = "BOM 설명은 500자 이하로 입력해야 합니다.")
    private String bomDescription;

}
