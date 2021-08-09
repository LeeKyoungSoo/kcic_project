package com.bethesda.kcic.datasales.controller;

import com.bethesda.kcic.datasales.domain.DataSaleVO;
import com.bethesda.kcic.datasales.domain.StudyAchieveVO;
import com.bethesda.kcic.datasales.service.StudyAchieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/studyAcApi")
public class StudyAchieveRestfulController {

    @Autowired
    StudyAchieveService studyAchieveService;

    @PostMapping("/dataSaleList")
    public HashMap goDataSaleList(DataSaleVO vo) throws Exception {
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

        int totalCnt = studyAchieveService.getDataSaleCnt(vo);
        List<DataSaleVO> dataList = studyAchieveService.getDataSaleList(vo);

        resultMap.put("totalCnt", totalCnt);
        resultMap.put("dataList", dataList);

        return resultMap;
    }

    @PostMapping("/datasaleInsert")
    public HashMap getDatasaleInsert(DataSaleVO vo) throws  Exception {
        HashMap resultMap = new HashMap<>();

        vo.setRegid("tester");
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
