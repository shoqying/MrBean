package com.mrbean.rawmaterialsreceiving;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;  // @Autowired 임포트 추가
import org.springframework.stereotype.Repository;


@Repository
public class RawMaterialsReceivingDAOImpl implements RawMaterialsReceivingDAO {

    @Autowired  // @Inject 대신 @Autowired 사용
    private SqlSession sqlSession;  // 변수명도 sqlSession으로 변경

    private static final String NAMESPACE = "com.mrbean.mappers.rawMataterialsReceivingMapper.";
    
    // 원자재 입고 번호 생성
    @Override
    public int creatRawMaterial(RawMaterialsReceivingVO material) {
        return sqlSession.insert(NAMESPACE + "registerRawMaterial", material);
    }

    // 원자재 입고 정보 수정
    @Override
    public String updateRawMaterial(RawMaterialsReceivingVO material) {
        return null;
    }

    // 원자재 입고 정보 삭제
    @Override
    public void deleteRawMaterial(String rrNo) {
    }

    // 원자재 입고 정보 전체 조회
    @Override
    public List<RawMaterialsReceivingVO> getAllRawMaterials() {
        return null;
    }

    // 원자재 입고 등록
    @Override
    public void registerRawMaterial(RawMaterialsReceivingVO rawMaterial) {
        sqlSession.insert(NAMESPACE + "registerRawMaterial", rawMaterial);
    }

	@Override
	public void createRawMaterial(RawMaterialsReceivingVO rrVO) {
		// TODO Auto-generated method stub
		
	}
}
