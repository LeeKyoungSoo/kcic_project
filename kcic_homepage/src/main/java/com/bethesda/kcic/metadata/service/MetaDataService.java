package com.bethesda.kcic.metadata.service;

import com.bethesda.kcic.metadata.domain.MetaDataVO;
import com.bethesda.kcic.metadata.mapper.MetaData;
import com.bethesda.kcic.util.BaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaDataService {
    @Autowired
    MetaData metaData;

    public List<BaseMap> getMetaDataDomainList() throws Exception {
        return  metaData.getMetaDataDomainList();
    };

    public int getMetaDataCnt(MetaDataVO vo) throws Exception {
        return metaData.getMetaDataCnt(vo);
    }

    public List<BaseMap> getMetaDataList(MetaDataVO vo) throws Exception {
        return metaData.getMetaDataList(vo);
    }

    public List<BaseMap> getMetaDataExcelList(MetaDataVO vo) throws Exception {
        return  metaData.getMetaDataExcelList(vo);
    }
}
