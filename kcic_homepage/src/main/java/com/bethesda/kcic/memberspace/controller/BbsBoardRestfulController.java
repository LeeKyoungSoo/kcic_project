package com.bethesda.kcic.memberspace.controller;

import com.bethesda.kcic.memberspace.domain.BoardVO;
import com.bethesda.kcic.memberspace.service.BbsBoardService;
import com.bethesda.kcic.metadata.domain.MetaDataVO;
import com.bethesda.kcic.util.BaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/bbsApi/")
public class BbsBoardRestfulController {
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static final String TAG_USER = "METADATA";

    @Value("${excel.fileDownPath.window}")
    private String fileDownPathWin;

    @Value("${excel.fileDownPath.linux}")
    private String fileDownPathLinux;

    @Autowired
    BbsBoardService bbsBoardService;

    @PostMapping("/bbsDataList")
    public HashMap goBbsDataList(BoardVO vo) throws Exception {
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

        int nTotalCnt = bbsBoardService.getBbsDataCnt(vo);
        List<BoardVO> metaDataList = bbsBoardService.getBbsDataList(vo);

        resultMap.put("totalCnt", nTotalCnt);
        resultMap.put("dataList", metaDataList);

        return resultMap;
    }
}

