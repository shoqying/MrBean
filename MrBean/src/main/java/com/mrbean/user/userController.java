package com.mrbean.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.mrbean.productionplan.ProductionPlanVO;
import com.mrbean.productionplan.ProductionplanController;
import com.mrbean.productionplan.ProductionplanService;


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


        // 메인페이지 연결
        //@GetMapping(value = "/main")
        @RequestMapping(value = "/main",method = RequestMethod.GET )
        public String mainPage() {
            
            System.out.println("출력");

            return "user/main";
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

        
     // 비밀번호 변경 컨트롤러 추가
        @GetMapping("/changePassword")
        public String changePasswordPage(HttpSession session, Model model) {
            userVO loggedInUser = (userVO) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return "redirect:/user/login"; // 로그인하지 않은 상태에서는 로그인 페이지로 리다이렉트
            }
            return "user/changePassword"; // 비밀번호 변경 JSP 페이지
        }

        @PostMapping("/changePassword")
        public String changePassword(@RequestParam("currentPassword") String currentPassword,
                                     @RequestParam("newPassword") String newPassword,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
            userVO loggedInUser = (userVO) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return "redirect:/user/login";
            }

            // 현재 비밀번호 확인
            if (!passwordEncoder.matches(currentPassword, loggedInUser.getUPasswordhash())) {
                redirectAttributes.addFlashAttribute("error", "현재 비밀번호가 일치하지 않습니다.");
                return "redirect:/user/changePassword";
            }

            // 비밀번호 업데이트
            loggedInUser.setUPasswordhash(passwordEncoder.encode(newPassword));
            userService.updateUser(loggedInUser);

            // 로그로 확인
            System.out.println("비밀번호 변경 성공 메시지 전달");
            redirectAttributes.addFlashAttribute("success", "passwordChange");

            return "redirect:/user/main";
        }

        
        @GetMapping("/list")
        public String userList(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
            // 로그인된 사용자 정보 가져오기
            userVO loggedInUser = (userVO) session.getAttribute("loggedInUser");

            if (loggedInUser == null) {
                // 로그인하지 않은 사용자는 접근 불가
                return "redirect:/user/login";
            }

            // 권한에 따라 사용자 목록 조회
            List<userVO> userList;
            if ("ADMIN".equals(loggedInUser.getURoleenum().name())) {
                userList = userService.getAllUsers(); // 전체 사용자 조회
            } else if ("MANAGER".equals(loggedInUser.getURoleenum().name())) {
                userList = userService.getUsersByRole("MEMBER"); // MEMBER만 조회
            } else {
                // MEMBER는 접근 불가, 경고 메시지 설정
                redirectAttributes.addFlashAttribute("error", "권한이 없습니다.");
                return "redirect:/user/main"; // 메인 페이지로 리다이렉트
            }

            model.addAttribute("userList", userList);
            return "user/list"; // user/list.jsp 경로
        }

        
    
        @GetMapping("/user/graph")
        public String showGraph(Model model, HttpSession session) {
            // 세션에서 로그인된 사용자 정보 확인
            Object loggedInUser = session.getAttribute("loggedInUser"); // 세션에 저장된 사용자 정보

            if (loggedInUser == null) {
                // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
                return "redirect:/user/login";
            }

            // 로그인된 경우 그래프 데이터를 추가
            int adminCount = 10;    // Admin 수
            int managerCount = 20;  // Manager 수
            int memberCount = 70;   // Member 수

            model.addAttribute("adminCount", adminCount);
            model.addAttribute("managerCount", managerCount);
            model.addAttribute("memberCount", memberCount);

            return "user/graph"; // graph.jsp 페이지 반환
        }
    
        

    // 샘플페이지 연결
    @RequestMapping(value = "/sample",method = RequestMethod.GET )
    public String samplePage() {
        
        System.out.println("출력");

        return "user/sample";
}
    

    @Autowired
    private ProductionplanService productionplanService;

    @GetMapping("/example")
    public String examplePage(Model model) {
        List<ProductionPlanVO> planList = productionplanService.getPlanList(new ProductionPlanVO());
        model.addAttribute("planList", planList);
        return "user/example";
    }

}

