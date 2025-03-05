package com.mrbean.products;

import com.mrbean.billofmaterials.domain.BomDropdownDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Repository
public class ProductsDAOImpl implements ProductsDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProductsDAOImpl.class);

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.mrbean.products.productsMapper";

    // BOM 목록을 가져오는 메서드
    @Override
    public List<BomDropdownDTO> getBomListForDropdown() throws Exception {
        logger.info("getBomListForDropdown() 호출");

        try {
            List<BomDropdownDTO> bomList = sqlSession.selectList(NAMESPACE + ".getBomListForDropdown");
            logger.info("BOM 목록 조회 완료, 개수: {}", bomList.size());
            return bomList;
        } catch (Exception e) {
            logger.error("BOM 목록 조회 중 오류 발생", e);
            throw new Exception("BOM 목록을 가져오는 중 오류가 발생했습니다.", e);
        }
    }

    // 완제품 등록 메서드
    @Override
    public void insertProduct(ProductsVO product) throws Exception {
        logger.info("insertProduct() 호출, 제품 코드: {}", product.getPCode());

        try {
            sqlSession.insert(NAMESPACE + ".insertProduct", product);
            logger.info("완제품 등록 완료, 제품 코드: {}", product.getPCode());
        } catch (Exception e) {
            logger.error("완제품 등록 중 오류 발생, 제품 코드: {}", product.getPCode(), e);
            throw new Exception("완제품 등록 중 오류가 발생했습니다.", e);
        }
    }

    // 완제품 목록을 DB에서 가져오는 메서드
    @Override
    public List<ProductsVO> getProductList() throws Exception {
        logger.info("getProductList() 호출");

        try {
            List<ProductsVO> productList = sqlSession.selectList(NAMESPACE + ".getProductList");
            logger.info("완제품 목록 조회 완료, 개수: {}", productList.size());
            if (!productList.isEmpty()) {
                logger.debug("완제품 목록: {}", productList);  // 상세 리스트 확인을 위한 디버깅 로그
            }
            return productList;
        } catch (Exception e) {
            logger.error("완제품 목록 조회 중 오류 발생", e);
            throw new Exception("완제품 목록 조회 중 오류가 발생했습니다.", e);
        }
    }
    
    // 완제품 수정
    @Override
    public void updateProduct(ProductsVO product) throws Exception {
        logger.info("완제품 수정 요청: {}", product);

        try {
            sqlSession.update(NAMESPACE + ".updateProduct", product);
            logger.info("완제품 수정 완료, 제품 코드: {}", product.getPCode());
        } catch (Exception e) {
            logger.error("완제품 수정 중 오류 발생, 제품 코드: {}", product.getPCode(), e);
            throw new Exception("완제품 수정 중 오류가 발생했습니다.", e);
        }
    }
    
    // 완제품 삭제
    @Override
    public void deleteProduct(String pCode) throws Exception {
        logger.info("완제품 삭제 요청, 제품 코드: {}", pCode);

        try {
            sqlSession.delete(NAMESPACE + ".deleteProduct", pCode);
            logger.info("완제품 삭제 완료, 제품 코드: {}", pCode);
        } catch (Exception e) {
            logger.error("완제품 삭제 중 오류 발생, 제품 코드: {}", pCode, e);
            throw new Exception("완제품 삭제 중 오류가 발생했습니다.", e);
        }
    }
}
