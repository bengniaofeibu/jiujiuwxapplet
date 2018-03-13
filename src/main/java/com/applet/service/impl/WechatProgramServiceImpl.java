package com.applet.service.impl;

import com.applet.entity.wechatprogram.ActivitiesInfo;
import com.applet.entity.wechatprogram.BannerInfor;
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
    public List<BannerInfor> getBanners(String cityName, Date now) {
        return wechatProgramMapper.getBanners(cityName,now);
    }

    @Override
    public List<BannerInfor> getBannerList(Date now) {
        return wechatProgramMapper.getBannerList(now);
    }

    @Override
    public List<ActivitiesInfo> getActivitiesInfos(String cityName,Integer os) {
        return wechatProgramMapper.getActivitiesInfos(cityName,os);
    }
}
