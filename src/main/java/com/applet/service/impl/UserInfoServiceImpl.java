package com.applet.service.impl;

import com.applet.annotation.SystemServerLog;
import com.applet.entity.UserInfo.UserInfoResponse;
import com.applet.mapper.*;
import com.applet.model.*;
import com.applet.service.UserInfoService;
import com.applet.utils.AppletResult;
import com.applet.utils.common.DateUtil;
import com.applet.utils.common.JSONUtil;
import com.applet.utils.common.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private WxUserInfoMapper wxUserInfoMapper;

    @Autowired
    private UserReportMapper userReportMapper;

    @Autowired
    private TransRecordTempMapper transRecordTempMapper;

    @Autowired
    private OperateConfigMapper operateConfigMapper;

    @Autowired
    private SysAreaMapper sysAreaMapper;

    private static final int FREE_DEPOSIT_STATUS_NO = 0;

    private static final int FREE_DEPOSIT_STATUS_YES = 1;

    @Override
    @SystemServerLog(funcionExplain = "添加新用户注册")
    @Transactional(rollbackFor = Exception.class)
    public UserInfoResponse addRegisterUser(UserInfo userInfo, WxUserInfo wxUserInfo) {
        if (userInfo.getUserSource() != 0) {
            userInfoMapper.insertSelective(userInfo);
            LOGGER.debug("记录单车用户信息 {}", JSONUtil.toJSONString(userInfo));
        }

        wxUserInfo.setUserId(userInfo.getId());
        wxUserInfoMapper.insertSelective(wxUserInfo);
        LOGGER.debug("记录微信用户信息 {}", JSONUtil.toJSONString(wxUserInfo));

        int userReportCount = userReportMapper.selectUserIdByUserId(wxUserInfo.getUserId());
        if (userReportCount == 0 ){
            UserReport userReport = new UserReport(UuidUtil.getUuid(), 0.0, 0.0, 0.0, userInfo.getId());
            userReportMapper.insertUserReportInfo(userReport);
            LOGGER.debug("记录用户report信息 {}", JSONUtil.toJSONString(userReport));
        }

        UserInfoResponse info = new UserInfoResponse();
        info.setAdminId(userInfo.getId());
        info.setStatus(userInfo.getAccountStatus());
        return info;
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    @SystemServerLog(funcionExplain = "获取用户信息")
    public UserInfoResponse getUserInfo(String id, String cityName) {
        UserInfo info = userInfoMapper.selectByPrimaryKey(id);
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setStatus(info.getAccountStatus());
        Integer borrowBicycle = info.getmBorrowBicycle();
        userInfoResponse.setBorrowBicycle(borrowBicycle);

        SysArea sysArea = sysAreaMapper.selecIdtByCountName(cityName);
        if (sysArea != null) {
            OperateConfig operateConfig = operateConfigMapper.selectLimitHourByCity(sysArea.getId());

            if (operateConfig != null) {
                setFreeDepositStatus(operateConfig.getLimitHour(), info, userInfoResponse);
            } else {
                OperateConfig countryConfig = operateConfigMapper.selectLimitHourByCountry();
                if (countryConfig != null) {
                    setFreeDepositStatus(countryConfig.getLimitHour(), info, userInfoResponse);
                } else {
                    userInfoResponse.setFreeDepositStatus(FREE_DEPOSIT_STATUS_NO);
                }
            }
        } else {
            userInfoResponse.setFreeDepositStatus(FREE_DEPOSIT_STATUS_NO);
        }

        if (borrowBicycle.intValue() == 1) {
            long borrowBicycleDate = (new Date().getTime() - info.getmBorrowBicycleDate().getTime()) / (1000);
            userInfoResponse.setRidingTime(borrowBicycleDate);
            int borrowBicycleNum = transRecordTempMapper.selectByUserIdAndTransFlag(id).getBorrowBicycleNum();
            userInfoResponse.setUserBicycleNo(borrowBicycleNum);
        }
        return userInfoResponse;
    }

    //设置用户免压状态
    private void setFreeDepositStatus(int limitHour, UserInfo userInfo, UserInfoResponse userInfoResponse) {
        boolean eAppointTime = DateUtil.isEAppointTime(userInfo.getAddTime(), new Date(), limitHour, DateUtil.TimeType.HOURS);

        //判断是否超过指定免交押金时间
        if (eAppointTime) {
            userInfoResponse.setFreeDepositStatus(FREE_DEPOSIT_STATUS_NO);
        } else {
            userInfoResponse.setFreeDepositStatus(FREE_DEPOSIT_STATUS_YES);
        }
    }
}
