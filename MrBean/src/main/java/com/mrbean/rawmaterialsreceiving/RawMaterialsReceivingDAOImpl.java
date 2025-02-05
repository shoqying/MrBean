package com.mrbean.rawmaterialsreceiving;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class RawMaterialsReceivingDAOImpl implements RawMaterialsReceivingDAO {

	@Inject
	private SqlSession sqs;
	 private static final String NAMESPACE = "com.mrbean.mappers.rawMataterialsReceivingMapper.";
	 
	 
	 	 
	// 원자재 입고 번호 생성
	@Override
	public String creatRawMaterial(RawMaterialsReceivingVO material) {
		
		return sqs.selectOne(NAMESPACE+"d", material);
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
	    public void createRawMaterial(RawMaterialsReceivingVO rrVO) {
	        String sql = "INSERT INTO raw_materials_receiving (rmlNo, rrBno, rmCode, rrQuantity, rrUnit, rrExpirydate) VALUES (?, ?, ?, ?, ?, ?)";
	        
	    }

}
