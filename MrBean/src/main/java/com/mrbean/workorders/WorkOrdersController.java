package com.mrbean.workorders;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrbean.common.NumberGenerationService;
import com.mrbean.productionplan.ProductionPlanVO;

@Controller
@RequestMapping(value = "/workorders")
public class WorkOrdersController {
	private static final Logger logger = LoggerFactory.getLogger(WorkOrdersController.class);
	
	@Inject 
	private NumberGenerationService ngs;
	@Inject
	private WorkOrdersService wos;
	
		/**
		 * 작업지시등록 페이지(GET) - 등록 form 과 List 함께출력
		 * http://localhost:8088/workorders/work
		 * 
		 */
		@RequestMapping(value = "/work", method = RequestMethod.GET)
		public String orderRegisterGET(Model model, WorkOrdersVO workVO) {
			logger.info("orderRegisterGET 호출()");

			// 작업지시 목록을 조회해서 모델이 추가
			List<WorkOrdersVO>workList = wos.getWorkList(workVO);
			model.addAttribute("workList", workList);
			logger.info("workList : "+ workList);
			return "workorders/work";
		}
		
		/**
		 * 작업지시등록 페이지(POST)
		 * http://localhost:8088/workorders/order
		 * 
		 */
		@RequestMapping(value = "/work", 
						method = RequestMethod.POST,
						produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<?> planRegisterPOST(@RequestBody WorkOrdersVO workVO ) {
			logger.info("planRegisterPOST 호출()");
			try {
				// 작업지시 등록
				wos.insertWorkOrders(workVO);
				
				// 최신 목록 반환
				List<WorkOrdersVO> workList = wos.getWorkList(workVO);

				return ResponseEntity.ok(workList);  // 성공적으로 목록을 반환
				
			} catch (Exception e) {
		        logger.error("작업지시 등록 실패", e);
		        return ResponseEntity.status(500).body("작업지시 등록에 실패했습니다.");
			}
		}


		/**
		 * 번호생성 API(GET)
		 * http://localhost:8088/workorders/generateWorkNumber
		 * 
		 */
		@RequestMapping(value = "/generateWorkNumber", method = RequestMethod.GET)
		@ResponseBody
		public String generateWorkNumberGET() {
			
			String workNumber = ngs.generateNumber("workorders");
			logger.info("작업지시넘버 생성");
			
			return workNumber;
		}
		/**
		 * 작업지시 목록 삭제
		 */
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> deleteWork(@RequestBody int wordId){
			try {
				wos.deleteWork(wordId);
		        List<WorkOrdersVO> workList = wos.getWorkList(new WorkOrdersVO());
		        return ResponseEntity.ok(workList);				
			} catch (Exception e) {
		        return ResponseEntity.status(500).body("삭제에 실패했습니다.");				
			}
			
		}
		
	

} //WorkOrdersController
