package com.mrbean.products;

import javax.inject.Inject;

import com.mrbean.billofmaterials.BomDropdownDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.mrbean.billofmaterials.BillOfMaterialsVO;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Inject
    private ProductsDAO productsDAO;

    // BOM 목록을 드롭다운에 표시하기 위해 가져오는 메서드
    @Override
    public List<BomDropdownDTO> getBomListForDropdown() throws Exception {
        return productsDAO.getBomListForDropdown(); // DAO에서 BOM 목록을 조회
    }
    
    // 완제품 등록 처리
    @Override
    public void registerProduct(ProductsVO product) throws Exception {
        productsDAO.insertProduct(product); // 완제품 등록
    }
}
