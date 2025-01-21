package com.mrbean.stockproducts;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class StockProductsVO {
	
	private int spBno; // 순번
	private int spQuantity; // 수량
	private String spUnit; // 단위
	private Timestamp spDate; // 입고 일자
	private String wCode; // 창고코드
	private String fpcLotbno; // LOT번호(완제품)
	private String pCode; // 완제품 코드
	private Date fpcExpirydate; // 완제품 유통기한
	private int fpcBno; // 품질관리 순번
	
	

}
