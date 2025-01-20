package com.mrbean.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


// http://localhost:8088/user/NewFile
// http://localhost:8088/user/main

@Controller
@RequestMapping(value = "/user/*")
public class userController {

    @Autowired
    private userService userService;

    
    // 회원가입 페이지
    @GetMapping("/register")
    public String registerPage() {
        return "user/register"; // 회원가입 JSP 경로
    }

    // 회원가입 처리
//    @PostMapping("/register")
//    public String registerUser(userVO user) {
//        // 추가적인 데이터 설정 (생성/수정 날짜 등)
//        user.setUCreatedat(new java.sql.Timestamp(System.currentTimeMillis()));
//        user.setUUpdatedat(new java.sql.Timestamp(System.currentTimeMillis()));
//
//        userService.createUser(user); // 회원가입 처리
//        return "redirect:/user/login"; // 회원가입 완료 후 로그인 페이지로 리다이렉트
//    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "user/login"; // 로그인 JSP 경로
    }

    // 로그인 처리
    @PostMapping("/login")
    public String loginUser(@RequestParam("userId") String userId,
                            @RequestParam("password") String password,
                            Model model) {
        userVO user = userService.getUserById(userId);

        if (user != null && user.getUPasswordhash().equals(password)) {
            // 로그인 성공 처리
            model.addAttribute("user", user);
            return "user/dashboard"; // 로그인 성공 후 대시보드 페이지로 이동
        } else {
            // 로그인 실패 처리
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "user/login"; // 다시 로그인 페이지로 이동
        }
    }
 
    
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
	 * dd
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

    @RequestMapping(value = "/sample",method = RequestMethod.GET )
    public String samplePage() {
        
        System.out.println("출력");

        return "user/sample";
}
    
    
    // 메인 페이지 연결
    //@GetMapping(value = "/example")
    @RequestMapping(value = "/example",method = RequestMethod.GET )
    public String mainPage() {
        
        System.out.println("출력");

        return "user/example";
}
    
    
    
}

