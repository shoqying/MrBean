package com.mrbean.finishedproductsoutgoing;

import java.util.List;

public interface FinishedProductsOutgoingService {

    // 완제품 출고 정보 수정
    String updateFinishedProduct(FinishedProductsOutgoingVO finishedProduct);

    // 완제품 출고 정보 삭제
    void deleteFinishedProduct(String foNo);

    // 완제품 출고 정보 전체 조회
    List<FinishedProductsOutgoingVO> getAllFinishedProducts();

    // 완제품 출고 등록
    void registerFinishedProduct(FinishedProductsOutgoingVO finishedProduct);
}
