package com.bethesda.kcic.memberspace.domain;

import com.bethesda.kcic.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BoardVO extends CommonVO {
    private String bId;
    private String gubun;
    private String subject;
    private String content;
    private String regId;
    private String regNm;
    private String regDt;
    private String modDt;
    private String hit;
    private String notiYn;

    private List<BoardFileVO> fileList;

    private String sRegDt;
    private String eRegDt;
}
