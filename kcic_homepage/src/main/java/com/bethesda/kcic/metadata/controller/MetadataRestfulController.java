package com.bethesda.kcic.metadata.controller;

import com.bethesda.kcic.metadata.domain.MetaDataVO;
import com.bethesda.kcic.metadata.service.MetaDataService;
import com.bethesda.kcic.util.BaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/metadataApi/")
public class MetadataRestfulController {

    @Autowired
    private MetaDataService metaDataService;

    @PostMapping("/metaDataList")
    public HashMap goMetaDataList(MetaDataVO vo) throws Exception {
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

        int nTotalCnt = metaDataService.getMetaDataCnt(vo);
        List<BaseMap> metaDataList = metaDataService.getMetaDataList(vo);

        resultMap.put("totalCnt", nTotalCnt);
        resultMap.put("dataList", metaDataList);

        return resultMap;
    }
}
