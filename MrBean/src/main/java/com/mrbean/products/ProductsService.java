package com.mrbean.products;

import java.util.List;

import com.mrbean.billofmaterials.BillOfMaterialsVO;

public interface ProductsService {
	
	// BOM 목록을 드롭다운에 표시하기 위해 가져오는 메서드
    public List<BillOfMaterialsVO> getBomListForDropdown() throws Exception;

    // 완제품 등록 처리
    public void registerProduct(ProductsVO product) throws Exception;
}
