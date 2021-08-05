package com.bethesda.kcic.user.controller;

import com.bethesda.kcic.datasales.domain.StudyAchieveVO;
import com.bethesda.kcic.security.CustomEncrypt;
import com.bethesda.kcic.user.domain.UsersVO;
import com.bethesda.kcic.user.service.UsersService;
import com.bethesda.kcic.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/dataView")
    public HashMap goDataView(UsersVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

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
        String resultMsg = (nResultCode > 0) ?  "사용자정보가 등록 되었습니다.":"등록 실패 입니다.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }

    @PostMapping("/dataUpdate")
    public HashMap goDataUpdate(UsersVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int nResultCode = usersService.uptData(vo);
        String resultMsg = (nResultCode > 0) ?  "사용자정보가 수정 되었습니다.":"수정 실패 입니다.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }

    @PostMapping("/dataUpdateSp")
    public HashMap goDataUpdateSp(UsersVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int nResultCode = usersService.uptDataSp(vo);
        String resultMsg = (nResultCode > 0) ?  "사용자정보가 수정 되었습니다.":"수정 실패 입니다.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
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
}
