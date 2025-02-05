package com.mrbean.workorders;

import java.sql.Date;
import java.sql.Timestamp;

import com.mrbean.enums.WorkOrdersStatus;
import lombok.Data;

@Data
public class WorkOrdersVO {
    private int workId;              // 작업 ID
    private String workOrderNo;      // 작업지시번호
    private String workPlanNo;       // 생산계획번호
    private Date workPlanDate;       // 작업예정일
    private int workQuantity;        // 작업수량
    private int completedQuantity;          // 완료수량
    private String workRemark;       // 비고
    private String workCreatedBy;    // 등록자
    private Timestamp workStartTime;        // 작업시작시간
    private Timestamp workEndTime;          // 작업종료시간
    private Date workCreatedAt;      // 등록일시
    private Date workUpdatedAt;      // 수정일시
    
    
    private int planId; // 생산계획 ID
    private String rmlNo; // 원자재 로트 번호
    
    private WorkOrdersStatus workStatus;  // 작업상태 (WAITING, IN_PROGRESS, COMPLETED, STOPPED)
    
    
    // 검색 조건용 필드
    private Date searchStartDate;           // 조회 시작일
    private Date searchEndDate;             // 조회 종료일
    private String searchKeyword;           // 검색어
    
    
}
