package com.applet.service.impl;

import com.applet.entity.wechatprogram.ActivitiesInfo;
import com.applet.entity.wechatprogram.BannerInfor;
import com.applet.entity.wechatprogram.ElectricFanceInfo;
import com.applet.entity.wechatprogram.UserInfo;
import com.applet.enums.ResultEnums;
import com.applet.mapper.LuckyMoneyDailyParameterMapper;
import com.applet.mapper.WechatProgramMapper;
import com.applet.model.LuckyMoneyDailyParameter;
import com.applet.service.WechatProgramService;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.CommonUtils;
import com.applet.utils.common.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    private static final Logger LOGGER= LoggerFactory.getLogger(WechatProgramServiceImpl.class);

    private final WechatProgramMapper wechatProgramMapper;

    @Autowired
    private LuckyMoneyDailyParameterMapper luckyMoneyDailyParameterMapper;

    @Autowired
    private RedisUtil redisUtil;

    private static final String luck_money_key = "user:luck:money";

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

    @Override
    public AppletResult getLuckMoneyPrompt(String userId){
        String currentDate = CommonUtils.getTimeFormat(new Date(),"yyyy-MM-dd");
        LuckyMoneyDailyParameter luckyMoneyDailyParameter = luckyMoneyDailyParameterMapper.getLuckyMoneyActivityInfo(currentDate);
        if(luckyMoneyDailyParameter != null){
            if(!StringUtils.isEmpty(userId) || !"0".equals(userId)){
                LOGGER.info("luckmoney的key:" + luck_money_key + currentDate);
                Object res = redisUtil.getValueByKeyAndDb(luck_money_key + currentDate,1,userId);
                if(res != null){
                    return ResultUtil.error(ResultEnums.LUCKY_MONEY_ERROR_TODAY_DONE);
                }else{
                    return ResultUtil.success();
                }
            }else{
                return ResultUtil.success();
            }
        }else{
            return ResultUtil.error(ResultEnums.LUCKY_MONEY_ERROR_NO_ACTIVITY);
        }

    }
}
