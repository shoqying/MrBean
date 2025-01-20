package com.mrbean.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


// http://localhost:8088/user/main
@Controller
@RequestMapping(value = "/user/*")
public class userController {

    @Autowired
    private userService userService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    // 회원가입 (ADMIN, MANAGER, MEMBER)
	/*
	 * @PostMapping("/signup") public void signUp(@RequestBody userVO user) { //
	 * 비밀번호 암호화 // user.setPassword(passwordEncoder.encode(user.getPassword())); //
	 * userService.createUser(user); }
	 * 
	 * // 로그인 (별도의 Security 설정에서 인증/인가 처리)
	 * 
	 * @PostMapping("/login") public String login() { return "Login successful!"; }
	 * 
	 * // 회원정보 조회 (본인만)
	 * 
	 * @GetMapping("/me")
	 * 
	 * @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'MEMBER')") public userVO
	 * getMyInfo(@RequestParam String userId) { return
	 * userService.getUserById(userId); }
	 * 
	 * // 회원정보 수정 (본인만)
	 * 
	 * @PutMapping("/me")
	 * 
	 * @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'MEMBER')") public void
	 * updateMyInfo(@RequestBody userVO user) { userService.updateUser(user); }
	 * 
	 * // 비밀번호 변경
	 * 
	 * @PutMapping("/me/password")
	 * 
	 * @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'MEMBER')") public void
	 * changePassword(@RequestParam String userId, @RequestParam String newPassword)
	 * { userVO user = userService.getUserById(userId); //
	 * user.setPassword(passwordEncoder.encode(newPassword));
	 * userService.updateUser(user); }
	 * 
	 * // 전체 회원 조회 (ADMIN만)
	 * 
	 * @GetMapping
	 * 
	 * @PreAuthorize("hasRole('ADMIN')") public List<userVO> getAllUsers() { return
	 * userService.getAllUsers(); }
	 * 
	 * // 특정 회원 조회 (ADMIN, MANAGER)
	 * 
	 * @GetMapping("/{userId}")
	 * 
	 * @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')") public userVO
	 * getUserById(@PathVariable String userId) { return
	 * userService.getUserById(userId); }
	 * 
	 * // 권한 부여 (ADMIN만)
	 * 
	 * @PutMapping("/{userId}/role")
	 * 
	 * @PreAuthorize("hasRole('ADMIN')") public void assignRole(@PathVariable String
	 * userId, @RequestParam String role) { userVO user =
	 * userService.getUserById(userId);
	 * user.setRole(userVO.Role.valueOf(role.toUpperCase()));
	 * userService.updateUser(user); }
	 */

    // 메인 페이지 연결
    //@GetMapping(value = "/main")
    @RequestMapping(value = "/main",method = RequestMethod.GET )
    public String mainPage() {
        
        System.out.println("ddddddddddddddddddddddddddddddddddddddd");

        return "user/main";
}
    
}

