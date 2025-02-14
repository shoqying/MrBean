package com.mrbean.productionplan;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrbean.enums.ProductionplanStatus;

import lombok.Data;

@Data
public class ProductionPlanVO {
	private int planId; // 생산계획 ID
	private String planNumber; // 생산오더 번호
	private String planType; // 생산계획 유형(일,월,반년등)\
	private Date planStartDate; // 계획 시작일자
	private Date planEndDate; // 계획 시작일자
	@JsonProperty("pName")
	private String pName; // 제품명
	private String bomCode; // bom코드
	private int planQuantity; //생산계획수량
	
	
	private String remark; // 비고
	private String createdBy;//등록자 
	
	private ProductionplanStatus plStatus; //계획상태(PLANNED,WAITING,IN_PROGRESS,COMPLETED,STOPPED)
	
	
	
	
	
	
}//ProductionVO


