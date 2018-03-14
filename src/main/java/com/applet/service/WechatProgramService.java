package com.applet.service;

import com.applet.entity.wechatprogram.ActivitiesInfo;
import com.applet.entity.wechatprogram.BannerInfor;
import com.applet.entity.wechatprogram.ElectricFanceInfo;
import com.applet.entity.wechatprogram.UserInfo;

import java.util.Date;
import java.util.List;

/**
 * WechatProgramService
 *
 * @author hcx
 * @date 2018/03/08
 */
public interface WechatProgramService {
    /**
     * 获取Banner图片信息
     * @param cityName 城市名
     * @param now 查询时间
     * @param type 查询类型
     * @return List<BannerInfor>
     */
    List<BannerInfor> getBanners(String cityName, Date now,Integer type);

    /**
     * 获取Banner图片信息
     * @param now 查询时间
     * @param type 查询类型
     * @return List<BannerInfor>
     */
    List<BannerInfor> getBannerList(Date now,Integer type);

    /**
     * 获取赳赳乐享列表信息
     * @param cityName 城市名称
     * @param os 设备名称
     * @return List<ActivitiesInfo>
     */
    List<ActivitiesInfo> getActivitiesInfos(String cityName,Integer os);

    /**
     * 获取钱包信息
     * @param adminId 用户id
     * @return UserInfo
     */
    UserInfo getUserByAdminId(String adminId);

    /**
     * 获取优惠券数量
     * @param adminId 用户id
     * @return Long
     */
    Long getCouponNum(String adminId);

    /**
     * 获取电子围栏
     * @param areaName 城市名称
     * @return  List<ElectricFanceInfo>
     */
    List<ElectricFanceInfo> findFanceByAreaName(String areaName);
}
