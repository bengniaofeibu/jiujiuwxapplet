package com.applet.mapper;

import com.applet.model.WxUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Component
@Mapper
public interface WxUserInfoMapper {

    int insertSelective(WxUserInfo record);

    String selectUserIdByMobile(String userMobile);

    int updateUserStatusById(WxUserInfo record);

    Integer selectNumByMobile(String userMobile);

   int updateNoLoginStatusByOpenId(@Param("openId") String openId);

   int updateAlreadyLoginStatusByOpenId(@Param("openId") String openId);

    Integer selectLoginStatusByMobile(String userMobile);



}