package com.bethesda.kcic.about.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
@RequestMapping("/about")
public class AboutController {

    @RequestMapping(value = "/sub01")
    public ModelAndView goSub01(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/about/sub_0101.html");
        return mav;
    }

    @RequestMapping(value = "/sub02")
    public ModelAndView goSub02(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/about/sub_0102.html");
        return mav;
    }

    @RequestMapping(value = "/sub03")
    public ModelAndView goSub03(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/about/sub_0103.html");
        return mav;
    }

}

