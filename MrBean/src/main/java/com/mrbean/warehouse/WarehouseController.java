package com.mrbean.warehouse;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class WarehouseController {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    /**
     * 창고 등록 (POST)
     * Example: POST http://localhost:8080/warehouses
     * Registers a new warehouse and returns a success message.
     */
    @PostMapping("/warehouses")
    @ResponseBody
    public ResponseEntity<Map<String, String>> registerWarehouse(@Valid @RequestBody WarehouseDTO warehouse, BindingResult result) throws Exception {
        // 유효성 검사
        if (result.hasErrors()) {
            logger.error("유효성 검사 실패: {}", result.getAllErrors());
            Map<String, String> errorResponse = new HashMap<>();
            StringBuilder errorMessages = new StringBuilder("유효성 검사 실패:\n");
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()));
            errorResponse.put("message", errorMessages.toString());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }

        // 창고 코드 중복 체크
        if (warehouseService.isWarehouseCodeExist(warehouse.getWCode())) {
            logger.error("이미 등록된 창고 코드: {}", warehouse.getWCode());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "이미 등록된 창고 입니다.");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }

        if (warehouseService.isWarehouseNameExist(warehouse.getWName())) { // New check
            logger.error("이미 등록된 창고 이름: {}", warehouse.getWName());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "이미 등록된 창고 이름입니다.");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }

        try {
            warehouseService.registerWarehouse(warehouse);
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", "창고 등록 성공");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(successResponse);

        } catch (Exception e) {
            logger.error("창고 등록 중 오류 발생", e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "창고 등록 실패");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }
    }

    /**
     * 창고 조회 (GET)
     * Example: GET http://localhost:8080/api/warehouses/{id}
     */
//    @GetMapping("/{id}")
//    public ResponseEntity<WarehouseVO> getWarehouse(@PathVariable Long id) {
//        WarehouseVO warehouse = warehouseService.getWarehouseById(id);
//        if (warehouse != null) {
//            return new ResponseEntity<>(warehouse, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    /**
     * 창고 목록 조회 (GET)
     * Example: GET http://localhost:8080/warehouses/refresh
     */
    @GetMapping("/warehouses/refresh")
    public ResponseEntity<?> getWarehouseList() {
        try {
            List<WarehouseVO> warehouseList = warehouseService.getWarehouseList();
            logger.info("창고 목록 조회 성공: {}", warehouseList);
            return ResponseEntity.ok(warehouseList);
        } catch (Exception e) {
            logger.error("창고 목록 조회 중 오류 발생", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("창고 목록 조회 실패", List.of(e.getMessage())));
        }
    }

    /**
     * 창고 정보 수정 (PUT)
     * Example: PUT http://localhost:8080/warehouses/{id}
     */
    @PutMapping("/warehouses/{wCode}")
    public ResponseEntity<?> updateWarehouse(@PathVariable String wCode, @Validated @RequestBody WarehouseDTO warehouse, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("창고 정보 수정 실패", errors));
        }

        try {
            warehouseService.updateWarehouse(warehouse);
            return ResponseEntity.ok(new ApiResponse("창고 정보 수정 성공", null));
        } catch (Exception e) {
            logger.error("창고 정보 수정 중 오류 발생", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("창고 정보 수정 실패", List.of(e.getMessage())));
        }
    }

    /**
     * 창고 정보 삭제 (DELETE)
     * Example: DELETE http://localhost:8080/warehouses/{id}
     */
    @DeleteMapping("/warehouses/{wCode}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable String wCode) {
        try {
            warehouseService.deleteWarehouse(wCode);
            return ResponseEntity.ok(new ApiResponse("창고 정보 삭제 성공", null));
        } catch (Exception e) {
            logger.error("창고 정보 삭제 중 오류 발생", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("창고 정보 삭제 실패", List.of(e.getMessage())));
        }
    }
    // ApiResponse 클래스
    @Getter
    public static class ApiResponse {
        private final String message;
        private final List<String> errors;

        public ApiResponse(String message, List<String> errors) {
            this.message = message;
            this.errors = errors;
        }

    }
}
