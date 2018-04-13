package com.applet.service.impl;

import com.applet.annotation.SystemServerLog;
import com.applet.entity.wechatprogram.ActivitiesInfo;
import com.applet.entity.wechatprogram.BannerInfor;
import com.applet.entity.wechatprogram.ElectricFanceInfo;
import com.applet.entity.wechatprogram.UserInfo;
import com.applet.enums.ResultEnums;
import com.applet.mapper.WechatProgramMapper;
import com.applet.service.WechatProgramService;
import com.applet.utils.AppletResult;
import com.applet.utils.HttpClient.HttpApiUtils;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * WechatProgramServiceImpl
 *
 * @author hcx
 * @date 2018/03/08
 */
@Service
public class WechatProgramServiceImpl implements WechatProgramService{


    private static final Logger LOGGER= LoggerFactory.getLogger(WechatProgramServiceImpl.class);

    private final WechatProgramMapper wechatProgramMapper;

    private static final String REQUEST_ERROR_CODE="500";

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

    /**
     * 获取优惠券图片url
     *
     * @param userId
     * @param cityName
     * @return
     */
    @SystemServerLog(funcionExplain = "获取优惠券图片url")
    @Override
    public AppletResult getCouponImageUrl(String userId, String cityName) throws Exception {
        String resResult = HttpApiUtils.getCouponImageUrl(userId, cityName);
        LOGGER.debug("获取优惠券图片结果 --> {}",resResult);
        AppletResult appletResult=JSONUtil.parseObject(resResult, AppletResult.class);
        if (!REQUEST_ERROR_CODE.equals(appletResult.getCode())){
            if (appletResult.getData()!=null){
                return ResultUtil.success(appletResult.getData());
            }else {
                return appletResult;
            }
        }
         return ResultUtil.error(ResultEnums.REQUEST_RESULT_FAIL);
    }


    @Override
    public List<ElectricFanceInfo> findFanceByAreaName(String areaName) {
        return wechatProgramMapper.findFanceByAreaName(areaName);
    }
}
