package com.mrbean.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class userVO {

    private String uUserid;         // 사용자 고유 ID
    private String uUsername;       // 사용자 이름
    private String uEmail;          // 사용자 이메일 (로그인 ID)
    private String uPhonenumber;    // 사용자 전화번호

    @ToString.Exclude
    private String uPasswordhash;   // 사용자 비밀번호 (해시)

    private Role uRoleenum;         // 역할
    private Integer uCreatedby;     // 계정을 만든 사용자 ID
    private Timestamp uCreateat;    // 계정 생성 날짜
    private Timestamp uUpdatedat;   // 계정 수정 날짜

    // Role Enum 정의
    public enum Role {
        ADMIN, MANAGER, MEMBER
    }

    // Enum 변환 메서드
    public static Role fromString(String value) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }
}
