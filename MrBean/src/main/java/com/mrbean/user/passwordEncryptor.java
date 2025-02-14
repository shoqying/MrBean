package com.mrbean.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class passwordEncryptor {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private userService userService;

    public void encryptExistingPasswords() {
        List<userVO> users = userService.getAllUsers(); // 모든 사용자 조회

        for (userVO user : users) {
            String rawPassword = user.getUPasswordhash();
            if (!rawPassword.startsWith("$2a$")) { // 암호화되지 않은 비밀번호 확인
                String encryptedPassword = passwordEncoder.encode(rawPassword);
                user.setUPasswordhash(encryptedPassword);

                // 암호화된 비밀번호를 업데이트
                userService.updatePassword(user);
                System.out.println("비밀번호 암호화 완료: " + user.getUUserid());
            }
        }
    }
}