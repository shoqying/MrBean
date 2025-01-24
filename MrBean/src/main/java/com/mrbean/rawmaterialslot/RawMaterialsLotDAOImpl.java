//package com.mrbean.rawmaterialslot;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//@Repository
//public class RawMaterialsLotDAOImpl implements RawMaterialsLotDAO {
//
//    // JdbcTemplate을 자동으로 주입받습니다.
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    // 원자재 로트번호 추가
//    @Override
//    public void addRawMaterialsLot(RawMaterialsLotVO rawMaterialsLot) {
//        String sql = "INSERT INTO rawmetarials_lot (rml_no, rm_code, w_code, rml_date, rml_batch) " +
//                     "VALUES (?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql, rawMaterialsLot.getRmlNo(), rawMaterialsLot.getRmCode(),
//                            rawMaterialsLot.getWCode(), rawMaterialsLot.getRmlDate(), rawMaterialsLot.getRmlBatch());
//    }
//
//    // 원자재 로트번호 목록 조회
//    @Override
//    public List<RawMaterialsLotVO> getRawMaterialsLotList() {
//        String sql = "SELECT * FROM rawmetarials_lot";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            RawMaterialsLotVO rawMaterialsLot = new RawMaterialsLotVO();
//            rawMaterialsLot.setRmlNo(rs.getString("rml_no"));
//            rawMaterialsLot.setRmCode(rs.getString("rm_code"));
//            rawMaterialsLot.setWCode(rs.getString("w_code"));
//            rawMaterialsLot.setRmlDate(rs.getTimestamp("rml_date"));
//            rawMaterialsLot.setRmlBatch(rs.getString("rml_batch"));
//            return rawMaterialsLot;
//        });
//    }
//
//    // 원자재 로트번호 수정
//    @Override
//    public void updateRawMaterialsLot(RawMaterialsLotVO rawMaterialsLot) {
//        String sql = "UPDATE rawmetarials_lot SET rm_code = ?, w_code = ?, rml_date = ?, rml_batch = ? " +
//                     "WHERE rml_no = ? AND rml_bno = ?";
//        jdbcTemplate.update(sql, rawMaterialsLot.getRmCode(), rawMaterialsLot.getWCode(),
//                            rawMaterialsLot.getRmlDate(), rawMaterialsLot.getRmlBatch(),
//                            rawMaterialsLot.getRmlNo(), rawMaterialsLot.getRmlBno());
//    }
//
//    // 원자재 로트번호 삭제
//    @Override
//    public void deleteRawMaterialsLot(String rmlNo, int rmlBno) {
//        String sql = "DELETE FROM rawmetarials_lot WHERE rml_no = ? AND rml_bno = ?";
//        jdbcTemplate.update(sql, rmlNo, rmlBno);
//    }
//
//    // 로트번호에 대한 다음 순번 조회
//    @Override
//    public int getNextBnoForLot(String rmlNo) {
//        String sql = "SELECT COALESCE(MAX(rml_bno), 0) + 1 FROM rawmetarials_lot WHERE rml_no = ?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{rmlNo}, Integer.class);
//    }
//}
