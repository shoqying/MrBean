package com.mrbean.rawmaterialsreceiving;

import java.sql.Date;
import lombok.Data;

@Data
public class RawMaterialsReceivingVO {
	
	private String rrNo;// 원자재 입고 번호
	private String rmlNo; // 원자재 로트 번호
	private int rmlBno; // 원자재
	private String rmCode; // 원자재 코드
	private int rrQuantity; // 원자재 수량, 원자재명도 넣기
	private String rrUnit = "g";  // 기본값 'g' 설정
	private Date rrExpirydate; // 원자재 유통기한 입고날짜 +6개월

}
