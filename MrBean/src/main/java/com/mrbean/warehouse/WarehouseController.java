package com.mrbean.warehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Validated
public class WarehouseController {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    @Autowired
    private WarehouseService warehouseService;

    /**
     * 창고 등록 (POST)
     * Example: POST http://localhost:8080/warehouses
     * Registers a new warehouse and returns a success message.
     */
    @PostMapping("/warehouses")
    @ResponseBody
    public ResponseEntity<Map<String, String>> registerWarehouse(@RequestBody WarehouseVO warehouse, BindingResult result) throws Exception {
        logger.info("Received request to register warehouse: {}", warehouse);

        // 유효성 검사
        if (result.hasErrors()) {
            logger.error("유효성 검사 실패: {}", result.getAllErrors());
            Map<String, String> errorResponse = new HashMap<>();
            StringBuilder errorMessages = new StringBuilder("유효성 검사 실패: ");
            result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append(" "));
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
            errorResponse.put("message", "이미 등록된 창고 코드입니다.");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }

        try {
            warehouseService.registerWarehouse(warehouse);
            logger.info("창고 등록 성공.");
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
    // ApiResponse 클래스
    public static class ApiResponse {
        private String message;
        private List<String> errors;

        public ApiResponse(String message, List<String> errors) {
            this.message = message;
            this.errors = errors;
        }

        public String getMessage() {
            return message;
        }

        public List<String> getErrors() {
            return errors;
        }
    }
}
