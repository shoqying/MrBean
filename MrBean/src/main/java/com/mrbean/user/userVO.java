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
        ADMIN, MANAGER, MEMBER;

        /**
         * Enum 값 유효성 확인 메서드
         * @param value 문자열로 전달받은 역할
         * @return 역할이 유효하면 true, 아니면 false
         */
        public static boolean isValid(String value) {
            for (Role role : Role.values()) {
                if (role.name().equalsIgnoreCase(value)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 문자열을 Enum으로 변환하는 메서드
         * @param value 문자열로 전달받은 역할
         * @return 변환된 Role Enum 값
         * @throws IllegalArgumentException 유효하지 않은 값일 경우 예외 발생
         */
        public static Role fromString(String value) {
            for (Role role : Role.values()) {
                if (role.name().equalsIgnoreCase(value)) {
                    return role;
                }
            }
            throw new IllegalArgumentException("Invalid Role value: " + value);
        }
    }
}
