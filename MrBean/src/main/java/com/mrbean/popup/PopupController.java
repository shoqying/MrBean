package com.mrbean.popup;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PopupController {

    @GetMapping("/popup/jusoPopup")
    public ModelAndView showJusoPopup() {
        return new ModelAndView("popup/jusoPopup");
    }

    @GetMapping("/api/jusoCallback")
    public void jusoCallback(
            @RequestParam("roadFullAddr") String roadFullAddr,
            @RequestParam("addrDetail") String addrDetail,
            @RequestParam("zipNo") String zipNo) {
        // Handle the callback data here
    }
}