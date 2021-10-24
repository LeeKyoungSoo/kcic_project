package com.bethesda.kcic.metadata.controller;

import com.bethesda.kcic.metadata.domain.MetaDataVO;
import com.bethesda.kcic.metadata.mapper.StudyMetaData;
import com.bethesda.kcic.metadata.service.MetaDataService;
import com.bethesda.kcic.metadata.service.StudyMetaDataService;
import com.bethesda.kcic.util.BaseMap;
import com.bethesda.kcic.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/metadataApi/")
public class MetadataRestfulController {

    @Autowired
    private MetaDataService metaDataService;

    @Autowired
    private StudyMetaDataService studyMetaDataService;

    @PostMapping("/metaDataList")
    public HashMap goMetaDataList(MetaDataVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();


        BaseMap baseMap = new BaseMap();
        baseMap.put("p_page_no", vo.getPageNum());
        baseMap.put("p_PagePerCount", vo.getPageSize());
        baseMap.put("p_domain", vo.getMDomain());
        baseMap.put("p_item_name", vo.getMItemName());
        baseMap.put("p_item_label", vo.getMItemLabel());
        String url = "http://106.241.16.5:48084/service/KcdhSync/selMetadataInfo";
        baseMap = HttpClientUtil.post(url, baseMap);

        /*
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

        int nTotalCnt = metaDataService.getMetaDataCnt(vo);
        List<BaseMap> metaDataList = metaDataService.getMetaDataList(vo);
        */

        resultMap.put("totalCnt", baseMap.get("totCnt"));
        resultMap.put("dataList", baseMap.get("metadataList"));

        return resultMap;
    }

    @PostMapping("/studyMetaDataListOld")
    public HashMap goStudyMetaDataListOld(MetaDataVO vo) throws Exception {
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

        List<String> itemSeqList = vo.getItemSeqList();
        if ( itemSeqList != null && itemSeqList.size() > 0 ) {
            for ( int i = 0; i < itemSeqList.size(); i++ ) {
                itemSeqList.set(i, itemSeqList.get(i).replace("[", "").replace("]", ""));
            }
        }

        int nTotalCnt = studyMetaDataService.getMetaDataCnt(vo);
        List<BaseMap> metaDataList = studyMetaDataService.getMetaDataList(vo);

        resultMap.put("totalCnt", nTotalCnt);
        resultMap.put("dataList", metaDataList);

        return resultMap;
    }

    @PostMapping("/studyMetaDataList")
    public HashMap goStudyMetaDataList(MetaDataVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        if ( (vo.getPageNum() == null) || vo.getPageNum().equals("") ) {
            vo.setPageNum("0");
        }

        List<String> itemSeqList = vo.getItemSeqList();
        String itemSeqStr = "";
        if ( itemSeqList != null && itemSeqList.size() > 0 ) {
            for ( int i = 0; i < itemSeqList.size(); i++ ) {
                //itemSeqList.set(i, itemSeqList.get(i).replace("[", "").replace("]", ""));
                itemSeqStr += itemSeqList.get(i).replace("[", "").replace("]", "") + ",";
            }
            itemSeqStr = itemSeqStr.substring(0, itemSeqStr.length() - 1);
        }

        itemSeqList = vo.getPStudyOid();
        String p_study_oid = "";
        if ( itemSeqList != null && itemSeqList.size() > 0 ) {
            for ( int i = 0; i < itemSeqList.size(); i++ ) {
                //itemSeqList.set(i, itemSeqList.get(i).replace("[", "").replace("]", ""));
                p_study_oid += itemSeqList.get(i).replace("[", "").replace("]", "") + ",";
            }
            p_study_oid = p_study_oid.substring(0, p_study_oid.length() - 1);
        }

        itemSeqList = vo.getPDomain();
        String p_domain = "";
        if ( itemSeqList != null && itemSeqList.size() > 0 ) {
            for ( int i = 0; i < itemSeqList.size(); i++ ) {
                //itemSeqList.set(i, itemSeqList.get(i).replace("[", "").replace("]", ""));
                p_domain += itemSeqList.get(i).replace("[", "").replace("]", "") + ",";
            }
            p_domain = p_domain.substring(0, p_domain.length() - 1);
        }

        BaseMap baseMap = new BaseMap();
        //baseMap.put("study_oid", vo.getStudySeq());
        //baseMap.put("p_item_seq", p_study_oid);
        //baseMap.put("p_item_seq", p_domain);
        baseMap.put("p_study_oid", vo.getPStudyOid());
        baseMap.put("p_domain", vo.getPDomain());
        baseMap.put("p_item_name", vo.getPItemName());
        baseMap.put("p_item_label", vo.getPItemLabel());
        baseMap.put("p_keyword", vo.getPItemLabel());
        baseMap.put("p_page_no", vo.getPageNum());
        String url = "http://106.241.16.5:48084/service/KcdhSync/selStudyMetadataInfo";
        baseMap = HttpClientUtil.post(url, baseMap);

        resultMap.put("totCnt", baseMap.get("totCnt"));
        resultMap.put("dataList", baseMap.get("metadataList"));

        return resultMap;
    }

    @PostMapping("/studyMetaDataListDataSale")
    public HashMap goStudyMetaDataListDataSale(MetaDataVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        List<String> itemSeqList = vo.getItemSeqList();
        String itemSeqStr = "";
        if ( itemSeqList != null && itemSeqList.size() > 0 ) {
            for ( int i = 0; i < itemSeqList.size(); i++ ) {
                //itemSeqList.set(i, itemSeqList.get(i).replace("[", "").replace("]", ""));
                itemSeqStr += itemSeqList.get(i).replace("[", "").replace("]", "") + ",";
            }
            itemSeqStr = itemSeqStr.substring(0, itemSeqStr.length() - 1);
        }

        itemSeqList = vo.getPStudyOid();
        String p_study_oid = "";
        if ( itemSeqList != null && itemSeqList.size() > 0 ) {
            for ( int i = 0; i < itemSeqList.size(); i++ ) {
                //itemSeqList.set(i, itemSeqList.get(i).replace("[", "").replace("]", ""));
                p_study_oid += itemSeqList.get(i).replace("[", "").replace("]", "") + ",";
            }
            p_study_oid = p_study_oid.substring(0, p_study_oid.length() - 1);
        }

        itemSeqList = vo.getPDomain();
        String p_domain = "";
        if ( itemSeqList != null && itemSeqList.size() > 0 ) {
            for ( int i = 0; i < itemSeqList.size(); i++ ) {
                //itemSeqList.set(i, itemSeqList.get(i).replace("[", "").replace("]", ""));
                p_domain += itemSeqList.get(i).replace("[", "").replace("]", "") + ",";
            }
            p_domain = p_domain.substring(0, p_domain.length() - 1);
        }

        BaseMap baseMap = new BaseMap();
        //baseMap.put("study_oid", vo.getStudySeq());
        //baseMap.put("p_item_seq", p_study_oid);
        //baseMap.put("p_item_seq", p_domain);
        baseMap.put("p_study_oid", vo.getPStudyOid());
        baseMap.put("p_domain", vo.getPDomain());
        baseMap.put("p_item_name", vo.getPItemName());
        baseMap.put("p_item_label", vo.getPItemLabel());
        String url = "http://106.241.16.5:48084/service/KcdhSync/selStudyDetailInfo";
        baseMap = HttpClientUtil.post(url, baseMap);

        /*
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

        List<String> itemSeqList = vo.getItemSeqList();
        if ( itemSeqList != null && itemSeqList.size() > 0 ) {
            for ( int i = 0; i < itemSeqList.size(); i++ ) {
                itemSeqList.set(i, itemSeqList.get(i).replace("[", "").replace("]", ""));
            }
        }

        int nTotalCnt = studyMetaDataService.getMetaDataCnt(vo);
        List<BaseMap> metaDataList = studyMetaDataService.getMetaDataList(vo);

        resultMap.put("totalCnt", nTotalCnt);
        resultMap.put("dataList", metaDataList);
        */

        List<Integer> chartYear = new ArrayList<>();
        List<Double> chartData = new ArrayList<>();
        List<Double> chartData2 = new ArrayList<>();
        List<HashMap> dataView = (List<HashMap>)baseMap.get("subjectYearSummary");
        for (Map oneData : dataView ) {
            chartYear.add(Integer.parseInt(oneData.get("YEAR").toString()));
            chartData.add(Double.parseDouble(oneData.get("CNT").toString()));
            chartData2.add(Double.parseDouble(oneData.get("T_CNT").toString()));
        }
        resultMap.put("chartYear", chartYear);
        resultMap.put("chartData", chartData);
        resultMap.put("chartData2", chartData2);

        Double[] humanCnt = {0.0, 0.0};
        List<HashMap> dataHumanView = (List<HashMap>)baseMap.get("subjectSexSummary");
        for (Map oneData : dataHumanView ) {
            if ( oneData.get("code_value").equals("1")) {
                humanCnt[0] = Double.parseDouble(oneData.get("s_cnt").toString());
            }
            else if ( oneData.get("code_value").equals("2")) {
                humanCnt[1] = Double.parseDouble(oneData.get("s_cnt").toString());
            }
        }
        resultMap.put("SexSummary", humanCnt);
        resultMap.put("dataList", baseMap.get("metadataList"));
        resultMap.put("StatSummary", baseMap.get("subjectStatSummary"));

        return resultMap;
    }

    @PostMapping("/studyMetaDataDomain")
    public HashMap goStudyMetaDataDomain(MetaDataVO vo) throws Exception {
        HashMap resultMap = new HashMap<>();

        BaseMap baseMap = new BaseMap();
        baseMap.put("temp", "");
        String url = "http://106.241.16.5:48084/service/KcdhSync/selStudyInfo";
        baseMap = HttpClientUtil.post(url, baseMap);

        /*
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

        List<String> itemSeqList = vo.getItemSeqList();
        if ( itemSeqList != null && itemSeqList.size() > 0 ) {
            for ( int i = 0; i < itemSeqList.size(); i++ ) {
                itemSeqList.set(i, itemSeqList.get(i).replace("[", "").replace("]", ""));
            }
        }

        int nTotalCnt = studyMetaDataService.getMetaDataCnt(vo);
        List<BaseMap> metaDataList = studyMetaDataService.getMetaDataList(vo);

        resultMap.put("totalCnt", nTotalCnt);
        resultMap.put("dataList", metaDataList);
        */

        resultMap.put("dataList", baseMap.get("studyInfoList"));
        resultMap.put("domainList", baseMap.get("domainList"));

        return resultMap;
    }
}
