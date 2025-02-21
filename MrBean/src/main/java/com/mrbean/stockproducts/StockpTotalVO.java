package com.mrbean.stockproducts;


import lombok.Data;

@Data
public class StockpTotalVO {
	
	private int sptBno; // 순번
	private String pName; // 원자재코드
	private double sptTotal; // 총 재고

}
