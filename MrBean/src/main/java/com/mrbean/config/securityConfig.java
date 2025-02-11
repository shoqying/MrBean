package com.mrbean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/user/login", "/user/register", "/resources/**").permitAll() // 접근 허용 경로
                .anyRequest().authenticated()
                .and()
            .logout()
                .logoutUrl("/user/logout") // 로그아웃 URL
                .logoutSuccessUrl("/user/login") // 로그아웃 성공 후 이동할 페이지
                .invalidateHttpSession(true) // 세션 무효화
                .deleteCookies("JSESSIONID") // 쿠키 삭제
                .and()
            .formLogin()
                .loginPage("/user/login") // 로그인 페이지 URL
                .permitAll();
    }
    
    // PasswordEncoder 빈 추가
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoder 사용
    }
}
