package com.bethesda.kcic.user.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
@RequestMapping("/users")
public class UsersController {
    @RequestMapping(value = "/registmember")
    public ModelAndView goReistMenger(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/users/registmember.html");
        return mav;
    }
}
