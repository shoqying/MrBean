package com.mrbean.productionplan;


import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProductionplanDAOImpl implements ProductionplanDAO {
	

	private static final Logger logger = LoggerFactory.getLogger(ProductionplanDAOImpl.class);


   @Inject
   private SqlSession sqs;
   private static final String NAMESPACE = "com.mrbean.mappers.productionplanMapper.";
   
   
   /**
    * WO 생성 
    * 동시성 제어를 위해 synchronized 키워드 사용 
    * 형식: PO-YYYYMMDD-###
    * 
    */
   
   @Override
   public synchronized String createProductionPlanNumber(String date) {
            
      // 해당 날짜의 마지막 번호를 조회(Mapper 에서 SQL문 작성)
      String lastNumber = sqs.selectOne(NAMESPACE+"getLastNumberForDate",date);
      
      return lastNumber;

   }

   /**
    * 생산계획 등록
    *  
    */
   @Override
	public void insertProductionPlan(ProductionPlanVO planVO) {
	   sqs.insert(NAMESPACE+"insertPP",planVO);
	}

   /**
    * 생산계획목록 조회
    * 
    * 
    */
   @Override
   public List<ProductionPlanVO> createPlanList(ProductionPlanVO planVO) {
       List<ProductionPlanVO> result = sqs.selectList(NAMESPACE + "selectPP", planVO);

       return result;
   }

   /**
    * 
    * 생산계획목록 삭제
    * 
    */
   
   
	@Override
	public void deleteProductionPlan(int planId) {
		sqs.delete(NAMESPACE + "deletePP",planId);
	}

   

}//ProductionplanImpl
