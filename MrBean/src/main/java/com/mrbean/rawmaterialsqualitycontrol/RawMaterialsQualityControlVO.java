package com.mrbean.rawmaterialsqualitycontrol;

import java.sql.Timestamp;
import java.util.List;

import com.mrbean.enums.QualityControlStatus;
import com.mrbean.rawmaterialslot.RawMaterialsLotVO;
import com.mrbean.workorders.WorkOrdersVO;

import lombok.Data;

@Data
public class RawMaterialsQualityControlVO {
	
	private int rqcBno; // 순번
	private String rmlNo; // LOT번호(원자재 입고)
	private Timestamp rqcDate; // 검사일자(품질검사 완료시)
	private QualityControlStatus rqcQualityCheck; // 품질검사(대기중, 완료)
	private QualityControlStatus rqcStatus; // 상태(합격, 불합격)
	private String workOrderNo; // 작업지시번호


//	// join 매핑
//	private Timestamp rmlDate; // 입고 날짜
//	private String rmCode; // 원자제 제품번호
//	private int workQuantity; // 검사량
	
	
	// join을 사용하여 생성된 정보 저장
	private List<RawMaterialsLotVO> rawMaterialsLotList;
	private List<WorkOrdersVO> workOrdersList;
	

}
