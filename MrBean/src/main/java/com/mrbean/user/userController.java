package com.mrbean.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mrbean.productionplan.ProductionplanController;


// http://localhost:8088/user/example
// http://localhost:8088/user/login
// http://localhost:8088/user/register
// http://localhost:8088/user/sample

@Controller
@RequestMapping(value = "/user/*")
public class userController {
   private static final Logger logger = LoggerFactory.getLogger(userController.class);
    @Autowired
    private userService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // 회원가입 페이지
    @GetMapping("/register")
    public String registerPage() {
        return "user/register"; // 회원가입 JSP 경로
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute userVO userVO, RedirectAttributes redirectAttributes) {
        logger.info("회원가입 호출");

        if (userVO.getUPasswordhash() != null) {
            userVO.setUPasswordhash(passwordEncoder.encode(userVO.getUPasswordhash()));
        }

        userService.createUser(userVO);
        logger.info("회원가입 완료");

        // 회원가입 성공 메시지 전달
        redirectAttributes.addFlashAttribute("success", "register");

        return "redirect:/user/main"; // 회원가입 후 메인 페이지로 리다이렉트
    }
    @RestController
    @RequestMapping("/admin")
    public class AdminController {

        @Autowired
        private passwordEncryptor passwordEncryptor;

        @GetMapping("/encryptPasswords")
        public String encryptPasswords() {
            passwordEncryptor.encryptExistingPasswords();
            return "기존 비밀번호 암호화가 완료되었습니다.";
        }
    }
    
    @GetMapping("/login")
    public String loginPage() {
        return "user/login"; // 로그인 JSP 경로 반환
    }
    
   

    @PostMapping("/login")
    public String loginUser(@ModelAttribute userDTO loginDTO, RedirectAttributes redirectAttributes, HttpSession session) {
        userVO user = userService.getUserById(loginDTO.getUUserid());

        if (user == null || user.getUPasswordhash() == null || 
            !passwordEncoder.matches(loginDTO.getUPasswordhash(), user.getUPasswordhash())) {
            redirectAttributes.addFlashAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/user/login"; // 로그인 실패 시 리다이렉트
        }

        // 로그인 성공
        session.setAttribute("loggedInUser", user);
        redirectAttributes.addFlashAttribute("success", "login"); // 성공 메시지로 "login" 전달
        return "redirect:/user/main"; // 메인 페이지로 리다이렉트
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate(); // 세션 무효화
        redirectAttributes.addFlashAttribute("success", "logout"); // 로그아웃 메시지 전달
        return "redirect:/user/main"; // 메인 페이지로 리다이렉트
    }

        @GetMapping("/user/info")
        public String getUserInfo(HttpSession session, Model model) {
            userVO loggedInUser = (userVO) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                logger.info("세션에 로그인된 사용자가 없습니다.");
                return "redirect:/user/login";
            }
            logger.info("세션에서 가져온 사용자 데이터: {}", loggedInUser);
            model.addAttribute("user", loggedInUser);
            return "user/info";
        }


     // 비밀번호 확인 페이지
        @GetMapping("/passwordcheck")
        public String passwordCheckPage() {
            return "user/passwordcheck"; // 비밀번호 확인 JSP 경로
        }

        @PostMapping("/passwordcheck")
        public String verifyPassword(@RequestParam("password") String password, HttpSession session, Model model) {
            userVO loggedInUser = (userVO) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return "redirect:/user/login";
            }

            // 비밀번호 검증
            if (!passwordEncoder.matches(password, loggedInUser.getUPasswordhash())) {
                return "redirect:/user/passwordcheck?error=true";
            }

            // 비밀번호가 맞다면 수정 페이지로 이동
            return "redirect:/user/update";
        }


        // 내정보 수정 페이지
        @GetMapping("/update")
        public String updateInfoPage(HttpSession session, Model model) {
            userVO loggedInUser = (userVO) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return "redirect:/user/login";
            }
            model.addAttribute("user", loggedInUser);
            return "user/update";
        }

        @PostMapping("/update")
        public String updateUserInfo(@ModelAttribute userVO updatedUser, HttpSession session, RedirectAttributes redirectAttributes) {
            userVO loggedInUser = (userVO) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return "redirect:/user/login";
            }

            // 업데이트된 정보 적용
            loggedInUser.setUUsername(updatedUser.getUUsername());
            loggedInUser.setUEmail(updatedUser.getUEmail());
            loggedInUser.setUPhonenumber(updatedUser.getUPhonenumber());
            loggedInUser.setURoleenum(updatedUser.getURoleenum());

            // DB에 저장
            userService.updateUser(loggedInUser);

            // 수정 완료 메시지 전달
            redirectAttributes.addFlashAttribute("success", "update");

            return "redirect:/user/main"; // main.jsp로 리다이렉트
        }

    
 

    // 샘플페이지 연결
    @RequestMapping(value = "/sample",method = RequestMethod.GET )
    public String samplePage() {
        
        System.out.println("출력");

        return "user/sample";
}
    
    
    // 예시페이지 연결
    //@GetMapping(value = "/example")
    @RequestMapping(value = "/example",method = RequestMethod.GET )
    public String examplePage() {
        
        System.out.println("출력");

        return "user/example";
}
    
    // 메인페이지 연결
    //@GetMapping(value = "/main")
    @RequestMapping(value = "/main",method = RequestMethod.GET )
    public String mainPage() {
        
        System.out.println("출력");

        return "user/main";
}
    
    
}

