package com.bethesda.kcic.memberspace.controller;

import com.bethesda.kcic.memberspace.service.BbsBoardService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
@RequestMapping("/memberSpace")
public class MemberSpaceController {
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static final String TAG_USER = "METADATA";

    @Value("${excel.fileDownPath.window}")
    private String fileDownPathWin;

    @Value("${excel.fileDownPath.linux}")
    private String fileDownPathLinux;

    @Autowired
    BbsBoardService bbsBoardService;

    @RequestMapping(value = "/sub01")
    public ModelAndView goSub01(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0601.html");
        return mav;
    }

    @RequestMapping(value = "/sub02")
    public ModelAndView goSub02(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0602.html");
        return mav;
    }

    @RequestMapping(value = "/sub02View")
    public ModelAndView goSub02View(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();

        mav.addObject("param", request);
        mav.setViewName("content/memberSpace/sub_0602_view.html");
        return mav;
    }

    @RequestMapping(value = "/sub03")
    public ModelAndView goSub03(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0603.html");
        return mav;
    }

    @RequestMapping(value = "/sub03View")
    public ModelAndView goSub03View(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0603_view.html");
        return mav;
    }

    @RequestMapping(value = "/sub04")
    public ModelAndView goSub04(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0604.html");
        return mav;
    }

    @RequestMapping(value = "/sub05")
    public ModelAndView goSub05(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0605.html");
        return mav;
    }

}
