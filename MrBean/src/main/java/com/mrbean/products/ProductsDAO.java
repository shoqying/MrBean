package com.mrbean.products;

import java.util.List;

import com.mrbean.billofmaterials.BillOfMaterialsVO;

public interface ProductsDAO {
	
	// BOM 목록을 가져오는 메서드
    public List<BillOfMaterialsVO> getBomListForDropdown() throws Exception;

    // 완제품을 등록하는 메서드
    public void insertProduct(ProductsVO product) throws Exception;
}

