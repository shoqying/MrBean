package com.mrbean.finishedproductsoutgoing;

import java.util.List;

public interface FinishedProductsOutgoingDAO {

    // 완제품 출고 정보 수정
    public String updateFinishedProductsOutgoing(FinishedProductsOutgoingVO material);

    // 완제품 출고 정보 삭제
    void deleteFinishedProducts(String foNo);

    // 완제품 출고 정보 전체 조회
    List<FinishedProductsOutgoingVO> getAllFinishedProducts();

    // 완제품 출고 등록
    void registerFinishedProductsOutgoing(FinishedProductsOutgoingVO finishedProduct);
}
