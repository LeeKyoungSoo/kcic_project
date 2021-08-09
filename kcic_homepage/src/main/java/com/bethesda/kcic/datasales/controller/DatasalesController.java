package com.bethesda.kcic.datasales.controller;

import com.bethesda.kcic.metadata.service.StudyMetaDataService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
@RequestMapping("/datasales")
public class DatasalesController {

    @Autowired
    private StudyMetaDataService studyMetaDataService;

    @RequestMapping(value = "/sub01")
    public ModelAndView goSub01(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/datasales/sub_0401.html");
        return mav;
    }

    @RequestMapping(value = "/sub02")
    public ModelAndView goSub02(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("metaDataDomainList", studyMetaDataService.getStudyMetaDataDomainList());
        mav.setViewName("content/datasales/sub_0402.html");
        return mav;
    }

    @RequestMapping(value = "/sub03")
    public ModelAndView goSub03(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/datasales/sub_0403.html");
        return mav;
    }

    @RequestMapping(value = "/sub04")
    public ModelAndView goSub04(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/datasales/sub_0404.html");
        return mav;
    }

    @RequestMapping(value = "/sub04View")
    public ModelAndView goSub04View(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();

        mav.addObject("param", request);
        mav.setViewName("content/datasales/sub_0404_view.html");
        return mav;
    }

    @RequestMapping(value = "/sub04Edit")
    public ModelAndView goSub04Edit(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();

        mav.addObject("param", request);
        mav.setViewName("content/datasales/sub_0404_edit.html");
        return mav;
    }
}
