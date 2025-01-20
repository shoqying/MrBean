package com.mrbean.finishedproductscontrol;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.mrbean.enums.QualityControlStatus;
import com.mrbean.productionplan.ProductionVO;

import lombok.Data;

@Data
public class FinishedProductsControlVO {
	
	private int fpcBno; // 순번
	private Timestamp fpcDate; // 제조일
	private String fpcLotbno; // LOT번호(완제품)
	private int bomId; // BOM ID
	private String pCode; // product code
	private Date fpcExpirydate; // 완재품 유통기한
	private String fpcQualityCheck; // 품질검사(대기중, 완료)
	private QualityControlStatus fpcStatus; // 상태(합격, 불합격)
	private double fpcYield; // 수율
	private int planId; // plan_id
	private int planQty; // 수량
	private double fpcQuantity; // 검사량
	
	// join을 사용하여 생성된 정보 저장
	private List<ProductionVO> productionList;
//	private List<AuthVO> authList;

}
