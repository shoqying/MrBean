package com.mrbean.products;

import javax.inject.Inject;

import com.mrbean.billofmaterials.domain.BomDropdownDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

	private static final Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);
	
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
        // 유효성 검사
        if (product.getPCode() == null || product.getPCode().trim().isEmpty()) {
            throw new IllegalArgumentException("제품 코드(pCode)는 필수 항목입니다.");
        }
        if (product.getPCode().length() > 20) {
            throw new IllegalArgumentException("제품 코드(pCode)는 최대 20자까지 입력 가능합니다.");
        }

        if (product.getPName() == null || product.getPName().trim().isEmpty()) {
            throw new IllegalArgumentException("제품명(pName)은 필수 항목입니다.");
        }
        if (product.getPName().length() > 50) {
            throw new IllegalArgumentException("제품명(pName)은 최대 50자까지 입력 가능합니다.");
        }

        if (product.getPDescription() == null || product.getPDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("제품 설명(pDescription)은 필수 항목입니다.");
        }
        if (product.getPDescription().length() > 200) {
            throw new IllegalArgumentException("제품 설명(pDescription)은 최대 200자까지 입력 가능합니다.");
        }

        if (product.getBomId() == null || product.getBomId().isEmpty()) {
            throw new IllegalArgumentException("BOM 선택은 필수입니다.");
        }

        // 유효성 검사를 통과하면 DAO를 통해 DB에 삽입
        productsDAO.insertProduct(product);
    }
    
    // 완제품 목록을 가져오는 메서드
    @Override
    public List<ProductsVO> getProductList() throws Exception {
        try {
            // DAO에서 완제품 목록을 조회
            return productsDAO.getProductList();
        } catch (Exception e) {
            throw new Exception("완제품 목록을 가져오는 중 오류가 발생했습니다.", e);
        }
    }
    
    // 완제품 수정
    @Override
    public void updateProduct(ProductsVO product) throws Exception {
        try {
            productsDAO.updateProduct(product);
        } catch (Exception e) {
            throw e;
        }
    }
    
    // 완제품 삭제
    @Override
    public void deleteProduct(String pCode) throws Exception {
        try {
            // 삭제 처리
            productsDAO.deleteProduct(pCode);
        } catch (Exception e) {
            throw e;
        }
    }
}
