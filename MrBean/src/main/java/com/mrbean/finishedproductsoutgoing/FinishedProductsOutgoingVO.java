package com.mrbean.finishedproductsoutgoing;

import java.sql.Date;
import lombok.Data;

@Data
public class FinishedProductsOutgoingVO {

    private String foNo;                       // 완제품 출고번호, 자동생성
    private String workOrderNo;                // ✅ 작업지시 번호 추가!
    private String fplNo;                      // 완제품 로트번호
    private String pCode;                      // 완제품 코드
    private int foQuantity;                    // 출고 수량
    private String foUnit = "개";              // 기본값 '개' 설정
    private String wCode;                      // ✅ 창고 코드 추가! (이거 없으면 JSP에서 오류 발생)
    private Date foDate;                       // 출고일(today)
    private String foShippingLocationName;     // 출고 위치명
    private String foShippingLocation;         // 출고 위치
}
