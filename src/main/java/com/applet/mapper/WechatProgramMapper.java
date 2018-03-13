package com.applet.mapper;

import com.applet.entity.wechatprogram.ActivitiesInfo;
import com.applet.entity.wechatprogram.BannerInfor;
import com.applet.entity.wechatprogram.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
/**
 * WechatProgramMapper
 *
 * @author hcx
 * @date 2018/03/08
 */
@Component
@Mapper
public interface WechatProgramMapper {
    /**
     * 微信小程序获取banners
     * @param cityName 城市名
     * @param now 查询时间
     * @return List<BannerInfor>
     */
    List<BannerInfor> getBanners(@Param("cityName")String cityName,@Param("now")Date now);

    /**
     * 微信小程序获取banners
     * @param now 查询时间
     * @return List<BannerInfor>
     */
    List<BannerInfor> getBannerList(@Param("now")Date now);

    /**
     * 微信小程序获取banners
     * @param cityName 城市名称
     * @param os 设备名称
     * @return List<ActivitiesInfo>
     */
    List<ActivitiesInfo> getActivitiesInfos(@Param("cityName")String cityName,@Param("os")Integer os);

    /**
     * 微信小程序获取钱包信息
     * @param adminId 用户id
     * @return UserInfo
     */
    UserInfo getUserByAdminId(@Param("adminId")String adminId);

    /**
     * 微信小程序获取优惠券数量
     * @param adminId 用户id
     * @return Long
     */
    Long getCouponNum(@Param("adminId")String adminId);
}
