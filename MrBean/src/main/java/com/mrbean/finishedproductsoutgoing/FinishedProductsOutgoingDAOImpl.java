package com.mrbean.finishedproductsoutgoing;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FinishedProductsOutgoingDAOImpl implements FinishedProductsOutgoingDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.mrbean.Mapper.FinishedProductsOutgoingMapper";

    // 출고 정보 등록
    @Override
    public void registerFinishedProduct(FinishedProductsOutgoingVO finishedProduct) {
        sqlSession.insert(NAMESPACE + ".registerFinishedProduct", finishedProduct);
    }

    // 출고 목록 조회
    @Override
    public List<FinishedProductsOutgoingVO> getAllFinishedProducts() {
        return sqlSession.selectList(NAMESPACE + ".getAllFinishedProducts");
    }

    // 출고 번호로 출고 정보 조회
    @Override
    public FinishedProductsOutgoingVO getFinishedProductByFoNo(String foNo) {
        return sqlSession.selectOne(NAMESPACE + ".getFinishedProductByFoNo", foNo);
    }

    // 출고 정보 수정
    @Override
    public void updateFinishedProduct(FinishedProductsOutgoingVO finishedProduct) {
        sqlSession.update(NAMESPACE + ".updateFinishedProduct", finishedProduct);
    }

    // 출고 정보 삭제
    @Override
    public void deleteFinishedProduct(String foNo) {
        sqlSession.delete(NAMESPACE + ".deleteFinishedProduct", foNo);
    }

    // 완제품 로트번호 목록 조회
    @Override
    public List<String> getLotNumbers() {
        return sqlSession.selectList(NAMESPACE + ".getLotNumbers");
    }

    // 제품 코드 목록 조회
    @Override
    public List<String> getProductCodes() {
        return sqlSession.selectList(NAMESPACE + ".getProductCodes");
    }

    // 창고 코드 목록 조회
    @Override
    public List<String> getWarehouseCodes() {
        return sqlSession.selectList(NAMESPACE + ".getWarehouseCodes");
    }
}
