package com.mrbean.billofmaterials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/billofmaterials")
public class BillOfMaterialsController {

	private static final Logger logger = LoggerFactory.getLogger(BillOfMaterialsController.class);

	private final BillOfMaterialsService billOfMaterialsService;

	@Autowired
	public BillOfMaterialsController(BillOfMaterialsService billOfMaterialsService) {
		this.billOfMaterialsService = billOfMaterialsService;
	}

	/**
	 * BOM 생성 API (POST 요청)
	 * @param billOfMaterialsDTO BOM 정보
	 * @return ResponseEntity 객체 (성공/실패 메시지 포함)
	 */
	@PostMapping("")
	public ResponseEntity<?> createBillOfMaterials(@RequestBody BillOfMaterialsDTO billOfMaterialsDTO) {
		try {
			// 서비스에서 BOM 생성 처리
			billOfMaterialsService.createBillOfMaterials(billOfMaterialsDTO);

			// 2. 성공적으로 저장되면 성공 메시지와 함께 201 상태 코드 반환
			return new ResponseEntity<>(new MessageResponse("BOM 생성에 성공했습니다."), HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("BOM 생성 중 오류 발생", e);
			// 3. 예외 발생 시, 실패 메시지와 함께 400 상태 코드 반환
			return new ResponseEntity<>(new MessageResponse("BOM 생성에 실패했습니다."), HttpStatus.BAD_REQUEST);
		}
	}
}
