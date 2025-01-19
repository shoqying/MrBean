package com.mrbean.warehouse;

import com.mrbean.rawmaterials.RawMaterialsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/warehouse")
public class WarehouseController {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    @Autowired
    private WarehouseService warehouseService;

    /**
     * http://localhost:8088/warehouse/register
     * 창고 등록 페이지(GET)
     * @param model
     * @return
     */
    @GetMapping("/register")
    public String warehouseRegistGET(Model model) {

        return "warehouse/register";
    }


    // 창고 등록 API (POST)
    @PostMapping
    public ResponseEntity<String> registerWarehouse(@RequestBody WarehouseVO warehouse) {
        try {
            // 창고 저장
            warehouseService.registerWarehouse(warehouse);
            return new ResponseEntity<>("Warehouse registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register warehouse", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
