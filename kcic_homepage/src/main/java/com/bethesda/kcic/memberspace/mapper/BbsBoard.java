package com.bethesda.kcic.memberspace.mapper;

import com.bethesda.kcic.memberspace.domain.BoardVO;
import com.bethesda.kcic.util.BaseMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface BbsBoard {
    int getBbsDataCnt(BoardVO vo) throws Exception;
    List<BoardVO> getBbsDataList(BoardVO vo) throws Exception;
}
