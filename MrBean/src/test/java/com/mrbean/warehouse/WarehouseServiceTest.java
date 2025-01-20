package com.mrbean.warehouse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // Spring 테스트 실행기
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WarehouseDAO warehouseDAO;

    @Test
    public void testRegisterWarehouse() throws Exception {
        // 테스트할 창고 데이터 준비
        WarehouseVO warehouse = new WarehouseVO();
        warehouse.setWCode("W-001");
        warehouse.setWName("Warehouse 1");
        warehouse.setWRoadFullAddr("General");
        warehouse.setWAddrDetail("Gyeonggi-do, Anyang-si");
        warehouse.setWZipNo("1200");
        warehouse.setWDescription("테스트창고");

        // 창고 등록
        warehouseService.registerWarehouse(warehouse);

        // 등록된 창고 데이터 확인
        WarehouseVO insertedWarehouse = warehouseDAO.selectWarehouseByCode("W-001");

        // 값이 제대로 등록되었는지 검증
        Assert.assertNotNull("등록된 창고가 null입니다.", insertedWarehouse);
        Assert.assertEquals("W-001", insertedWarehouse.getWCode());
        Assert.assertEquals("Warehouse 1", insertedWarehouse.getWName());
        Assert.assertEquals("General", insertedWarehouse.getWRoadFullAddr());
        Assert.assertEquals("Gyeonggi-do, Anyang-si", insertedWarehouse.getWAddrDetail());
        Assert.assertEquals("1200", insertedWarehouse.getWZipNo());
        Assert.assertEquals("테스트창고", insertedWarehouse.getWDescription());
    }
}