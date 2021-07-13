package com.bethesda.kcic.memberspace.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileVO {
    private int fId;
    private int bId;
    private String fileOrgNm;
    private String fileSaveNm;
    private String fileSize;
    private String regDt;
}
