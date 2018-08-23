package com.applet.mapper;

import com.applet.model.WxPublicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface WxPublicInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insertWxPublicUserInfo(WxPublicInfo record);

    int selectCountByUnionId(@Param("unionId") String unionId);

    int selectCountByOpenId(@Param("openId") String openId);

    int updateWxPublicUserInfoByOpenid(WxPublicInfo record);

}