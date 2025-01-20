package com.mrbean.workorders;

import java.sql.Date;

import lombok.Data;

@Data
public class WorkOrdersVO {
    private int workId;              // 작업 ID
    private String workOrderNo;      // 작업지시번호
    private String workPlanNo;       // 생산계획번호
    private Date workPlanDate;       // 작업예정일
    private int workQuantity;        // 작업수량
    private String workStatus;       // 작업상태 (WAITING, IN_PROGRESS, COMPLETED, STOPPED)
    private String workRemark;       // 비고
    private String workCreatedBy;    // 등록자
    private Date workCreatedAt;      // 등록일시
    private Date workUpdatedAt;      // 수정일시
    
}//workorders
