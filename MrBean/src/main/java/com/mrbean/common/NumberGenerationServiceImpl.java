package com.mrbean.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.mrbean.productionplan.ProductionplanDAO;
import com.mrbean.workorders.WorkOrdersDAO;

@Repository
public class NumberGenerationServiceImpl implements NumberGenerationService {

private static final Logger logger = LoggerFactory.getLogger(NumberGenerationServiceImpl.class);
	
	@Inject
	private ProductionplanDAO pdao;
	
	@Inject
	private WorkOrdersDAO wdao;
	
	/**
	 * 번호 생성 
	 * 동시성 제어를 위해 synchronized 키워드 사용 
	 * 형식: WO-YYYYMMDD-### or .....
	 * 
	 */
	@Override
	public synchronized String generateNumber(String controllerName) {
		
			logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		// "yyyyMMdd" 형식으로 날짜를 포맷하는 SimpleDateFormat 객체 생성
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		// 현재 시스템 날짜와 시간을 가져와서 "yyyyMMdd" 형식으로 변환하여 문자열date에 저장
		String date = sdf.format(new Date());
		
		// 포맷팅 뒤에 숫자를 위한 시퀀스 번호 생성
		int sequence = 1;
		String lastNumber;
		
		// controller별 번호 format 해서 반환
		switch(controllerName){
			case "productionplan":
				// 생산계획의 마지막 번호 조회
				lastNumber = pdao.createProductionPlanNumber(date);
				if(lastNumber != null && !lastNumber.isEmpty()) {
					// lastNumber에서 마지막("-") 뒤의 부분을 추출하여 sequenceStr로 저장
					String sequenceStr = lastNumber.substring(lastNumber.lastIndexOf("-")+1);
					// 정수로 변환해서 +1 
					sequence = Integer.parseInt(sequenceStr) + 1;
				}
				return String.format("PO-%s-%03d", date, sequence);
				
			case "workorders":
				// 작업지시의 마지막 번호 조회
				lastNumber = wdao.createWorkOrderNumber(date);
				if(lastNumber != null && !lastNumber.isEmpty()) {
					// lastNumber에서 마지막("-") 뒤의 부분을 추출하여 sequenceStr로 저장
					String sequenceStr = lastNumber.substring(lastNumber.lastIndexOf("-")+1);
					// 정수로 변환해서 +1 
					sequence = Integer.parseInt(sequenceStr) + 1;
				}
				return String.format("WO-%s-%03d", date, sequence);
				
			default:
				return "";
		} //switch case
		
	} // generateNumber

} //NumberGenerationServiceImpl