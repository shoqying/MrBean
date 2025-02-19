package com.mrbean.finishedproductsoutgoing;

import java.sql.Date;
import lombok.Data;

@Data
public class FinishedProductsOutgoingVO {

    private String foNo;                       // 완제품 출고번호, 자동생성
    private String fplNo;                      // 완제품 로트번호, 만들어진 완제품 로트번호를 조회해서 가져옴
    private String pCode;                      // 완제품 코드, 만들어진 완제품 코드를 조회해서 가져옴
    private int foQuantity;                    // 출고 수량
    private String foUnit = "개";              // 기본값 '개' 설정
    private String wCode;                      // 창고 코드, 만들어진 완제품 코드를 조회해서 가져옴
    private Date foDate;                  // 출고일(today)
    private String foShippingLocationName;     // 출고 위치명
    private String foShippingLocation;         // 출고 위치
}
