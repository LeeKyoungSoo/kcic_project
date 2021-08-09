package com.bethesda.kcic.datasales.domain;

import com.bethesda.kcic.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String itemseqlist;
    private String state;
    private String reason;

    private String sSubject;
    private String sState;
}
