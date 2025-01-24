package com.mrbean.user;

import lombok.Data;

@Data
public class userDTO {
    private String uUserid;       // 사용자 ID
    private String uUsername;     // 사용자 이름
    private String uEmail;        // 사용자 이메일
    private String uPhonenumber;  // 사용자 전화번호
    private String uRoleenum;     // 역할 (ADMIN, MANAGER, MEMBER)
    private String uPasswordhash;  // 사용자 비밀번호
}