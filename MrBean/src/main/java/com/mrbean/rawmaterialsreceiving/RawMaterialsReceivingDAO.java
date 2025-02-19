package com.mrbean.rawmaterialsreceiving;

import java.util.List;
import java.util.Map;

public interface RawMaterialsReceivingDAO {

    // 원자재 입고 등록
    public void createRawMaterial(RawMaterialsReceivingVO rawMaterial) throws Exception;
    
    // 로트 번호 자동 생성 (DB에서 오늘 날짜의 로트번호 개수를 이용하여 생성)
    public String selectNextLotNumber() throws Exception;

    // 로트 번호 중복 체크 (현재 사용하지 않음)
    public boolean checkDuplicateLotNumber(String lotNoPrefix) throws Exception;

    // 로트 번호 rawmaterials_lot 테이블에 저장
    public void insertLotNumber(String lotNo) throws Exception;
    
    // 원자재 입고 리스트 조회 (페이징, 정렬, 검색 조건 포함)
    public List<RawMaterialsReceivingVO> selectRawMaterialsReceivingList(Map<String, Object> params) throws Exception;

    // 원자재 입고 수정
    public void updateRawMaterialReceiving(RawMaterialsReceivingVO rawMaterial) throws Exception;

    // 원자재 입고 삭제
    public void deleteRawMaterialReceiving(int rrNo) throws Exception;
    
}

