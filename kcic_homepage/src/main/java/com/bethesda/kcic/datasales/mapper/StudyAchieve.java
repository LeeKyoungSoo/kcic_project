package com.bethesda.kcic.datasales.mapper;

import com.bethesda.kcic.datasales.domain.DataSaleVO;
import com.bethesda.kcic.datasales.domain.StudyAchieveVO;
import com.bethesda.kcic.memberspace.domain.BoardVO;
import com.bethesda.kcic.util.BaseMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudyAchieve {
    int getDataCnt(StudyAchieveVO vo) throws Exception;
    List<StudyAchieveVO> getDataList(StudyAchieveVO vo) throws Exception;
    int insData(StudyAchieveVO vo) throws Exception;
    int uptData(StudyAchieveVO vo) throws Exception;
    int delData(StudyAchieveVO vo) throws Exception;
    StudyAchieveVO getDataView(StudyAchieveVO vo) throws  Exception;

    List<BaseMap> getStudyYearChartData() throws Exception;
    int uptManagerState(DataSaleVO vo) throws Exception;
    int insDataSale(DataSaleVO vo) throws Exception;
    int getDataSaleCnt(DataSaleVO vo) throws Exception;
    List<DataSaleVO> getDataSaleList(DataSaleVO vo) throws Exception;
    DataSaleVO getStudyDataView(DataSaleVO vo) throws Exception;
}
