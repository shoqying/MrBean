package com.mrbean.lothistory;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LotHistoryVO {
	
	private int historyId;
	private int workId;
	private String rmlNo;
	private int quantityUsed;
	private Timestamp usageDate;

}
