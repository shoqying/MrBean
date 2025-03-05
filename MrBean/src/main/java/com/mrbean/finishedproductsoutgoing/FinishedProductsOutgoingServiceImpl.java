package com.mrbean.finishedproductsoutgoing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinishedProductsOutgoingServiceImpl implements FinishedProductsOutgoingService {

    @Autowired
    private FinishedProductsOutgoingDAO finishedProductsOutgoingDAO;

    // 출고 정보 등록
    @Override
    public void registerFinishedProduct(FinishedProductsOutgoingVO finishedProduct) {
        finishedProductsOutgoingDAO.registerFinishedProduct(finishedProduct);
    }

    // 출고 목록 조회
    @Override
    public List<FinishedProductsOutgoingVO> getAllFinishedProducts() {
        return finishedProductsOutgoingDAO.getAllFinishedProducts();
    }

    // 출고번호로 출고 정보 조회
    @Override
    public FinishedProductsOutgoingVO getFinishedProductByFoNo(String foNo) {
        return finishedProductsOutgoingDAO.getFinishedProductByFoNo(foNo);
    }

    // 출고 정보 수정
    @Override
    public void updateFinishedProduct(FinishedProductsOutgoingVO finishedProduct) {
        finishedProductsOutgoingDAO.updateFinishedProduct(finishedProduct);
    }

    // 출고 정보 삭제
    @Override
    public void deleteFinishedProduct(String foNo) {
        finishedProductsOutgoingDAO.deleteFinishedProduct(foNo);
    }

    // 완제품 로트번호 목록 조회
    @Override
    public List<String> getLotNumbers() {
        return finishedProductsOutgoingDAO.getLotNumbers();
    }

    // 제품 코드 목록 조회
    @Override
    public List<String> getProductCodes() {
        return finishedProductsOutgoingDAO.getProductCodes();
    }

    // 창고 코드 목록 조회
    @Override
    public List<String> getWarehouseCodes() {
        return finishedProductsOutgoingDAO.getWarehouseCodes();
    }
    
    @Override
    public List<String> getCompletedWorkOrderNumbers() {
        return finishedProductsOutgoingDAO.getCompletedWorkOrderNumbers();
    }

}
