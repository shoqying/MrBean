package com.mrbean.productionplan;

import java.time.LocalDate;

import com.mrbean.enums.ProductionplanStatus;

import lombok.Data;

@Data
public class ProductionVO {
	private int planId; // 생산계획 ID
	private String planNumber; // 생산오더 번호
	private String planType; // 생산계획 유형(일,월,반년등)\
	private LocalDate planStartDate; // 계획 시작일자
	private LocalDate planEndDate; // 계획 시작일자
	private String productCode; // 제품코드
	private String bomCode; // bom코드
	private int planQuantity; //생산계획수량
	private int priority; //우선순위
	
	
	private String remark; // 비고
	private String createdBy;//등록자 
	
	private ProductionplanStatus status; //계획상태 필요한가? => 작업지시에서 필요할듯함
	

	
	
	
	

}//ProductionVO


