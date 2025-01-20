package com.mrbean.warehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    @Autowired
    private WarehouseDAO warehouseDAO;

    @Override
    public void registerWarehouse(WarehouseVO warehouse) throws Exception {
        warehouseDAO.insertWarehouse(warehouse);
    }

    @Override
    public boolean isWarehouseCodeExist(String wCode) throws Exception{
        try {
            return warehouseDAO.checkWarehouseCodeExists(wCode);  // MyBatis Mapper 호출
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
