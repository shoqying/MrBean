package com.mrbean.user;

import java.util.List;

public interface userService {
    userVO getUserById(String userId); // 사용자 단일 조회
    List<userVO> getAllUsers(); // 모든 사용자 조회
    void createUser(userVO user); // 사용자 추가
    void updateUser(userVO user); // 사용자 업데이트
    void updatePassword(userVO user); // 사용자 비밀번호 업데이트 추가
    List<userVO> getUsersByRole(String role); // 특정 회원 조회

}