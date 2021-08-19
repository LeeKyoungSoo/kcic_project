package com.bethesda.kcic.user.domain;

import com.bethesda.kcic.common.domain.MemberRole;
import com.bethesda.kcic.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class UsersVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = -8274004534207618049L;

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
    private String sUserid;
    private String sRegDt;
    private String eRegDt;

    private String beforUserpw;
    private List<MemberRole> roleList;
}
