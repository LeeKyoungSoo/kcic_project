package com.bethesda.kcic.registry.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
@RequestMapping("/registry")
public class RegistryController {

    @RequestMapping(value = "/sub01")
    public ModelAndView goSub01(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/registry/sub_02001.html");
        return mav;
    }

    @RequestMapping(value = "/sub02")
    public ModelAndView goSub02(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/registry/sub_02001.html");
        return mav;
    }

    @RequestMapping(value = "/sub03")
    public ModelAndView goSub03(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/registry/sub_02001.html");
        return mav;
    }

    @RequestMapping(value = "/sub04")
    public ModelAndView goSub04(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/registry/sub_0201.html");
        return mav;
    }

    @RequestMapping(value = "/sub05")
    public ModelAndView goSub05(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/registry/sub_0201.html");
        return mav;
    }

    @RequestMapping(value = "/sub06")
    public ModelAndView goSub06(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/registry/sub_0201.html");
        return mav;
    }

    @RequestMapping(value = "/sub07")
    public ModelAndView goSub07(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/registry/sub_0201.html");
        return mav;
    }
}
