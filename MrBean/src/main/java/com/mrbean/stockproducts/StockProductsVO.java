package com.mrbean.stockproducts;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class StockProductsVO {
	
    private int spBno; // 순번
    private int fpcQuantity; // 수량
    private String spUnit; // 단위
    private Timestamp spDate; // 입고 일자
    private String wCode; // 창고 코드 (String으로 유지)
    private String fplNo; // LOT번호(완제품)
    private String pCode; // 완제품 코드
    private Date fpcExpirydate; // 완제품 유통기한
    private int fpcBno; // 품질관리 순번
    private int planId; // plan_id
    private int spTotal; // 총 재고량
	
}
