package com.mrbean.finishedproductsoutgoing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinishedProductsOutgoingServiceImpl implements FinishedProductsOutgoingService {

    @Autowired
    private FinishedProductsOutgoingDAO finishedProductsOutgoingDAO;

    // 완제품 출고 정보 수정
    @Override
    public String updateFinishedProduct(FinishedProductsOutgoingVO finishedProduct) {
        return finishedProductsOutgoingDAO.updateFinishedProductsOutgoing(finishedProduct);
    }

    // 완제품 출고 정보 삭제
    @Override
    public void deleteFinishedProduct(String foNo) {
        finishedProductsOutgoingDAO.deleteFinishedProducts(foNo);
    }

    // 완제품 출고 정보 전체 조회
    @Override
    public List<FinishedProductsOutgoingVO> getAllFinishedProducts() {
        return finishedProductsOutgoingDAO.getAllFinishedProducts();
    }

    // 완제품 출고 등록
    @Override
    public void registerFinishedProduct(FinishedProductsOutgoingVO finishedProduct) {
        try {
            finishedProductsOutgoingDAO.registerFinishedProductsOutgoing(finishedProduct);
        } catch (Exception e) {
            throw new RuntimeException("완제품 출고 등록 중 오류 발생", e);
        }
    }
}
