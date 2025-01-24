//package com.mrbean.rawmaterialslot;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service  // Spring에서 이 클래스는 서비스로 인식
//public class RawMaterialsLotServiceImpl implements RawMaterialsLotService {
//
//    // DAO를 주입받기 위해 @Autowired 사용
//    @Autowired
//    private RawMaterialsLotDAO rawMaterialsLotDAO;
//
//    // 원자재 로트번호 추가
//    @Override
//    public void addRawMaterialsLot(RawMaterialsLotVO rawMaterialsLot) {
//        // 원자재 로트번호 추가 작업
//        rawMaterialsLotDAO.addRawMaterialsLot(rawMaterialsLot);
//    }
//
//    // 원자재 로트번호 목록 조회
//    @Override
//    public List<RawMaterialsLotVO> getRawMaterialsLotList() {
//        // 원자재 로트번호 목록을 DAO로부터 조회
//        return rawMaterialsLotDAO.getRawMaterialsLotList();
//    }
//
//    // 원자재 로트번호 수정
//    @Override
//    public void updateRawMaterialsLot(RawMaterialsLotVO rawMaterialsLot) {
//        // 원자재 로트번호 수정 작업
//        rawMaterialsLotDAO.updateRawMaterialsLot(rawMaterialsLot);
//    }
//
//    // 원자재 로트번호 삭제
//    @Override
//    public void deleteRawMaterialsLot(String rmlNo, int rmlBno) {
//        // 원자재 로트번호 삭제 작업
//        rawMaterialsLotDAO.deleteRawMaterialsLot(rmlNo, rmlBno);
//    }
//
//    // 로트번호에 대한 다음 순번 조회
//    @Override
//    public int getNextBnoForLot(String rmlNo) {
//        // 원자재 로트번호에 대한 다음 순번 조회
//        return rawMaterialsLotDAO.getNextBnoForLot(rmlNo);
//    }
//}
