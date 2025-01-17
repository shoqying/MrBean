package com.mrbean.rawmaterialsqualitycontrol;

import java.sql.Timestamp;
import java.util.List;

import com.mrbean.enums.QualityControlStatus;
import com.mrbean.lowmaterialslot.LowMaterialsLotVO;
import com.mrbean.productionplan.ProductionVO;

import lombok.Data;

@Data
public class RawMaterialsQualityControlVO {
	
	private int rqcBno; // 순번
	private Timestamp rmlDate; // 입고일
	private String rmlNo; // LOT번호(원자재 입고)
	private String rmCode; // 원자재 코드
	private String WCode; // 창고 코드
	private int rmlBatch; // 배치번호
	private Timestamp rqcDate; // 검사일자(품질검사 완료시)
	private String rqcQualityCheck; // 품질검사(대기중, 완료)
	private QualityControlStatus rqcStatus; // 상태(합격, 불합격)
	private String workOrderNo; // 작업지시번호
	private int workQty; // 검사량
	
	// join을 사용하여 생성된 정보 저장
	private List<LowMaterialsLotVO> LowMaterialsLotList;
	private List<ProductionVO> productionList;
	

}
