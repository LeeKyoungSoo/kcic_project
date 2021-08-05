package com.bethesda.kcic.metadata.service;

import com.bethesda.kcic.datasales.mapper.StudyAchieve;
import com.bethesda.kcic.metadata.domain.MetaDataVO;
import com.bethesda.kcic.metadata.mapper.MetaData;
import com.bethesda.kcic.metadata.mapper.StudyMetaData;
import com.bethesda.kcic.util.BaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyMetaDataService {
    @Autowired
    StudyMetaData studyMetaData;

    public List<BaseMap> getStudyMetaDataDomainList() throws Exception{
        return studyMetaData.getStudyMetaDataDomainList();
    }

    public List<BaseMap> getMetaDataDomainList() throws Exception {
        return  studyMetaData.getMetaDataDomainList();
    }

    public int getMetaDataCnt(MetaDataVO vo) throws Exception {
        return studyMetaData.getMetaDataCnt(vo);
    }

    public List<BaseMap> getMetaDataList(MetaDataVO vo) throws Exception {
        return studyMetaData.getMetaDataList(vo);
    }

    public List<BaseMap> getMetaDataExcelList(MetaDataVO vo) throws Exception {
        return  studyMetaData.getMetaDataExcelList(vo);
    }
}
