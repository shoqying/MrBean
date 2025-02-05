package com.mrbean.workorders;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class WorkOrdersDAOImpl implements WorkOrdersDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(WorkOrdersDAOImpl.class);
	
	@Inject
	private SqlSession sqs;
	private static final String NAMESPACE = "com.mrbean.mappers.workOrdersMapper.";
	

   /**
    * WO 생성 
    * 동시성 제어를 위해 synchronized 키워드 사용 
    * 형식: WO-YYYYMMDD-###
    * 
    */	
	
	@Override
	public String createWorkOrderNumber(String date) {
		
		// 해당 날짜의 마지막 번호를 조회(Mapper 에서 SQL문 작성)
		String lastNumber = sqs.selectOne(NAMESPACE+"getLastNumberForDate", date);
		
		return lastNumber;
	}
	
   /**
    * 작업지시 등록
    *  
    */	

	@Override
	public void insertWorkOrders(WorkOrdersVO workVO) {
		sqs.insert(NAMESPACE+"insertWO", workVO);
	}
	
   /**
    * 작업목록 조회
    *  
    */
	
	@Override
	public List<WorkOrdersVO> createOrderList(WorkOrdersVO workVO) {
		List<WorkOrdersVO> result = sqs.selectList(NAMESPACE + "selectWO", workVO);

		return result;
	}

	/**
    * 작업목록 삭제
    *  
    */

	@Override
	public void deleteWorkOrders(int workId) {
		sqs.delete(NAMESPACE + "deleteWO",workId);		
	}
	
	/**
	 * 
	 * 작업 상태변경
	 */

	@Override
	public void updateWorkStatus(WorkOrdersVO workVO) {
		sqs.update(NAMESPACE + "updateWorkStatus", workVO);
		
	}
	
	/**
	 * 생산계획 ID 로 작업목록 조회
	 * 
	 */
	@Override
	public List<WorkOrdersVO> findByPlanId(int planId) {
		return sqs.selectList(NAMESPACE + "findByPlanId", planId);
	}

	@Override
	public Integer getPlanIdByWorkId(int workId) {
		return sqs.selectOne(NAMESPACE + "getPlanIdByWorkId", workId);
	}

	
	
} //WorkOrdersDAOImpl
