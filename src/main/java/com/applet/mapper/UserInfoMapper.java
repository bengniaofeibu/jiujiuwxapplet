package com.applet.mapper;

import com.applet.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserInfoMapper {

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByStatusById(UserInfo record);

    int updateBorrowFlagById(UserInfo record);

    UserInfo selectByUserPhone(String phone);
}