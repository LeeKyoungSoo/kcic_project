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
@RequestMapping("/noticeSpace")
public class GuestSpaceController {
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static final String TAG_USER = "METADATA";

    @Value("${excel.fileDownPath.window}")
    private String fileDownPathWin;

    @Value("${excel.fileDownPath.linux}")
    private String fileDownPathLinux;

    @Autowired
    BbsBoardService bbsBoardService;


    /**
     * 공지사항 목록
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub02")
    public ModelAndView goSub02(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0602.html");
        return mav;
    }

    /**
     * 공지사항 상세보기
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub02View")
    public ModelAndView goSub02View(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();

        mav.addObject("param", request);
        mav.setViewName("content/memberSpace/sub_0602_view.html");
        return mav;
    }

    /**
     * 공지사항 등록,수정
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub02Edit")
    public ModelAndView goSub02Edit(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();

        mav.addObject("param", request);
        mav.setViewName("content/memberSpace/sub_0602_edit.html");
        return mav;
    }

    /**
     * 자료실 목록
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub03")
    public ModelAndView goSub03(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0603.html");
        return mav;
    }

    /**
     * 자료실 상세보기
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub03View")
    public ModelAndView goSub03View(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0603_view.html");
        return mav;
    }

    /**
     * 자료실 등록,수정
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub03Edit")
    public ModelAndView goSub03Edit(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();

        mav.addObject("param", request);
        mav.setViewName("content/memberSpace/sub_0603_edit.html");
        return mav;
    }

    /**
     * 일정관리 목록
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub04")
    public ModelAndView goSub04(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0604.html");
        return mav;
    }

    /**
     * 일정관리 상세보기
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub04View")
    public ModelAndView goSub04View(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0604_view.html");
        return mav;
    }

    /**
     * 일정관리 등록,수정
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub04Edit")
    public ModelAndView goSub04Edit(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();

        mav.addObject("param", request);
        mav.setViewName("content/memberSpace/sub_0604_edit.html");
        return mav;
    }

    @RequestMapping(value = "/sub05")
    public ModelAndView goSub05(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0605.html");
        return mav;
    }

    /**
     * 보도자료 목록
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub06")
    public ModelAndView goSub06(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0606.html");
        return mav;
    }

    /**
     * 보도자료 상세보기
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub06View")
    public ModelAndView goSub06View(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0606_view.html");
        return mav;
    }

    /**
     * 보도자료 등록,수정
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub06Edit")
    public ModelAndView goSub06Edit(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();

        mav.addObject("param", request);
        mav.setViewName("content/memberSpace/sub_0606_edit.html");
        return mav;
    }
}
