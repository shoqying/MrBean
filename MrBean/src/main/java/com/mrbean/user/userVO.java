package com.mrbean.user;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class userVO {

    private String userId;         // 사용자 고유 ID
    private String userName;       // 사용자 이름
    private String email;          // 사용자 이메일 (로그인 ID)
    private String phoneNumber;    // 사용자 전화번호
    private String password;       // 사용자 비밀번호
    private Role role;             // 역할
    private Integer createdBy;     // 계정을 승인한 사용자 ID
    private Timestamp createdAt;   // 계정 생성 날짜
    private Timestamp updatedAt;   // 계정 수정 날짜

    // Role Enum 정의
    public enum Role {
        ADMIN, MANAGER, MEMBER
    }
}
