package com.mrbean.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginRequiredException.class)
    public ModelAndView handleLoginRequiredException(LoginRequiredException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user/login");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
}