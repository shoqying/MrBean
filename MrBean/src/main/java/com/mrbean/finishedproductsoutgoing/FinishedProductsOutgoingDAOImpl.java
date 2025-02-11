package com.mrbean.finishedproductsoutgoing;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class FinishedProductsOutgoingDAOImpl implements FinishedProductsOutgoingDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.mrbean.mappers.finishedProductsOutgoingMapper";

    // 완제품 출고 정보 수정
    @Override
    public String updateFinishedProductsOutgoing(FinishedProductsOutgoingVO material) {
        sqlSession.update(NAMESPACE + ".updateFinishedProductsOutgoing", material);
        return material.getFoNo();  // 수정된 출고번호 반환
    }

    // 완제품 출고 정보 삭제
    @Override
    public void deleteFinishedProducts(String foNo) {
        sqlSession.delete(NAMESPACE + ".deleteFinishedProduct", foNo);
    }

    // 완제품 출고 정보 전체 조회
    @Override
    public List<FinishedProductsOutgoingVO> getAllFinishedProducts() {
        return sqlSession.selectList(NAMESPACE + ".getAllFinishedProducts");
    }

    // 완제품 출고 등록
    @Override
    public void registerFinishedProductsOutgoing(FinishedProductsOutgoingVO finishedProduct) {
        sqlSession.insert(NAMESPACE + ".registerFinishedProduct", finishedProduct);
    }
}
