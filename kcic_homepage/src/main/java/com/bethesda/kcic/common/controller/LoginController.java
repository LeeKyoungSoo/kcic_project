package com.bethesda.kcic.common.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
@RequestMapping("/accounts")
public class LoginController {
    @RequestMapping(value = "/login")
    public ModelAndView goLogin(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();

        //이전 페이지 저장
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);

        mav.setViewName("content/users/login.html");
        return mav;
    }

    @RequestMapping(value = "/accessDenied")
    public ModelAndView goAccessDenied(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/users/accessDenied.html");
        return mav;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView goLogout(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/users/login.html");
        return mav;
    }
}
