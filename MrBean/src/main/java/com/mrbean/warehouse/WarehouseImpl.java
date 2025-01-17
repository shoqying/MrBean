package com.mrbean.warehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseImpl implements WarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseImpl.class);

    @Autowired
    private WarehouseDAO warehouseDAO;

    @Override
    public void registerWarehouse(WarehouseVO warehouse) throws Exception {
        warehouseDAO.insertWarehouse(warehouse);
    }
}
