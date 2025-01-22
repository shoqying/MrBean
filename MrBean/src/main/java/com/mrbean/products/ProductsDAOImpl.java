package com.mrbean.products;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mrbean.billofmaterials.BillOfMaterialsVO;

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
    public List<BillOfMaterialsVO> getBomListForDropdown() throws Exception {
        logger.info("getBomListForDropdown() 호출");

        List<BillOfMaterialsVO> bomList = sqlSession.selectList(NAMESPACE + ".getBomListForDropdown");

        logger.info("BOM 목록 조회 완료, 개수: {}", bomList.size());
        return bomList;
    }

    // 완제품 등록 메서드
    @Override
    public void insertProduct(ProductsVO product) throws Exception {
        logger.info("insertProduct() 호출, 제품 코드: {}", product.getPCode());

        sqlSession.insert(NAMESPACE + ".insertProduct", product);

        logger.info("완제품 등록 완료, 제품 코드: {}", product.getPCode());
    }
}
