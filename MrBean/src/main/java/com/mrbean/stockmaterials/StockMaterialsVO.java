package com.mrbean.stockmaterials;



import java.sql.Timestamp;
import java.util.Date;


import lombok.Data;

@Data
public class StockMaterialsVO {


	private int smBno; // 순번
	private int rrQuantity; // 수량
	private String rrUnit; // 단위
	private Timestamp rmlDate; // 입고일
	//private String wCode; // 창고 코드
	private String rmlNo; // LOT번호(원자재 입고)
	private String rmCode; // 원자재 코드
	private Date rrExpirydate; // 원자재 유통기한
	private String rrNo; // 원자재 입고 번호
	private int smTotal; // 총 재고량


}