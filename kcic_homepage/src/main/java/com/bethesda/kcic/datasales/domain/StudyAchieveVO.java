package com.bethesda.kcic.datasales.domain;

import com.bethesda.kcic.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudyAchieveVO extends CommonVO {
    private String bId;
    private String gubun;
    private String subject;
    private String cont;
    private String summary;
    private String regDt;
    private String modDt;
    private String hit;

    private String sSubject;
    private String sRegDt;
    private String eRegDt;
}
