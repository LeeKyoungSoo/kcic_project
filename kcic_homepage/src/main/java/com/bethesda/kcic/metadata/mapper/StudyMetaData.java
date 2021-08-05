package com.bethesda.kcic.metadata.mapper;

import com.bethesda.kcic.metadata.domain.MetaDataVO;
import com.bethesda.kcic.util.BaseMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudyMetaData {
    List<BaseMap> getStudyMetaDataDomainList() throws Exception;
    List<BaseMap> getMetaDataDomainList() throws Exception;
    int getMetaDataCnt(MetaDataVO vo) throws Exception;
    List<BaseMap> getMetaDataList(MetaDataVO vo) throws Exception;
    List<BaseMap> getMetaDataExcelList(MetaDataVO vo) throws Exception;
}
