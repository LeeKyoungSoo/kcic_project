package com.bethesda.kcic.user.controller;

import com.bethesda.kcic.security.CustomEncrypt;
import com.bethesda.kcic.user.domain.UsersVO;
import com.bethesda.kcic.user.service.UsersService;
import com.bethesda.kcic.util.DateTimeUtil;
import com.bethesda.kcic.util.MailSend;
import com.bethesda.kcic.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/usersApi")
public class UsersRestfulController {
    @Autowired
    UsersService usersService;

    @Autowired
    MailSend mailSend;

    @PostMapping("/idSearch")
    public HashMap goIdSearch(UsersVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        UsersVO uservo = usersService.getDataSrch(vo);
        int resultCode = 300;
        String resultMsg = "";
        if(uservo != null){
            if ( uservo.getUseyn().equals("N")) {
                resultMsg = "stopUser";
            }
            else if ( !uservo.getEmail().equals(vo.getEmail())) {
                resultMsg = "noEmail";
            }
            else {
                resultCode = 200;
                resultMsg = uservo.getUserid();
            }
        }
        else{
            resultMsg = "fail";
        }
        resultMap.put("resultCode", resultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }


    @PostMapping("/pwSearch")
    public HashMap goPwSearch(UsersVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        UsersVO usersvo = usersService.getDataSrch(vo);
        int resultCode = 200;
        String resultMsg = "";
        if(usersvo != null){
            if ( usersvo.getUseyn().equals("N")) {
                resultMsg = "stopUser";
            }
            else if ( !usersvo.getEmail().equals(vo.getEmail())) {
                resultMsg = "noEmail";
            }
            else {
                String title = "?????????????????? ??????";
                String sendpw = WebUtil.getTempPassword(8);
                String body = sendpw;
                mailSend.SendEmail(vo.getEmail(), title, body);

                try {
                    sendpw = CustomEncrypt.encryptPassword(sendpw ,usersvo.getUserid());
                    vo.setUserpw(sendpw);
                    if ( usersService.uptSrchPw(vo) < 1 ) {
                        resultCode = 0;
                    }
                } catch (Exception e) {
                    resultCode = 0;
                    e.printStackTrace();
                }

                resultMsg = (resultCode > 0) ?  "????????????????????? ????????? ?????????????????????":"??????????????? ?????? ????????????.";
                resultMap.put("resultCode", resultCode);
                resultMap.put("resultMsg", resultMsg);
                return resultMap;
            }
        }
        else{
            resultMsg = "fail";
        }
        resultMap.put("resultCode", resultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }

    @PostMapping("/dataList")
    public HashMap goDataList(UsersVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        if ( (vo.getPageNum() == null) || vo.getPageNum().equals("") ) {
            vo.setOffSet(-1);
        }
        else {
            int nLimit = 10;
            if ( vo.getPageSize() != null && !vo.getPageSize().equals("") ) {
                nLimit = Integer.parseInt(vo.getPageSize());
            }
            int nOffSet = (Integer.parseInt(vo.getPageNum()) - 1) * nLimit;
            vo.setLimit(nLimit);
            vo.setOffSet(nOffSet);
        }

        int totalCnt = usersService.getDataCnt(vo);
        List<UsersVO> dataList = usersService.getDataList(vo);

        resultMap.put("totalCnt", totalCnt);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/getUserCheck")
    public HashMap getUserCheck(UsersVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();
        String returnVal = "N";
        UsersVO dataView = usersService.getUserCheck(vo);
        if ( dataView != null ) {
            returnVal = "Y";
        }
        resultMap.put("resultCode", returnVal);
        resultMap.put("resultMsg", returnVal);
        return resultMap;
    }

    @PostMapping("/dataView")
    public HashMap goDataView(UsersVO vo, HttpServletRequest request) throws Exception {
        HashMap resultMap = new HashMap<>();

        if (vo.getUid() == null || vo.getUid().equals("") ) {
            HttpSession session = request.getSession();
            vo.setUid(session.getAttribute("userKey").toString());
        }

        UsersVO dataView = usersService.getDataView(vo);
        resultMap.put("dataView", dataView);
        return resultMap;
    }

    @PostMapping("/dataInsert")
    public HashMap goDataInsert(UsersVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        vo.setUid("U00" + DateTimeUtil.getNowDateTime());
        vo.setUserpw(CustomEncrypt.encryptPassword(vo.getUserpw(), vo.getUserid()));

        int nResultCode = usersService.insData(vo);
        String resultMsg = (nResultCode > 0) ?  "?????????????????? ?????? ???????????????. ?????????????????? ???????????????.":"?????? ?????? ?????????.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }

    @PostMapping("/dataUpdate")
    public HashMap goDataUpdate(UsersVO vo, HttpServletRequest request) throws Exception {
        HashMap resultMap = new HashMap<>();

        if (vo.getUid() == null || vo.getUid().equals("") ) {
            HttpSession session = request.getSession();
            vo.setUid(session.getAttribute("userKey").toString());
        }

        if (vo.getUserlv() == null || vo.getUserlv().equals("") ) {
            HttpSession session = request.getSession();
            vo.setUserlv(session.getAttribute("userLv").toString());
        }

        if (vo.getUseyn() == null || vo.getUseyn().equals("") ) {
            vo.setUseyn("Y");
        }

        int nResultCode = usersService.uptData(vo);
        String resultMsg = (nResultCode > 0) ?  "?????????????????? ?????? ???????????????.":"?????? ?????? ?????????.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }

    @PostMapping("/dataUpdateSp")
    public HashMap goDataUpdateSp(UsersVO vo, HttpServletRequest request) throws Exception {
        HashMap resultMap = new HashMap<>();
        HttpSession session = request.getSession();
        String enpassword = null;
        try {
            enpassword = CustomEncrypt.encryptPassword(vo.getBeforUserpw() ,session.getAttribute("userId").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (vo.getUid() == null || vo.getUid().equals("") ) {
            vo.setUid(session.getAttribute("userKey").toString());
        }

        UsersVO loginVoIn = new UsersVO();
        loginVoIn.setSUserid(session.getAttribute("userId").toString());
        UsersVO user = usersService.getUserCheck(loginVoIn);

        if (!enpassword.equals(user.getUserpw())) {
            resultMap.put("resultCode", "200");
            resultMap.put("resultMsg", "?????? ??????????????? ?????? ?????? ????????????.");
            return resultMap;
        }
        else {
            try {
                enpassword = CustomEncrypt.encryptPassword(vo.getUserpw() ,session.getAttribute("userId").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            vo.setUserpw(enpassword);
            int nResultCode = usersService.uptDataSp(vo);
            String resultMsg = (nResultCode > 0) ?  "??????????????? ?????? ???????????????.":"?????? ????????? ?????? ????????????.";

            resultMap.put("resultCode", nResultCode);
            resultMap.put("resultMsg", resultMsg);
            return resultMap;
        }
    }

    @PostMapping("/dataDelete")
    public HashMap goDataDelete(UsersVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int nResultCode = usersService.delData(vo);
        String resultMsg = (nResultCode > 0) ?  "?????????????????? ?????? ???????????????.":"?????? ?????? ?????????.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }

    @PostMapping("/dataState")
    public HashMap dataState(UsersVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int nResultCode = usersService.uptState(vo);
        String resultMsg = (nResultCode > 0) ?  "?????????????????? ?????? ???????????????.":"?????? ?????? ?????????.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }
}
