package com.mrbean.productionplan;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;

public class ProductionplanImpl implements ProductionplanDAO {

	@Inject
	private SqlSession sqs;
	private static final String NAMESPACE = "com.mrbean.mapper.productionplanMapper.";
	
	
	/**
	 * WO 생성 
	 * 동시성 제어를 위해 synchronized 키워드 사용 
	 * 형식: WO-YYYYMMDD-###
	 * 
	 */
	
//	@Override
//	public synchronized String createProductionPlanNumber() {
//		
//				
//		// 해당 날짜의 마지막 번호를 조회(Mapper 에서 SQL문 작성)
//		String lastNumber = sqs.selectOne(NAMESPACE+"",date);
//		
//		return "";
//		
//
//	}
	
	

}//ProductionplanImpl
