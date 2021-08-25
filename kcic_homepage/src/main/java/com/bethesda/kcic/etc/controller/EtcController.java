package com.bethesda.kcic.etc.controller;


import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
@RequestMapping("/etc")
public class EtcController {
    //홈페이지 이용안내
    @RequestMapping(value = "/etc01")
    public ModelAndView goEtc01(HttpServletRequest request) throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/etc/etc_0701.html");
        return mav;
    }
    //개인정보 처리방침
    @RequestMapping(value = "/etc02")
    public ModelAndView goEtc02(HttpServletRequest request) throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/etc/etc_0702.html");
        return mav;
    }
    //웹접근성 및 저장권 정책
    @RequestMapping(value = "/etc03")
    public ModelAndView goEtc03(HttpServletRequest request) throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/etc/etc_0703.html");
        return mav;
    }
}
