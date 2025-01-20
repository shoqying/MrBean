package com.mrbean.user;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class userVO {

    private String uUserid;         // 사용자 고유 ID
    private String uUsername;       // 사용자 이름
    private String uEmail;          // 사용자 이메일 (로그인 ID)
    private String uPhonenumber;    // 사용자 전화번호
    private String uPasswordhash;       // 사용자 비밀번호
    private Role uRoleenum;             // 역할
    private Integer uCreatedby;     // 계정을 승인한 사용자 ID
    private Timestamp uCreateat;   // 계정 생성 날짜
    private Timestamp uUpdatedat;   // 계정 수정 날짜

    // Role Enum 정의
    public enum Role {
        ADMIN, MANAGER, MEMBER
    }
}
