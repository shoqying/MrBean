package com.mrbean.rawmetarialsreceiving;

import java.sql.Date;
import lombok.Data;

@Data
public class RawMaterialsReceiningVO {
	
	
	private String rrNo;// 원자제 입고 번호
	private String rmlNo; // 원자제 로트 번호
	private int rrBno; // 원자제
	private String rmCode; // 원자제 코드
	private int rrQuantity; // 원자제 수량, 원자제명도 넣기
	private String rrUnit; // 원자제 수량의 단위 기본값 'g'
	private Date rrExpirydate ; // 원자제 유통기한 입고날짜 +6개월
	
	

}
