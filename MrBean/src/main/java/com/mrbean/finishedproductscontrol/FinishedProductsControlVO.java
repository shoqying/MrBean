package com.mrbean.finishedproductscontrol;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.mrbean.billofmaterials.domain.BillOfMaterialsVO;
import com.mrbean.enums.QualityControlStatus;
import com.mrbean.finishedproductslot.FinishedProductsLotVO;
import com.mrbean.lothistory.LotHistoryVO;
import com.mrbean.productionplan.ProductionPlanVO;
import com.mrbean.products.ProductsVO;
import com.mrbean.warehouse.WarehouseDTO;
import com.mrbean.workorders.WorkOrdersVO;

import lombok.Data;

@Data
public class FinishedProductsControlVO {
	
	private int fpcBno; // 순번
	private Timestamp fpcDate; // 제조일
	private String fplNo; // LOT번호(완제품)
	private Date fpcExpirydate; // 완재품 유통기한
	private Timestamp fpcCheckdate; // 검사 일자
	private QualityControlStatus fpcQualityCheck; // 품질검사(대기중, 완료)
	private QualityControlStatus fpcStatus; // 상태(합격, 불합격)
	private double fpcYield; // 수율
	private double fpcQuantity; // 검사량
	private int planQuantity; // 검사량
	private String workOrderNo;
	private int workId;
	
	// join 매핑
//	private String pName; // 제품 명
//	private int planQuantity; // 생산 수량
	
	// join을 사용하여 생성된 정보 저장
	private List<ProductionPlanVO> productionPlanList;
	private List<ProductsVO> productsList;
	private List<WarehouseDTO> warehouseList;
	private List<FinishedProductsLotVO> finishedProductsLotList;
	private List<WorkOrdersVO> workOrdersList;
	
	


}
