package com.applet.mapper;

import com.applet.entity.wechatprogram.ActivitiesInfo;
import com.applet.entity.wechatprogram.BannerInfor;
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
}
