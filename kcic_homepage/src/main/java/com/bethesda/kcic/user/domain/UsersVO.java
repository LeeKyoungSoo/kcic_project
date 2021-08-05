package com.bethesda.kcic.user.domain;

import com.bethesda.kcic.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersVO extends CommonVO {
    private String uid;
    private String userid;
    private String userpw;
    private String usernm;
    private String sosuk;
    private String jobnm;
    private String email;
    private String userlv;
    private String useyn;
    private String regdt;
    private String moddt;

    private String sUsernm;
    private String sRegDt;
    private String eRegDt;
}
