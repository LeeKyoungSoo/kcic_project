package com.bethesda.kcic.datasales.controller;

import com.bethesda.kcic.datasales.domain.DataSaleVO;
import com.bethesda.kcic.datasales.domain.StudyAchieveVO;
import com.bethesda.kcic.datasales.service.StudyAchieveService;
import com.bethesda.kcic.util.BaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/studyAcApi")
public class StudyAchieveRestfulController {

    @Autowired
    StudyAchieveService studyAchieveService;

    @PostMapping("/goStudyYearChartData")
    public HashMap goStudyYearChartData(DataSaleVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        List<Integer> chartYear = new ArrayList<>();
        List<Integer> chartData = new ArrayList<>();
        List<BaseMap> dataView = studyAchieveService.getStudyYearChartData();
        for (BaseMap oneData : dataView ) {
            chartYear.add(Integer.parseInt(oneData.get("years").toString()));
            chartData.add(Integer.parseInt(oneData.get("cnt").toString()));
        }
        resultMap.put("chartYear", chartYear);
        resultMap.put("chartData", chartData);

        return resultMap;
    }

    @PostMapping("/goManagerState")
    public HashMap goManagerState(DataSaleVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int nResultCode = studyAchieveService.uptManagerState(vo);
        String resultMsg = (nResultCode > 0) ?  "상태를 변경하였습니다..":"정상적으로 처리되지 않았습니다.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);

        return resultMap;
    }

    @PostMapping("/goStudyDataview")
    public HashMap goStudyDataview(DataSaleVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        DataSaleVO dataView = studyAchieveService.getStudyDataView(vo);
        resultMap.put("dataView", dataView);
        return resultMap;
    }

    @PostMapping("/dataSaleList")
    public HashMap goDataSaleList(DataSaleVO vo, HttpServletRequest request) throws Exception {
        HashMap resultMap = new HashMap<>();

        HttpSession session = request.getSession();

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

        if ( vo.getGubun() != null && vo.getGubun().equals("study")) {
            vo.setRegid(session.getAttribute("userId").toString());
        }

        int totalCnt = studyAchieveService.getDataSaleCnt(vo);
        List<DataSaleVO> dataList = studyAchieveService.getDataSaleList(vo);

        resultMap.put("totalCnt", totalCnt);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/datasaleInsert")
    public HashMap getDatasaleInsert(DataSaleVO vo, HttpServletRequest request) throws  Exception {
        HashMap resultMap = new HashMap<>();

        HttpSession session = request.getSession();
        vo.setRegid(session.getAttribute("userId").toString());

        int nResultCode = studyAchieveService.insDataSale(vo);
        String resultMsg = (nResultCode > 0) ?  "신청이 완료되었습니다.":"신청이 완료되지 않았습니다. 신청약식을 다시 확인해 주십시오";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }

    @PostMapping("/dataList")
    public HashMap goDataList(StudyAchieveVO vo) throws Exception {
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

        int totalCnt = studyAchieveService.getDataCnt(vo);
        List<StudyAchieveVO> dataList = studyAchieveService.getDataList(vo);

        resultMap.put("totalCnt", totalCnt);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/dataView")
    public HashMap goDataView(StudyAchieveVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        StudyAchieveVO dataView = studyAchieveService.getDataView(vo);
        resultMap.put("dataView", dataView);
        return resultMap;
    }

    @PostMapping("/dataInsert")
    public HashMap goDataInsert(StudyAchieveVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int nResultCode = studyAchieveService.insData(vo);
        String resultMsg = (nResultCode > 0) ?  "게시글이 등록 되었습니다.":"등록 실패 입니다.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }

    @PostMapping("/dataUpdate")
    public HashMap goDataUpdate(StudyAchieveVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int nResultCode = studyAchieveService.uptData(vo);
        String resultMsg = (nResultCode > 0) ?  "게시글이 수정 되었습니다.":"수정 실패 입니다.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }

    @PostMapping("/dataDelete")
    public HashMap goDataDelete(StudyAchieveVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        int nResultCode = studyAchieveService.delData(vo);
        String resultMsg = (nResultCode > 0) ?  "게시글이 삭제 되었습니다.":"삭제 실패 입니다.";

        resultMap.put("resultCode", nResultCode);
        resultMap.put("resultMsg", resultMsg);
        return resultMap;
    }
}
