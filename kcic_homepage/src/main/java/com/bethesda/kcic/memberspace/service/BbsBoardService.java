package com.bethesda.kcic.memberspace.service;

import com.bethesda.kcic.memberspace.domain.BoardVO;
import com.bethesda.kcic.memberspace.mapper.BbsBoard;
import com.bethesda.kcic.util.BaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BbsBoardService {
    @Autowired
    BbsBoard bbsBoard;

    public int getBbsDataCnt(BoardVO vo) throws Exception {
        return  bbsBoard.getBbsDataCnt(vo);
    }

    public List<BoardVO> getBbsDataList(BoardVO vo) throws Exception {
        return bbsBoard.getBbsDataList(vo);
    }
}
