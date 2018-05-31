package com.applet.service.impl;

import com.applet.annotation.SystemServerLog;
import com.applet.entity.UserInfo.EsUserInfo;
import com.applet.entity.UserInfo.UserInfoResponse;
import com.applet.mapper.*;
import com.applet.model.*;
import com.applet.service.UserInfoService;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private LoginDailyLogMapper loginDailyLogMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private EsUtil esUtil;

    private static final int FREE_DEPOSIT_STATUS_NO = 0;

    private static final int FREE_DEPOSIT_STATUS_YES = 1;

    private static final String USER_LOGIN_STATUS_KEY="user:login:status:";

    @Value("${spring.data.elasticsearch.index}")
    private String esIndex;

    @Value("${spring.data.elasticsearch.type}")
    private String esType;



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
    public UserInfoResponse getUserInfo(String openId,String id, String cityName) {
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

//        Integer loginStatus = wxUserInfoMapper.selectLoginStatusByMobile(info.getPhone());
//        LOGGER.debug("用户登录状态 -->{}",loginStatus);
//
//        userInfoResponse.setLoginStatus(loginStatus);

        return userInfoResponse;
    }

    /**
     * 更新用户登录状态
     *
     * @param phone
     * @return
     */
    @Override
    public void updateUserLoginStatus(String phone,String openId) {

        //更新用户状态为未登录状态
        int updateNum = wxUserInfoMapper.updateNoLoginStatusByOpenId(openId);
        if (updateNum > 0){
            return ;
        }

        String userLoginStatusKey=new StringBuilder(USER_LOGIN_STATUS_KEY).append(phone).toString();
        LOGGER.debug("userLoginStatusKey {}",userLoginStatusKey);

        //判断key是否存在
        boolean isExist = redisUtil.ifExist(userLoginStatusKey);
        if (isExist){

          //获取之前openId登录的手机号
          String openIdBefore = redisUtil.getValuesStr(userLoginStatusKey);

          if (openIdBefore !=null ){

              if (!openId.equals(openIdBefore)){

                  //修改之前openId为已登录状态
                  wxUserInfoMapper.updateAlreadyLoginStatusByOpenId(openIdBefore);
              }
          }
         return;
        }
        //记录用户登录状态到缓存
        redisUtil.setObj(userLoginStatusKey,openId);
    }

    /**
     * 记录用户打开小程序
     *
     * @param userInfo
     */
    @SystemServerLog(funcionExplain = "记录用户打开小程序")
    @Override
    public AppletResult recordUserOpenXcx(UserInfo userInfo) {

        //查询用户当天有没有打开小程序记录
        long count = loginDailyLogMapper.selectUserCurdateCountById(userInfo.getId());

        if (count == 0){
            //记录用户当天打开小程序记录
            LoginDailyLog loginDailyLog=new LoginDailyLog();
            loginDailyLog.setId(UuidUtil.getUuid());
            loginDailyLog.setUserId(userInfo.getId());
            loginDailyLog.setAppVersion("");
            loginDailyLog.setPhoneSystemVersion("");
            loginDailyLogMapper.insertUserLoginRecord(loginDailyLog);
        }


        //存储到es
        esUtil.save(esIndex,esType,userInfo);

        return ResultUtil.success();
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
