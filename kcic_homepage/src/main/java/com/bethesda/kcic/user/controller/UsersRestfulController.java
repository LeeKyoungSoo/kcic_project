package com.bethesda.kcic.user.controller;

import com.bethesda.kcic.datasales.domain.StudyAchieveVO;
import com.bethesda.kcic.security.CustomEncrypt;
import com.bethesda.kcic.user.domain.UsersVO;
import com.bethesda.kcic.user.service.UsersService;
import com.bethesda.kcic.util.DateTimeUtil;
import org.apache.catalina.User;
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
        String resultMsg = (nResultCode > 0) ?  "사용자정보가 등록 되었습니다. 로그인창으로 이동합니다.":"등록 실패 입니다.";

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
        String resultMsg = (nResultCode > 0) ?  "사용자정보가 수정 되었습니다.":"수정 실패 입니다.";

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
            resultMap.put("resultMsg", "현재 비밀번호가 일치 하지 않습니다.");
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
            String resultMsg = (nResultCode > 0) ?  "비밀번호가 수정 되었습니다.":"정보 변경이 실패 했습니다.";

            resultMap.put("resultCode", nResultCode);
            resultMap.put("resultMsg", resultMsg);
            return resultMap;
        }
    }

    @PostMapping("/dataDelete")
    public HashMap goDataDelete(UsersVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int nResultCode = usersService.delData(vo);
        String resultMsg = (nResultCode > 0) ?  "사용자정보가 삭제 되었습니다.":"삭제 실패 입니다.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }

    @PostMapping("/dataState")
    public HashMap dataState(UsersVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int nResultCode = usersService.uptState(vo);
        String resultMsg = (nResultCode > 0) ?  "사용자정보가 수정 되었습니다.":"수정 실패 입니다.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }
}
