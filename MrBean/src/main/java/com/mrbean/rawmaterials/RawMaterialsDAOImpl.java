package com.mrbean.rawmaterials;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class RawMaterialsDAOImpl implements RawMaterialsDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String namespace = "com.mrbean.mappers.rawMaterialsMapper";

    // 원자재 등록
    @Override
    public void insertRawMaterials(RawMaterialsVO rawMaterialsVO) throws Exception {
        sqlSession.insert(namespace + ".insertRawMaterials", rawMaterialsVO);
    }
    
    // 원자재 목록 조회
    @Override
    public List<RawMaterialsVO> selectRawMaterialsList() throws Exception {
        return sqlSession.selectList(namespace + ".selectRawMaterialsList"); // SQL 실행
    }
    
    // 원자재 코드로 원자재 정보 조회
    @Override
    public RawMaterialsVO selectRawMaterialByCode(String rmCode) throws Exception {
        return sqlSession.selectOne(namespace + ".selectRawMaterialByCode", rmCode);
    }

    // 원자재 정보 수정
    @Override
    public void updateRawMaterial(RawMaterialsVO rawMaterialsVO) throws Exception {
        sqlSession.update(namespace + ".updateRawMaterial", rawMaterialsVO);
    }
}
