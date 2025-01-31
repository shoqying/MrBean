package com.mrbean.rawmaterials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rawmaterials")
public class RawMaterialsRESTController {

    private static final Logger logger = LoggerFactory.getLogger(RawMaterialsRESTController.class);
    private final RawMaterialsService rawMaterialsService;

    @Autowired
    public RawMaterialsRESTController(RawMaterialsService rawMaterialsService) {
        this.rawMaterialsService = rawMaterialsService;
    }

    /**
     * 원자재 목록 조회 API (GET 요청)
     * @return 원자재 목록(List) JSON
     */
    @GetMapping
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
