package com.applet.mapper;

import com.applet.model.BicycleWxUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface BicycleWxUserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BicycleWxUserInfo record);

    int insertSelective(BicycleWxUserInfo record);

    BicycleWxUserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BicycleWxUserInfo record);

    int updateByPrimaryKey(BicycleWxUserInfo record);

    int updateWxUser(BicycleWxUserInfo record);

    String selectUserIdByUnionId(@Param("unionId") String unionId);
}