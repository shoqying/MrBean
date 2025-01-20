package com.mrbean.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.mrbean.productionplan.ProductionplanDAO;


@Repository
public class NumberGenerationServiceImpl implements NumberGenerationService {

	@Inject
	private ProductionplanDAO pdao;
	
	/**
	 * 번호 생성 
	 * 동시성 제어를 위해 synchronized 키워드 사용 
	 * 형식: WO-YYYYMMDD-### or .....
	 * 
	 */
	
	@Override
	public synchronized String generateNumber(String controllerName) {
		
		// "yyyyMMdd" 형식으로 날짜를 포맷하는 SimpleDateFormat 객체 생성
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		// 현재 시스템 날짜와 시간을 가져와서 "yyyyMMdd" 형식으로 변환하여 문자열date에 저장
		String date = sdf.format(new Date());
		
		
		// 해당 날짜의 마지막 번호를 조회(Mapper 에서 SQL문 작성)
		String lastNumber = pdao.createProductionPlanNumber(date);
		
		// 포맷팅 뒤에 숫자를 위한 시퀀스 번호 생성
		int sequence = 1;
			if(lastNumber !=null && !lastNumber.isEmpty()) {
				// lastNumber에서 마지막("-") 뒤의 부분을 추출gotj sequenceStr 으로 저장
				String sequenceStr = lastNumber.substring(lastNumber.lastIndexOf("-")+1);
				// 정수로 변환해서 +1 
				sequence = Integer.parseInt(sequenceStr) +1;
			}
		
		// controller별 번호 format 해서 반환
			switch(controllerName){
				case "productionplan":
					return String.format("WO-%s-%03d", date,sequence);
				case "":
					return"";
				default :
					return "";
			} //switch case
		
	} // generateNumber

} //NumberGenerationServiceImpl


