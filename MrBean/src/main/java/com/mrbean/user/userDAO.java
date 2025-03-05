package com.mrbean.user;

import java.util.List;
import java.util.Map;

import com.mrbean.productionplan.ProductionPlanVO;

public interface userDAO {
    userVO getUserById(String userId); // 사용자 단일 조회
    List<userVO> getAllUsers();        // 모든 사용자 조회
    void createUser(userVO user);      // 사용자 추가
    void updateUser(userVO user);      // 사용자 업데이트
    void updatePassword(userVO user); // 사용자 비밀번호 업데이트
    List<userVO> getUsersByRole(String role); // 특정 직급 사용자 조회
    int countUsersByRole(String role); // 역할별 사용자 수 조회
    List<ProductionPlanVO> getUserProductionPlans(String createdBy); // 생산 계획 가져오기
    userVO findUserByDetails(Map<String, Object> userDetails);
    userVO getUserByUsername(String username); // 사용자 이름으로 조회

}