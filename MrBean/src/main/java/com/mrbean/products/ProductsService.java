package com.mrbean.products;

import java.util.List;

import com.mrbean.billofmaterials.domain.BomDropdownDTO;

public interface ProductsService {
	
	// BOM 목록을 드롭다운에 표시하기 위해 가져오는 메서드
    public List<BomDropdownDTO> getBomListForDropdown() throws Exception;

    // 완제품 등록 처리
    public void registerProduct(ProductsVO product) throws Exception;
    
    // 완제품 목록을 가져오는 메서드
    public List<ProductsVO> getProductList() throws Exception;
    
    // 완제품 수정
    public void updateProduct(ProductsVO product) throws Exception;  
    
    // 완제품 삭제
    public void deleteProduct(String pCode) throws Exception;
}
