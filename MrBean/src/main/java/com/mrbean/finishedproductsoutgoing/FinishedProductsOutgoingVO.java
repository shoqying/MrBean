package com.mrbean.finishedproductsoutgoing;

import java.sql.Date;
import lombok.Data;

@Data
public class FinishedProductsOutgoingVO {

    private String foNo;               // 출고번호
    private String fpcLotbno;          // 로트번호
    private String fplNo;              // 출고 품목 번호
    private String pCode;              // 제품 코드
    private int foQuantity;            // 출고 수량
    private String foUnit = "개";      // 기본값 '개' 설정
    private String wCode;              // 창고 코드
    private Date foDate;               // 출고일
    private String foShippingLocationName; // 출고 위치명
    private String foShippingLocation; // 출고 위치
    private int foBno;                 // 출고 번호
}
