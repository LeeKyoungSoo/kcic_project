package com.bethesda.kcic.datasales.service;

import com.bethesda.kcic.datasales.domain.DataSaleVO;
import com.bethesda.kcic.datasales.domain.StudyAchieveVO;
import com.bethesda.kcic.datasales.mapper.StudyAchieve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyAchieveService {
    @Autowired
    StudyAchieve studyAchieve;

    public int getDataCnt(StudyAchieveVO vo) throws Exception {
        return studyAchieve.getDataCnt(vo);
    }

    public List<StudyAchieveVO> getDataList(StudyAchieveVO vo) throws Exception {
        return studyAchieve.getDataList(vo);
    }

    public int insData(StudyAchieveVO vo) throws Exception {
        return studyAchieve.insData(vo);
    }

    public int uptData(StudyAchieveVO vo) throws Exception {
        return studyAchieve.uptData(vo);
    }

    public int delData(StudyAchieveVO vo) throws Exception {
        return studyAchieve.delData(vo);
    }

    public StudyAchieveVO getDataView(StudyAchieveVO vo) throws  Exception {
        return studyAchieve.getDataView(vo);
    }

    public int insDataSale(DataSaleVO vo) throws Exception {
        return studyAchieve.insDataSale(vo);
    }
}
