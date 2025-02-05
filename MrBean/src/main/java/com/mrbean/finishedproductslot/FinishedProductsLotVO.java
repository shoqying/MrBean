package com.mrbean.finishedproductslot;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class FinishedProductsLotVO {
	
	private int fplBno;
	private String fplNo;
	private String fplBatch;
	private Timestamp fplFinishedDate;
	private String pCode;
	private String wCode;
	private int planId;

}
