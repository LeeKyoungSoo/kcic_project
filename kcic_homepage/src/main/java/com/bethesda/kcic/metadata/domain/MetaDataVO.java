package com.bethesda.kcic.metadata.domain;

import com.bethesda.kcic.common.mapper.CommonVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class MetaDataVO extends CommonVO implements Serializable{
    private static final long serialVersionUID = -4684486837635919802L;

    private String mItemSeq;
    private String mDomain;
    private String mItemName;
    private String mItemLabel;
    private String codeCmnt;
    private String mDataType;
    private String mItemLength;
    private String mSignificantDigits;
    private String mInputType;
    private String mItemCodelist;
    private String mMeasurementUnit;
    private String mCmnt;

    private String studySeq;
    private String studyName;

    private List<String> mDomainList;
    private List<String> itemSeqList;

    private List<String> pStudyOid;
    private List<String> pDomain;
    private String pItemName;
    private String pItemLabel;
    private String pKeyword;
}

