package com.mrbean.finishedproductsoutgoing;

import java.sql.Date;

import lombok.Data;

@ Data
public class FinishedProductsOutgoingVO {
	private String foNo; // 완제품 출고 번호
	private String fplNo; // 완제품 로트 번호
	private String pCode; // 완제품 코드
	private String pName; // 완제품 제품명
	private int foQuantity; // 완제품 수량
	private String foUnit; // 완제품 수량 단위 default 'ea'
	private String wCode; // 완제품 창고 코드
	private Date foDate; // 완제품 출고 날짜 , 오늘 날짜로 설정 예정
	private String foShippingLocationName; // 완제품 출고지명
	private String foShippingLocation; // 완제품 출고 날짜, 현재 날짜로 할 예정
	private int foBno; // 완제품 로트번호 순번

}
