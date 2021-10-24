package com.bethesda.kcic.datasales.domain;

import com.bethesda.kcic.common.mapper.CommonVO;
import com.bethesda.kcic.memberspace.domain.BoardFileVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DataSaleVO extends CommonVO {
    private String sid;
    private String studynm;
    private String sosuk;
    private String readuser;
    private String partuser;
    private String sdt;
    private String edt;
    private String purpose;
    private String cont;
    private String regid;
    private String regdt;
    private String moddt;
    private String itemlist;
    private String state;
    private String statecode;
    private String reason;

    private List<String> itemSeqList;
    private String sSubject;
    private String sState;
    private String gubun;

    private List<BoardFileVO> fileList;
    private String studySeq;

}
