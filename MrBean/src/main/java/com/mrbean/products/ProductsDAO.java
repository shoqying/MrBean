package com.mrbean.products;

import java.util.List;

import com.mrbean.billofmaterials.domain.BomDropdownDTO;

public interface ProductsDAO {
	
	// BOM 목록을 가져오는 메서드
    List<BomDropdownDTO> getBomListForDropdown() throws Exception;

    // 완제품을 등록하는 메서드
    void insertProduct(ProductsVO product) throws Exception;
    
    // 완제품 목록을 DB에서 가져오는 메서드
    List<ProductsVO> getProductList() throws Exception;
    
    // 완제품 수정
    void updateProduct(ProductsVO product) throws Exception;
    
    // 완제품 삭제
    void deleteProduct(String pCode) throws Exception;
}

