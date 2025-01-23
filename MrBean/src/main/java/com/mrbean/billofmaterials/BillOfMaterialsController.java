package com.mrbean.billofmaterials;

import com.mrbean.rawmaterials.RawMaterialsDTO;
import com.mrbean.rawmaterials.RawMaterialsService;
import com.mrbean.rawmaterials.RawMaterialsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BillOfMaterialsController {

	private static final Logger logger = LoggerFactory.getLogger(BillOfMaterialsController.class);

	private final BillOfMaterialsService billOfMaterialsService;
	private final RawMaterialsService rawMaterialsService;

	@Autowired
	public BillOfMaterialsController(BillOfMaterialsService billOfMaterialsService, RawMaterialsService rawMaterialsService) {
		this.billOfMaterialsService = billOfMaterialsService;
		this.rawMaterialsService = rawMaterialsService;
	}

	/**
	 * BOM 생성 API (POST 요청)
	 * @param billOfMaterialsDTO BOM 정보
	 * @return ResponseEntity 객체 (성공/실패 메시지 포함)
	 */
	@PostMapping("/billofmaterials")
	public ResponseEntity<?> createBillOfMaterials(@Valid @RequestBody BillOfMaterialsDTO billOfMaterialsDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// 유효성 검사 실패 처리
			String errorMessage = bindingResult.getAllErrors()
					.stream()
					.map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(", "));
			return new ResponseEntity<>(new MessageResponse(errorMessage), HttpStatus.BAD_REQUEST);
		}

		try {
			// 서비스에서 BOM 생성 처리
			billOfMaterialsService.createBillOfMaterials(billOfMaterialsDTO);

			// 성공적으로 저장되면 성공 메시지와 함께 201 상태 코드 반환
			return new ResponseEntity<>(new MessageResponse("BOM 생성에 성공했습니다."), HttpStatus.CREATED);

		} catch (DuplicateKeyException e) {
			// 중복 키 예외 처리 (예: BOM ID가 이미 존재하는 경우)
			logger.error("중복된 BOM ID: " + billOfMaterialsDTO.getBomId(), e);
			return new ResponseEntity<>(new MessageResponse("중복된 BOM ID입니다."), HttpStatus.CONFLICT);

		} catch (ValidationException e) {
			// 유효성 검사 실패 처리
			logger.error("BOM 데이터 유효성 검사 실패: " + e.getMessage(), e);
			return new ResponseEntity<>(new MessageResponse("입력 데이터가 유효하지 않습니다."), HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// 일반적인 예외 처리
			logger.error("BOM 생성 중 알 수 없는 오류 발생", e);
			return new ResponseEntity<>(new MessageResponse("BOM 생성에 실패했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 원자재 목록 조회 API (GET 요청)
	 * @return 원자재 목록(List) JSON
	 */
	@GetMapping("/rawmaterials")
	public ResponseEntity<List<RawMaterialsVO>> getAllRawMaterials() {
		try {
			// 서비스 계층에서 원자재 목록 가져오기
			List<RawMaterialsVO> rawMaterials = rawMaterialsService.getRawMaterialsList();
			return new ResponseEntity<>(rawMaterials, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("원자재 목록 조회 중 오류 발생", e);
			// 오류 시 빈 목록 + INTERNAL_SERVER_ERROR 등으로 처리 가능
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
