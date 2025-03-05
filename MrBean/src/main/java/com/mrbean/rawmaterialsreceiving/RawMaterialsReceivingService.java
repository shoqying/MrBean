package com.mrbean.rawmaterialsreceiving;

import java.util.List;

public interface RawMaterialsReceivingService {
   
    // 원자재 입고 등록
    public void registerRawMaterial(RawMaterialsReceivingVO rawMaterial) throws Exception;
    
    // 원자재 입고 번호 생성
    public String generateLotNumber() throws Exception;
    
    // 로트 번호 rawmaterials_lot 테이블에 저장
    public void saveLotNumberToRawMaterialsLot(String lotNo) throws Exception;
    
    // 원자재 입고 리스트 조회 (페이징, 정렬, 검색 포함)
    public List<RawMaterialsReceivingVO> getRawMaterialsReceivingList(int page, String sortColumn, String sortOrder, String searchKeyword) throws Exception;
    
    // 원자재 입고 수정
    public void updateRawMaterialsReceiving(RawMaterialsReceivingVO rawMaterial) throws Exception;
    
    // 원자재 입고 삭제
    public void deleteRawMaterialReceiving(int rrNo) throws Exception;
    
}
