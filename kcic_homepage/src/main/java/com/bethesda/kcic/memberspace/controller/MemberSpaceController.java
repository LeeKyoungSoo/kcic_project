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


    @RequestMapping(value = "/sub02Sale")
    public ModelAndView goSub02Sale(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("param", request);
        mav.setViewName("content/datasales/sub_0402_sale.html");
        return mav;
    }

    /**
     *  나의 스터디
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub01")
    public ModelAndView goSub01(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/sub_0601.html");
        return mav;
    }

    /**
     *  마이페이지
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub07")
    public ModelAndView goSub07(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/mypage.html");
        return mav;
    }


    /**
     *  분양신청관리
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub08")
    public ModelAndView goSub08(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/study_admin.html");
        return mav;
    }

    /**
     *  회원관리
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sub09")
    public ModelAndView goSub09(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("content/memberSpace/member_admin.html");
        return mav;
    }
}
