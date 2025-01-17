package com.mrbean.rawmaterialsqualitycontrol;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RMQCVO {
	
	private int rqcBno;
	private Timestamp rmlDate;
	private String rmlNo;
	private String rmCode;
	private String WCode;
	private int rmlBatch;
	private Timestamp rqcDate;
	private String rqcQualityCheck;
	private String rqcStatus;
	private String workOrderNo;
	private int workQty;

}
