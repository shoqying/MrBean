package com.mrbean.finishedproductsoutgoing;

import java.util.List;

public interface FinishedProductsOutgoingService {

    // 출고 정보 등록
    void registerFinishedProduct(FinishedProductsOutgoingVO finishedProduct);

    // 출고 목록 조회
    List<FinishedProductsOutgoingVO> getAllFinishedProducts();

    // 출고번호로 출고 정보 조회
    FinishedProductsOutgoingVO getFinishedProductByFoNo(String foNo);

    // 출고 정보 수정
    void updateFinishedProduct(FinishedProductsOutgoingVO finishedProduct);

    // 출고 정보 삭제
    void deleteFinishedProduct(String foNo);

    // 완제품 로트번호 목록 조회
    List<String> getLotNumbers();

    // 제품 코드 목록 조회
    List<String> getProductCodes();

    // 창고 코드 목록 조회
    List<String> getWarehouseCodes();
}
