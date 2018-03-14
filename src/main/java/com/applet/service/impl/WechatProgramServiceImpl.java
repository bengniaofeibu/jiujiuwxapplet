package com.applet.service.impl;

import com.applet.entity.wechatprogram.ActivitiesInfo;
import com.applet.entity.wechatprogram.BannerInfor;
import com.applet.entity.wechatprogram.ElectricFanceInfo;
import com.applet.entity.wechatprogram.UserInfo;
import com.applet.mapper.WechatProgramMapper;
import com.applet.service.WechatProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 * WechatProgramServiceImpl
 *
 * @author hcx
 * @date 2018/03/08
 */
@Service
public class WechatProgramServiceImpl implements WechatProgramService{

    private final WechatProgramMapper wechatProgramMapper;

    @Autowired
    public WechatProgramServiceImpl(WechatProgramMapper wechatProgramMapper){
        this.wechatProgramMapper = wechatProgramMapper;
    }


    @Override
    public List<BannerInfor> getBanners(String cityName, Date now,Integer type) {
        return wechatProgramMapper.getBanners(cityName,now,type);
    }

    @Override
    public List<BannerInfor> getBannerList(Date now,Integer type) {
        return wechatProgramMapper.getBannerList(now,type);
    }

    @Override
    public List<ActivitiesInfo> getActivitiesInfos(String cityName,Integer os) {
        return wechatProgramMapper.getActivitiesInfos(cityName,os);
    }

    @Override
    public UserInfo getUserByAdminId(String adminId) {
        return wechatProgramMapper.getUserByAdminId(adminId);
    }

    @Override
    public Long getCouponNum(String adminId) {
        return wechatProgramMapper.getCouponNum(adminId);
    }

    @Override
    public List<ElectricFanceInfo> findFanceByAreaName(String areaName) {
        return wechatProgramMapper.findFanceByAreaName(areaName);
    }
}
