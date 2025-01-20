package com.mrbean.rawmaterialslot;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RawMaterialsLotVO {
	
	private String rmlNo; // 원자재 로트 번호
	private String rmCode; // 원자제 제품번호
	private String wCode; // 창고 코드
	private Timestamp rmlDate; // 입고 날짜
	private String rmlBatch; // 입고 배치

}
