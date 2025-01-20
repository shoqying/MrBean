package com.mrbean.popup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PopupController {

    @RequestMapping("/popup/jusoPopup")
    public String showJusoPopup() {
        // 여기에 추가적인 로직이 필요할 경우 처리할 수 있습니다.
        return "popup/jusoPopup";  // JSP 파일의 경로를 리턴
    }
}