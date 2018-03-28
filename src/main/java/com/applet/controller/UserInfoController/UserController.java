package com.applet.controller.UserInfoController;

import com.applet.annotation.SystemControllerLog;
import com.applet.controller.BaseController;
import com.applet.entity.Cat;
import com.applet.entity.UserInfo.WxGeneralUserInfo;
import com.applet.entity.UserInfo.WxUserRegisterRequest;
import com.applet.entity.UserInfo.PhoneRegisterRequest;
import com.applet.entity.UserInfo.UserInfoResponse;
import com.applet.entity.UserInfo.WxDetailedUserInfo;
import com.applet.enums.ResultEnums;
import com.applet.model.UserInfo;
import com.applet.model.WxUserInfo;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.*;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/re")
public class UserController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @SystemControllerLog(funcionExplain = "进入微信注册登录控制层")
    @GetMapping("/wx_xcx_auth")
    public AppletResult register(WxUserRegisterRequest request, @RequestHeader("session") String session) {
        LOGGER.debug("session---> {}",session);

        Cat authInfo = getAuthInfo(session);
        LOGGER.debug(" authInfo {}", JSONUtil.toJSONString(authInfo));



        StringBuffer buffer = new StringBuffer(request.getRawData());
        String signature = Sha1Util.encode(buffer.append(authInfo.getSessionKey()).toString());
        if (!signature.equals(request.getSignature())) {
            LOGGER.debug("signature {} wxsignature {}", signature, request.getSignature());
            return ResultUtil.error(ResultEnums.USER_DATA_VALIDATE_FAIL);
        }

        try {

            WxDetailedUserInfo detailedUserInfo = JSONUtil.parseObject(request.getRawData(), WxDetailedUserInfo.class);

            WxGeneralUserInfo generalUserInfo = encryptedDataToObject(request.getGeneralEncryptedData(), authInfo.getSessionKey(), request.getIv(), WxGeneralUserInfo.class);

            String phoneNumber = generalUserInfo.getPhoneNumber();

            //验证用户是否小程序上已经注册
            AppletResult userRegistered = isUserRegistered(phoneNumber);
            if (userRegistered != null) {
                return ResultUtil.success(getUserInfoByopenId(phoneNumber,request.getCityName()));
            }

            //验证用户是否在单车上注册过
            UserInfo userInfo = userInfoMapper.selectByUserPhone(phoneNumber);
            if (userInfo != null) {
                UserInfoResponse userInfoResponse = setBicycleUserInfo(userInfo);
                baseAddRegisterUser(0,userInfo.getId(),authInfo.getOpenId(), phoneNumber, detailedUserInfo.getCountry(), detailedUserInfo.getGender(), detailedUserInfo.getNickName(), detailedUserInfo.getAvatarUrl());
                return ResultUtil.success(userInfoResponse);
            }

            UserInfoResponse info = baseAddRegisterUser(2,UuidUtil.getUuid(),authInfo.getOpenId(), phoneNumber, detailedUserInfo.getCountry(), detailedUserInfo.getGender(), detailedUserInfo.getNickName(), detailedUserInfo.getAvatarUrl());

            return ResultUtil.success(info);
        } catch (Exception e) {
            LOGGER.error(" ERROR {}", e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }

    @SystemControllerLog(funcionExplain = "进入手机号注册登录控制层")
    @GetMapping("/wx_xcx_phone")
    public AppletResult phoneRegister(PhoneRegisterRequest request, @RequestHeader("session") String session) {
        LOGGER.debug("session---> {}",session);

        UserInfoResponse info;
        try {

            Cat authInfo = getAuthInfo(session);
            LOGGER.debug(" authInfo {}", JSONUtil.toJSONString(authInfo));

            String phone = request.getPhone();

            //验证用户是否小程序上已经注册
            AppletResult userRegistered = isUserRegistered(phone);
            if (userRegistered != null) {
                return ResultUtil.success(getUserInfoByopenId(phone,request.getCityName()));
            }

            //验证用户是否在单车上注册过
            UserInfo userInfo = userInfoMapper.selectByUserPhone(phone);
            if (userInfo != null) {
                UserInfoResponse userInfoResponse = setBicycleUserInfo(userInfo);
                baseAddRegisterUser(0,userInfo.getId(),authInfo.getOpenId(), phone, null, null, null, null);
                return ResultUtil.success(userInfoResponse);
            }


             info = baseAddRegisterUser(2,UuidUtil.getUuid(),authInfo.getOpenId(), phone, null, null, null, null);
            return ResultUtil.success(info);
        } catch (Exception e) {
            LOGGER.error(" ERROR {}", e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }


    @SystemControllerLog(funcionExplain = "进入获取用户信息控制层")
    @GetMapping(value = "/wx_xcx_userinfo")
    public AppletResult getUserInfo(String id,String cityName) {
        try {
            UserInfoResponse userInfo = userInfoService.getUserInfo(id,cityName);
            return ResultUtil.success(userInfo);
        } catch (Exception e) {
            LOGGER.error(" ERROR {}", e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }

    /**
     * 添加新用户的公共方法
     *
     * @param openId
     * @param phone
     * @param country
     * @return
     */
    private UserInfoResponse baseAddRegisterUser(Integer userSource,String id, String openId, String phone, String country, Integer gender, String nickname, String picurl) {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setUserSource(userSource);
        userInfo.setAccountStatus(0);
        userInfo.setPhone(phone);
        userInfo.setNationality(country == null ? "" : country);
        userInfo.setGuesterState(gender == null ? 0 : gender);
        userInfo.setNickname(nickname == null ? "" : nickname);
        userInfo.setPicurl(picurl == null ? "" : picurl);

        WxUserInfo wxUserInfo = new WxUserInfo();
        wxUserInfo.setOpenId(openId);
        wxUserInfo.setUserName(nickname == null ? "" : nickname);
        wxUserInfo.setUserMobile(phone);
        wxUserInfo.setRegistFlag(4);
        UserInfoResponse info = userInfoService.addRegisterUser(userInfo, wxUserInfo);
        return info;
    }

    /**
     * 设置单车用户信息
     * @param userInfo
     * @return
     */
    private UserInfoResponse setBicycleUserInfo(UserInfo userInfo){
        UserInfoResponse info = new UserInfoResponse();
        info.setAdminId(userInfo.getId());
        Integer accountStatus = userInfo.getAccountStatus();

        //如果用户状态等于0 或者 2 代表没有支付 否则已经支付
        if (accountStatus == 0 || accountStatus == 2){
            info.setStatus(0);
        }else{
            info.setStatus(1);
        }

        Integer mBorrowBicycle = userInfo.getmBorrowBicycle();
        info.setBorrowBicycle(mBorrowBicycle);
        if(mBorrowBicycle.equals(1)){
            long borrowBicycleDate=(new Date().getTime()-userInfo.getmBorrowBicycleDate().getTime())/(1000);
            info.setRidingTime(borrowBicycleDate);
        }
        return info;
    }
}

