package finishedproductslot;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class FinishedProductsLotVO {
	
    private int fplBno;             // 순번
    private String fplNo;           // LOT 번호 (완제품)
    private String pCode;           // 완제품 코드
    private String wCode;           // 창고 코드
    private Timestamp fplFinishedDate;   // 검사일자 (품질검사 완료 시)
    private String fplBatch;        // 배치번호

}
