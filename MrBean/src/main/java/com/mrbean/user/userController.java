package com.mrbean.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.mrbean.common.annotation.NoLoginCheck;
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
import com.mrbean.user.userVO.Role;


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
    public String registerUser(@RequestParam("uUserid") String uUserid,
                               @RequestParam("uUsername") String uUsername,
                               @RequestParam("uEmail") String uEmail,
                               @RequestParam("uPhonenumber") String uPhonenumber,
                               @RequestParam("uPasswordhash") String uPasswordhash,
                               @RequestParam("confirmPassword") String confirmPassword,
                               @RequestParam("uRoleenum") String uRoleenum,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        // 비밀번호와 비밀번호 확인 비교
        if (!uPasswordhash.equals(confirmPassword)) {
            model.addAttribute("error", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return "user/register"; // 회원가입 페이지로 다시 이동
        }

        // 아이디 중복 확인
        if (userService.getUserById(uUserid) != null) {
            model.addAttribute("error", "이미 존재하는 회원입니다.");
            return "user/register"; // 회원가입 페이지로 다시 이동
        }

        // Role 값 검증
        if (!Role.isValid(uRoleenum)) {
            model.addAttribute("error", "유효하지 않은 역할 값입니다.");
            return "user/register"; // 회원가입 페이지로 다시 이동
        }

        // 회원 데이터 생성 및 저장
        userVO userVO = new userVO();
        userVO.setUUserid(uUserid);
        userVO.setUUsername(uUsername);
        userVO.setUEmail(uEmail);
        userVO.setUPhonenumber(uPhonenumber);
        userVO.setUPasswordhash(passwordEncoder.encode(uPasswordhash));
        userVO.setURoleenum(Role.fromString(uRoleenum)); // 문자열을 Enum으로 변환

        userService.createUser(userVO);

        // 성공 메시지 전달
        redirectAttributes.addFlashAttribute("success", "회원가입이 완료되었습니다.");
        return "redirect:/user/main"; // 메인 페이지로 리다이렉트
    }


    

    @GetMapping("/userByRole")
    public String getUsersByRole(@RequestParam("role") String role, Model model) {
        List<userVO> users = userService.getUsersByRole(role); // 역할별 사용자 목록 가져오기
        model.addAttribute("users", users); // 모델에 사용자 데이터 추가
        return "user/userList"; // JSP 페이지 경로 (userList.jsp)
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

    @NoLoginCheck
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

        
   
     // 비밀번호 찾기 - 정보 확인 페이지
        @GetMapping("/findPassword1")
        public String findPassword1Page() {
            // 단순히 페이지 반환
            return "user/findPassword1"; // 비밀번호 찾기 페이지
        }

        @PostMapping("/findPassword1")
        public String verifyUserDetails(@RequestParam("username") String username,
                                        @RequestParam("name") String name,
                                        @RequestParam("email") String email,
                                        @RequestParam("phoneNumber") String phoneNumber,
                                        Model model) {
            userVO user = userService.findUserByDetails(username, name, email, phoneNumber);

            if (user == null) {
                model.addAttribute("error", "입력한 정보와 일치하는 사용자가 없습니다.");
                return "user/findPassword1";
            }

            // 사용자 확인 후 임시 비밀번호 생성
            String tempPassword = UUID.randomUUID().toString().substring(0, 8); // 8자리 임시 비밀번호
            user.setUPasswordhash(passwordEncoder.encode(tempPassword)); // 비밀번호 암호화 후 저장
            userService.updatePassword(user);

            // 성공 메시지 및 임시 비밀번호를 모델에 추가
            model.addAttribute("success", "사용자 정보가 확인되었습니다.");
            model.addAttribute("tempPassword", tempPassword); // 임시 비밀번호 추가
            return "user/findPassword2"; // JSP 페이지로 이동
        }




        @GetMapping("/findPassword2")
        public String findPassword2Page(@RequestParam("username") String username, Model model) {
            // username을 기반으로 사용자 정보 확인
            userVO user = userService.getUserByUsername(username);

            if (user == null) {
                // 사용자가 없으면 다시 findPassword1 페이지로 이동
                model.addAttribute("error", "사용자를 찾을 수 없습니다.");
                return "redirect:/user/findPassword1";
            }

            // 사용자 정보를 모델에 추가하여 페이지 렌더링
            model.addAttribute("username", username);
            return "user/findPassword2";
        }

        @PostMapping("/findPassword2")
        public String resetPassword(@RequestParam(value = "username", required = false) String username,
                                    Model model) {
            if (username == null || username.isEmpty()) {
                model.addAttribute("error", "올바르지 않은 요청입니다. username 파라미터가 없습니다.");
                return "redirect:/user/findPassword1"; // 비밀번호 찾기 1단계로 리다이렉트
            }

            // 사용자 정보 확인
            userVO user = userService.getUserByUsername(username);
            if (user == null) {
                model.addAttribute("error", "사용자를 찾을 수 없습니다.");
                return "redirect:/user/findPassword1"; // 정보 확인 페이지로 리다이렉트
            }

            // 임시 비밀번호 생성
            String tempPassword = UUID.randomUUID().toString().substring(0, 8); // 8자리 임시 비밀번호
            user.setUPasswordhash(passwordEncoder.encode(tempPassword)); // 암호화된 임시 비밀번호 설정
            userService.updatePassword(user); // 비밀번호 업데이트

            // 임시 비밀번호를 모델에 추가하여 뷰 페이지에 표시
            model.addAttribute("success", "임시 비밀번호가 생성되었습니다.");
            model.addAttribute("tempPassword", tempPassword);
            return "user/findPassword2"; // 동일 페이지에서 임시 비밀번호 표시
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

        
    

        @GetMapping("/user/mcount")
        public String showGraph(Model model, HttpSession session) {
            Object loggedInUser = session.getAttribute("loggedInUser");

            if (loggedInUser == null) {
                return "redirect:/user/login";
            }

            int adminCount = userService.countUsersByRole("ADMIN");
            int managerCount = userService.countUsersByRole("MANAGER");
            int memberCount = userService.countUsersByRole("MEMBER");

            System.out.println("Controller - Admin Count: " + adminCount);
            System.out.println("Controller - Manager Count: " + managerCount);
            System.out.println("Controller - Member Count: " + memberCount);

            model.addAttribute("adminCount", adminCount);
            model.addAttribute("managerCount", managerCount);
            model.addAttribute("memberCount", memberCount);

            return "user/mcount";
        }

        
        @GetMapping("/process")
        public String showProcessPage(Model model, HttpSession session) {
            // 세션에서 로그인된 사용자 정보를 가져오기
            userVO loggedInUser = (userVO) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                // 로그인되지 않았을 경우 로그인 페이지로 리다이렉트
                return "redirect:/user/login";
            }

            // 로그인된 사용자 기반 생산 계획 목록 가져오기
            String createdBy = loggedInUser.getUUsername(); // 사용자 이름 또는 ID를 기준으로 데이터 필터링
            ProductionPlanVO filter = new ProductionPlanVO();
            filter.setCreatedBy(createdBy); // 등록자 기준으로 데이터 필터링

            List<ProductionPlanVO> planList = productionplanService.getPlanList(filter);

            // 데이터 확인 로그 출력
            if (planList == null || planList.isEmpty()) {
                System.out.println("planList가 비어 있습니다. 데이터베이스 쿼리를 확인하세요.");
            } else {
                for (ProductionPlanVO plan : planList) {
                    System.out.println("데이터 확인 - 계획 ID: " + plan.getPlanId());
                    System.out.println("데이터 확인 - 계획 번호: " + plan.getPlanNumber());
                    System.out.println("데이터 확인 - 시작 날짜: " + plan.getPlanStartDate());
                  
                 
                }
            }

            // JSP로 데이터 전달
            model.addAttribute("planList", planList);
            return "user/process"; // JSP 파일 경로
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

