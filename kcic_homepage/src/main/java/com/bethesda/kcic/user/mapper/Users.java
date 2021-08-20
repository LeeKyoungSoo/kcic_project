package com.bethesda.kcic.user.mapper;

import com.bethesda.kcic.user.domain.UsersVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Users {
    int getDataCnt(UsersVO vo) throws Exception;
    List<UsersVO> getDataList(UsersVO vo) throws Exception;
    UsersVO getUserCheck(UsersVO vo) throws Exception;
    UsersVO getDataView(UsersVO vo) throws Exception;
    int insData(UsersVO vo) throws Exception;
    int uptData(UsersVO vo) throws Exception;
    int uptDataSp(UsersVO vo) throws Exception;
    int delData(UsersVO vo) throws Exception;
    int uptState(UsersVO vo) throws Exception;
}
