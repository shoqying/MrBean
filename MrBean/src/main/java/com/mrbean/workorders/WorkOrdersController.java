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
		
	

} //WorkOrdersController
